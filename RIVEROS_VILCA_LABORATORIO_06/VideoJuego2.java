import java.util.*;

public class VideoJuego2 {
    public static void main(String[] args) {
        Random rand = new Random();
        ArrayList<ArrayList<Boolean>> casillasOcupadas = new ArrayList<>();
        ArrayList<ArrayList<Soldado>> ejercito1 = new ArrayList<>();
        ArrayList<ArrayList<Soldado>> ejercito2 = new ArrayList<>();
        ArrayList<String> nombresEjercito1 = new ArrayList<>();
        ArrayList<String> nombresEjercito2 = new ArrayList<>();
        int numSoldados1 = rand.nextInt(10) + 1;
        int numSoldados2 = rand.nextInt(10) + 1;

        for (int i = 0; i < 10; i++) {
            casillasOcupadas.add(new ArrayList<Boolean>());
            ejercito1.add(new ArrayList<Soldado>());
            ejercito2.add(new ArrayList<>());
            for (int j = 0; j < 10; j++) {
                casillasOcupadas.get(i).add(false);
                ejercito1.get(i).add(null);
                ejercito2.get(i).add(null);
            }
        }
        crearEjercito(rand, numSoldados1, ejercito1, casillasOcupadas, nombresEjercito1, 1);
        crearEjercito(rand, numSoldados2, ejercito2, casillasOcupadas, nombresEjercito2, 2);

        showBoard(casillasOcupadas, ejercito1, ejercito2);

        System.out.println("Ejercito 1:");
        mostrarDatosEjercito(ejercito1, nombresEjercito1, numSoldados1);
        System.out.println("Ejército 2:");
        mostrarDatosEjercito(ejercito2, nombresEjercito2, numSoldados2);
        determinarGanador(ejercito1, ejercito2);
    }

    // Determina el ganador basándose en la vida total de ambos ejércitos
    public static void determinarGanador(ArrayList<ArrayList<Soldado>> ejercito1,
                                         ArrayList<ArrayList<Soldado>> ejercito2) {
        int totalVidaEjercito1 = calcularTotalVida(ejercito1);
        int totalVidaEjercito2 = calcularTotalVida(ejercito2);

        System.out.printf("Total Vida Ejército 1: %d\n", totalVidaEjercito1);
        System.out.printf("Total Vida Ejército 2: %d\n", totalVidaEjercito2);

        if (totalVidaEjercito1 > totalVidaEjercito2) {
            System.out.println("El Ejército 1 gana la batalla.");
        } else if (totalVidaEjercito2 > totalVidaEjercito1) {
            System.out.println("El Ejército 2 gana la batalla.");
        } else {
            System.out.println("La batalla termina en empate.");
        }
    }

    // Muestra la información del ejército, incluyendo el soldado con mayor vida y el promedio
    public static void mostrarDatosEjercito(ArrayList<ArrayList<Soldado>> ejercito, ArrayList<String> nombresEjercito, int numSoldados) {
        String mayorVida = findMaxLifeSoldier(ejercito);
        System.out.println("Soldado de Mayor Vida: " + mayorVida);

        double promedioVida = calcularPromedioVida(ejercito, numSoldados);
        System.out.println("Promedio de nivel de vida: " + promedioVida);

        System.out.println("Soldados en el orden de creación:");
        armyCreationOrder(nombresEjercito); // Mostrar los nombres en el orden de creación

        ArrayList<Soldado> soldadosFila = toUnidimensional(ejercito);
        bubbleSortLife(soldadosFila); // Ordenar soldados por vida usando bubbleSort
        System.out.println("Ranking de poder (Bubble Sort):");
        showArmyInfo(soldadosFila);

        soldadosFila = toUnidimensional(ejercito);
        insertionSortLife(soldadosFila); // Ordenar soldados por vida usando insertionSort
        System.out.println("Ranking de poder (Insertion Sort):");
        showArmyInfo(soldadosFila);
    }

    // Crea un ejército aleatorio de soldados y los agrega a la lista
    public static void crearEjercito(Random rand, int numSoldados, ArrayList<ArrayList<Soldado>> ejercito,
                                     ArrayList<ArrayList<Boolean>> casillasOcupadas,
                                     ArrayList<String> nombresEjercito, int ejercitoNum) {
        int count = 0;
        while (count < numSoldados) {
            int randColumn = rand.nextInt(10);
            int randRow = rand.nextInt(10);
            if (!casillasOcupadas.get(randRow).get(randColumn)) {
                casillasOcupadas.get(randRow).set(randColumn, true);
                String nombreSoldado = "Soldado" + count + "X" + ejercitoNum;
                nombresEjercito.add(nombreSoldado); // Agregar el nombre al ArrayList
                ejercito.get(randRow).set(randColumn, new Soldado(nombreSoldado, randRow, randColumn,
                        rand.nextInt(5) + 1));
                count++;
            }
        }
    }

    // Muestra el tablero de batalla
    public static void showBoard(ArrayList<ArrayList<Boolean>> casillasOcupadas, ArrayList<ArrayList<Soldado>> ejercito1,
                                 ArrayList<ArrayList<Soldado>> ejercito2) {
        System.out.print("\t");
        for (char i = 'A'; i < 'K'; i++) {
            System.out.print(i + "    ");
        }
        System.out.println();
        System.out.print("   ");
        for (int l = 0; l < 12; l++) {
            System.out.print("____");
        }
        System.out.println();
        for (int j = 0; j < 10; j++) {
            System.out.print((j + 1) + (j != 9 ? "  " : " "));
            for (int k = 0; k < 10; k++) {
                System.out.print("|");
                if (casillasOcupadas.get(j).get(k)) {
                    String soldadoNombre = "";
                    if (!ejercito1.get(j).isEmpty() && ejercito1.get(j).get(k) != null) {
                        soldadoNombre = "+";
                    } else if (!ejercito2.get(j).isEmpty() && ejercito2.get(j).get(k) != null) {
                        soldadoNombre = "*";
                    }
                    System.out.print(soldadoNombre);
                } else {
                    System.out.print(" ");
                }
                System.out.print("|  ");
            }
            System.out.println();
            System.out.print("   ");
            for (int l = 0; l < 12; l++) {
                System.out.print("____");
            }
            System.out.println();
        }
    }

    // Muestra los nombres de los soldados en el orden de creación
    public static void armyCreationOrder(ArrayList<String> nombresEjercito) {
        for (String nombre : nombresEjercito) {
            System.out.println(nombre);
        }
        System.out.println();
    }

    // Convierte el ejército bidimensional en un ArrayList unidimensional
    public static ArrayList<Soldado> toUnidimensional(ArrayList<ArrayList<Soldado>> soldados) {
        ArrayList<Soldado> unidimensional = new ArrayList<>();
        for (ArrayList<Soldado> fila : soldados) {
            for (Soldado soldado : fila) {
                if (soldado != null) {
                    unidimensional.add(soldado);
                }
            }
        }
        return unidimensional;
    }

    // Muestra la información de cada soldado en el ejército
    public static void showArmyInfo(ArrayList<Soldado> ejercito) {
        for (Soldado soldado : ejercito) {
            if (soldado != null) {
                System.out.print(soldado);
            }
        }
    }

    // Calcula la vida total de todos los soldados en el ejército
    public static int calcularTotalVida(ArrayList<ArrayList<Soldado>> soldados) {
        int totalVida = 0;
        for (ArrayList<Soldado> fila : soldados) {
            for (Soldado soldado : fila) {
                if (soldado != null) {
                    totalVida += soldado.getNivelVida();
                }
            }
        }
        return totalVida;
    }

    // Calcula el promedio de vida de los soldados
    public static double calcularPromedioVida(ArrayList<ArrayList<Soldado>> soldados, int numSoldados) {
        int totalVida = calcularTotalVida(soldados);
        return (double) totalVida / numSoldados;
    }

    // Encuentra el soldado con mayor vida en el ejército
    public static String findMaxLifeSoldier(ArrayList<ArrayList<Soldado>> soldados) {
        Soldado max = null;
        for (ArrayList<Soldado> fila : soldados) {
            for (Soldado soldado : fila) {
                if (soldado != null && (max == null || soldado.getNivelVida() > max.getNivelVida())) {
                    max = soldado;
                }
            }
        }
        return max.toString();
    }

    // Ordena el ejército por vida utilizando el método insertionSort
    public static void insertionSortLife(ArrayList<Soldado> ejercito) {
        for (int i = 1; i < ejercito.size(); i++) {
            Soldado key = ejercito.get(i);
            int j = i - 1;
            while (j >= 0 && ejercito.get(j).getNivelVida() > key.getNivelVida()) {
                ejercito.set(j + 1, ejercito.get(j));
                j--;
            }
            ejercito.set(j + 1, key);
        }
    }

    // Ordena el ejército por vida utilizando el método bubbleSort
    public static void bubbleSortLife(ArrayList<Soldado> ejercito) {
        for (int i = 0; i < ejercito.size() - 1; i++) {
            for (int j = 0; j < ejercito.size() - i - 1; j++) {
                if (ejercito.get(j).getNivelVida() > ejercito.get(j + 1).getNivelVida()) {
                    Soldado temp = ejercito.get(j);
                    ejercito.set(j, ejercito.get(j + 1));
                    ejercito.set(j + 1, temp);
                }
            }
        }
    }
}

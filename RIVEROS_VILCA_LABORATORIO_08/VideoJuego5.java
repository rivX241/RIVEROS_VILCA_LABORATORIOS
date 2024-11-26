import java.util.*;
public class VideoJuego5 {
    public static void main(String[] args) {
        Random rand = new Random();
        HashMap<Integer, Soldado> ejercito1 = new HashMap<>();
        HashMap<Integer, Soldado> ejercito2 = new HashMap<>();
        ArrayList<String> nombresEjercito1 = new ArrayList<>();
        ArrayList<String> nombresEjercito2 = new ArrayList<>();
        HashMap<String, Boolean> casillasOcupadas = new HashMap<>();

        int numSoldados1 = rand.nextInt(10) + 1;
        int numSoldados2 = rand.nextInt(10) + 1;

        // Crear los ejércitos
        crearEjercito(rand, numSoldados1, ejercito1, casillasOcupadas, nombresEjercito1, 1);
        crearEjercito(rand, numSoldados2, ejercito2, casillasOcupadas, nombresEjercito2, 2);

        // Mostrar el estado del tablero
        showBoard(casillasOcupadas, ejercito1, ejercito2);

        // Mostrar los detalles de los ejércitos
        System.out.println("Ejército 1:");
        mostrarDatosEjercito(ejercito1, nombresEjercito1, numSoldados1);
        System.out.println("Ejército 2:");
        mostrarDatosEjercito(ejercito2, nombresEjercito2, numSoldados2);

        // Determinar el ganador
        determinarGanador(ejercito1, ejercito2);
    }
    /*Metodo que determina al ganador de acuerdo al total de vida de los ejercitos*/
    public static void determinarGanador(Soldado[][] ejercito1, Soldado[][] ejercito2) {
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
    /*Metodo que muestra todos los datos d eun ejercitos almacenados en un arreglo bidimensional*/
    public static void mostrarDatosEjercito(HashMap<Integer,Soldado> ejercito, ArrayList<String> nombresEjercito, int numSoldados) {
        String mayorVida = findMaxLifeSoldier(ejercito);
        System.out.println("Soldado de Mayor Vida: " + mayorVida);

        double promedioVida = calcularPromedioVida(ejercito, numSoldados);
        System.out.println("Promedio de nivel de vida: " + promedioVida);

        System.out.println("Soldados en el orden de creación:");
        armyCreationOrder(nombresEjercito);

        ArrayList<Soldado> soldadosLista = toList(ejercito);
        bubbleSortLife(soldadosLista);
        System.out.println("Ranking de poder (Bubble Sort):");
        showArmyInfo(soldadosLista);

        soldadosLista = toList(ejercito);
        insertionSortLife(soldadosLista);
        System.out.println("Ranking de poder (Insertion Sort):");
        showArmyInfo(soldadosLista);
    }
    // El HashMap para almacenar las casillas ocupadas será un HashMap de (Fila, Columna) -> Boolean
    public static void crearEjercito(Random rand, int numSoldados, HashMap<String, Soldado> ejercito,
                                     HashMap<String, Boolean> casillasOcupadas, ArrayList<String> nombresEjercito,
                                     int ejercitoNum) {
        int count = 0;
        
        while (count < numSoldados) {
            int randColumn = rand.nextInt(10);
            int randRow = rand.nextInt(10);
            
            // Crear la clave única para las casillas (fila, columna)
            String key = randRow + "," + randColumn;
            
            // Verificar si la casilla está ocupada
            if (!casillasOcupadas.containsKey(key) || !casillasOcupadas.get(key)) {
                casillasOcupadas.put(key, true);
                
                // Generar nombre del soldado
                String nombreSoldado = "Soldado" + count + "X" + ejercitoNum;
                nombresEjercito.add(nombreSoldado);
                
                // Crear el soldado y agregarlo al HashMap
                ejercito.put(key, new Soldado(nombreSoldado, randRow, randColumn, rand.nextInt(5) + 1));
                count++;
            }
        }
    }
    /*Metodo que muestra el tablero con los soldados y sus vidas correspondientes*/
    public static void showBoard(boolean[][] casillasOcupadas, HashMap<Integer,Soldado> ejercito1, HashMap<Integer,Soldado> ejercito2) {
        System.out.print("\t");
        for (char i = 'A'; i < 'K'; i++) {
            System.out.print("   " + i + "   ");
        }
        System.out.println();
        System.out.print("   ");
        for (int l = 0; l < 10; l++) {
            System.out.print("_______");
        }
        System.out.println();
        for (int j = 0; j < 10; j++) {
            System.out.print((j + 1) + (j < 9 ? "  " : " "));
            for (int k = 0; k < 10; k++) {
                System.out.print("|");
                if (casillasOcupadas[j][k]) {
                    if (ejercito1[j][k] != null) {
                        System.out.printf(" +:%-2d ", ejercito1[j][k].getNivelVida());
                    } else if (ejercito2[j][k] != null) {
                        System.out.printf(" *:%-2d ", ejercito2[j][k].getNivelVida());
                    }
                } else {
                    System.out.print("      ");
                }
            }
            System.out.println("|");
            System.out.print("   ");
            for (int l = 0; l < 10; l++) {
                System.out.print("_______");
            }
            System.out.println();
        }
    }

    /*Metodo que muestra el orden de creacion de un ejercito */
    public static void armyCreationOrder(ArrayList<String> nombresEjercito) {
        for (String nombre : nombresEjercito) {
            System.out.println(nombre);
        }
        System.out.println();
    }
    /*Metodo que devuelve un arraylist de un ejercito almacenado en un arreglo bidimesional*/
    public static ArrayList<Soldado> toList(HashMap<Integer,Soldado> soldados) {
        ArrayList<Soldado> lista = new ArrayList<>();
        for (Soldado[] fila : soldados) {
            for (Soldado soldado : fila) {
                if (soldado != null) {
                    lista.add(soldado);
                }
            }
        }
        return lista;
    }
    /*Metodo que retorna los atributos de los soldados de un ejercito almacenado en arraylist*/
    public static void showArmyInfo(ArrayList<Soldado> ejercito) {
        for (Soldado soldado : ejercito) {
            System.out.print(soldado);
        }
    }
    /*Metodo que retorna el total de vida de un ejercito*/
    public static int calcularTotalVida(Soldado[][] soldados) {
        int totalVida = 0;
        for (Soldado[] fila : soldados) {
            for (Soldado soldado : fila) {
                if (soldado != null) {
                    totalVida += soldado.getNivelVida();
                }
            }
        }
        return totalVida;
    }
    /*Metodo que retorna el promedio de vida de un ejercito*/
    public static double calcularPromedioVida(Soldado[][] soldados, int numSoldados) {
        int totalVida = calcularTotalVida(soldados);
        return (double) totalVida / numSoldados;
    }
    /*Metodo que encuentra el soldado con la mayor cantidad de vida de un ejercito*/
    public static String findMaxLifeSoldier(Soldado[][] soldados) {
        Soldado max = null;
        for (Soldado[] fila : soldados) {
            for (Soldado soldado : fila) {
                if (soldado != null && (max == null || soldado.getNivelVida() > max.getNivelVida())) {
                    max = soldado;
                }
            }
        }
        return max != null ? max.toString() : "No hay soldados.";
    }
    /*Metodo que realiza bubble sort con arraylist de soldados*/
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


    /*Metodo que realiza bubble sort con arraylist de soldados*/
    public static void bubbleSortLife(ArrayList<Soldado> ejercito) {
        for (int i = 0; i < ejercito.size() - 1; i++) {
            for (int j = 0; j < ejercito.size() - i - 1; j++) {
                if (ejercito.get(j).getNivelVida() < ejercito.get(j + 1).getNivelVida()) {
                    Soldado temp = ejercito.get(j);
                    ejercito.set(j, ejercito.get(j + 1));
                    ejercito.set(j + 1, temp);
                }
            }
        }
    }
}

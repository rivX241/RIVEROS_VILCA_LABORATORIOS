import java.util.*;

public class VideoJuego5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        do {
            Random rand = new Random();
            HashMap<String, Soldado> ejercito1 = new HashMap<>();
            HashMap<String, Soldado> ejercito2 = new HashMap<>();
            String[] nombresEjercito1 = new String[10];
            String[] nombresEjercito2 = new String[10];
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

            // Preguntar si se quiere jugar otra partida
            System.out.print("¿Quieres jugar otra partida? (si/no): ");
        } while (scanner.nextLine().trim().equalsIgnoreCase("si"));

        System.out.println("Gracias por jugar. ¡Hasta luego!");
        scanner.close();
    }

    // Compara la vida total de ambos ejércitos para determinar al ganador
    // o si el enfrentamiento termina en empate.
    public static void determinarGanador(HashMap<String, Soldado> ejercito1,
                                         HashMap<String, Soldado> ejercito2) {
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

    // Muestra información del ejército, como el soldado con mayor vida,
    // el promedio de vida, el orden de creación y rankings basados en la vida.
    public static void mostrarDatosEjercito(HashMap<String, Soldado> ejercito,
                                            String[] nombresEjercito, int numSoldados) {
        String mayorVida = findMaxLifeSoldier(ejercito);
        System.out.println("Soldado de Mayor Vida: " + mayorVida);

        double promedioVida = calcularPromedioVida(ejercito, numSoldados);
        System.out.println("Promedio de nivel de vida: " + promedioVida);

        System.out.println("Soldados en el orden de creación:");
        armyCreationOrder(nombresEjercito, numSoldados);

        Soldado[] soldadosLista = toArray(ejercito);
        bubbleSortLife(soldadosLista);
        System.out.println("Ranking de poder (Bubble Sort):");
        showArmyInfo(soldadosLista);

        soldadosLista = toArray(ejercito);
        insertionSortLife(soldadosLista);
        System.out.println("Ranking de poder (Insertion Sort):");
        showArmyInfo(soldadosLista);
    }

    // Genera un ejército de soldados en posiciones aleatorias del tablero,
    // asegurándose de que las casillas no se repitan.
    public static void crearEjercito(Random rand, int numSoldados, HashMap<String, Soldado> ejercito,
                                     HashMap<String, Boolean> casillasOcupadas, String[] nombresEjercito,
                                     int ejercitoNum) {
        int count = 0;

        while (count < numSoldados) {
            int randColumn = rand.nextInt(10);
            int randRow = rand.nextInt(10);

            String key = randRow + "," + randColumn;

            if (!casillasOcupadas.containsKey(key) || !casillasOcupadas.get(key)) {
                casillasOcupadas.put(key, true);

                String nombreSoldado = "Soldado" + count + "X" + ejercitoNum;
                nombresEjercito[count] = nombreSoldado;

                ejercito.put(key, new Soldado(nombreSoldado, randRow, randColumn,
                        rand.nextInt(5) + 1));
                count++;
            }
        }
    }

    // Imprime el estado actual del tablero, mostrando las posiciones ocupadas
    // por los soldados de ambos ejércitos y sus niveles de vida.
    public static void showBoard(HashMap<String, Boolean> casillasOcupadas, HashMap<String,
            Soldado> ejercito1, HashMap<String, Soldado> ejercito2) {
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
                String key = j + "," + k;
                if (casillasOcupadas.getOrDefault(key, false)) {
                    if (ejercito1.containsKey(key)) {
                        System.out.printf(" +:%-2d ", ejercito1.get(key).getNivelVida());
                    } else if (ejercito2.containsKey(key)) {
                        System.out.printf(" *:%-2d ", ejercito2.get(key).getNivelVida());
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

    // Imprime los nombres de los soldados en el orden en que fueron creados.
    public static void armyCreationOrder(String[] nombresEjercito, int numSoldados) {
        for (int i = 0; i < numSoldados; i++) {
            System.out.println(nombresEjercito[i]);
        }
        System.out.println();
    }

    // Convierte un HashMap de soldados a un arreglo para facilitar el procesamiento.
    public static Soldado[] toArray(HashMap<String, Soldado> ejercito) {
        return ejercito.values().toArray(new Soldado[0]);
    }

    // Imprime información de cada soldado en el ejército, como su posición y nivel de vida.
    public static void showArmyInfo(Soldado[] ejercito) {
        for (Soldado soldado : ejercito) {
            System.out.print(soldado);
        }
    }

    // Calcula la suma total de los niveles de vida de todos los soldados del ejército.
    public static int calcularTotalVida(HashMap<String, Soldado> ejercito) {
        int totalVida = 0;
        for (Soldado soldado : ejercito.values()) {
            totalVida += soldado.getNivelVida();
        }
        return totalVida;
    }

    // Calcula el promedio del nivel de vida de los soldados en el ejército.
    public static double calcularPromedioVida(HashMap<String, Soldado> ejercito,
                                              int numSoldados) {
        int totalVida = calcularTotalVida(ejercito);
        return (double) totalVida / numSoldados;
    }

    // Encuentra al soldado con el nivel de vida más alto en el ejército.
    public static String findMaxLifeSoldier(HashMap<String, Soldado> ejercito) {
        Soldado max = null;
        for (Soldado soldado : ejercito.values()) {
            if (max == null || soldado.getNivelVida() > max.getNivelVida()) {
                max = soldado;
            }
        }
        return max != null ? max.toString() : "No hay soldados.";
    }

    // Ordena el arreglo de soldados por nivel de vida en orden ascendente
    // utilizando el algoritmo de inserción (Insertion Sort).
    public static void insertionSortLife(Soldado[] ejercito) {
        for (int i = 1; i < ejercito.length; i++) {
            Soldado key = ejercito[i];
            int j = i - 1;
            while (j >= 0 && ejercito[j].getNivelVida() > key.getNivelVida()) {
                ejercito[j + 1] = ejercito[j];
                j--;
            }
            ejercito[j + 1] = key;
        }
    }

    // Ordena el arreglo de soldados por nivel de vida en orden descendente
    // utilizando el algoritmo de burbuja (Bubble Sort).
    public static void bubbleSortLife(Soldado[] ejercito) {
        for (int i = 0; i < ejercito.length - 1; i++) {
            for (int j = 0; j < ejercito.length - i - 1; j++) {
                if (ejercito[j].getNivelVida() < ejercito[j + 1].getNivelVida()) {
                    Soldado temp = ejercito[j];
                    ejercito[j] = ejercito[j + 1];
                    ejercito[j + 1] = temp;
                }
            }
        }
    }
}


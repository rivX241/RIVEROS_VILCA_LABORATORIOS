import java.util.*;

public class VideoJuego2 {
    public static void main(String[] args) {
        Random rand = new Random();
        boolean[][] casillasOcupadas = new boolean[10][10];
        Soldado[][] soldados = new Soldado[10][10];
        int numSoldados = rand.nextInt(10) + 1;
        String[] ordenSoldados = new String[numSoldados];
        int count=0;
        do{
            int randColumn = rand.nextInt(10);
            int randRow = rand.nextInt(10);
            while(isFull(randRow,randColumn,casillasOcupadas)){
                randColumn = rand.nextInt(10);
                randRow = rand.nextInt(10);
            }
            casillasOcupadas[randRow][randColumn] = true;
            soldados[randRow][randColumn] = new Soldado("Soldado"+count,randRow,randColumn,rand.nextInt(5)+1);
            ordenSoldados[count] = soldados[randRow][randColumn].getNombre();
            count++;
        }while(count<numSoldados);
        showBoard(casillasOcupadas);
        String mayorVida = findMaxLifeSoldier(soldados);
        System.out.print("Soldado de Mayor Vida: "+mayorVida);
        double promedioVida = calcularPromedioVida(soldados, numSoldados);
        int totalVida = calcularTotalVida(soldados);
        System.out.println("Promedio de nivel de vida: "+promedioVida);
        System.out.println("Total de vida del ejercito: "+totalVida);
        System.out.println("Soldados en el orden de creación");
        armyCreation(ordenSoldados,soldados);
        Soldado[] soldadosFila = toUnidimensional(soldados);
        bubbleSortLife(soldadosFila);
        System.out.println("Ranking de poder (Bubble Sort):");
        showArmyInfo(soldadosFila);
        insertionSortLife(soldadosFila);
        System.out.println("Ranking de poder (Insertion Sort):");
        showArmyInfo(soldadosFila);
    }
    public static boolean isFull(int row, int column, boolean[][] casillasOcupadas) {
        return casillasOcupadas[row][column];
    }
    public static void showBoard(boolean[][] casillasOcupadas) {
        System.out.print("\t");
        for(int i = 0;i<10;i++){
            System.out.print((i+1)+"    ");
        }
        System.out.println();
        System.out.print("   ");
        for(int l = 0;l<12;l++){
            System.out.print("____");
        }
        System.out.println();
        for(int j = 0;j<10;j++){
            if(j!=9)
                System.out.print((j+1)+"  ");
            else
                System.out.print((j+1)+" ");
            for(int k = 0;k<10;k++){
                System.out.print("|");
                if(isFull(j,k,casillasOcupadas)){
                    System.out.print("+");
                }else{
                    System.out.print(" ");
                }
                System.out.print("|  ");
            }
            System.out.println();
            System.out.print("   ");
            for(int l = 0;l<12;l++){
                System.out.print("____");
            }
            System.out.println();
        }
    }
    public static void armyCreation(String[] orden, Soldado[][] ejercito) {
        for (String nombre : orden) {
            Soldado soldado = findSoldier(nombre, ejercito);
            if (soldado != null) {
                System.out.print(soldado.toString());
            }
        }
    }

    public static Soldado findSoldier(String nombre, Soldado[][] ejercito) {
        for (Soldado[] fila : ejercito) {
            for (Soldado soldado : fila) {
                if (soldado != null && nombre.equals(soldado.getNombre())) {
                    return soldado;
                }
            }
        }
        return null;
    }
    public static void bubbleSortLife(Soldado[] ejercito) {
        for (int i = 0; i < ejercito.length - 1; i++) {
            for (int j = 0; j < ejercito.length - 1 - i; j++) {
                if (ejercito[j] != null && ejercito[j + 1] != null &&
                        ejercito[j].getNivelVida() < ejercito[j + 1].getNivelVida()) {
                    Soldado temp = ejercito[j];
                    ejercito[j] = ejercito[j + 1];
                    ejercito[j + 1] = temp;
                }
            }
        }
    }

    public static Soldado[] toUnidimensional(Soldado[][] soldados){
        Soldado[] unidimensional = new Soldado[soldados.length*soldados[0].length];
        int index = 0;
        for (Soldado[] fila : soldados) {
            for (Soldado soldado : fila) {
                if (soldado != null) {
                    unidimensional[index] = soldado;
                    index++;
                }
            }
        }
        return unidimensional;
    }
    public static void showArmyInfo(Soldado[] ejercito) {
        for (Soldado soldado : ejercito) {
            if (soldado != null) {
                System.out.print(soldado);
            }
        }
    }
    public static int calcularTotalVida(Soldado[][] soldados) {
        int totalVida = 0;
        for(Soldado[] fila : soldados){
            for(Soldado soldado : fila){
                if(soldado!=null){
                    totalVida += soldado.getNivelVida();
                }
            }
        }
        return totalVida;
    }
    public static double calcularPromedioVida(Soldado[][] soldados, int numSoldados) {
        int totalVida = calcularTotalVida(soldados);
        return (double) totalVida / numSoldados;
    }
    public static String findMaxLifeSoldier(Soldado[][] soldados) {
        Soldado max = null;
        for (Soldado[] fila : soldados) {
            for (Soldado soldado : fila) {
                if (soldado != null && (max == null || soldado.getNivelVida() > max.getNivelVida())) {
                    max = soldado;
                }
            }
        }
        return max.toString();
    }
    public static void insertionSortLife(Soldado[] ejercito) {
        for (int i = 1; i < ejercito.length; i++) {
            Soldado key = ejercito[i];
            int j = i - 1;

            while (j >= 0 && ejercito[j] != null && key != null && ejercito[j].getNivelVida() < key.getNivelVida()) {
                ejercito[j + 1] = ejercito[j];
                j = j - 1;
            }
            ejercito[j + 1] = key;
        }
    }
}

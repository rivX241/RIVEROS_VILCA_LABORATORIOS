import java.util.*;

public class Main {
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
        Soldado.mostrarEjercito(casillasOcupadas,soldados);
        System.out.println("WAZA");
        Soldado.creacionEjercito(ordenSoldados,soldados);
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
}

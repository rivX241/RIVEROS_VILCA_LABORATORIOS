import java.util.*;
public class Actividad05 {
    public static void main(String[] args){
        Random rand = new Random();
        int numSoldEjercito1 = rand.nextInt(5)+1,numSoldEjercito2 = rand.nextInt(5)+1;
        String[] ejercito1 = new String[numSoldEjercito1],ejercito2 = new String[numSoldEjercito2];
        inicializarEjercito(ejercito1);
        inicializarEjercito(ejercito2);
        System.out.println("Ejercito nro 1:");
        mostrarEjercito(ejercito1);
        System.out.println("Ejercito nro 2:");
        mostrarEjercito(ejercito2);
        System.out.println(mostrarGanador(ejercito1,ejercito2));
    }
    public static void inicializarEjercito(String[] ejercito){
        for(int i = 0;i < ejercito.length; i++){
            ejercito[i] = "Soldado" + i;
        }
    }
    public static void mostrarEjercito(String[] ejercito){
        for(String soldado : ejercito){
            System.out.print(soldado+"\n");
        }
    }
    public static String mostrarGanador(String[] ejercito1,String[] ejercito2){
        if(ejercito1.length==ejercito2.length){
            return "Es un empate.";
        }else{
            return ejercito1.length>ejercito2.length?"El ganador es el ejercito 1":"El ganador es el ejercito 2";
        }
    }
}

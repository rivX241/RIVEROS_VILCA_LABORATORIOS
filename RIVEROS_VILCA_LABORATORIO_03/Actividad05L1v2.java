import java.util.*;
public class Actividad05L1v2 {
    public static void main(String[] args){
        Random rand = new Random();
        int numSoldEjercito1 = rand.nextInt(5)+1,numSoldEjercito2 = rand.nextInt(5)+1;
        Soldado[] ejercito1 = new Soldado[numSoldEjercito1],ejercito2 = new Soldado[numSoldEjercito2];
        inicializarEjercito(ejercito1);
        inicializarEjercito(ejercito2);
        System.out.println("Ejercito nro 1:");
        mostrarEjercito(ejercito1);
        System.out.println("Ejercito nro 2:");
        mostrarEjercito(ejercito2);
        System.out.println(mostrarGanador(ejercito1,ejercito2));
    }
    public static void inicializarEjercito(Soldado[] ejercito){
        for(int i = 0;i < ejercito.length; i++){
            ejercito[i] = new Soldado("Soldado" + i);
        }
    }
    public static void mostrarEjercito(Soldado[] ejercito){
        for(Soldado soldado : ejercito){
            System.out.print(soldado.getNombre()+"\n");
        }
    }
    public static String mostrarGanador(Soldado[] ejercito1,Soldado[] ejercito2){
        if(ejercito1.length==ejercito2.length){
            return "Es un empate.";
        }else{
            return ejercito1.length>ejercito2.length?"El ganador es el ejercito 1":"El ganador es el ejercito 2";
        }
    }
}

import java.util.*;
public class Actividad04L1v2 {
    public static void main(String[] args){
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        Soldado[] nameSoldiers = new Soldado[5];
        for(int i = 0;i < nameSoldiers.length;i++){
            nameSoldiers[i] = new Soldado();
            System.out.printf("Ingrese el nombre del soldado nro %d:",i+1);
            nameSoldiers[i].setNombre(sc.next());
            nameSoldiers[i].setVida(rand.nextInt(5)+1);
        }
        System.out.println("Informacion de los soldados: ");
        for (Soldado nameSoldier : nameSoldiers) {
            System.out.printf(nameSoldier.toString() + "\n");
        }
    }
}

import java.util.*;
public class Actividad04 {
    public static void main(String[] args){
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        String[] nameSoldiers = new String[5];
        int[] health = new int[5];
        for(int i = 0;i < nameSoldiers.length;i++){
            System.out.printf("Ingrese el nombre del soldado nro %d:",i+1);
            nameSoldiers[i] = sc.nextLine();
            health[i] = rand.nextInt(5)+1;
        }
        System.out.println("Informacion de los soldados: ");
        for(int j = 0;j < nameSoldiers.length;j++){
            System.out.printf("\nNombre del soldado %d: %s \tSalud:%d"
                    ,j+1,nameSoldiers[j],health[j]);
        }
    }
}

import java.util.*;
public class Actividad03 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] nameSoldiers = new String[5];
        for(int i = 0;i < nameSoldiers.length;i++){
            System.out.printf("Ingrese el nombre del soldado nro %d:",i+1);
            nameSoldiers[i] = sc.nextLine();
        }
        System.out.println("Informacion de los soldados: ");
        for(int j = 0;j < nameSoldiers.length;j++){
            System.out.printf("\nNombre del soldado %d: %s ",j+1,nameSoldiers[j]);
        }
    }
}

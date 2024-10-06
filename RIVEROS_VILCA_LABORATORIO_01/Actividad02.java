import java.util.*;
public class Actividad02 {
    public static void main(String[] args){
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        String soldierName1,soldierName2,soldierName3,
                soldierName4,soldierName5;
        System.out.println("Ingrese el nombre del primer soldado.");
        soldierName1 = sc.nextLine();
        System.out.println("Ingrese el nombre del segundo soldado.");
        soldierName2 = sc.nextLine();
        System.out.println("Ingrese el nombre del tercer soldado.");
        soldierName3 = sc.nextLine();
        System.out.println("Ingrese el nombre del cuarto soldado.");
        soldierName4 = sc.nextLine();
        System.out.println("Ingrese el nombre del quinto soldado.");
        soldierName5 = sc.nextLine();
        int health1 = rand.nextInt(5)+1;
        int health2 = rand.nextInt(5)+1;
        int health3 = rand.nextInt(5)+1;
        int health4 = rand.nextInt(5)+1;
        int health5 = rand.nextInt(5)+1;
        String message = "Nombre del soldado nro 1: "+soldierName1+" salud: "+health1+
                "\nNombre del soldado nro 2: "+soldierName2+" salud: "+health2+
                "\nNombre del soldado nro 3: "+soldierName3+" salud: "+health3+
                "\nNombre del soldado nro 4: "+soldierName4+" salud: "+health4+
                "\nNombre del soldado nro 5: "+soldierName5+" salud: "+health5;
        System.out.println(message);
    }
}

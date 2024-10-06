import java.util.Scanner;

public class Actividad01 {
    public static void main(String[] args){
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
        String message = "Nombre del soldado nro 1: "+soldierName1+
                            "\nNombre del soldado nro 2: "+soldierName2+
                            "\nNombre del soldado nro 3: "+soldierName3+
                            "\nNombre del soldado nro 4: "+soldierName4+
                            "\nNombre del soldado nro 5: "+soldierName5;
        System.out.println(message);
    }
}

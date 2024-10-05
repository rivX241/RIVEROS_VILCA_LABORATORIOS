import java.util.*;
public class DemoBatalla {
    public static void main(String [] args){
        Nave [] misNaves = new Nave[5];
        Scanner sc = new Scanner(System.in);
        String nomb, col;
        int fil, punt;
        Random rand = new Random();
        boolean est;
        for (int i = 0; i < misNaves.length; i++) {
            System.out.println("Nave " + (i+1));
            System.out.print("Nombre: ");
            nomb = sc.next();
            System.out.print("Fila: ");
            fil = sc    .nextInt();
            System.out.print("Columna: ");
            col = sc.next();
            System.out.print("Estado: ");
            est = sc.nextBoolean();
            System.out.print("Puntos: ");
            punt = sc.nextInt();
            misNaves[i] = new Nave(); //Se crea un objeto Nave y se asigna su referencia a misNaves
            misNaves[i].setNombre(nomb);
            misNaves[i].setFila(fil);
            misNaves[i].setColumna(col);
            misNaves[i].setEstado(est);
            misNaves[i].setPuntos(punt);
        }
        System.out.println("\nNaves creadas:");
        mostrarNaves(misNaves);
        mostrarPorNombre(misNaves);
        mostrarPorPuntos(misNaves);
        System.out.println("Flota desordenada");
        Nave[] flota2 = nuevaFlota(misNaves);
        mostrarNaves(flota2);
        System.out.println("\nNave con mayor número de puntos: " + mostrarMayorPuntos(misNaves));
    }
    public static void mostrarNaves(Nave [] flota){
        for (Nave nave : flota) {
            System.out.println(nave.toString());
        }
    }
    public static void mostrarPorNombre(Nave [] flota){
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el nombre de la nave: ");
                    String name = sc.nextLine();
        for(Nave naveBuscada : flota){
            if(naveBuscada.getNombre().equals(name)){
                System.out.println(naveBuscada);
            }
        }
    }
    public static void mostrarPorPuntos(Nave [] flota){
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el número de puntos para buscar las naves inferiores a este: ");
        int puntos = sc.nextInt();
        for(Nave naveFiltrada : flota){
            if(naveFiltrada.getPuntos()<=puntos){
                System.out.println(naveFiltrada);
            }
        }
    }
    public static String mostrarMayorPuntos(Nave [] flota){
        int index = 0;
        for(int i=1;i< flota.length;i++){
            if(flota[i].getPuntos()>flota[i-1].getPuntos()&&flota[i].getEstado()){
                index = i;
            }
        }
        return flota[index].toString();
    }
    public static Nave[] nuevaFlota(Nave[] flota){
        Nave[] nuevaFlota = new Nave[flota.length];
        Random rand = new Random();
        int randomIndex;
        for(int i = 0;i< flota.length;i++){
            randomIndex = rand.nextInt(flota.length);
            nuevaFlota[i] = flota[randomIndex];
        }
        return nuevaFlota;
    }
}

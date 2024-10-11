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
    //Método para buscar la primera nave con un nombre que se pidió por teclado
    public static int busquedaLinealNombre(Nave[] flota, String s){
        for(int i=0;i<=flota.length;i++){
          if(flota[i].getNombre().equals(s)){
            return i;
          }
        }
        return -1;
    }
    //Método que ordena por número de puntos de menor a mayor
    public static void ordenarPorPuntosBurbuja(Nave[] flota){
        for(int i=1;i<flota.length;i++){
            for(int j=0;j<flota.length-i;j++){
                if(flota[j].getPuntos()<flota[j+1].getPuntos()){
                    Nave temp = flota[j];
                    flota[j] = flota[j+1];
                    flota[j+1] = temp;
                }
            }
        }
    }
    //Método que ordena por nombre de A a Z
    public static void ordenarPorNombreBurbuja(Nave[] flota){
        for(int i=1;i<flota.length;i++){
            for(int j=0;j<flota.length-i;j++){
                if(flota[j].getNombre().compareTo(flota[j+1].getNombre())>0){
                    Nave temp = flota[j];
                    flota[j] = flota[j+1];
                    flota[j+1] = temp;
                }
            }
        }
    }
    //Método para buscar la primera nave con un nombre que se pidió por teclado
    public static int busquedaBinariaNombre(Nave[] flota, String s){
        int alta=flota.length-1,baja=0,media;
        while(baja<=alta){
            media = (alta+baja)/2;
            if(flota[media].getNombre().equals(s)){
                return media;
            }else if(flota[media].getNombre().compareTo(s)>0){
                alta = media-1;
            }else{
                baja = media+1;
            }
        }
        return -1;
    }
    //Método que ordena por número de puntos de menor a mayor
    public static void ordenarPorPuntosSeleccion(Nave[] flota){
        for(int i=0;i<flota.length;i++){
            int min = i;
            for(int j=i+1;j<flota.length;j++){
                if(flota[j].getPuntos()<flota[min].getPuntos()){
                    min = j;
                }
            }
            if(i != min){
                Nave temp = flota[i];
                flota[i] = flota[min];
                flota[min] = temp;
            }
        }
    }
    //Método que ordena por nombre de A a Z
    public static void ordenarPorNombreSeleccion(Nave[] flota){
        for(int i=0;i<flota.length;i++){
            int min = i;
            for(int j=i+1;j<flota.length;j++){
                if(flota[j].getNombre().compareTo(flota[min].getNombre())<0){
                    min = j;
                }
            }
            if(i != min){
                Nave temp = flota[i];
                flota[i] = flota[min];
                flota[min] = temp;
            }
        }
    }
//Método que muestra las naves ordenadas por número de puntos de mayor a menor
    public static void ordenarPorPuntosInsercion(Nave[] flota){
        for(int i=1;i<flota.length;i++){
            Nave actual = flota[i];
            int j=i-1;
            while(j>=0 && flota[i].getPuntos()>actual.getPuntos()){
                flota[j+1] = flota[j];
                j++;
            }
            flota[j+1] = actual;
        }
    }
//Método que muestra las naves ordenadas por nombre de Z a A
    public static void ordenarPorNombreInsercion(Nave[] flota){
        for(int i=1;i<flota.length;i++){
            Nave actual = flota[i];
            int j=i-1;
            while(j>=0 && flota[j].getNombre().compareTo(actual.getNombre())>0){
                flota[j+1] = flota[j];
                j++;
            }
            flota[j+1] = actual;
        }
    }
}

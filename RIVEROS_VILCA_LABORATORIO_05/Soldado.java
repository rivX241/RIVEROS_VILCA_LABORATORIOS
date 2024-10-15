public class Soldado {
    private String nombre;
    private int fila;
    private int columna;
    private int nivelVida;
    // Metodos mutadores
    public Soldado(String nombre, int fila, int columna,int nivelVida) {
        this.nombre = nombre;
        this.fila = fila;
        this.columna = columna;
        this.nivelVida = nivelVida;
    }
    public void setNombre(String n){
        nombre = n;
    }
    public void setFila(int f){
        fila = f;
    }
    public void setColumna(int c){
        columna = c;
    }

    public void setNivelVida(int p){
        nivelVida = p;
    }
    // Metodos accesores
    public String getNombre(){
        return nombre;
    }
    public int getFila(){
        return fila;
    }
    public int getColumna(){
        return columna;
    }

    public int getNivelVida(){
        return nivelVida;
    }
    @Override
    public String toString(){
        return "[Nombre: "+nombre+"\tFila: "+(fila+1)+"\tColumna: "+(columna+1)+"\tnivel de Vida: "+ nivelVida
                +"]"+"\n";
    }
    public static void creacionEjercito(String[] orden,Soldado[][] ejercito){
        for (int i = 0; i < orden.length; i++) {
            for (int j = 0; j < orden[i].length(); j++) {
                if(ejercito[i][j]!=null&&orden[i].equals(ejercito[i][j].getNombre())){
                    System.out.print(ejercito[i][j].toString());
                }
            }
        }
    }
    public static void mostrarEjercito(boolean[][] posiciones,Soldado[][] ejercito){
        for(int i = 0; i < posiciones.length; i++){
            for(int j = 0; j < posiciones.length; j++){
                if(posiciones[i][j]){
                    System.out.print(ejercito[i][j].toString());
                }
            }
        }
    }
}

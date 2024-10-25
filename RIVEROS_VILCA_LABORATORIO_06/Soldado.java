public class Soldado {
    private String nombre;
    private int fila;
    private int columna;
    private int nivelVida;
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
    public void setNivelVida(int p) {
        nivelVida = p;
    }
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
        return "[Nombre: "+nombre+"\tFila: "+(fila+1)+"\tColumna: "+(columna+1)+ "\tnivel de Vida: "+ nivelVida +"]"+"\n";
    }
}
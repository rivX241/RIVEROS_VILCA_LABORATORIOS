public class Soldado {
    private String nombre;
    private int vida=0;
    public Soldado(String nombre) {
        this.nombre = nombre;
    }
    public Soldado(){
        this.nombre = "";
        this.vida = 0;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getVida() {
        return vida;
    }
    public void setVida(int vida) {
        this.vida = vida;
    }
    @Override
    public String toString() {
        return "Soldado{" + "nombre=" + nombre + ", vida=" + vida + '}';
    }
}

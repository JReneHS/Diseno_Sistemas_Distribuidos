/**
 * Proyecto 2
 * Mexicano Ixtepan Alejandro
 * 4CM13
 * Coordenada.java
 */

public class Coordenada{
    private int x, y;
    
    public Coordenada(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Método setter de x 
    public void setAbcisa(int x){
        this.x = x;
    }

    // Método setter de y 
    public void setOrdenada(int y){
        this.y = y;
    }

    //Metodo getter de x
    public int abcisa( ) {
        return x;
    }

    //Metodo getter de y
    public int ordenada( ) {
        return y;
    }

    //Sobreescritura del método de la superclase objeto para imprimir con System.out.println( )
    @Override
    public String toString( ) {
        return "[" + x + "," + y + "]";
    }
}
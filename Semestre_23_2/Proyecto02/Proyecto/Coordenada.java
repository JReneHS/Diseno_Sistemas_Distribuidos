package Proyecto;

/**
 * Proyecto 2
 * Hernandez Sanchez Juan Rene
 * 4CM13
 * Coordenada.java
 */

public class Coordenada {

    private int x, y;

    public Coordenada(int x, int y) {

        this.x = x;

        this.y = y;

    }

    // Metodo getter de x

    public int abcisa() {
        return x;
    }

    // Metodo getter de y

    public int ordenada() {
        return y;
    }

    // Sobreescritura del método de la superclase objeto para imprimir con
    // System.out.println( )

    @Override

    public String toString() {

        return "[" + x + "," + y + "]";

    }

}
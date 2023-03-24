//package Semestre_23_2.Clase11;

import java.text.DecimalFormat;

public class Coordenada {

    private double x;
    private double y;
    private double magnitud;

    public Coordenada(double x, double y) {

        this.x = x;
        this.y = y;
        this.magnitud = calcularMagnitud();
    }

    // Calcula Magnitud = \sqrt(x^2 + y^2)

    private double calcularMagnitud() {
        return Math.sqrt((this.x * this.x) + (this.y * this.y));
    }

    // Metodo getter de x

    public double abcisa() {
        return x;
    }

    // Metodo getter de y

    public double ordenada() {
        return y;
    }

    // Metodo getter de Magnitud

    public double magnitud() {
        return magnitud;
    }

    // Sobreescritura del método de la superclase objeto para imprimir con
    // System.out.println( )

    @Override

    public String toString() {

        DecimalFormat decF = new DecimalFormat("0.00");

        return "[" + decF.format(x) + "," + decF.format(y) + "] - Mag: " + decF.format(magnitud);

    }

}
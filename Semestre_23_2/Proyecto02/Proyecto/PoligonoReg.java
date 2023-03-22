package Proyecto;

/**
 * Proyecto 2
 * Hernandez Sanchez Juan Rene
 * 4CM13
 * PoligonoReg.java
 */

public class PoligonoReg {

    private int nvertices;
    private double angulo;
    private Coordenada[] vertices;

    // Constructor de la clase
    public PoligonoReg(int nvertices) {
        this.nvertices = nvertices;
        this.angulo = (double) 2.0 / nvertices * Math.PI;

        /*
         * Inicializa el arreglo de las coordenadas
         * y asingna las coodenadas en pixeles del poligono
         */

        this.vertices = new Coordenada[nvertices];

        // Genera los n vertices
        for (int i = 0, x, y; i < nvertices; i++) {
            x = (int) (130 * (Math.cos((double) ((2.0d * Math.PI * i) + angulo) / nvertices)));
            y = (int) (130 * (Math.sin((double) ((2.0d * Math.PI * i) + angulo) / nvertices)));
            this.vertices[i] = new Coordenada(x + 130, y + 130);
        }
    }

    public Coordenada[] obtenerVertices() {
        return this.vertices;
    }

    public double obtenerArea() {
        return (double) this.nvertices * Math.cos(this.angulo / 2.0d) * Math.sin(this.angulo / 2.0d);
    }
}

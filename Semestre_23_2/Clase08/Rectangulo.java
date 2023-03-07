//package Semestre_23_2.Clase08;

public class Rectangulo {

    private Coordenada superiorIzq, inferiorDer;

    public Rectangulo() {

        superiorIzq = new Coordenada(0, 0);

        inferiorDer = new Coordenada(0, 0);

    }

    public Rectangulo(double xSupIzq, double ySupIzq, double xInfDer, double yInfDer) {

        superiorIzq = new Coordenada(xSupIzq, ySupIzq);

        inferiorDer = new Coordenada(xInfDer, yInfDer);

    }

    // Metodo getter de la coordenada superior izquierda

    public Rectangulo(Coordenada c1, Coordenada c2) {
        this.superiorIzq = c1;
        this.inferiorDer = c2;
    }

    public Coordenada superiorIzquierda() {
        return superiorIzq;
    }

    // Metodo getter de la coordenada inferior derecha

    public Coordenada inferiorDerecha() {
        return inferiorDer;
    }

    // Sobreescritura del método de la superclase objeto para imprimir con
    // System.out.println( )

    @Override

    public String toString() {

        return "Esquina superior izquierda: " + superiorIzq + "\tEsquina superior derecha:" + inferiorDer + "\n";

    }

}
//package Semestre_23_2.Clase10;

public class Rectangulo extends Figura {

    private Coordenada superiorIzq, inferiorDer;

    public Rectangulo() {

        superiorIzq = new Coordenada(0, 0);

        inferiorDer = new Coordenada(0, 0);

        this.numeroLados = 4;

    }

    public Rectangulo(double xSupIzq, double ySupIzq, double xInfDer, double yInfDer) {

        superiorIzq = new Coordenada(xSupIzq, ySupIzq);

        inferiorDer = new Coordenada(xInfDer, yInfDer);

        this.numeroLados = 4;

    }

    // Metodo getter de la coordenada superior izquierda

    public Rectangulo(Coordenada c1, Coordenada c2) {
        this.superiorIzq = c1;
        this.inferiorDer = c2;

        this.numeroLados = 4;
    }

    public Coordenada superiorIzquierda() {
        return superiorIzq;
    }

    // Metodo getter de la coordenada inferior derecha

    public Coordenada inferiorDerecha() {
        return inferiorDer;
    }
    
    @Override
    public double Area() {
        double ancho, alto;
        alto = this.superiorIzquierda().ordenada() - this.inferiorDerecha().ordenada();
        ancho = this.inferiorDerecha().abcisa() - this.superiorIzquierda().abcisa();
        return alto * ancho;
    }
    
    // Sobreescritura del método de la superclase objeto para imprimir con
    // System.out.println( )
    @Override

    public String toString() {
        String supIzq = "Esquina superior izquierda: " + superiorIzq;
        String infDer = "\tEsquina inferior derecha:" + inferiorDer;
        String area= "\nEl área del rectángulo es = " + this.Area() + "\n";
        String numLados = "Numero de lados del Rectángulo = " + this.numeroLados  + "\n";
        return supIzq+ infDer + area + numLados;

    }
}
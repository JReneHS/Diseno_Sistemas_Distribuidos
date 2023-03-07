//package Semestre_23_2.Clase08;

public class PruebaRectangulo {

    public static void main(String[] args) {

        Rectangulo rect1 = new Rectangulo(2, 3, 5, 1);
        
        Coordenada c1 = new Coordenada(2, 3);
        Coordenada c2 = new Coordenada(5, 1);

        Rectangulo rect2 = new Rectangulo(c1, c2);

        ImprimirDatos(rect1);
        ImprimirDatos(rect2);

    }

    private static void ImprimirDatos(Rectangulo rect) {
        double ancho, alto;

        System.out.println("Calculando el área de un rectángulo dadas sus coordenadas en un plano cartesiano:");

        System.out.println(rect);

        alto = rect.superiorIzquierda().ordenada() - rect.inferiorDerecha().ordenada();

        ancho = rect.inferiorDerecha().abcisa() - rect.superiorIzquierda().abcisa();

        System.out.println("El área del rectángulo es = " + ancho * alto + "\n");
    }

}
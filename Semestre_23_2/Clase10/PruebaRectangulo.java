//package Semestre_23_2.Clase08;

public class PruebaRectangulo {

    public static void main(String[] args) {

        Rectangulo rect1 = new Rectangulo(5, 6, 2, 11);
        
        Coordenada c1 = new Coordenada(5, 6);
        Coordenada c2 = new Coordenada(2, 11);

        Rectangulo rect2 = new Rectangulo(c1, c2);

        ImprimirDatos(rect1);
        ImprimirDatos(rect2);

    }

    public static void ImprimirDatos(Rectangulo rect) {
        System.out.println("Calculando el área de un rectángulo dadas sus coordenadas en un plano cartesiano:");
        System.out.println(rect);
    }

}
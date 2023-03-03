public class PruebaPerimetro {
    public static void main(String[] args) {
        Cuadrado cuadrito = new Cuadrado(0.0,0.0, 5.0);
        cuadrito.area();
        cuadrito.imprimePerimetro();

        Rectangulo rectangulito = new Rectangulo(0.0, 0.0, 4.0, 3.0);
        rectangulito.area();
        rectangulito.imprimePerimetro();
    }
}

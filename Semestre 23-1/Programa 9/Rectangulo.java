public class Rectangulo extends Figura implements Perimetro {
    private Coordenada superiorIzq;
    private Coordenada inferiorDer;

    public Rectangulo(){
        super();
        this.setNumeroLados(4);
        superiorIzq = new Coordenada(0,0);
        inferiorDer = new Coordenada(0,0);
    }
    public Rectangulo(double xSupIzq, double ySupIzq, double xInfDer, double yInfDer){
        super();
        this.setNumeroLados(4);
        superiorIzq = new Coordenada(xSupIzq, ySupIzq);
        inferiorDer = new Coordenada(xInfDer, yInfDer);        
    }
    // El nuevo contructor para PROGRAMA 7
    public Rectangulo( Coordenada c1, Coordenada c2 ){
        superiorIzq = c1;
        inferiorDer = c2;
    }
    //Metodo getter de la coordenada superior izquierda
    public Coordenada superiorIzquierda( ) { return superiorIzq; }
    //Metodo getter de la coordenada inferior derecha
    public Coordenada inferiorDerecha( ) { return inferiorDer; }
    //Sobreescritura del método de la superclase objeto para imprimir con System.out.println( )
    public  void area(){
        double alto = Math.abs(this.superiorIzquierda().ordenada() - this.inferiorDerecha().ordenada());
        double ancho = Math.abs(this.inferiorDerecha().abcisa() - this.superiorIzquierda().abcisa());
        System.out.println("El area es: " + (alto*ancho));
    }

    public float imprimePerimetro() {
        float alto = (float) Math.abs(this.superiorIzquierda().ordenada() - this.inferiorDerecha().ordenada());
        float ancho = (float) Math.abs(this.inferiorDerecha().abcisa() - this.superiorIzquierda().abcisa());

        float perimetro = 2*(alto+ancho);
        System.out.println("Perimetro del rectangulo: " + perimetro);
        return perimetro;
    }  

    @Override
    public String toString( ) {
        return "Esquina superior izquierda: " + superiorIzq + "\tEsquina inferior derecha:" + inferiorDer + "\n";
    }
}
public class Cuadrado extends Figura implements Perimetro{
        private Coordenada superiorIzq;
        private double longitudLado;
        
        public Cuadrado(){
            super();
            this.setNumeroLados(4);
            superiorIzq = new Coordenada(0,0);
            longitudLado = 1.0;
        }
        public Cuadrado(double xSupIzq, double ySupIzq, double longitudLado){
            super();
            this.setNumeroLados(4);
            superiorIzq = new Coordenada(xSupIzq, ySupIzq);
            this.longitudLado = longitudLado;
        }
        // El nuevo contructor para PROGRAMA 7
        public Cuadrado( Coordenada c1, double longLado ){
            this.superiorIzq = c1;
            this.longitudLado = longLado;
        }
        //Metodo getter de la coordenada superior izquierda
        public Coordenada superiorIzquierda( ) { return superiorIzq; }
        //Metodo getter de la coordenada inferior derecha
        public double getLongitudLado( ) { return longitudLado; }
        //Sobreescritura del método de la superclase objeto para imprimir con System.out.println( )
        public  void area(){
            System.out.println("El area es: " + (this.longitudLado*this.longitudLado));
        }
        
        public float imprimePerimetro() {
            float perimetro = (float)longitudLado * 4;
            System.out.println("Perimetro del cuadrado: " + perimetro);
            return perimetro;
        }    
        
        @Override
        public String toString( ) {
            return "Esquina superior izquierda: " + superiorIzq + "\tLongitud del lado: " + longitudLado + "\n";
        }
        
        
}

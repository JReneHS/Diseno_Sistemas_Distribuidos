//package Semestre_23_2.Clase08;

public class PoligonoIrreg {

    private Coordenada[] vertices;

    public PoligonoIrreg() {

        vertices = new Coordenada[2];
        vertices[0] = new Coordenada(0,0);
        vertices[1] = new Coordenada(0,0);

    }

    public PoligonoIrreg(Coordenada[] coordenadas) {

        this.vertices = coordenadas;

    }

    public Coordenada[] getVertices() {
        return this.vertices;
    }


    // Sobreescritura del método de la superclase objeto para imprimir con
    // System.out.println( )

    @Override
    public String toString() {

        String coordenadas = "Coordenadas: \n"; 
        
        for (int i = 0; i < this.vertices.length; i++) {
            coordenadas = coordenadas + vertices[i] + "\n";
        }

        return coordenadas;
    }
    
}

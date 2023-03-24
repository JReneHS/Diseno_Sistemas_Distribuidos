//package Semestre_23_2.Clase11;

import java.util.ArrayList;
import java.util.Collections;

public class PoligonoIrreg {

    private ArrayList<Coordenada> vertices;

    // Constructores de PoligonoIrreg

    public PoligonoIrreg() {

        this.vertices = new ArrayList<Coordenada>();
        this.vertices.add(new Coordenada(0,0));
        this.vertices.add(new Coordenada(0,0));

    }

    public PoligonoIrreg(ArrayList<Coordenada> coordenadas) {

        this.vertices = coordenadas;

    }

    // Getter de Vertices

    public ArrayList<Coordenada> getVertices() {
        return this.vertices;
    }

    // Metodo para Ordenar los Vertices por Magnitud

    public void ordenaVertices() {
        Collections.sort(this.vertices, new SortByMagnitud());
    }


    // Sobreescritura del método de la superclase objeto para imprimir con
    // System.out.println( )

    @Override
    public String toString() {

        String coordenadas = "Coordenadas: \n";

        for (Coordenada cood : this.vertices) {
            coordenadas = coordenadas + cood + "\n";
        }

        return coordenadas;
    }
    
}

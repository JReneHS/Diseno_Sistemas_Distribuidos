import java.util.ArrayList;
import java.util.List;

/**
 * Proyecto 2
 * Mexicano Ixtepan Alejandro
 * 4CM13
 * PoligonoReg.java
 */

public class PoligonoReg {
    public static final int MAX_VERTEX = 100; 
    
    private int nvertices;
    private double angulo;
    private List<Coordenada> vertices;

	//Constructor de la clase
	public PoligonoReg(int nvertices){
        this.nvertices = nvertices;
        this.angulo = (double) 2.0 / nvertices * Math.PI;

        /*Inicializa el arreglo de las coordenadas 
            y asingna las coodenadas en pixeles del poligono */

        vertices = new ArrayList<Coordenada>();
        
        // Genera los n vertices 
        for(int i = 0,x,y; i < nvertices;i++){
            x = (int) (130 * (Math.cos((double) ((2.0d*Math.PI *i) + angulo)/nvertices)));
            y = (int) (130 * (Math.sin((double) ((2.0d*Math.PI *i) + angulo)/nvertices)));
            this.anadeVertice(new Coordenada(x,y));
        }
    }

    private void anadeVertice(Coordenada c){
        vertices.add(c);
    }
    
    public List<Coordenada> obtenerVertices(){
        return this.vertices;
    }

    public double obtenerArea(){
        return (double) this.nvertices * Math.cos(this.angulo / 2.0d) * Math.sin(this.angulo / 2.0d);
    }
}

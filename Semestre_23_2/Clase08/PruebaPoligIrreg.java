//package Semestre_23_2.Clase08;
import java.sql.SQLInvalidAuthorizationSpecException;
import java.util.Random;

public class PruebaPoligIrreg {
    public static void main(String[] args) {
        Random random = new Random();

        Coordenada[] coordenadas = new Coordenada[10];
        
        for(int i= 0; i<10; i++) {
            double x = random.nextDouble(0.0,100.0);
            double y = random.nextDouble(0.0,100.0);
            coordenadas[i]  =  new Coordenada(x, y);
            System.out.println(coordenadas[i]);
        }
        System.out.println();
    
        PoligonoIrreg pol1 = new PoligonoIrreg(coordenadas);
        System.out.println(pol1);
    }


}

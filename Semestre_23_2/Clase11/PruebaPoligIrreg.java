//package Semestre_23_2.Clase11;

import java.util.ArrayList;
import java.util.Random;

public class PruebaPoligIrreg {
    public static double limInf = -100.0;
    public static double limSup = 100.0;

    public static void main(String[] args) {
        Random random = new Random();

        ArrayList<Coordenada> coordenadas = new ArrayList<Coordenada>();

        for (int i = 0; i < 10; i++) {
            double x = random.nextDouble(limInf, limSup);
            double y = random.nextDouble(limInf, limSup);
            coordenadas.add(new Coordenada(x, y));
        }

        PoligonoIrreg pol1 = new PoligonoIrreg(coordenadas);

        System.out.println("Coordenadas sin ordenar");
        System.out.println(pol1);

        pol1.ordenaVertices();

        System.out.println("Coordenadas Ordenadas por Magnitud");
        System.out.println(pol1);
    }

}

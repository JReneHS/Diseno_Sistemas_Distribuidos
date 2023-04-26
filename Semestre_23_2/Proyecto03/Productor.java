package Semestre_23_2.Proyecto03;

import java.io.File;
import java.io.FileWriter;
import java.util.concurrent.TimeUnit;

public class Productor {
    private static int tiempoEspera = 1;
    private static int numCurpsGeneradas = 5;

    public static void main(String[] args) {
        File fichero = new File("Curp.txt");
        try {
            if (fichero.createNewFile()) {
                System.out.println("Archivo Creado: " + fichero.getName());
            } else {
                System.out.println("El archivo ya existe.");
            }
        } catch (Exception e) {
            System.out.println("Error al Abrir el Archivo.");
            e.printStackTrace();
        }

        try {

            while (true) {
                FileWriter escribir = new FileWriter(fichero, true);

                for (int i = 0; i < numCurpsGeneradas; i++) {
                    Voto voto = new Voto();
                    escribir.write(voto.getVoto() + "\n");
                }

                try {
                    TimeUnit.SECONDS.sleep(tiempoEspera);
                } catch (Exception e) {
                    System.out.println("Mensaje: " + e.getMessage());
                }

                escribir.close();
            }

        } catch (Exception e) {
            System.out.println("Error al Abrir Editar el Archivo.");
            e.printStackTrace();
        }

    }
}

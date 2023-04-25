package Semestre_23_2.Proyecto03;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Contador {
    private static ArrayList<Voto> votos;

    public static void main(String[] args) {
        File fichero = new File("Curps.txt");
		Scanner s = null;

		try {
			// Leemos el contenido del fichero
			s = new Scanner(fichero);
			// Leemos linea a linea el fichero
			while (s.hasNextLine()) {
                String voto = s.nextLine();
                votos.add(new Voto(voto.split(" ")));
			}

		} catch (Exception ex) {
			System.out.println("Mensaje: " + ex.getMessage());
		} finally {
			// Cerramos el fichero tanto si la lectura ha sido correcta o no
			try {
				if (s != null)
					s.close();
			} catch (Exception ex2) {
				System.out.println("Mensaje 2: " + ex2.getMessage());
			}
		}
    }
}

package Semestre_23_2.Proyecto03;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Tablero {
    private static int tiempoEspera = 3;

    public static void main(String[] args) {
        while (true) {
            Contador cont = new Contador();
            ArrayList<Voto> contVotos = cont.contar();
            Hashtable<String, Integer> hashtable = new Hashtable<>();

            for (Voto voto : contVotos) {
                String partido = voto.getPartido();
                if (hashtable.containsKey(partido)) {
                    Integer prev = hashtable.get(partido);
                    hashtable.replace(partido, prev + 1);
                } else {
                    hashtable.put(partido, 1);
                }
            }

            imprimir(hashtable);

            try {
                TimeUnit.SECONDS.sleep(tiempoEspera);
            } catch (Exception e) {
                System.out.println("Mensaje: " + e.getMessage());
            }

            limpiar();
        }
    }

    private static void imprimir(Hashtable<String, Integer> hashtable) {

        System.out.println("          0    50   100  150  200");
        System.out.println("          |    |     |    |    | ");

        Set<String> partidos = hashtable.keySet();
        for (String partido : partidos) {
            System.out.print(partido + " ");
            int tam = hashtable.get(partido);
            tam /= 10;
            for (int i = 0; i < tam; i++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    private static void limpiar() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

package Proyecto;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Proyecto 2
 * Hernandez Sanchez Juan Rene
 * 4CM13
 * SimpleGui2.java
 */

public class SimpleGui2 extends JFrame {
    public static int numPoligonos = 10;
    public static int totalLados = 20;
    public static int limAbsis = 550;
    public static int limOrd = 350;
    public static int casePanel = -1;
    public static int width = 800;
    public static int height = 600;
    public static boolean clean = false;
    public static ArrayList<PoligonoReg> poligonosList;

    public static void main(String[] args) {
        // Asignacion mediante terminal del numero de Figuras
        try {
            numPoligonos = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("Valor no valido o no ingresado, se asigna " + numPoligonos + " como estandar");
        }

        // Arreglo de Poligonos Regulares, en este case un ArrayList para usar Iterators
        poligonosList = new ArrayList<PoligonoReg>();

        // Creacion de los n Poligonos Regulares de manera Aleatoria (entre 3 y 20
        // Lados)
        for (int i = 0, rand; i < numPoligonos; i++) {
            rand = (int) (Math.random() * totalLados + 3);
            poligonosList.add(new PoligonoReg(rand));
        }

        // Sort de los poligonos regulares
        poligonosList.sort((actPol, nextPol) -> actPol.obtenerNumVertices()
                .compareTo(nextPol.obtenerNumVertices()));

        // Inicializacion e Instanciacion del Panel y JFrame
        SimpleGui2 gui = new SimpleGui2();
        gui.setVisible(true);
        Panel p = gui.new Panel();
        gui.add(p);

        // Seleccion de Tiempos y Casos de Impresion
        while (casePanel < numPoligonos) {
            p.repaint();
            try {
                // Caso -1 es para las n figuras de forma aleatoria
                if (casePanel == -1 && !clean) {
                    TimeUnit.SECONDS.sleep(3);
                    // Este es un Tiempo de espera para Limpiar Frame
                } else if (clean) {
                    TimeUnit.MILLISECONDS.sleep(10);
                    // Tiempo de Visualizacion de cada figura por separado
                } else {
                    TimeUnit.MILLISECONDS.sleep(800);
                }
            } catch (Exception e) {

            }
            casePanel++;
        }
    }

    public SimpleGui2() {
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class Panel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            // Si la bandera de Clean esta activa, se refresca el Frame
            if (clean) {
                limpiarFrame(g);
                casePanel--;
                // Caso para imprimir todas las figuras de manera Aleatoria
            } else if (casePanel == -1) {
                for (PoligonoReg poligonoReg : poligonosList) {
                    int x = (int) (Math.random() * limAbsis + 1);
                    int y = (int) (Math.random() * limOrd + 1);
                    Polygon poligono = crearPolygon(poligonoReg, x, y);
                    dibujaPoligono(g, poligono);
                    dibujaCirculo(g, x, y);
                    clean = true;
                }
                // Caso en el que se imprime figura por figura
                // Mediante la variable general casePanel se sabe que figura toca
            } else {
                int x = width / 3;
                int y = height / 4;
                Polygon poligono = crearPolygon(poligonosList.get(casePanel), x, y);
                dibujaPoligono(g, poligono);
                dibujaCirculo(g, x, y);
                clean = true;
            }

        }

    }

    public Polygon crearPolygon(PoligonoReg pol, int x, int y) {
        Polygon poligono = new Polygon();
        for (Coordenada cood : pol.obtenerVertices()) {
            poligono.addPoint(cood.abcisa() + x, cood.ordenada() + y);
        }
        return poligono;
    }

    public void limpiarFrame(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, width, height);
        clean = false;
    }

    public void dibujaPoligono(Graphics g, Polygon pol) {
        g.setColor(Color.blue);
        g.drawPolygon(pol);
    }

    public void dibujaCirculo(Graphics g, int x, int y) {
        g.setColor(Color.RED);
        g.drawOval(x, y, 260, 260);
    }
}
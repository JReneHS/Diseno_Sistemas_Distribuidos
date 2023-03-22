package Proyecto;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * Proyecto 2
 * Hernandez Sanchez Juan Rene
 * 4CM13
 * SimpleGui2.java
 */

public class SimpleGui2 extends JFrame {
    public static int numPoligonos = 50;

    public static void main(String[] args) {
        try {
            numPoligonos = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("Valor no valido o no ingresado, se asigna 50 como estandar");
        }

        SimpleGui2 gui = new SimpleGui2();
        gui.setVisible(true);
    }

    public SimpleGui2() {
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Panel p = new Panel();
        add(p);
    }

    private class Panel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            ArrayList<PoligonoReg> poligonosList = new ArrayList<PoligonoReg>();

            for (int i = 0; i < numPoligonos; i++) {
                poligonosList.add(new PoligonoReg(i + 3));
            }
            g.setColor(Color.blue);
            Polygon poligono = new Polygon();
            for (PoligonoReg poligonoReg : poligonosList) {
                for (Coordenada cood : poligonoReg.obtenerVertices()) {
                    poligono.addPoint(cood.abcisa(), cood.ordenada());
                }
            }
            g.drawPolygon(poligono);
        }
    }
}
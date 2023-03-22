package Proyecto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Polygon;

/**
 * Proyecto 2
 * Hernandez Sanchez Juan Rene
 * 4CM13
 * App.java
 */

public class App extends JFrame {
    public static void main(String[] args) {
        int nPolygons;
        Scanner scan = new Scanner(System.in);
        // Introducir los poligonos a generar
        System.out.println("Introduzca el número de poligonos a generar:");
        nPolygons = scan.nextInt();
        scan.close();

        App gui = new App(nPolygons);
        gui.setVisible(true);

    }

    public App(int nPolygons) {
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        InnerApp panel = new InnerApp(nPolygons);
        this.add(panel);
    }

    private class InnerApp extends JPanel {
        private int actual;
        private boolean unsortedPolygons, hasChanged;
        private List<PoligonoReg> lpolyg;

        private final Comparator<PoligonoReg> comparator = (PoligonoReg p1, PoligonoReg p2) -> {
            if (p1.obtenerArea() > p2.obtenerArea()) {
                return 1;
            } else {
                return p1.obtenerArea() == p2.obtenerArea() ? 0 : -1;
            }
        };

        public InnerApp(int nPolygons) {

            this.actual = 0;
            this.unsortedPolygons = false;
            this.hasChanged = false;
            this.lpolyg = new ArrayList<PoligonoReg>();

            Random rd = new Random();
            for (int i = 0; i < nPolygons; i++) {
                this.lpolyg.add(new PoligonoReg(rd.nextInt(100 - 3) + 3));
            }
            this.repaint();

            ExecutorService service = Executors.newSingleThreadExecutor();
            Thread th[] = new Thread[nPolygons];

            th[0] = new Thread(() -> {
                try {
                    Thread.sleep(5000);
                    Collections.sort(lpolyg, comparator);
                    this.repaint();
                    this.unsortedPolygons = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });

            for (int i = 1; i < nPolygons; i++) {
                th[i] = new Thread(() -> {
                    try {
                        this.hasChanged = true;
                        Thread.sleep(3000);
                        this.repaint();
                        this.actual++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }

            for (Thread t : th) {
                service.submit(t);
            }
            service.shutdown();

        }

        @Override
        public void paintComponent(Graphics g) {
            int x, y;
            Polygon poligono = new Polygon();
            // Imprime los poligonos en posiciones random
            if (!this.unsortedPolygons) {
                Random rd = new Random();
                for (PoligonoReg p : this.lpolyg) {

                    x = rd.nextInt(this.getHeight());
                    y = rd.nextInt(this.getHeight());

                    poligono.reset();
                    g.setColor(Color.BLUE);
                    for (Coordenada c : p.obtenerVertices()) {
                        poligono.addPoint(x + 130 + c.abcisa(), y + 130 + c.ordenada());
                    }
                    g.drawPolygon(poligono);

                    g.setColor(Color.RED);
                    g.drawOval(x, y, 260, 260);
                }
            }
            // Imprime poligono a poligono
            else if (this.hasChanged) {
                x = 270;
                y = 170;
                g.setColor(Color.BLUE);
                for (Coordenada c : lpolyg.get(actual).obtenerVertices()) {
                    poligono.addPoint(x + 130 + c.abcisa(), y + 130 + c.ordenada());
                }
                g.drawPolygon(poligono);

                g.setColor(Color.RED);
                g.drawOval(x, y, 260, 260);
            }
        }
    }
}
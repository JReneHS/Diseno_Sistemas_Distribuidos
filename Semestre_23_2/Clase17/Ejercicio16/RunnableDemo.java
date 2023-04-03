package Semestre_23_2.Clase17.Ejercicio16;

public class RunnableDemo {
    public static long hilo1;
    public static long hilo2;
    public static long hilo3;
    public static int n = 20;
    public static char[] pila = new char[10];
    public static int tope = -1;

    public static void main(String[] args) {
        
        try {
            n = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("Error al leer entrada, se asigna " + n + " como valor por defecto");
        }

        Thread productor = new Thread(new RunnableDemo().new RunnableImpl());
        Thread consumidor = new Thread(new RunnableDemo().new RunnableImpl());
        Thread limpieza = new Thread(new RunnableDemo().new RunnableImpl());

        hilo1 = productor.getId();
        hilo2 = consumidor.getId();
        hilo3 = limpieza.getId();

        productor.start();
        consumidor.start();
        limpieza.start();

        try {
            productor.join();
            consumidor.join();
            limpieza.join();
        } catch (Exception e) {
            System.out.println("Error al terminar los hilos");
        }

    }

    private class RunnableImpl implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < n; i++) {
                modifica();
            }
        }

        public synchronized void modifica() {

            long id = getIdThread();
            long time = (long)(Math.random() * 600 + 250);

            
            if(id == hilo1)
                producir();
                try {
                    Thread.currentThread();
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
     
            if(id == hilo2)
                consumir();
                try {
                    Thread.currentThread();
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            
            if (id == hilo3)
                limpiar();
        }

        public long getIdThread() {
            return Thread.currentThread().getId();
        }

        private void producir() {
            if (tope >= 9) {
                return;
            }
            pila[++tope] = (char) (Math.random() * (90 - 66) + 65);
        }

        private void consumir() {
            if (tope < 0) {
                return;
            }
            pila[tope--] = ' ';
        }

        private void limpiar() {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.print("[");
            System.out.print(pila);
            System.out.println("]");
        }

    }

}
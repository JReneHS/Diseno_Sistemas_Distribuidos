package Semestre_23_2.Clase17.Ejercicio15;

public class RunnableDemo {
    public static long hilo1;
    public static long hilo2;
    public static int var_compartida = 0;
    public static int n = 20;
    public static void main(String[] args) {
        
        try {
            n = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("Error al leer entrada, se asigna " + n + " como valor por defecto");
        }

        Thread th1 = new Thread(new RunnableDemo().new RunnableImpl());
        Thread th2 = new Thread(new RunnableDemo().new RunnableImpl());
        
        hilo1 = th1.getId();
        hilo2 = th2.getId();
        
        th1.start();
        th2.start();

        try {
            th1.join();
            th2.join();
        } catch (Exception e) {
            System.out.println("Error al terminar los hilos");
        }

        System.out.println(var_compartida);

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
     
            if(id == hilo1)
     
                  var_compartida++;
     
            if(id == hilo2)
     
                  var_compartida--;           
     
        }

        public long getIdThread() {
            return Thread.currentThread().getId();
        }


    }

}
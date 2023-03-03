import java.util.Scanner;

public class T03SegundosAHoras {
    public static void main(String[] args) {
        int segundos;

        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la cantidad de Segundos a convertir:");
        segundos = leerNumeros(sc);
        sc.close();

        obtenerHoras(segundos);
    }

    public static void obtenerHoras(int tiempo) {
        System.out.println("La converion de " + tiempo + "s a Horas es de:");
        short seg = (short) (tiempo % 60);
        tiempo /= 60;
        short min = (short) (tiempo % 60);
        tiempo /= 60;
        short hrs = (short) (tiempo % 24);
        tiempo /= 24;

        System.out.println("Dias: " + tiempo);
        System.out.println("Horas: " + hrs);
        System.out.println("Minutos: " + min);
        System.out.println("Segundos: " + seg);
    }

    public static int leerNumeros(Scanner sc) {
        int tipo = 1;
        boolean flag = true;
        while (flag) {
            try {
                tipo = sc.nextInt();
                if (tipo > 0) {
                    System.out.println("Ingrese un Numero Positivo");
                    flag = false;
                }
            } catch (Exception e) {
                System.out.println("Ingrese un Numero Valido Positivo Mayor a 0");
                sc.nextLine();
            }
        }
        return tipo;
    }
}

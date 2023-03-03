import java.util.Scanner;

public class T01VolumenCilindroCono {
    public static void main(String[] args) {
        double radio;
        double altura;

        Scanner sc = new Scanner(System.in);
        radio = leerNumeros("el radio", sc);
        altura = leerNumeros("la altura", sc);
        sc.close();

        double volumenCilindro = Math.PI * Math.PI * radio * altura;
        double volumenCono = volumenCilindro / 3.0;

        double diferencia = volumenCilindro - volumenCono;

        System.out.printf("Volumen Cilindro = %.4f\n", volumenCilindro);
        System.out.printf("Volumen Cono = %.4f\n", volumenCono);
        System.out.printf("Diferencia de Volumenes = %.4f\n", diferencia);
    }

    public static double leerNumeros(String nombre, Scanner sc) {
        double tipo = 1.0;
        boolean flag = true;
        while (flag) {
            try {
                System.out.println("Ingrese " + nombre + " de la Figura:");
                tipo = sc.nextDouble();
                if (tipo > 0.0) {
                    flag = false;
                }
            } catch (Exception e) {
                System.out.println("Ingrese un Numero Valido Positivo separado por '.'");
                System.out.println("Error: " + e);
                sc.nextLine();
            }
        }
        return tipo;
    }
}
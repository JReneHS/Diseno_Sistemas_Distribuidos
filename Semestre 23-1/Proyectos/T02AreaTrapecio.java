import java.util.Scanner;

public class T02AreaTrapecio {
    public static void main(String[] args) {
        double ladoA;
        double ladoB;
        double altura;

        Scanner sc = new Scanner(System.in);
        System.out.println("*Area de un Trapecio*");

        System.out.println("Ingrese la medida del Lado A:");
        ladoA = leerNumeros(sc);

        System.out.println("Ingrese la medida del Lado B:");
        ladoB = leerNumeros(sc);

        System.out.println("Ingrese la medida de la Altura:");
        altura = leerNumeros(sc);

        sc.close();

        double area = ((ladoA + ladoB) / 2.0) * altura;

        System.out.printf("El area Total del trapecio de lados %.2f y %.2f con altura %.2f es de: %.2f\n",
                ladoA,
                ladoB,
                altura,
                area);
    }

    public static double leerNumeros(Scanner sc) {
        double tipo = 1.0;
        boolean flag = true;
        while (flag) {
            try {
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

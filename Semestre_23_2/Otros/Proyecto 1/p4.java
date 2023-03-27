import java.util.Scanner;

public class p4 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("\n\033[1mSolucionador de ax² + bx + c\033[0m");
		// Pedimos los valores de la ecuacion
		System.out.print("Valor de a --> ");
		double a = sc.nextDouble();

		System.out.print("Valor de b --> ");
		double b = sc.nextDouble();

		System.out.print("Valor de c --> ");
		double c = sc.nextDouble();
		sc.close();
		// Discriminante
		double disc = b*b - 4*a*c;

		if ( disc < 0 ){
			System.out.println("La ecuacion no tiene soluciones reales.");
			System.exit((int)disc);
		}

		// Calculando ambas respuestas
		double sqrDisc = Math.sqrt(disc);
		double plusAns = (-b + sqrDisc) / (2*a);
		double minusAns = (-b - sqrDisc) / (2*a);

		System.out.println("\n\033[1mSoluciones...\033[0m");
		System.out.printf("x1 = %.4f \nx2 = %.4f \n", plusAns, minusAns);

	}
}
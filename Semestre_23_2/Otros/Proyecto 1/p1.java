import java.util.Scanner;
/**
 * p1
 */
public class p1 {
    public static void main(String[] args) {
		// Scanner for user input
		Scanner scanner = new Scanner(System.in);

		double radio = scanner.nextDouble();
		double altura = scanner.nextDouble();

		double areaCilindro = (Math.PI * radio * radio * altura);
		double areaCono = areaCilindro / 3;

		System.out.printf("Area del cilindro: %.4f u³\n", areaCilindro);
		System.out.printf("Area del cono: %.4f u³\n", areaCono);
		System.out.printf("Diferencia de áreas: %.4f u³\n", (areaCilindro - areaCono) );
    
		scanner.close();
	}
    
}
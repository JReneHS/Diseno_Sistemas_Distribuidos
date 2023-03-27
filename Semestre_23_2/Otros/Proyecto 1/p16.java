import java.util.Scanner;
public class p16 {
	public static void main(String[] args) {
		Scanner sc = new Scanner( System.in );
		System.out.println("Oh.. se viene una larga suma...");
		System.out.print("Ingresa un número --> ");
		int num = sc.nextInt();

		long sum = num*num;
		while ( num != 0 ){
			System.out.print("Ingresa el siguiente --> ");
			num = sc.nextInt();
			sum+= num*num;
		}
		sc.close();

		System.out.println("La suma...\nDe los cuadrados...\nDe todos los numeros ingresados, es...");
		System.out.println("\033[1m"+sum+"\033[0m");
	}
}

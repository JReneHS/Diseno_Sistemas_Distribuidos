import java.util.Scanner;
public class p10 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Ingresa un numero --> ");
		int num = sc.nextInt();
		sc.close();
		long fact = 1;

		for ( int i = 1; i <= num; i++ ){
			fact*= i;
		}

		System.out.println("Factorial --> " + fact);
	}
}

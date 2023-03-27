import java.util.Scanner;

public class p8 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Numero 1 --> ");
		int n1 =  sc.nextInt();
		System.out.print("Numero 2 --> ");
		int n2 = sc.nextInt();
		sc.close();

		if ( n1 % n2 == 0 )
			System.out.println("El \033[1mnumero 1\033[0m es multiplo del \033[1mnumero 2\033[0m");
		else if ( n2 % n1 == 0 )
			System.out.println("El \033[1mnumero 2\033[0m es multiplo del \033[1mnumero 1\033[0m");
		else 
			System.out.println("Los numeros no son multiplos entre sí");

	}
}

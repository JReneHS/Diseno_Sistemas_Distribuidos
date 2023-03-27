import java.util.Scanner;
public class p13 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Cadena a invertir --> ");
		String cad = sc.next();
		sc.close();

		System.out.print("Cadena invertida --> ");
		for( int i = cad.length() - 1; i >= 0; i-- )
			System.out.print(cad.charAt(i));
		System.out.println("");
	}
}

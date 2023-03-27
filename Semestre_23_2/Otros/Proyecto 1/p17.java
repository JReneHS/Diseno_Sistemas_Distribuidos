import java.util.Scanner;
public class p17 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] arr = new int[10];

		for( int i = 0; i < 10; i++ ){
			System.out.printf("Le numero %d es --> ", i);
			arr[i] = sc.nextInt();
		}
		sc.close();
		System.out.println("Los numeros en orden son... ");
		for( int num : arr ){
			System.out.print(""+num+", ");
		}
		// Se va 2 chars para atras y los reemplaza por espacio en blanco
		System.out.println("\033[2D  ");
	}
}

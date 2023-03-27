import java.util.Scanner;
public class p18 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] arr1 = new int[10];
		int[] arr2 = new int[10];
		int[] arr3 = new int[20];

		System.out.println("Arreglo 1...");
		for (int j = 0; j < 10; j++) {
			System.out.printf("Elemento %d > ", j);
			arr1[j] = sc.nextInt();
		}
		System.out.println("Arreglo 2...");
		for (int j = 0; j < 10; j++) {
			System.out.printf("Elemento %d > ", j);
			arr2[j] = sc.nextInt();
		}
		sc.close();
		// Juntando ambos arreglos
		for (int i = 0, j = 0; i < arr3.length; i+=2, j++) {
			arr3[i] = arr1[j];
			arr3[i+1] = arr2[j];
		}

		for (int num : arr3) {
			System.out.print(""+num+", ");
		}
		System.out.println("\033[2D  ");
	}
}

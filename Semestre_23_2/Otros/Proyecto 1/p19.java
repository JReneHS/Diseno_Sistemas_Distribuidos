import java.util.Scanner;
public class p19 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] arr = new int[10];
		int[] arr2 = new int[10];

		for (int i = 0; i < 10; i++) {
			arr[i] = sc.nextInt();
		}
		sc.close();
		System.out.println("Arreglo antes de moverlo...");
		printArr(arr);

		for ( int i = 0; i < 10; i ++ ){
			arr2[ (i+1)%10 ] = arr[i];
		}
		arr = arr2;

		System.out.println("Arreglo luego de moverse...");
		printArr(arr);
	}

	public static void printArr(int[] arr) {
		System.out.print("[");
		for (int i : arr) 
			System.out.print(""+i+", ");
		System.out.print("\033[2D]");
	}
}

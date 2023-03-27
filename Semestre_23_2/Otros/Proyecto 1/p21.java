import java.util.Scanner;
public class p21 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] matriz = new int[5][5];

		// Llenamos la matriz
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				matriz[i][j] = sc.nextInt();
			}
		}
		sc.close();
		// Sacamos la suma de la diagonal principal
		int suma = 0;
		for (int i = 0; i < matriz.length; i++) {
			suma += matriz[i][i];
		}

		System.out.println("Suma de la diagonal --> "+suma);

	}
}

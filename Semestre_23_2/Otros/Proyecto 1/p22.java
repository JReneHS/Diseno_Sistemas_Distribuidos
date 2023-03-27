import java.util.Scanner;
public class p22 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] mat1 = new int[3][3];
		int[][] mat2 = new int[2][3];
		int[][] res0 = new int[2][3];

		// Llenamos la matriz 1
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				mat1[i][j] = sc.nextInt();
			}
		}

		// Llenamos la matriz 2
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				mat2[i][j] = sc.nextInt();
			}
		}

		int m2Rows = mat2.length;
		int m2Cols = mat2[0].length;
		int m1Cols = mat1[0].length;
		// Multiplicamos las matrices m2 * m1
		//Para cada fila y columna de la matriz resultante
		for( int i = 0; i < m2Rows; i++ ){
			for( int j = 0; j < m1Cols; j++ ){
				//Obtenemos la suma de los productos para cada elemento
				int sum = 0;
				for( int k = 0; k < m2Cols; k++ ){
					sum += mat2[i][k] * mat1[k][j];
				}
				res0[i][j] = sum;
			}
		}

		// Imprimimos la matriz
		for( int i = 0; i < res0.length; i++ ){
			for( int j = 0; j < res0[0].length; j++ )
				System.out.printf( "%d  ", res0[i][j] );
			System.out.printf("\n");
		}
	}
}

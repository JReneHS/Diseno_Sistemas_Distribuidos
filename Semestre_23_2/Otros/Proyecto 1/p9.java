import java.util.Scanner;

public class p9 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] simbolos = { 'I', 'V', 'X', 'L', 'C', 'D', 'M' }; 
		// indice del mult. de 10 en el arreglo de simbolos
		int index; 
		int num;
		StringBuilder romano = new StringBuilder();
		String numStr;
		System.out.println("Conversor de romanos");
		System.out.print("Ingresa un numero --> ");
		num = sc.nextInt();
		sc.close();
		numStr = ""+num;

		if ( num > 3000 || num < 1 ) {
			System.out.println("El numero debe ser mayor a 0 y menor a 3000");
			System.exit(-1);
		}

		// Le damos la base al numero a convertir
		if ( num > 1000 )
			index = 6;
		else if ( num > 100 )
			index = 4;
		else if ( num > 10 )
			index = 2;
		else	
			index = 0;

		// Aplicamos las reglas de conversión de romanos por digito
		for ( char digit : numStr.toCharArray() ){
			int digVal = Character.getNumericValue(digit);
			// Repetimos el digito hasta 3 veces
			if ( digVal <= 3 ){
				for ( int i = 0; i < digVal; i++ )
					romano.append(simbolos[index]);
			// Se resta 1 al mult. de 5
			} else if (digVal == 4) {
				romano.append(simbolos[index]);
				romano.append(simbolos[index+1]);
			} else if (digVal == 5) {
				romano.append(simbolos[index+1]);
			// Se suman hasta 3 al mult. de 5
			} else if (digVal <= 8) {
				romano.append(simbolos[index+1]);
				for ( int i = 0; i < digVal - 5; i++ )
					romano.append(simbolos[index]);
			// Se resta 1 al multiplo de 10 sig.
			} else if (digVal == 9) {
				romano.append(simbolos[index]);
				romano.append(simbolos[index+2]);
			}
			// Reducimos la base en 10 unidades
			index-=2;
		}

		// Imprimimos romano convertido
		System.out.println("El numero romano es --> "+romano);
	}	
}

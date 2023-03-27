public class p11 {
	public static void main(String[] args) {
		long cubeRes;
		int numAux;
		for ( int i = 1; i <= 5000; i++ ){
			cubeRes = 0;
			// Obtenemos todos los digitos del número
			// y sumamos sus cubos
			for ( numAux = i ; numAux != 0; numAux/= 10 ){
				int digito = numAux % 10;
				cubeRes += (digito) * (digito) * (digito);
			}
			
			// Imprimimos si la suma de cubos es igual al numero
			if ( cubeRes == i )
				System.out.println(i);

		}
	}	
}

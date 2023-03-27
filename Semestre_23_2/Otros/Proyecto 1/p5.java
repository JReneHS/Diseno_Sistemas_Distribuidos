import java.util.Scanner;

public class p5 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Calificación del estudiante --> ");
		int calif = sc.nextInt();

		if ( calif < 0 || calif > 100 ) {
			System.out.println("Valor fuera de rango...");
			System.exit(-1);
		}

		System.out.println("¿Imprimir una letra \033[1m(l)\033[0m o descripción \033[1m(d)\033[0m?");
		char dec = sc.next().charAt(0);
		sc.close();
		switch (dec){
			case 'd':
				if ( calif < 60 )
					System.out.println("No suficiente");
				else if ( calif < 80 )
					System.out.println("Suficiente");
				else if ( calif < 90 )
					System.out.println("Bien!");
				else if ( calif < 100 )
					System.out.println("¡Muy bien!");
			break;
			case 'l':
				if ( calif < 60 )
					System.out.println("F");
				else if ( calif < 80 )
					System.out.println("C");
				else if ( calif < 90 )
					System.out.println("B!");
				else if ( calif < 100 )
					System.out.println("¡A!");
			break;
			default:
			System.out.println("Error: Ingresa solo las letras \033[1ml\033[0m o \033[1mm\033[0m");
		}
	}
}

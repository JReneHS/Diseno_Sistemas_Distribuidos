import java.util.Scanner;

public class p7 {
	public static void main(String[] args) {
		// Guarda los precios acumulados segun la tabulación
		// descrita en el ejercicio
		double[] precios = {162.5, 375, 1425, 3675};
		Scanner sc = new Scanner(System.in);
		char contrato;
		double kw;
		double pago = 0;

		// precios[0] = 250 * .65;
		// precios[1] = precios[0] + 250*.85;
		// precios[2] = precios[1] + (1200 - 500) * 1.5;
		// precios[3] = precios[2] + (2100 - 1200) * 2.5;

		System.out.println("¿Tipo de contrato del cliente?");
		System.out.println("> Contrato de hogar (h)");
		System.out.print("> Contrato de negocios (n)\n--> ");
		contrato = sc.next().charAt(0);

		if ( contrato != 'h' && contrato != 'n' ){
			System.out.println("No existe el contrato "+contrato);
			System.exit(-1);
		}

		System.out.print("¿Cantidad de kW consumidos?\n--> ");
		kw = sc.nextDouble();
		sc.close();

		if ( contrato == 'h' ){
			if ( kw > 2100 )
				pago = precios[3] + (kw-2100) * 3;
			else if ( kw > 1200 )
				pago = precios[2] + (kw-1200) * 2.5; 
			else if ( kw > 500 )
				pago = precios[1] + (kw-500) * 1.5;
			else if ( kw > 250 )
				pago = precios[0] + (kw-250) * .85;
			else if ( kw > 0 )
				pago = kw * .65;
		} else {
			pago = kw * 5;
		}
			
		System.out.println("El cliente pagará $" + pago);

	}	
}

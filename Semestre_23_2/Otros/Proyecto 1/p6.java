import java.util.Scanner;

class Cliente{
	public double compra;
	public String nombre;
	public int edad;
	public char sexo;
}

public class p6 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Cliente c1 = new Cliente();

		System.out.println("\033[43;1;31m ----- Hamburguesas Teresita ----- \033[0m\n");
		System.out.print("Total de la compra --> ");
		c1.compra = sc.nextDouble();

		System.out.print("Primer nombre del cliente --> ");
		c1.nombre = sc.next().toLowerCase();

		System.out.print("Edad del cliente --> ");
		c1.edad = sc.nextInt();

		System.out.print("Sexo del cliente (h o m) --> ");
		c1.sexo = sc.next().charAt(0);
		sc.close();
		if ( ( c1.nombre.compareTo("tere") 		== 0 ||
			   c1.nombre.compareTo("teresa") 	== 0 ||
			   c1.nombre.compareTo("teresita") 	== 0)||
			 ( c1.edad >= 16 && c1.edad <= 22 && c1.sexo == 'm' ) ){
				 c1.compra = c1.compra * (1-0.07);
			}			
		
		System.out.println("\n\033[1mTotal de la compra --> $" + c1.compra +"\033[0m");
	}	
}

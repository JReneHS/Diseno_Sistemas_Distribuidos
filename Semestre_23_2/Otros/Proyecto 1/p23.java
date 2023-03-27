import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
public class p23 {
	public static void main(String[] args) {
		// do {
			try (Scanner sc = new Scanner(System.in);
				FileWriter fw = new FileWriter("p23_Data", false)) {
				int edad;
				System.out.print("Ingresa los datos...");
				while (true) {
					System.out.print("\nEdad --> ");
					if ( (edad = sc.nextInt()) == 0 ){
						System.out.println("Bueno, Adios!");
						break;
					}
					fw.append(""+edad+", ");
					System.out.print("Estatura --> ");
					fw.append( ""+sc.nextDouble()+"\n" );
				}
			} catch (IOException e) {
				File data = new File("p23_Data");
				try {
					data.createNewFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		// } while ( true );
	}
}

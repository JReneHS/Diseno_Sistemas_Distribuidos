import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class p24 {
	public static void main(String[] args) {
		try (Scanner fsc = new Scanner( new File("p24_Data") )) {
			int max = Integer.MIN_VALUE;
			int line = 1, maxLine = 0;;
			while ( fsc.hasNextLine() ){
				int num = Integer.parseInt(fsc.nextLine());
				if ( max < num ){
					max = num;
					maxLine = line;
				}
				line++;
			}

			System.out.println("El numero maximo es "+max+" en la línea "+maxLine);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}

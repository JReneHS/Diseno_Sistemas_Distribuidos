import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
public class p15 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int secret = ThreadLocalRandom.current().nextInt(1, 101);

		System.out.println("\n\033[1;97;44;6m ----- [ADIVINA EL NUMERO] ----- \033[0m\n");
		
		while (true) {
			System.out.print(">>> ");
			int guess = sc.nextInt();

			if ( guess > 100 || guess < 0 ){
				System.out.println("\033[1;103;30mEl numero está entre 100 y 1\033[0m");
				continue;
			} else if ( guess == 0 ){
				System.out.println("\033[7;31;1mTe rendiste\noh, bueno... Adiós...\033[0m");
				sc.close();
				System.exit(-1);
			}

			if ( guess < secret )
				System.out.println("... ¡Estás por abajo del número!");
			else if ( guess > secret )
				System.out.println("... ¡Te pasaste del número!");
			else {
				System.out.println("\n\033[32;1;7;6m¡¡[FELICIDADES]!!\033[0m\n");
				sc.close();
				System.exit(0);
			}
		}
	}
}

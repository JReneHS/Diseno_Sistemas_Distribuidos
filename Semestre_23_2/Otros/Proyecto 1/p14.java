import java.util.Scanner;
public class p14 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Numero --> ");
		int num = sc.nextInt();
		sc.close();

		while (num <= 150) {
			System.out.println(num);
			num+= 5;
		}

	}
}

public class p12 {
	public static void main(String[] args) {
		double valorPresente = 0;

		for( int i = 0; i < 20; i++ ){
			if ( i < 10 )
				valorPresente += 500;
			valorPresente += valorPresente * 0.05;
		}
		
		System.out.println("Valor de la cuenta --> \033[1;6m$"+valorPresente+"\033[0m");
	}
}

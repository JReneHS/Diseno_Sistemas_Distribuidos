public class T06CadenasIPN {
    /**
     * @param args
     * T06CadenasIPN
     */
    public static void main(String[] args) {
        StringBuilder str = new StringBuilder();
        long n;
        try {
            n = Long.parseLong(args[0]);
        } catch (Exception e) {
            n = 17576 * 10;
        }
        for (long i = 0; i < n; i++) {
            str.append((char) (Math.random() * (90 - 66) + 65));
            str.append((char) (Math.random() * (90 - 66) + 65));
            str.append((char) (Math.random() * (90 - 66) + 65));
            str.append(" ");

        }
        long time1 = System.nanoTime();
        
        int index = str.indexOf("IPN");
        int contador = 0;
        while(index >= 0){
            index = str.indexOf("IPN", index+1);
            contador++;
        }
        long time2 = System.nanoTime();
        long runtime = time2 - time1;
        double runtime_in_sec = (double) runtime / 1000000000.0 ;
        System.out.println("Total de IPN's: " + contador);
        //System.out.println(str);
        System.out.println("Tiempo de búsqueda en segundos " + runtime_in_sec);

    }

    public static String crearCadena() {
        StringBuilder univ = new StringBuilder();
        for (long i = 0; i < 3; i++) {
            univ.append((char) (Math.random() * (90 - 66) + 65));
        }
        return univ.toString();
    }
}

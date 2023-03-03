public class T06CadenasIPN {
    /**
     * @param args
     */
    public static void main(String[] args) {
        StringBuilder str = new StringBuilder();
        int n;
        try {
            n = Integer.parseInt(args[0]);
        } catch (Exception e) {
            n = (int) 26e3;
        }
        for (int i = 0; i < n; i++) {
            str.append(crearCadena() + " ");   
        }

        System.out.println(str);
        int index = str.indexOf("IPN");
        int contador = 0;
        while(index >= 0){
            index = str.indexOf("IPN", index+1);
            contador++;
        }
        System.out.println("Total de IPN's: " + contador);
    }

    public static String crearCadena() {
        StringBuilder univ = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            univ.append((char) (Math.random() * (90 - 66) + 65));
        }
        return univ.toString();
    }
}

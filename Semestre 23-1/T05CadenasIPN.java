/**
 * T05CadenasIPN
**/
public class T05CadenasIPN {
    public static void main(String[] args) {
        String universo = "";
        int n;
        try {
            n = Integer.parseInt(args[0]);
        } catch (Exception e) {
            n = (int) 26e3;
        }
        for (int i = 0; i < n; i++) {
            universo += crearCadena();
            universo += " ";
        }
        MetodSearch("IPN", universo);
    }

    private static void MetodSearch(String txt, String universo) {
      // TODO document why this method is empty
    }

    public static String crearCadena() {
        String univ = "";
        for (int i = 0; i < 3; i++) {
            char str = (char) (Math.random() * (90 - 66) + 65);
            univ += str;
        }

        return univ;
    }
}
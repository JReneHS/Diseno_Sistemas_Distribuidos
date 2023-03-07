import java.util.Vector;

/**
 * @param args
 * T05CadenasIPN
**/
public class T05CadenasIPN {
    public static void main(String[] args) {
        int n;
        try {
            n = Integer.parseInt(args[0]);
        } catch (Exception e) {
            n = 17576 * 10;
        }

        char[] universo = new char[n*4];

        for (int i = 0; i < n*4; i+=4) {
            universo[i] = (char) (Math.random() * (90 - 66) + 65);
            universo[i+1] = (char) (Math.random() * (90 - 66) + 65);
            universo[i+2] = (char) (Math.random() * (90 - 66) + 65);
            universo[i+3] = ' ';
        }
        long time1 = System.nanoTime();
        Vector<Integer> posiciones = MetodSearch("IPN", universo);
        long time2 = System.nanoTime();
        long runtime = time2 - time1;
        double runtime_in_sec = (double) runtime / 1000000000.0 ;

        System.out.println("Posiciones de aparicion:");
        for (int c : posiciones) {
            System.out.print(c + " ");
        }
        System.out.println();

        System.out.println("Tiempo de búsqueda en segundos " + runtime_in_sec);
    }

    private static Vector<Integer> MetodSearch(String txt, char[] universo) {
        int conteo = 0;
        Vector<Integer> posiciones = new Vector<>();
        int tokens = universo.length / 4;
        for (int i = 0; i < tokens; i += 1) {
            if(universo[i*4] == 'I')
                if(universo[i*4 + 1] == 'P')
                    if(universo[i*4 + 2] == 'N') {
                        conteo++;
                        posiciones.add(i);
                    }

        }
        System.out.println("Num de Apariciones de '" + txt + "': " + conteo);
        return posiciones;
    }
}

//package Clase12;
import java.util.ArrayList;
import java.util.Iterator;

class Curp {
    static int num = 10;
    static char gen = 'H';
    static int ejercicio = 1;

    public static void main(String[] args) {
        try {
            num = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("Numero de CURPs Invalido, se asigna 10 por defecto");
        }
        try {
            gen = args[1].charAt(0);
        } catch (Exception e) {
            System.out.println("Sexo no valido, se asigna 'H' por defecto (E1)");
        } finally {
            if (gen != 'H' && gen != 'h') {
                if (gen != 'M' && gen != 'm') {
                    System.out.println("Sexo no valido, se asigna 'H' por defecto (E2)");
                    gen = 'H';
                }
            }
        }
        try {
            ejercicio = Integer.parseInt(args[2]);
        } catch (Exception e) {
            System.out.println("Error de ejercicio, se asigna 1 por defecto");
        }
        if (ejercicio == 1) {
            ArrayList<String> curps = genradorCurp(num);
            filtrarCurps(curps);
        } else {
            generadorCurpsOrdenadas(num);
        }

    }

    public static String getCURP() {
        String Letra = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Numero = "0123456789";
        String Sexo = "HM";
        String Entidad[] = { "AS", "BC", "BS", "CC", "CS", "CH", "CL", "CM", "DF", "DG", "GT", "GR", "HG", "JC", "MC",
                "MN", "MS", "NT", "NL", "OC", "PL", "QT", "QR", "SP", "SL", "SR", "TC", "TL", "TS", "VZ", "YN", "ZS" };
        int indice;

        StringBuilder sb = new StringBuilder(18);

        for (int i = 1; i < 5; i++) {
            indice = (int) (Letra.length() * Math.random());
            sb.append(Letra.charAt(indice));
        }

        for (int i = 5; i < 11; i++) {
            indice = (int) (Numero.length() * Math.random());
            sb.append(Numero.charAt(indice));
        }

        indice = (int) (Sexo.length() * Math.random());
        sb.append(Sexo.charAt(indice));

        sb.append(Entidad[(int) (Math.random() * 32)]);

        for (int i = 14; i < 17; i++) {
            indice = (int) (Letra.length() * Math.random());
            sb.append(Letra.charAt(indice));
        }

        for (int i = 17; i < 19; i++) {
            indice = (int) (Numero.length() * Math.random());
            sb.append(Numero.charAt(indice));
        }
        return sb.toString();
    }

    public static ArrayList<String> genradorCurp(int num) {
        ArrayList<String> curps = new ArrayList<String>();
        for (int i = 0; i < num; i++) {
            curps.add(getCURP());
        }
        return curps;
    }

    public static void generadorCurpsOrdenadas(int num) {
        ArrayList<String> curps = new ArrayList<String>();
        for (int i = 0; i < num - 1; i++) {
            curps.add(getCURP());
            curps.sort((newCurp, curpActual) -> newCurp.substring(0, 5).compareTo(curpActual.substring(0, 5)));
            System.out.println(curps);
        }
    }

    public static void filtrarCurps(ArrayList<String> curps) {
        System.out.println("CURPs Generadas:");
        System.out.println(curps + "\n\n");
        Iterator<String> iter = curps.iterator();
        // Iterator<String> filtrado;
        while (iter.hasNext()) {
            String curr = iter.next();
            if (curr.charAt(10) == gen) {
                iter.remove();
            }
        }
        System.out.println("CURPs Filtradas");
        System.out.println(curps);
    }
}
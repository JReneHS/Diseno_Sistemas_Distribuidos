package Semestre_23_2.Proyecto03;

public class Curp {
    private String curp;

    public Curp() {
        this.curp = createCURP();
    }


    public Curp(String curp) {
        this.curp = curp;
    }

    public String getCurp() {
        return this.curp;
    }

    public int getAnio() {
        String anioString = this.curp.substring(4, 6);
        int anio = 0;
        try {
            anio = Integer.parseInt(anioString);
            
        } catch (Exception e) {
            System.out.println("Error al parsear el año de Nacimiento");
        }

        return anio;
    }

    public String getEstado() {
        return this.curp.substring(11, 13);        
    }

    public String getSexo() {
        return this.curp.substring(10, 11); 
    }

    private String createCURP() {
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

}

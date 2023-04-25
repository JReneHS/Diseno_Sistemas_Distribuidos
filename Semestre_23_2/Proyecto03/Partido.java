package Semestre_23_2.Proyecto03;

public class Partido {
    private String partido;

    public Partido() {
        this.partido = createPartido();
    }

    public Partido(String partido) {
        this.partido = partido;
    }

    public String getPartido() {
        return this.partido;
    }

    private String createPartido() {
        String[] partidos = { "Uno", "Dos", "Tres", "Cuatro", "Cinco", "Seis", "Siete"};
        return partidos[(int) (Math.random() * 7)];
    }
}

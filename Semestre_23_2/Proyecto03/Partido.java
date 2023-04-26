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
        String[] partidos = { "Partido01", "Partido02", "Partido03", "Partido04", "Partido05", "Partido06",
                "Partido07" };
        return partidos[(int) (Math.random() * 7)];
    }
}

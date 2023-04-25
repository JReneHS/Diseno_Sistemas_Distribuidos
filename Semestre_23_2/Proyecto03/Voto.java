package Semestre_23_2.Proyecto03;

public class Voto {
    private Curp curp;
    private Partido partido;

    public Voto() {
        this.curp = new Curp();
        this.partido = new Partido();
    }

    public Voto(String curp, String partido) {
        this.curp = new Curp(curp);
        this.partido = new Partido(partido);
    }

    public Voto(String[]voto) {
        this.curp = new Curp(voto[0]);
        this.partido = new Partido(voto[1]);
    }

    public String getVoto() {
        return this.curp.getCurp() + this.partido.getPartido();
    }
}

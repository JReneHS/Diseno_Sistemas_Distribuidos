public abstract class Figura{
    private int numeroLados;

    protected Figura(){
        this.numeroLados = 0;
    }

    public abstract void area();

    protected void setNumeroLados(int a){
        this.numeroLados = a;
    }
}
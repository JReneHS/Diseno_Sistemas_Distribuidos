//package Semestre_23_2.Clase11;

import java.util.Comparator;

public class SortByMagnitud implements Comparator<Coordenada> {

    @Override
    public int compare(Coordenada o1, Coordenada o2) {
        return (int)(o1.magnitud() - o2.magnitud());
    }
}
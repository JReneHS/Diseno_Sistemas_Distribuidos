public class tribonachi {
    public static void main(String[] args) {
        int a1, a2, a3, aAux;
        a1 = 0;
        a2 = 1;
        a3 = 1;
        System.out.printf("%d %d %d ", a1, a2, a3);
        for (int i = 0; i<20; i++) {
            aAux = a1 + a2 + a3;
            System.out.printf("%d ", aAux);
            a1 = a2;
            a2 = a3;
            a3 = aAux;
        }
        System.out.println();

    }
}
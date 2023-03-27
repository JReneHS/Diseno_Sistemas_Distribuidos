
public class p3 {
    public static void main(String[] args) {
        int segundos = 3725;
        // Conversion sencilla a horas
        int horas = segundos / 3600;
        // 60 seg por min. 60 min por h
        int minutos = (segundos / 60) % 60;
        // seg % 60 porque 60 seg en 1 minuto
        System.out.printf("\n\033[92;1m%d\033[0m horas, \033[92;1m%d\033[0m minutos, \033[92;1m%d\033[0m segundos\n", horas, minutos, segundos % 60);
    }

    

}

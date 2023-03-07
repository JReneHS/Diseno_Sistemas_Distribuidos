
/**
 * DemoLambda
 */

public class DemoLambda {
    public static void main(String[] args) {
        // Imprimir la lista completa pero usando la función lambda como argumento de
        // forEach
        // En la segunda línea se demuestra la utizacion de funciones intermedias de un
        // stream
        // donde la función intermedia... prácticamente no hace nada pues "filtra",
        // permitiendo a todo
        System.out.println("*** Lista de Alumnos ***");
        listaAlumnos.stream().forEach(a -> System.out.println(a));
        listaAlumnos.stream().filter(a -> true).forEach(a -> System.out.println(a));

        // La función filter solo permite que en el stream estén los datos que hagan que
        // la función lambda retorne verdadero; luego el
        // forEach itera por ese stream filtrado e imprime cada elemento
        System.out.println("\n*** Alumnos cuyo nombre empiezan con el caracter L u G ***");
        listaAlumnos.stream()
                .filter(c -> c.getApellidos().charAt(0) == 'L' || c.getApellidos().charAt(0) == 'G')
                .forEach(c -> System.out.println(c));

        System.out.println("\n**** Número de Alumnos ***");
        // número de elementos en la lista
        System.out.println(listaAlumnos.stream().count());

        // Exactamente lo mismo que en el ejemplo antepasado, pero con una condición a
        // evaluar diferente.
        System.out.println("\n**** Alumnos con nota mayor a 9 y que sean del curso PHP ***");
        // alumnos con notas mayores a 9
        listaAlumnos.stream()
                .filter(a -> a.getNota() > 9 && a.getNombreCurso().equals("PHP"))
                .forEach(p -> System.out.println(p));

        System.out.println("\n**** Imprimir los 2 primeros Alumnos de la lista ***");
        // Utilizamos la operacion intermedia limit para mostrar los primeros 2 Alumnos
        listaAlumnos.stream().limit(2).forEach(a -> System.out.println(a));

        System.out.println("\n**** Imprimir el alumno con menor edad ***");
        // Utilizamos min que recibe una funcion lambda comparadora que dependiendo de
        // si es menor, igual o mayor, obtendra el minimo
        System.out.println(listaAlumnos.stream().min((a1, a2) -> a1.getEdad() - a2.getEdad()));

        System.out.println("\n**** Imprimir el alumno con mayor edad ***");
        // Utilizamos max que recibe una funcion lambda comparadora que dependiendo si
        // es menor, igual o mayor, obtendra el maximo
        System.out.println(listaAlumnos.stream().max((a1, a2) -> a1.getEdad() - a2.getEdad()));

        System.out.println("\n**** Encontrar el primer Alumno***");
        // Utilizamos la funcion findFirst lambda para encontrar el primer alumno de la
        // lista
        System.out.println(listaAlumnos.stream().findFirst());
        // filtra todos los nombre que terminen en t y despues el forEach utiliza la
        // funcion println para mostrarlos en pantalla
        System.out.println("\n**** Alumnos en los  que los nombres de los cursos (lenguajes) que terminan en t ***");
        listaAlumnos.stream().filter(a -> a.getNombreCurso().endsWith("t")).forEach(System.out::println);
        // Filtra todos los nombre de los alumnos que contengan una a minuscula y los
        // imprime en pantalla
        System.out.println("\n**** Alumnos que tienen un curso en el que el nombre contienen la A ***");
        listaAlumnos.stream().filter(a -> a.getNombreCurso().contains("a")).forEach(System.out::println);
        // Filtra e imprime todos los nombre que tengan mas de 10 caracteres
        System.out.println("\n**** Alumnos en que su tamaño de su nombre es mayor a 10 caracteres ***");
        listaAlumnos.stream().filter(a -> a.getNombres().length() > 10).forEach(System.out::println);

        // combinar predicados
        System.out.println("\n**** Combinación de predicados ***");
        Predicate<Alumno> empiezaConJ = a -> a.getNombreCurso().startsWith("P");
        Predicate<Alumno> longitud = a -> a.getNombreCurso().length() <= 6;
        // Obtiene los alumnos en los cuales el nombre del curso empieza con el
        // caracter 'P' y la longitud sea <= a 6
        listaAlumnos.stream().filter(empiezaConJ.and(longitud)).forEach(System.out::println);
        // Creamos una nueva lista filtrando los nombres que contengan una a minuscula y
        // se hace el casteo de un Stream a una lista.
        List<Alumno> nuevaLista = listaAlumnos.stream().filter(a -> a.getNombreCurso().contains("a"))
                .collect(Collectors.toList());
        // Imprime :v
        nuevaLista.forEach(System.out::println);

    }
}
package Semestre_23_2.Proyecto03;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

public class Estadisticas {
    private static ArrayList<Voto> contVotos;
    private static int anioActual = 2023;
    private static Hashtable<String, String> estados;

    public static void main(String[] args) {
        String selector = "";
        Scanner scanner = new Scanner(System.in);

        while (selector != "S") {
            imprimirMenu();

            contVotos = new Contador().contar();

            selector = scanner.nextLine();

            switch (selector) {
                case "1":
                    conteoSexo();
                    break;

                case "2":
                    conteoEstados();
                    break;

                case "3":
                    conteoEdad(scanner);
                    break;

                case "4":
                    conteoPartidos();
                    break;

                case "s":
                    System.out.println("Hasta luego...");
                    selector = "S";
                    break;

                case "S":
                    System.out.println("Hasta luego...");
                    selector = "S";
                    break;

                default:
                    System.out.println("Ingrese un valor Válido...");
                    break;
            }

            pressEnterToContinue();

            limpiar();

        }

        scanner.close();
    }

    private static void conteoEdad(Scanner scanner) {
        int edad = -1;
        long contador = 0;

        while (edad < 18) {
            System.out.println("Ingrese Edad a Analizár mayor a 18 años:");
            try {
                edad = scanner.nextInt();
                if (edad < 18) {
                    System.out.println("Ingrese una Edad válida");
                }
            } catch (Exception e) {
                System.out.println("Ingrese un valor numérico válido");
            }
        }

        for (Voto voto : contVotos) {
            int anio = voto.getObjectCurp().getAnio();
            if (anio < anioActual - 2000) {
                anio += 2000;
            } else {
                anio += 1900;
            }
            int edadVoto = anioActual - anio;
            if (edadVoto == edad) {
                contador++;
            }
        }

        System.out.println("Votos Realizados por Ciudadanos de " + edad + " años de Edad " + contador);
    }

    private static void conteoPartidos() {
        Hashtable<String, Integer> hashtable = new Hashtable<>();

        for (Voto voto : contVotos) {
            String partido = voto.getPartido();
            if (hashtable.containsKey(partido)) {
                Integer prev = hashtable.get(partido);
                hashtable.replace(partido, prev + 1);
            } else {
                hashtable.put(partido, 1);
            }
        }

        imprimirTablaHash(hashtable);
    }

    private static void conteoEstados() {
        genEstados();

        Hashtable<String, Integer> hashtable = new Hashtable<>();

        for (Voto voto : contVotos) {
            String estadoVoto = voto.getObjectCurp().getEstado();
            String edo = estados.get(estadoVoto);

            if (hashtable.containsKey(edo)) {
                Integer prev = hashtable.get(edo);
                hashtable.replace(edo, prev + 1);
            } else {
                hashtable.put(edo, 1);
            }
        }

        imprimirTablaHash(hashtable);

    }

    private static void conteoSexo() {
        long contadorHombres = 0;
        long contadorMujeres = 0;

        for (Voto voto : contVotos) {
            String sexo = voto.getObjectCurp().getSexo();
            if (sexo.equals("M")) {
                contadorMujeres++;
            } else {
                contadorHombres++;
            }
        }

        System.out.println("Conteo de Votos por Sexo:");
        System.out.println("Hombres: " + contadorHombres);
        System.out.println("Mujeres: " + contadorMujeres);
        System.out.println("Total:  " + (contadorHombres + contadorMujeres));

    }

    private static void imprimirTablaHash(Hashtable<String, Integer> hashtable) {

        Set<String> claves = hashtable.keySet();

        for (String string : claves) {
            System.out.println(string + ": " + hashtable.get(string));
        }
    }

    private static void genEstados() {

        estados = new Hashtable<String, String>();

        estados.put("AS", "Aguascalientes");
        estados.put("BC", "Baja California");
        estados.put("BS", "Baja California Sur");
        estados.put("CC", "Campeche");
        estados.put("CS", "Chiapas");
        estados.put("CH", "Chihuahua");
        estados.put("DF", "Ciudad de México");
        estados.put("CL", "Coahuila");
        estados.put("CM", "Colima");
        estados.put("DG", "Durango");
        estados.put("GT", "Guanajuato");
        estados.put("GR", "Guerrero");
        estados.put("HG", "Hidalgo");
        estados.put("JC", "Jalisco");
        estados.put("MC", "México");
        estados.put("MN", "Michoacán");
        estados.put("MS", "Morelos");
        estados.put("NT", "Nayarit");
        estados.put("NL", "Nuevo León");
        estados.put("OC", "Oaxaca");
        estados.put("PL", "Puebla");
        estados.put("QT", "Querétaro");
        estados.put("QR", "Quintana Roo");
        estados.put("SP", "San Luis Potosí");
        estados.put("SL", "Sinaloa");
        estados.put("SR", "Sonora");
        estados.put("TC", "Tabasco");
        estados.put("TL", "Tamaulipas");
        estados.put("TS", "Tlaxcala");
        estados.put("VZ", "Veracruz");
        estados.put("YN", "Yucatán");
        estados.put("ZS", "Zacatecas");
    }

    private static void pressEnterToContinue() {
        System.out.println("Press Enter key to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
            // lul
        }
    }

    private static void imprimirMenu() {
        System.out.println("**********************************************************************************");
        System.out.println("* Sistema de Estadísticas del INE                                                *");
        System.out.println("**********************************************************************************");
        System.out.println("* Seleccione una opcion del menú                                                 *");
        System.out.println("*                                                                                *");
        System.out.println("* 1) ¿Cuántos votos totales se han realizado por sexo?                           *");
        System.out.println("* 2) ¿Cuántos votos totales se han realizado por cada estado de la república?    *");
        System.out.println("* 3) ¿Cuántos votos se han realizado por ciudadanos de x años de edad?           *");
        System.out.println("* 4) ¿Cuántos votos van por cada partido?                                        *");
        System.out.println("**********************************************************************************");
        System.out.println("* (s/S) Salir del Sistema                                                        *");
        System.out.println("**********************************************************************************");
    }

    private static void limpiar() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

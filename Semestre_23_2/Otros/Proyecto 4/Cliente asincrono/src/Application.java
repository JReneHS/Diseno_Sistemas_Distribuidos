/** Proyecto - 4
 * Hernández Velázquez Emmanuel Alejandro
 * 2019600553
 * Grupo - 4CM13
 */


/*
 *  MIT License
 *
 *  Copyright (c) 2019 Michael Pogrebinsky - Distributed Systems & Cloud Computing with Java
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

import java.util.Arrays;
import java.util.List;

public class Application {
    private static final String WORKER_ADDRESS_1 = "http://161.35.238.152:8080/task";
    private static final String entidades[] = {"AS", "BC", "BS", "CC", "CS", "CH", "CL", "CM", "DF", "DG", "GT", "GR", "HG", "JC", "MC", "MN", "MS", "NT", "NL", "OC", "PL", "QT", "QR", "SP", "SL", "SR", "TC", "TL", "TS", "VZ", "YN", "ZS"};
    private static final String nombresEntidad[] = {"Aguascalientes", "Baja California", "Baja California Sur", "Campeche", "Chiapas", "Chihuahua", "Coahuila", "Colima", "Ciudad de México", "Durango", "Guanajuato", "Guerrero", "Hidalgo", "Jalisco", "Estado de México", "Michoacán", "Morelos", "Nayarit", "Nuevo León", "Oaxaca", "Puebla", "Querétaro", "Quintana Roo", "San Luis Potosí", "Sinaloa", "Sonora", "Tabasco", "Tamaulipas", "Tlaxcala", "Veracruz", "Yucatán", "Zacatecas"};
    public static void main(String[] args) {
        // Creamos una instancia de la clase Aggregator
        // Solamente enviará 1 sola tarea a un solo servidor
        Aggregator aggregator = new Aggregator();
        aggregator.set_verbose(false);

        System.out.println("\033[1m>>>----------------- CURP Generator -----------------<<<\033[0m");
        while (true) {   
            System.out.println("\033[1m>>>--------- Escribe una opción a consultar ---------<<<\033[0m");
            System.out.println("1. CURPS por segundo generados ");
            System.out.println("2. Total de CURPs en el sistema ");
            System.out.println("3. Total de CURPs por servidor ");
            System.out.println("4. Total de CURPs de hombres y mujeres ");
            System.out.println("5. Total de CURPs por estado ");
            System.out.println("6. Bytes de memoria usados por los servidores ");

            // Leemos la entrada del usuario
            System.out.print("\n>>> ");
            String input = System.console().readLine();
            String query = "curps";
            List<String> result;
            try {
                if ( Integer.parseInt(input) < 1 || Integer.parseInt(input) > 6 ) {
                    System.out.println("Opción no válida");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Opción no válida");
                continue;
            }

            switch (input) {
                case "1":
                    query = "curps";
                    break;
                case "2":
                    query = "totalTodo";
                    break;
                case "3":
                    query = "totalSeparado";
                    break;
                case "4":
                    query = "sexo";
                    break;
                case "5":
                    System.out.println("\033[1m>>>--------- Escribe la entidad a consultar ---------<<<\033[0m");
                    System.out.println("Entidades...");
                    for (int i = 0; i < entidades.length; i+=3) {
                        System.out.print(entidades[i] + ". " + nombresEntidad[i]);
                        if (i + 1 < entidades.length) {
                            System.out.print("\t" + entidades[i + 1] + ". " + nombresEntidad[i + 1]);
                        }
                        if (i + 2 < entidades.length) {
                            System.out.print("\t" + entidades[i + 2] + ". " + nombresEntidad[i + 2] + "\n");
                        }
                    }
                    System.out.print("\n>>> ");
                    String entidadIngresada = System.console().readLine();
                    if ( Arrays.asList(entidades).contains(entidadIngresada) == false ) {
                        System.out.println("Esa entidad no existe");
                        continue;
                    }
                    query = entidadIngresada;
                    break;
                case "6":
                    query = "bytes";
                    break;
            }

            // Enviamos la tarea al servior
            result = aggregator.sendTasksToWorkers(
                Arrays.asList(WORKER_ADDRESS_1),
                Arrays.asList(query)
            );

            System.out.println("\033[1m>>>----------------- Resultado -----------------<<<\033[0m");
            String res[] = result.get(0).split(", ");
            // Mostramos el resultado de manera distinta segun la opcion elegida
            switch (input) {
                case "1":
                    System.out.println("CURPS por segundo generados >>> " + res[0]);
                    break;
                case "2":
                    System.out.println("Total de CURPs en el sistema >>> " + res[0]);
                    break;
                case "3":
                    System.out.println("Total de CURPs en el servidor 1 >>> " + res[0]);
                    System.out.println("Total de CURPs en el servidor 2 >>> " + res[1]);
                    System.out.println("Total de CURPs en el servidor 3 >>> " + res[2]);
                    int total = Integer.parseInt(res[0]) + Integer.parseInt(res[1]) + Integer.parseInt(res[2]);
                    System.out.println("Lo que da un total de... >>> " + total + " CURPs <<< en el sistema");
                    break;
                case "4":
                    System.out.println("Total de CURPs de hombres >>> " + res[0]);
                    System.out.println("Total de CURPs de mujeres >>> " + res[1]);
                    break;
                case "5":
                    System.out.println("Total de CURPs en " + nombresEntidad[Arrays.asList(entidades).indexOf(query)] + " >>> " + res[0]);
                    break;
                case "6":
                    System.out.println("Bytes de memoria usados por el servidor 1 >>> " + res[0]);
                    System.out.println("Bytes de memoria usados por el servidor 2 >>> " + res[1]);
                    System.out.println("Bytes de memoria usados por el servidor 3 >>> " + res[2]);
                    int bytes = Integer.parseInt(res[3]);
                    if (bytes >= 1000000000) {
                        System.out.println("Lo que da un total de... >>> " + bytes/1000000000 + " GB <<< en el sistema");
                    } else if (bytes >= 1000000) {
                        System.out.println("Lo que da un total de... >>> " + bytes/1000000 + " MB <<< en el sistema");
                    } else if (bytes >= 1000) {
                        System.out.println("Lo que da un total de... >>> " + bytes/1000 + " KB <<< en el sistema");
                    } else if (bytes < 1000) {
                        System.out.println("Lo que da un total de... >>> " + bytes + " bytes <<< en el sistema");
                    }
                    break;
            }
            System.out.println("");
        }

    }
}

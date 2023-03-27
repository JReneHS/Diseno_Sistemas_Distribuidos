/*
 * Proyecto 3
 * Hernández Velázquez Emmanuel Alejandro - 2019600553
 * 2019-2
 * Desarrollo de Sistemas Distribuidos
 * Grupo: 4CM13
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
import java.util.concurrent.ThreadLocalRandom;

public class Application {
    // private static final String WORKER_ADDRESS_1 = "http://localhost:8081/searchtoken";
    // private static final String WORKER_ADDRESS_2 = "http://localhost:8082/searchtoken";
    // private static final String WORKER_ADDRESS_3 = "http://localhost:8083/searchtoken";
    private static final String WORKER_ADDRESS_1 = "http://137.184.10.125:8081/searchtoken";
    private static final String WORKER_ADDRESS_2 = "http://137.184.10.125:8082/searchtoken";
    private static final String WORKER_ADDRESS_3 = "http://137.184.10.125:8083/searchtoken";

    public static void main(String[] args) {
        // Creamos una instancia de la clase Aggregator
        Aggregator aggregator = new Aggregator();
        String[] workers = { WORKER_ADDRESS_1, WORKER_ADDRESS_2, WORKER_ADDRESS_3 };
        String[] tasks = new String[26];
        // Creamos TASKS desde la A hasta la Z
        for ( int i = 0; i < 26; i++ ) {
            // String currentLetter = "Y";
            String currentLetter = String.valueOf((char) (i + 'A'));
            tasks[i] = ThreadLocalRandom.current().nextInt(1757600, 17576000) + ", " + currentLetter + currentLetter + currentLetter;
        }
        
        List<String> results = aggregator.sendTasksToWorkers (
            Arrays.asList(workers),
            Arrays.asList(tasks)
        );

        // Imprimimos la lista de resultados junto con sus respectiva tarea 
        // en el orden que terminaron
        for ( int i = 0; i < results.size(); i += 2 ) {
            System.out.printf("%s para la tarea \"%s\"\n", results.get(i), results.get(i+1));
        }
    }
}
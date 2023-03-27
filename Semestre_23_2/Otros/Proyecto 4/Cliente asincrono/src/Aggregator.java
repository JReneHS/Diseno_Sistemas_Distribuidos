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

import networking.WebClient;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

/**
 * Clase que se encarga de unir en un solo arreglo las solicitudes CompletableFuture enviadas a los servidores de manera asíncrona, mediante un WebClient, para poder
 * manejarlas como si de un simple arreglo se tratara 
 * 
 */
public class Aggregator {
    private WebClient webClient;
    private boolean is_verbose; 

    /**
     * Permite establecer si es que se quiere que el cliente imprima toda la información de las tareas enviadas, incluyendo sus estados de procesamiento y los servidores a los que se enviaron, según 
     * se vallan procesando y asignando
     * @param is_verbose - Pasarle un valor <b>verdadero</b> si se requiere imprimir toda esa información
     */
    public void set_verbose(boolean is_verbose) {
        this.is_verbose = is_verbose;
    }

    public Aggregator( boolean is_verbose ) {
        this.webClient = new WebClient();
        this.is_verbose = is_verbose;
    }

    public Aggregator() {
        this.webClient = new WebClient();
        this.is_verbose = false;
    }

    /**
     * Función que se encarga de enviar objetos serializados a varios servidores, sin ningún orden en específico, pára luego obtener las respuestas a las operaciones realizadas con dichos objetos conforme se vallan recibiendo las respuestas
     * @param workersAddresses Lista de direcciones IP de los servidores a consultar
     * @param objects Una lista con los objetos serializados a enviar
     * @return Una lista de objetos serializados con las respuestas a las operaciones realizadas con los objetos enviados
     */
    public List<byte[]> sendObjectToToWorkers(List<String> workersAddresses, List<byte[]> objects) {
        final int workerQuantity = workersAddresses.size();
        final int objectQuantity = objects.size();
        // Arreglo de los valores futuros a obtener
        CompletableFuture<byte[]>[] futures = new CompletableFuture[workerQuantity];
        // Arreglo que almancena el número de objeto asignado a ese servidor
        int[] assignedObjects = new int[workerQuantity];
        // Lista de resultados a obtener de las distintintas tareas
        List<byte[]> results = new ArrayList();
        // Ultima tarea asignada a algún servidor
        int lastAssignedObject = 0;

        for (int i = 0; i < Math.min(workerQuantity, objectQuantity); i++) {
            byte[] object = objects.get(i);
            String workerAddress = workersAddresses.get(i);

            // Enviamos un objeto a cada uno de los servidores
            futures[i] = webClient.sendObject(workerAddress, object);
            assignedObjects[i] = i;
            if (is_verbose)
                System.out.printf("Se mandó el objeto %d hacia el servidor %s\n", i, workerAddress);
            // guardamos la ultima tarea asignada
            lastAssignedObject = i;
        }


        // Comprobamos si es que, entre los servidores a los que se les asignó tareas (futures), uno ha terminado
        boolean bandera = true;
        while(bandera) {
            for(int j = 0; j < workerQuantity; j++){
                if ( futures[j].isDone() ) {
                    if (is_verbose)
                        System.out.printf("\nEl servidor %s ha terminado de operar el objeto %d\n", workersAddresses.get(j), assignedObjects[j]);
                    // Guaradamos la respuesta obtenida por este servidor que ha terminado
                    // no esperará a que termine, pues ya ha terminado
                    results.add(futures[j].join());

                    // Le asignamos una nueva tarea al servidor que ha terminado, si es que hay aún tareas que asignar
                    if ( lastAssignedObject + 1 < objectQuantity ) {
                        if (is_verbose)
                            System.out.printf("... Asignándole el objeto... %d\n", lastAssignedObject + 1, objects.get(lastAssignedObject + 1));
                        byte[] object = objects.get(lastAssignedObject + 1);
                        futures[j] = webClient.sendObject(workersAddresses.get(j), object);
                        // Actualizamos la tarea asignada 
                        assignedObjects[j] = lastAssignedObject + 1;
                        // Y también actualizamos cuál es la ultima tarea asignada
                        lastAssignedObject ++;
                    } else {
                        // De lo contrario, terminamos el ciclo y ya no asignamos tareas
                        if (is_verbose)
                            System.out.println("... Ya no hay más tareas que asignar\n");
                        // Y también indicamos que ya no tiene ningún objeto asignado
                        // de esa marea, podermos saber que servidor o servidores no han terminado
                        assignedObjects[j] = -1;
                        bandera = false;
                    } 
                }
            }
        }

        // Y solo esperamos a que se terminen los sevidores que no lo han hecho
        for ( int k = 0; k < workerQuantity; k++ ) {
            if ( assignedObjects[k] != -1 ) {
                if (is_verbose)
                    System.out.printf("Esperando a que el servidor %s termine de operar el objeto %d\n\n", workersAddresses.get(k), assignedObjects[k] );
                // Este join si esperará hasta que el servidor actual termine (si es bloqueante)
                results.add(futures[k].join());
            }
        }
        if ( is_verbose )
            System.out.println("...Se han operado todos los objetos!!\n");

        return results;
    }


    /**
     * Función que toma una lista de direcciones IP y una lista de tareas representadas por los datos a enviar en cada una de ellas, para distribuírlas entre los 
     * servidores, obteniendo  una respuesta de cada uno de ellos, una vez que todas las tareas han terminado.
     * @param workersAddresses Lista de direcciones IP de los servidores a consultar
     * @param tasks Lista de cadenas que representan los datos a enviar a los servidores mediante POST
     * @return Una lista de cadenas que son las respuestas obtenidas de los servidores, luego de terminar de ejecutar cada una de las tareas.
     */
    public List<String> sendTasksToWorkers(List<String> workersAddresses, List<String> tasks) {
        final int workerQuantity = workersAddresses.size();
        final int taskQuantity = tasks.size();
        // Arreglo de los valores futuros a obtener
        CompletableFuture<String>[] futures = new CompletableFuture[workerQuantity];
        // Arreglo que almancena el número de tarea asignado a ese servidor
        int[] assignedTasks = new int[workerQuantity];
        // Lista de resultados a obtener de las distintintas tareas
        List<String> results = new ArrayList<String>();
        // Ultima tarea asignada a algún servidor
        int lastAssignedTask = 0;
        for (int i = 0; i < Math.min(workerQuantity, taskQuantity); i++) {
            String task = tasks.get(i);
            String workerAddress = workersAddresses.get(i);
            // Enviamos una tarea a cada uno de los servidores
            byte[] requestPayload = task.getBytes();
            futures[i] = webClient.sendTask(workerAddress, requestPayload);
            assignedTasks[i] = i;
            if ( is_verbose )
                System.out.printf("Enviada la tarea %d: \"%s\"; hacia el servidor %s\n", i, task, workerAddress);
            // guardamos la ultima tarea asignada
            lastAssignedTask = i;
        }

        // A cada servidor que halla terimnado se le asigna una nueva tarea, si es que hay tareas que asignar
        boolean bandera = true;
        while(bandera) {
            for(int j = 0; j < workerQuantity; j++) {
                if ( futures[j].isDone() ) {
                    if ( is_verbose )
                        System.out.printf("\nEl servidor %s ha terminado con la tarea %d: \"%s\"\n", workersAddresses.get(j), assignedTasks[j], tasks.get( assignedTasks[j] ));
                    // Guaradamos la respuesta obtenida por este servidor que ha terminado
                    // fture.join() no esperará a que termine, pues ya ha terminado (no es bloqueante por ahora)
                    results.add(futures[j].join());
                    // Y también guardamos la tarea que se terminó
                    results.add(tasks.get( assignedTasks[j] ));
                    // Le asignamos una nueva tarea al servidor que ha terminado, si es que hay aún tareas que asignar
                    if ( lastAssignedTask + 1 < taskQuantity ) {
                        if ( is_verbose )
                            System.out.printf("... Asignándole la tarea %d: %s\n", lastAssignedTask + 1, tasks.get(lastAssignedTask + 1));
                        byte[] requestPayload = tasks.get(lastAssignedTask + 1).getBytes();
                        futures[j] = webClient.sendTask( workersAddresses.get(j), requestPayload);
                        // Actualizamos la tarea asignada a este servidor
                        assignedTasks[j] = lastAssignedTask + 1;
                        // Y también actualizamos cuál es la ultima tarea asignada
                        lastAssignedTask ++;
                    } else {
                        // De lo contrario, terminamos el ciclo y ya no asignamos tareas
                        if ( is_verbose )
                            System.out.println("... Ya no hay más tareas que asignar\n");
                        // Y también indicamos que ya no tiene ninguna tarea asignada
                        // de esa marea, podermos saber que servidor o servidores no han terminado
                        assignedTasks[j] = -1;
                        bandera = false;
                    } 
                }
            }
        }
        // Y solo esperamos a que se terminen los sevidores que no lo han hecho
        for ( int k = 0; k < workerQuantity; k++ ) {
            if ( assignedTasks[k] != -1 ) {
                if ( is_verbose )
                    System.out.printf("Esperando a que termine el servidor %s con la tarea %d: \"%s\"\n\n", workersAddresses.get(k), assignedTasks[k], tasks.get( assignedTasks[k] ));
                // Este join si esperará hasta que el servidor actual termine (si es bloqueante)
                results.add(futures[k].join());
                // Y también guardamos la tarea que se terminó
                results.add(tasks.get( assignedTasks[k] ));
            }
        }
        if ( is_verbose )
            System.out.println("...Todas las tareas han sido completadas!!\n");
        return results;
    }
}

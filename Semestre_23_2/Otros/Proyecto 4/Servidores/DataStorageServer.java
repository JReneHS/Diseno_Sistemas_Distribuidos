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
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class DataStorageServer {
    // Los datos a ser guardados
    private static ArrayList<String> storage = new ArrayList<>();
    // Los nombres que reciben nuestros endpoint
    private static final String STORAGE_ENDPOINT = "/storage";
    private static final String RETRIEVE_ENDPOINT = "/retrieve";
    private static final String STATUS_ENDPOINT = "/status";
    // El puerto en el que escuchará el servidor HTTP
    private final int port;
    // La instancia del servidor HTTP que recibirá las solicitudes mediante un
    // socket TCP
    private HttpServer server;

    // ------------------------------------<<<<- Main ->>>>-------------------------------------
    // -----------------------------------------------------------------------------------------
    public static void main(String[] args) {
        int serverPort = 8080;
        if (args.length == 1) {
            serverPort = Integer.parseInt(args[0]);
        }
        DataStorageServer webServer = new DataStorageServer(serverPort);
        webServer.startServer();
        System.out.println("Servidor escuchando en el puerto " + serverPort);
    }
    // -----------------------------------------------------------------------------------------
    
    // Constructor, solamente le asigna un puerto al servidor para ser ejecutado
    public DataStorageServer(int port) {
        this.port = port;
    }

    public void startServer() {
        // Creamos la instancia del servidor HTTP con nuestra propia IP
        try {
            this.server = HttpServer.create(new InetSocketAddress(port), 0);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        // Cada endpoint de Java debe de tener su propio contexto HTTP
        HttpContext statusContext = server.createContext(STATUS_ENDPOINT);
        HttpContext storageContext = server.createContext(STORAGE_ENDPOINT);
        HttpContext retrieveContext = server.createContext(RETRIEVE_ENDPOINT);
        // Cada contexto HTTP tendrá una función asociada para manejar sus conexiones
        statusContext.setHandler(this::handleStatusCheckRequest);
        storageContext.setHandler(this::handleStorageRequest);
        retrieveContext.setHandler(this::handleRetrieveRequest);
        // El servidor HTTP creado dentro del bloque try anterior
        // puede recibir varias solicitudes, así que tiene un ThreadPool que usará para
        // manejarlas
        server.setExecutor(Executors.newFixedThreadPool(8));
        server.start();
    }

    // Función que maneja las solicitudes enviadas al endpoint /storage
    private void handleStorageRequest(HttpExchange exchange) throws IOException {
        // Cerramos la conexió si es que los datos no se enviaron por el método POST
        if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {
            exchange.close();
            return;
        }

        // Guardamos la CURP recibida en el array local y salimos si hay 10 CURPS
        String curp = new String(exchange.getRequestBody().readAllBytes());
        storage.add(curp);
        // FOR TESTING PURPOSES ONLY
        // if (storage.size() >= 10) {
        //     System.out.println("Se han recibido 10 CURPS, el servidor se cerrará");
        //     for (String data : storage) {
        //         System.out.println(data);
        //     }
        //     server.stop(0);
        //     System.exit(0);
        // }
        // Enviamos confirmación de "OK" solo para cerrar esta solicitud
        sendResponse( "OK".getBytes(), exchange );

    }

    // Función que maneja las solicitudes enviadas al endpoint /retrieve
    private void handleRetrieveRequest(HttpExchange exchange) throws IOException {
        
        // Enviamos una acción diferente según el mensaje recibido
        String responseMessage = "No se ha recibido un mensaje válido";
        String queryMessage = new String ( exchange.getRequestBody().readAllBytes() );

        // Registros totales en este servidor
        if ( queryMessage.equals("total") ) {
            responseMessage = String.valueOf(storage.size());
        } else if ( queryMessage.equals("bytes") ) {
        // Cantidad de bytes que ocupan los registros de este servidor, según la codificación por defecto
            byte[] stringBytes = storage.get(0).getBytes();
            responseMessage = String.valueOf( stringBytes.length * storage.size() );
        } else if ( queryMessage.equals("sexoooo") ) {
        // Cantidad de varones y mujueres en este servidor
        // Hacemos un filtro de la lista
        // Pero primero hacemos una copia de la lista para que no se muera el servidor al leerla al ser modificada
            
            List<String> listMales = new ArrayList<String>(storage);
            List<String> listFemales = new ArrayList<String>(listMales);
            
            listMales = storage.stream()
            .filter( curp -> (curp.charAt(10) == 'H') )
            .collect(Collectors.toList());
            
            listFemales = storage.stream()
                .filter( curp -> (curp.charAt(10) == 'M') )
                .collect(Collectors.toList());

            responseMessage = String.valueOf(listMales.size()) + ", " + String.valueOf(listFemales.size());
        // Cantidad de registros de una entidad en específico
        } else if ( queryMessage.matches("entidad, [A-Z]{2}") ) {
            String entidad = queryMessage.substring(9);
            List<String> filteredList = storage.stream()
                .filter( curp -> curp.substring(11,13).equals(entidad) )
                .collect(Collectors.toList());

            System.out.println("Entidad buscada: " + entidad);
            
            responseMessage = String.valueOf(filteredList.size());
        }
        sendResponse(responseMessage.getBytes(), exchange);
    }

    // Función que maneja las solicitudes enviadas al endpoint /status
    private void handleStatusCheckRequest(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("get")) {
            exchange.close();
            return;
        }
        String responseMessage = "El servidor está vivo\n";
        sendResponse(responseMessage.getBytes(), exchange);
    }

    // Función de conveniencia que se utiliza para enviar una respuesta mediante una
    // conversación HTTP
    private void sendResponse(byte[] responseBytes, HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, responseBytes.length);
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(responseBytes);
        outputStream.flush();
        outputStream.close();
        exchange.close();
    }

}

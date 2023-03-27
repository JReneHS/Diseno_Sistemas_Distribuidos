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
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.*;
import java.io.*;

public class Ejercicio_2 {
    // Los nombres que reciben nuestros endpoint
    private static final String TASK_ENDPOINT = "/task";
    private static final String STATUS_ENDPOINT = "/status";
    private static final String TOKEN_ENDPOINT = "/searchtoken";
    // El puerto en el que escuchará el servidor HTTP
    private final int port;
    // La instancia del servidor HTTP que recibirá las solicitudes mediante un socket TCP
    private HttpServer server;
    // --------------------------------------- Main --------------------------------------------
    public static void main(String[] args) {
        int serverPort = 8080;
        if (args.length == 1) {
            serverPort = Integer.parseInt(args[0]);
        }
        Ejercicio_2 webServer = new Ejercicio_2(serverPort);
        webServer.startServer();
        System.out.println("Servidor escuchando en el puerto " + serverPort);
    }
    // -----------------------------------------------------------------------------------------
    public Ejercicio_2(int port) {
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
        HttpContext taskContext = server.createContext(TASK_ENDPOINT);
        HttpContext tokenContext = server.createContext(TOKEN_ENDPOINT);
        // Cada contexto HTTP tendrá una función asociada para manejar sus conexiones
        statusContext.setHandler(this::handleStatusCheckRequest);
        taskContext.setHandler(this::handleTaskRequest);
        tokenContext.setHandler(this::handleTokenRequest);
        // El servidor HTTP creado dentro del bloque try anterior
        // puede recibir varias solicitudes, así que tiene un ThreadPool que usará para manejarlas
        server.setExecutor(Executors.newFixedThreadPool(8));
        server.start();
    }
    // Función que maneja las solicitudes enviadas al endpoint /task
    private void handleTaskRequest(HttpExchange exchange) throws IOException {
        // Cerramos la conexió si es que los datos no se enviaron por el método POST
        if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {
            exchange.close();
            return;
        }
        // Obtenemos todos los datos de cabecera para definir conducta especial para los headers personalizados
        Headers headers = exchange.getRequestHeaders();
        // Buscamos la cabecera "X-Test" en los headers obtenidos y sólo enviamos una respuesta si contiene el valor "true"
        if (headers.containsKey("X-Test") && headers.get("X-Test").get(0).equalsIgnoreCase("true")) {
            String dummyResponse = "123\n";
            sendResponse(dummyResponse.getBytes(), exchange);
            return;
        }
        // Buscamos la cabecera "X-Debug" en los headers obtenidos y sólo enviamos una respuesta si contiene el valor "true"
        boolean isDebugMode = false;
        if (headers.containsKey("X-Debug") && headers.get("X-Debug").get(0).equalsIgnoreCase("true")) {
            isDebugMode = true;
        }
        // Realizamos el cálculo mediante núneros grandes de los valores enviados por POST hacie el endpoint
        // y tomamos el tiempo que tardó realizar dicha operación
        long startTime = System.nanoTime();
            // Obtenemos el cuerpo de la solicitud
            byte[] requestBytes = exchange.getRequestBody().readAllBytes();
            // Obtenemos la respuesta calculada
            byte[] responseBytes = calculateResponse(requestBytes);
        long finishTime = System.nanoTime();
        // Enviamos un header personalizado con el tiempo que tardó el calculo de la respuesta si es existía el header Debug
        if (isDebugMode) {
            long t = finishTime - startTime;
            long seconds = TimeUnit.NANOSECONDS.toSeconds(t);
            long milis = TimeUnit.NANOSECONDS.toMillis(t);
            milis = milis - seconds*1000;
            String debugMessage = String.format("La Operacion tardo %d nanosegundos = %d segundos %d milisegundos", t, seconds, milis);
            exchange.getResponseHeaders().put("X-Debug-Info", Arrays.asList(debugMessage));
        }
        sendResponse(responseBytes, exchange);
    }
    // Función que ejecuta la multiplicación de nuestro enspoint /task, usando numeros muy grandes
    private byte[] calculateResponse(byte[] requestBytes) {
        String bodyString = new String(requestBytes);
        String[] stringNumbers = bodyString.split(",");
        BigInteger result = BigInteger.ONE;
        for (String number : stringNumbers) {
            BigInteger bigInteger = new BigInteger(number);
            result = result.multiply(bigInteger);
        }
        return String.format("El resultado de la multiplicación es %s\n", result).getBytes();
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
    // Función de conveniencia que se utiliza para enviar una respuesta mediante una conversación HTTP
    private void sendResponse(byte[] responseBytes, HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, responseBytes.length);
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(responseBytes);
        outputStream.flush();
        outputStream.close();
        exchange.close();
    }
    // Función para manejar las solicitudes enviadas al endpoint /searchtoken
    private void handleTokenRequest( HttpExchange exchange ) throws IOException {
        
        // Obtenemos las cabeceras de la solicitud
        Headers headers = exchange.getRequestHeaders();
        // Buscamos la cabecera "X-Debug" en los headers obtenidos y sólo enviamos una respuesta si contiene el valor "true"
        boolean isDebugMode = false;
        if (headers.containsKey("X-Debug") && headers.get("X-Debug").get(0).equalsIgnoreCase("true")) {
            isDebugMode = true;
        }
        // Realizamos el cálculo mediante núneros grandes de los valores enviados por POST hacie el endpoint
        // y tomamos el tiempo que tardó realizar dicha operación
        long startTime = System.nanoTime();
            // Obtenemos el cuerpo de la solicitud
            byte[] requestBytes = exchange.getRequestBody().readAllBytes();
            // Y obtenemos los datos para buscar los token
            String bodyString = new String(requestBytes);
            String[] datos = bodyString.split(",\\s+");
            if ( datos.length != 2 ){
                sendResponse( "Los datos de la solicitud únicamente deben ser: <Numero_Tokens>, <Token_a_buscar>, con el Token a buscar siendo sólo de 3 letras".getBytes(), exchange);
                return;
            }
            // Verificamos que los datos sean correctos
            String searchToken = new String();
            int tokenNumber = 0;
            try{
                tokenNumber = Integer.parseInt(datos[0]);
                searchToken = datos[1];
                if ( tokenNumber <= 0 )
                    throw new NumberFormatException();
                if ( searchToken.length() < 1 || searchToken.length() > 3 ) {
                    sendResponse( "El <Token_a_buscar> sólo debe ser de tamaño 3".getBytes(), exchange);
                    return;
                }
            } catch (NumberFormatException e) {
                sendResponse( ("El numero de la cadenota de tokens en la que se buscará \"" + searchToken + "\" no es válido").getBytes(), exchange);
            }
            // Obtenemos la respuesta calculada
            int response = searchToken( tokenNumber, searchToken );
        long finishTime = System.nanoTime();
        // Enviamos un header personalizado con el tiempo que tardó el calculo de la respuesta si es existía el header Debug
        if (isDebugMode) {
            long t = finishTime - startTime;
            long seconds = TimeUnit.NANOSECONDS.toSeconds(t);
            long milis = TimeUnit.NANOSECONDS.toMillis(t);
            milis = milis - seconds*1000;
            String debugMessage = String.format("La Operacion tardo %d nanosegundos = %d segundos %d milisegundos", t, seconds, milis);
            exchange.getResponseHeaders().put("X-Debug-Info", Arrays.asList(debugMessage));
        }
        sendResponse( ("Se encontraron " + response + " ocurrencias de " + searchToken).getBytes(), exchange);
        
        
    }
    // FUnción que crea tokens de 3 letras mayúsculas al azar
    private String crearCadena() {
        String aux = "";
        for (int i=0; i<3; i++) {
            aux = aux + (char) (Math.random() * (90 - 66) + 65);
        }
        return aux.toString();
    }
    private int searchToken( int tokenNumber, String searchToken ) {
        int n, cont=0;
        n = tokenNumber;
        StringBuilder str = new StringBuilder();
        
        // Creamos una cadenota con tokens de 3 letras al azar
        for (int i=0; i<n; i++) {
            str.append(crearCadena() + " ");   
        }
        System.out.println();
        
        // Si encontramos algún índice, entonces aumentamos la cuenta por 1
        int index = str.indexOf(searchToken);
        if ( index >= 0)
            cont++;
        // Mientras existan ocurrencias de la palabra "searchToken", entonces, le indicamos a indexOf que comeince a buscar después de la última ocurrencia
        while(index >= 0){
            index = str.indexOf("IPN", index+1);
            cont++;
        }
        System.out.println("Total = " + cont);
        // Regresamos la cantidad de tokens encontradas
        return cont;
    }
}

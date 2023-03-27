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
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Version;
import java.time.Duration;
import java.util.concurrent.Executors;

public class MainServer {
    // Cantidad de CURPs generador por segundo
    private final int CURPS_PER_SECOND;
    // Los nombres que reciben nuestros endpoint
    private static final String TASK_ENDPOINT = "/task";
    private static final String STATUS_ENDPOINT = "/status";
    // El puerto en el que escuchará el servidor HTTP
    private final int port;
    // La instancia del servidor HTTP que recibirá las solicitudes mediante un
    // socket TCP
    private HttpServer server;
    private String[] storageServers;

    // ------------------------------------<<<<- Main ->>>>-------------------------------------
    // -----------------------------------------------------------------------------------------
    public static void main(String[] args) {
        int serverPort = 8080;
        int curpsPerSecond = 100;
        if (args.length == 2) {
            serverPort = Integer.parseInt(args[0]);
            curpsPerSecond = Integer.parseInt(args[1]);
        } else {
            System.out.println("Uso: java MainServer <puerto> <curpsPorSegundo>");
            System.out.println("Ejemplo: java MainServer 8080 100");
            System.exit(-1);
        }
        MainServer webServer = new MainServer(serverPort, curpsPerSecond);
        webServer.startServer();
        System.out.println("Servidor escuchando en el puerto " + serverPort);
    }
    // -----------------------------------------------------------------------------------------
    
    // Constructor, solamente le asigna un puerto al servidor para ser ejecutado
    public MainServer(int port, int curpsPerSecond) {
        this.port = port;
        this.CURPS_PER_SECOND = curpsPerSecond;
        this.storageServers = new String[]{
            "159.223.193.123:8081", "159.223.192.71:8082", "159.223.192.251:8083"
        };
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
        // Cada contexto HTTP tendrá una función asociada para manejar sus conexiones
        statusContext.setHandler(this::handleStatusCheckRequest);
        taskContext.setHandler(this::handleTaskRequest);
        // El servidor HTTP creado dentro del bloque try anterior
        // puede recibir varias solicitudes, así que tiene un ThreadPool que usará para
        // manejarlas
        server.setExecutor(Executors.newFixedThreadPool(8));

        // Iniciamos la generación de CURPS
        CURPGenerator generator = new CURPGenerator(CURPS_PER_SECOND);
        generator.setStorageServers(
            storageServers[0],
            storageServers[1],
            storageServers[2]
        );
        Thread generatorThread = new Thread( generator );
        generatorThread.start();

        server.start();
    }

    // Función que maneja las solicitudes enviadas al endpoint /task
    private void handleTaskRequest(HttpExchange exchange) throws IOException {

        // Ignoramos el tipo de meódo de la solicitud del cliente

        // Obtenemos todos los datos de cabecera para definir conducta especial para los
        // headers personalizados
        Headers headers = exchange.getRequestHeaders();
        // Buscamos la cabecera "X-Test" en los headers obtenidos y sólo enviamos una
        // respuesta si contiene el valor "true"
        if (headers.containsKey("X-Test") && headers.get("X-Test").get(0).equalsIgnoreCase("true")) {
            String dummyResponse = "This is a dummy response, dummy!\n";
            sendResponse(dummyResponse.getBytes(), exchange);
            return;
        }

        // Usamos exactamente el mismo mensaje que espera el servidor de almacenamiento con algunos extras
        // segun el cliente solicite información sobre los CURPS
        String queryMessage = new String( exchange.getRequestBody().readAllBytes() );
        String responseMessage = new String();
        
        if ( queryMessage.equals("totalTodo") ) {
            int total = 0;
            // Hacemo una petición a todos los servidores y enviamos un total de los 3
            for ( String ip : storageServers ) {
                total += Integer.parseInt(sendToStorageServer("total", ip, "retrieve"));
            }
            responseMessage = String.valueOf(total);
        } else if ( queryMessage.equals("totalSeparado") ) {
            // Respondemos con un total de cada servidor
            for ( String ip : storageServers ) {
                responseMessage += sendToStorageServer("total", ip, "retrieve") + ", ";
            }
            responseMessage = responseMessage.substring(0, responseMessage.length() - 2);
        } else if ( queryMessage.equals("bytes") ) {
            // Respondemos con la cantidad total de bytes de toda la BD y tambien separada
            int total = 0;
            for ( String ip : storageServers ) {
                String bytes = sendToStorageServer("bytes", ip, "retrieve");
                total += Integer.parseInt(bytes);
                responseMessage += bytes + ", ";
            }
            responseMessage += total;
        } else if ( queryMessage.equals("sexo") ) {
            // Obtenemos el total de hombres y mujeres en toa la BD
            int totalHombres = 0;
            int totalMujeres = 0;
            for ( String ip : storageServers ) {
                String[] sexos = sendToStorageServer("sexoooo", ip, "retrieve").split(", ");
                totalHombres += Integer.parseInt(sexos[0]);
                totalMujeres += Integer.parseInt(sexos[1]);
            }
            responseMessage = totalHombres + ", " + totalMujeres;
        } else if ( queryMessage.equals("curps") ) {
            responseMessage = String.valueOf(CURPS_PER_SECOND);
        } else {
            // De lo contrario, se trata de una busqueda de entidad
            String entidad = queryMessage;
            int total = 0;
            for ( String ip : storageServers ) {
                total += Integer.parseInt(sendToStorageServer("entidad, " + entidad, ip, "retrieve"));
            }
            responseMessage = String.valueOf(total);
        }

        // Enviamos la respuesta esperada al cliente, según su solicitud
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

    public String sendToStorageServer( String message, String ipAddress, String endpoint ) {
        // Creamos el cliente Http para enviar las solicitudes
        HttpClient client = HttpClient.newBuilder()
            .version(Version.HTTP_1_1)
            .connectTimeout(Duration.ofMillis(5000))
            .build();
        
        // Solicitud a ser llenada durante el ciclo infinito
        HttpRequest request;
        try {
            request = HttpRequest.newBuilder()
                .uri(URI.create("http://" + ipAddress + "/" + endpoint))
                .POST(HttpRequest.BodyPublishers.ofString(message))
                .build();
            
            String response;
            // Enviamos la solicitud y esperamos la respuesta
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            response = httpResponse.body();

            return response;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error";
        }
    }

    /**
     * Clase cuyo propósito es el de generar CURPs constantemente y enviarlos 
     * a los servidores de almacenamiento conforma se vallan creando, de manera secuencial.
     */
    class CURPGenerator implements Runnable{
        public final int CURPS_PER_SECOND;
        private String[] storageServers;

        public CURPGenerator(int curpsPerSecond) {
            this.CURPS_PER_SECOND = curpsPerSecond;
        }

        public void setStorageServers( String server1, String server2, String server3 ) {
            this.storageServers = new String[3];
            this.storageServers[0] = server1;
            this.storageServers[1] = server2;
            this.storageServers[2] = server3;
        }

        public String generateRandomCURP() {
            String Letra = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String Numero = "0123456789";
            String Sexo = "HM";
            String Entidad[] = {"AS", "BC", "BS", "CC", "CS", "CH", "CL", "CM", "DF", "DG", "GT", "GR", "HG", "JC", "MC", "MN", "MS", "NT", "NL", "OC", "PL", "QT", "QR", "SP", "SL", "SR", "TC", "TL", "TS", "VZ", "YN", "ZS"};
            int indice;
            
            StringBuilder sb = new StringBuilder(18);
            
            for (int i = 1; i < 5; i++) {
                indice = (int) (Letra.length()* Math.random());
                sb.append(Letra.charAt(indice));        
            }
            
            for (int i = 5; i < 11; i++) {
                indice = (int) (Numero.length()* Math.random());
                sb.append(Numero.charAt(indice));        
            }
    
            indice = (int) (Sexo.length()* Math.random());
            sb.append(Sexo.charAt(indice));        
            
            sb.append(Entidad[(int)(Math.random()*32)]);
    
            for (int i = 14; i < 17; i++) {
                indice = (int) (Letra.length()* Math.random());
                sb.append(Letra.charAt(indice));        
            }
    
            for (int i = 17; i < 19; i++) {
                indice = (int) (Numero.length()* Math.random());
                sb.append(Numero.charAt(indice));        
            }
            
            return sb.toString();
        }

        public void fillStorageServers() {
            // Creamos el cliente Http para enviar las solicitudes
            HttpClient client = HttpClient.newBuilder()
                .version(Version.HTTP_1_1)
                .connectTimeout(Duration.ofMillis(1500))
                .build();
    
            // Solicitud a ser llenada durante el ciclo infinito
            HttpRequest request;
            
            // Curps generados
            String[] curps = new String[3];

            while (true) {
                try {
                    Thread.sleep(1000 / CURPS_PER_SECOND);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                curps[0] = generateRandomCURP();
                curps[1] = generateRandomCURP();
                curps[2] = generateRandomCURP();

                for ( int i = 0; i < storageServers.length; i++ ) {
                    String storageIP = storageServers[i];
                    String curp = curps[i];
                    try {
                        request = HttpRequest.newBuilder()
                            .uri(URI.create("http://" + storageIP + "/storage"))
                            .POST(HttpRequest.BodyPublishers.ofString(curp))
                            .build();
                        client.send(request, HttpResponse.BodyHandlers.ofString());
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    
        public void run() {
            fillStorageServers();
        }
    }

}

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.time.Duration;
public class HttpClientSynchronous {
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    public static void main(String[] args) throws IOException, InterruptedException {
        // URI  -  Uniform Resource Identifier
        //  Permiten nombrar a un recurso sin necesariamente decir como encontrarlo
        // URL  -  Uniform Resource Locator
        //  Permiten encontrar a un recurso. Son un sub-conjunto de las URI
        HttpRequest request = HttpRequest.newBuilder()
                // Se mandan datos en la solicitud mediante un BodyPublisher que contenga los datos a mandar
                // siguiendo el modelo Publisher-Subscriber, donde el Subscriber es el servidor y nosotros publicamos los datos a él
                .POST( BodyPublishers.ofString("17576000, IPN") )
                .uri(URI.create("http://137.184.10.125:8082/searchtoken"))
                .setHeader("X-Debug", "true") // add request header
                .setHeader("User-Agent", "Java 19 HttpClient Bot") // add request header
                .build();
        // Se envía la solicitud de manera síncrona (esperemaos a que se envíe la solicitud y se reciba al respuesta)
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        // print response headers
        HttpHeaders headers = response.headers();
        System.out.println("Cabeceras HTTP: \n");
        headers.map().forEach((k, v) -> System.out.println(k + " : " + v));
        // print status code
        System.out.println("\nStatus: " + response.statusCode());
        // print response body
        System.out.println("\nCuerpo de la respuesta: \n" + response.body());
    }
}

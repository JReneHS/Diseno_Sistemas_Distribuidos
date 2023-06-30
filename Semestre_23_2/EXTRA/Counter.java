package Semestre_23_2.EXTRA;



public class Counter implements Runnable{
    private Long contador;
    private String storageServer;

    public Counter(Long cont){
        this.contador = cont;
    }

    public void incremento() {
        this.contador += 1;
    }

    public void setStorageServers( String server) {
        this.storageServer = server;
    }

    public void fillStorageServers() {
        // Creamos el cliente Http para enviar las solicitudes
        HttpClient client = HttpClient.newBuilder()
            .version(Version.HTTP_1_1)
            .connectTimeout(Duration.ofMillis(2600))
            .build();

        // Solicitud a ser llenada durante el ciclo infinito
        HttpRequest request;

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            incremento();

            String storageIP = storageServer;
            try {
                request = HttpRequest.newBuilder()
                    .uri(URI.create("http://" + storageIP + "/storage"))
                    .POST(HttpRequest.BodyPublishers.ofString(Long.toString(contador)))
                    .build();
                client.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
            fillStorageServers();
    }
}

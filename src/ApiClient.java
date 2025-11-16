import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {

    private final HttpClient client;
    private static final String API_KEY = "TU_API_KEY_AQUI";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public ApiClient() {
        this.client = HttpClient.newHttpClient();
    }

    public double obtenerTasa(String from, String to) {
        try {
            String url = BASE_URL + API_KEY + "/pair/" + from + "/" + to;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            String body = response.body();

            // Extraer conversion_rate SIN librerías externas
            int index = body.indexOf("conversion_rate");
            if (index == -1) return -1;

            int start = body.indexOf(":", index) + 1;
            int end = body.indexOf(",", start);
            String rateStr = body.substring(start, end).trim();

            return Double.parseDouble(rateStr);

        } catch (Exception e) {
            System.out.println("Error obteniendo datos de la API: " + e.getMessage());
            return -1;
        }
    }
}
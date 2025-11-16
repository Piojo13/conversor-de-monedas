import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ApiClient {

    private final HttpClient client;
    private final Gson gson = new Gson();

    private static final String API_KEY = "a744e835b239367b9f8b1945";
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

            // Convertimos toda la respuesta JSON a un objeto
            JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);

            // Obtenemos la propiedad "conversion_rate"
            return jsonResponse.get("conversion_rate").getAsDouble();

        } catch (Exception e) {
            System.out.println("Error obteniendo datos de la API: " + e.getMessage());
            return -1;
        }
    }
}

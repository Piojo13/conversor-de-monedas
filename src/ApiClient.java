import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ApiClient {

    private static final String API_KEY = "TU_API_KEY_AQUI";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    private final HttpClient client = HttpClient.newHttpClient();

    public double obtenerTasa(String from, String to) {
        try {
            String url = BASE_URL + API_KEY + "/pair/" + from + "/" + to;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parseo del JSON usando JsonParser
            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();

            // Devuelve la tasa numérica
            return json.get("conversion_rate").getAsDouble();

        } catch (Exception e) {
            System.out.println("Error al obtener datos de la API: " + e.getMessage());
            return -1;
        }
    }
}
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ApiClient {

    private static final String API_KEY = "a744e835b239367b9f8b1945";
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

            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();

            if (!json.has("base_code") || !json.has("target_code")) {
                System.out.println("La respuesta de la API no contiene currency codes.");
                return -1;
            }

            String baseCode = json.get("base_code").getAsString();
            String targetCode = json.get("target_code").getAsString();

            if (!baseCode.equals(from) || !targetCode.equals(to)) {
                System.out.println(" La API devolvió códigos de moneda inesperados.");
                System.out.println("   Esperado: " + from + " → " + to);
                System.out.println("   Recibido: " + baseCode + " → " + targetCode);
                return -1;
            }

            return json.get("conversion_rate").getAsDouble();

        } catch (Exception e) {
            System.out.println("Error al obtener datos de la API: " + e.getMessage());
            return -1;
        }
    }
}
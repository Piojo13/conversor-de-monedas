public class Conversor {

    private final ApiClient apiClient;

    public Conversor(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public double convertir(String from, String to, double monto) {
        double tasa = apiClient.obtenerTasa(from, to);

        if (tasa <= 0) {
            System.out.println("No se pudo obtener la tasa de conversión.");
            return -1;
        }

        return monto * tasa;
    }
}
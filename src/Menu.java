import java.util.Scanner;

public class Menu {

    private final Conversor conversor;
    private final Scanner scanner = new Scanner(System.in);

    public Menu(Conversor conversor) {
        this.conversor = conversor;
    }

    public void iniciar() {
        int opcion;

        System.out.println("=== CONVERSOR DE MONEDAS ===");

        do {
            mostrarOpciones();
            opcion = scanner.nextInt();

            if (opcion >= 1 && opcion <= 6) {
                procesarConversion(opcion);
            }

        } while (opcion != 7);

        System.out.println("¡Gracias por usar el conversor!");
    }

    private void mostrarOpciones() {
        System.out.println("\nSeleccione una opción:");
        System.out.println("1) Dólar → Peso Argentino");
        System.out.println("2) Peso Argentino → Dólar");
        System.out.println("3) Dólar → Real Brasileño");
        System.out.println("4) Real Brasileño → Dólar");
        System.out.println("5) Dólar → Peso Colombiano");
        System.out.println("6) Peso Colombiano → Dólar");
        System.out.println("7) Salir");
        System.out.print("Opción: ");
    }

    private void procesarConversion(int opcion) {
        System.out.print("Ingrese el monto a convertir: ");
        double monto = scanner.nextDouble();

        String from = "";
        String to = "";

        switch (opcion) {
            case 1 -> { from = "USD"; to = "ARS"; }
            case 2 -> { from = "ARS"; to = "USD"; }
            case 3 -> { from = "USD"; to = "BRL"; }
            case 4 -> { from = "BRL"; to = "USD"; }
            case 5 -> { from = "USD"; to = "COP"; }
            case 6 -> { from = "COP"; to = "USD"; }
        }

        double resultado = conversor.convertir(from, to, monto);

        if (resultado != -1) {
            System.out.println("Resultado: " + monto + " " + from + " = " + resultado + " " + to);
        }
    }
}
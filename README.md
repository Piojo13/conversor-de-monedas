# Conversor de Monedas -- Proyecto Java

Este proyecto es una aplicación de consola en Java que permite convertir
montos entre distintas monedas utilizando la API pública
**ExchangeRate-API**.

Incluye: - Uso de **HttpClient**, **HttpRequest**, **HttpResponse**. -
Parseo JSON mediante **Gson**, usando `JsonParser` y `JsonObject`. -
Validación mediante atributo `currency_code` de la respuesta JSON. -
Menú interactivo con 7 opciones de conversión.

## Estructura del Proyecto

### **Main**

Crea las instancias principales: - `ApiClient` - `Conversor` - `Menu`

Y ejecuta el programa con `menu.iniciar()`.

### **Menu**

Presenta las opciones de conversión, lee input del usuario y llama al
conversor.\
Traduce la opción seleccionada a códigos de moneda.

### **Conversor**

Recibe moneda origen, destino y el monto.\
Llama a `ApiClient.obtenerTasa()` y calcula el resultado.

### **ApiClient**

Se conecta a la API externa usando HTTP GET.\
Parsea el JSON y obtiene: - `base_code` - `target_code` -
`conversion_rate`

Incluye filtrado explícito para validar que los currency codes devueltos
coincidan con lo solicitado.

## Ejecución

Compilar y ejecutar:

    javac *.java
    java Main

## Requisitos

-   Java 11 o superior
-   Dependencia Gson (`com.google.code.gson:gson`)

## API

Se usa:

    https://v6.exchangerate-api.com/v6/TU_API/pair/{from}/{to}

La respuesta contiene: - `base_code` - `target_code` - `conversion_rate`

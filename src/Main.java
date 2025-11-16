public class Main {
    public static void main(String[] args){
        ApiClient apiClient = new ApiClient();
        Conversor conversor = new Conversor(apiClient);
        Menu menu = new Menu(conversor);
        
        menu.iniciar();
    }
}

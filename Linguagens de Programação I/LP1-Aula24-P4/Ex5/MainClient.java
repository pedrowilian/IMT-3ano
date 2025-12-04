import java.io.IOException;

public class MainClient {
    public static void main(String[] args) throws IOException {

        InterfaceChat chatScreen = new InterfaceChat();
        chatScreen.iniciar(new ClienteBatepapo());

    }
}

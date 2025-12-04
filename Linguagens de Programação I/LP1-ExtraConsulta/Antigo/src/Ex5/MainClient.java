package Ex5;

import java.io.IOException;

public class MainClient {
    public static void main(String[] args) {

        try {
            ChatScreen chatScreen = new ChatScreen();
            chatScreen.start(new Ex5ClienteBatepapo());
        } catch (IOException e) {
            System.out.println("Erro ao iniciar cliente: " + e.getMessage());
        }

    }
}

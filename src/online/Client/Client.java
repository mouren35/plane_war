package online.Client;

import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        ClientFrame f = new ClientFrame();
        ClientPanel p = new ClientPanel(f);
        f.setVisible(true);
    }
}

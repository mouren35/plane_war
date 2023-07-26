package online.Client;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import static java.lang.System.exit;

public class ClientPanel extends JPanel {
    ClientPanel(ClientFrame f) throws IOException {
        Socket socket = new Socket("192.168.10.2", 8099);
        OutputStream os = socket.getOutputStream();

        KeyAdapter ka = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int KeyCode = e.getKeyCode();
                if (KeyCode == 27) {
                    try {
                        os.close();
                        socket.close();
                        exit(0);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }
                try {
                    os.write(KeyCode);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        };
        f.addKeyListener(ka);
    }
}

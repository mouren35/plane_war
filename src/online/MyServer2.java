package online;

import game.airplane.ClientHero;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer2 {
    private final int PORT = 8099;

    public void listen() throws IOException {
//        创建一个服务器，给它分配一个端口
        ServerSocket SS = new ServerSocket(PORT);
        Socket socket1 = SS.accept();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream is1 = socket1.getInputStream();
                    OutputStream os2 = socket1.getOutputStream();
                    while (true) {
                        byte[] by = new byte[1024];
                        int n = is1.read(by);
                        if (n == -1)
                            break;
                        System.out.println("我是服务器   accept1发来消息" + by[0]);
                        if (by[0] == 38) {
                            ClientHero.y -= 10;
                        } else if (by[0] == 40) {
                            ClientHero.y += 10;
                        } else if (by[0] == 39) {
                            ClientHero.x += 10;
                        } else if (by[0] == 37) {
                            ClientHero.x -= 10;
                        }
                        os2.write(by, 0, n);

                    }
                    is1.close();
                    os2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }
}
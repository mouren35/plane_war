package 窗口;

import javax.swing.*;

public class frame extends JFrame {
    public frame() {
        setTitle("全民飞机大战");
        setSize(512, 726);
        setResizable(false);
//        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

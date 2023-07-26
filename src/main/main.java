package main;

import Login_Register.Login_Register;
import online.MyServer2;
import game.music.Music;
import 窗口.frame;
import 窗口.panel;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;


public class main {
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        Login_Register login_register = new Login_Register();
        try {
            new MyServer2().listen();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}

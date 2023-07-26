package game.music;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {

    public void playMusic() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        String path="D:\\飞机大战\\src\\img\\game_music.wav";
        File file = new File(path);
        AudioInputStream ais = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(ais);
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }


}

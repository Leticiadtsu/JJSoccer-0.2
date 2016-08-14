/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package controller.managers;

import java.applet.AudioClip;
import java.util.HashMap;

/**
 *
 * @author Andre Chateaubriand
 */
public class AudioPlayer implements Runnable {

    private HashMap<String, AudioClip> audios;
    private HashMap<String, AudioClip> audios2;

    private AudioPlayer() {
    }

    public static AudioPlayer getInstance() {
        return AudioPlayerHolder.INSTANCE;
    }

    @Override
    public void run() {

    }

    private static class AudioPlayerHolder {

        private static final AudioPlayer INSTANCE = new AudioPlayer();
    }
}

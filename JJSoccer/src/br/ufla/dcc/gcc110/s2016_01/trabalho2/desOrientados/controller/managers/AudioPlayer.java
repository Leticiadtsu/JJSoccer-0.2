package br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.controller.managers;

import java.applet.AudioClip;
import java.util.HashMap;

@Deprecated
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

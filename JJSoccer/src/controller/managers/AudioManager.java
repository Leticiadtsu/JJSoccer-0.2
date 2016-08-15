package controller.managers;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

/**
 * Classe única, que realiza o carregamento do áudio. Sua inicialização só é permitido pelo
 * método getInstance, não permitindo criar outro objeto desta classe, utilizando o padrão Singleton.
 */
public class AudioManager {

    static private AudioManager instance;
    private HashMap<String, AudioClip> clips;

    private AudioManager() {
        clips = new HashMap<String, AudioClip>();
    }

    /**
     * Método responsável por permitir apenas uma instância da classe.
     * @return instancia da classe.
     */
    static public AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }

    /**
     * Carrega o audio que será executado. O arquivo de audio deve estar presente no pacote
     * "resources".
     * @param fileName nome do arquivo de audio.
     * @return audio.
     * @throws IOException 
     */
    public AudioClip loadAudio(String fileName) throws IOException {
        URL url = getClass().getResource("/resources/" + fileName);
        if (url == null) {
            throw new RuntimeException("O áudio /" + fileName
                    + " não foi encontrado.");
        } else {
            if (clips.containsKey(fileName)) {
                return clips.get(fileName);
            } else {
                AudioClip clip =
                        Applet.newAudioClip(getClass().getResource("/resources/"
                        + fileName));
                clips.put(fileName, clip);
                
                return clip;
            }
        }
    }
}
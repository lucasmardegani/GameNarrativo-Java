package gamenarrativo;

import javax.sound.sampled.*;
import java.io.File;

public class ReprodutorSom {
    private Clip clip;
    private Thread musicaThread;
    
    public void tocarMusicaFundo(String caminhoArquivo) {
        // Cria uma Thread separada para a música
        musicaThread = new Thread(() -> {
            try {
                File arquivo = new File(caminhoArquivo);
                
                // Carrega o áudio
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(arquivo);
                
                // Pega o formato original
                AudioFormat formatoBase = audioStream.getFormat();
                
                // Cria um formato compatível com 16 bits (buffer maior)
                AudioFormat formatoCompativel = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    formatoBase.getSampleRate(),
                    16,
                    formatoBase.getChannels(),
                    formatoBase.getChannels() * 2,
                    formatoBase.getSampleRate(),
                    false
                );
                
                // Converte para o formato compatível
                AudioInputStream audioConvertido = AudioSystem.getAudioInputStream(
                    formatoCompativel, 
                    audioStream
                );
                
                // Cria o Clip e abre com o áudio convertido
                clip = AudioSystem.getClip();
                clip.open(audioConvertido);
                
                // Toca em loop infinito
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
                
                System.out.println("Música tocando: " + caminhoArquivo);
                
            } catch (Exception e) {
                System.out.println("Erro ao tocar música: " + e.getMessage());
            }
        });
        
        // Inicia a Thread
        musicaThread.start();
    }
    
    public void pararMusica() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
        if (musicaThread != null) {
            musicaThread.interrupt();
        }
        System.out.println("Música parada");
    }
}
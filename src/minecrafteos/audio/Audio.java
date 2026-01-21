/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minecrafteos.audio;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
/**
 *
 * @author dam2_alu13@inf.ald
 */
public class Audio {
    private Clip clip;
    private FloatControl volumeControl;

    public void play(String resourcePath) {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(
                getClass().getResource(resourcePath)
            );
            clip = AudioSystem.getClip();
            clip.open(audio);

            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                setVolume(0.6f); // volumen inicial (50%)
            }

            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void playLoop(String resourcePath) {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(
                getClass().getResource(resourcePath)
            );
            clip = AudioSystem.getClip();
            clip.open(audio);

            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                setVolume(1.0f);
            }

            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param volume valor entre 0.0 (silencio) y 1.0 (máximo)
     */
    public void setVolume(float volume) {
        if (volumeControl == null) return;

        float min = volumeControl.getMinimum(); // normalmente -80.0
        float max = volumeControl.getMaximum(); // normalmente 6.0
        float gain = min + (max - min) * volume;
        volumeControl.setValue(gain);
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }
}

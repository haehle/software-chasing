import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

public class BackgroundMusic extends JFrame {
    
    private static final long serialVersionUID = 1L;
    private static final String MUSIC_FILE_LOBBY = "Music/battle-of-the-dragons-8037.wav"; 
    private static final String MUSIC_FILE_GAME = "Music/nightmare-on-imaginationland-8040.wav"; 

    public volatile boolean running, looping;
    private Clip clip;

    public BackgroundMusic(String type) {
        
        looping = running = false;
        try {
            clip = AudioSystem.getClip();
            if(type == "game"){
                clip.open(AudioSystem.getAudioInputStream(new File(MUSIC_FILE_GAME)));
            } else{
                clip.open(AudioSystem.getAudioInputStream(new File(MUSIC_FILE_LOBBY)));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    public void play() {
        running = true;
        clip.setFramePosition(0);
        new Thread(new Runnable(){
            public void run(){
                clip.start();
                while(true){
                    if(clip.getMicrosecondPosition() == clip.getMicrosecondLength())
                        break;
                    if(!running)
                        break;
                }
            }
        }).start();
    }

    
    public void pause(){
        running = false;
        clip.stop();
    }

    public void resume(){
        running = true;
        new Thread(new Runnable(){
            public void run(){
                clip.start();
                while(true){
                    if(clip.getMicrosecondPosition() == clip.getMicrosecondLength())
                        break;
                    if(!running)
                        break;
                }
            }
        }).start();
    }

    public void stop(){
        running = false;
        looping = false;
        clip.setFramePosition(0);
    }

    
    public void loop(){
        looping = true;
        new Thread(new Runnable(){
            public void run(){
                clip.start();
                while(looping){
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                }
            }
        }).start();
    }
    public boolean isResumed(){
        if(clip.getMicrosecondPosition()>0)
            return true;
        return false;
    }
}
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

public class BackgroundMusic extends JFrame {
    
    private static final long serialVersionUID = 1L;
    private static final String MUSIC_FILE_LOBBY = "Music/battle-of-the-dragons-8037.wav"; 
    private static final String MUSIC_FILE_GAME = "Music/nightmare-on-imaginationland-8040.wav"; 

    public BackgroundMusic(String type) {
        // create the frame
        super("My App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        
        // play the background music
        playMusic(type);
    }

    private void playMusic(String type) {
        try {
            File musicFile = null;
            if(type == "game"){
                musicFile = new File(MUSIC_FILE_GAME);
            } else{
                musicFile = new File(MUSIC_FILE_LOBBY);    
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // loop the music indefinitely
        } catch (Exception e) {
            System.out.println("Error playing music: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        BackgroundMusic app = new BackgroundMusic("lobby");
        app.setVisible(true);
    }
}
import javax.swing.JFrame;

public class CharacterFrame {
    public CharacterFrame(int type){
        JFrame frame = new JFrame("Character Panel");
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new CharacterPanel(type));
        frame.setVisible(true);
    }
}

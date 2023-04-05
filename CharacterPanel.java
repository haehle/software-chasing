import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CharacterPanel extends JPanel {
    private int characterX = 100;
    private int characterY = 100;
    private int characterWidth = 64;
    private int characterHeight = 64;
    private Map<Integer, Image> characterImages;
    private int currentImageIndex = 0;
    private boolean upKeyPressed = false;
    private boolean downKeyPressed = false;
    private boolean leftKeyPressed = false;
    private boolean rightKeyPressed = false;
    
    public CharacterPanel(int type) {
        setFocusable(true);
        characterImages = new HashMap<>();
        if(type == 1){
            // Initialize the map of character images
            characterImages.put(KeyEvent.VK_UP, loadImage("char1-right.png"));
            characterImages.put(KeyEvent.VK_DOWN, loadImage("char1-right.png"));
            characterImages.put(KeyEvent.VK_LEFT, loadImage("char1-left.png"));
            characterImages.put(KeyEvent.VK_RIGHT, loadImage("char1-right.png"));
        } else if(type == 2){            
            // Initialize the map of character images
            characterImages.put(KeyEvent.VK_UP, loadImage("char2-right.png"));
            characterImages.put(KeyEvent.VK_DOWN, loadImage("char2-right.png"));
            characterImages.put(KeyEvent.VK_LEFT, loadImage("char2-left.png"));
            characterImages.put(KeyEvent.VK_RIGHT, loadImage("char2-right.png"));
        } else{
            // Initialize the map of character images
            characterImages.put(KeyEvent.VK_UP, loadImage("char3-right.png"));
            characterImages.put(KeyEvent.VK_DOWN, loadImage("char3-right.png"));
            characterImages.put(KeyEvent.VK_LEFT, loadImage("char3-left.png"));
            characterImages.put(KeyEvent.VK_RIGHT, loadImage("char3-right.png"));
        }
        
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        upKeyPressed = true;
                        currentImageIndex = KeyEvent.VK_UP;
                        break;
                    case KeyEvent.VK_DOWN:
                        downKeyPressed = true;
                        currentImageIndex = KeyEvent.VK_DOWN;
                        break;
                    case KeyEvent.VK_LEFT:
                        leftKeyPressed = true;
                        currentImageIndex = KeyEvent.VK_LEFT;
                        break;
                    case KeyEvent.VK_RIGHT:
                        rightKeyPressed = true;
                        currentImageIndex = KeyEvent.VK_RIGHT;
                        break;
                    default:
                        break;
                }
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        upKeyPressed = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        downKeyPressed = false;
                        break;
                    case KeyEvent.VK_LEFT:
                        leftKeyPressed = false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        rightKeyPressed = false;
                        break;
                    default:
                        break;
                }
            }
        });
        
        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (upKeyPressed) {
                    characterY -= 2;
                }
                if (downKeyPressed) {
                    characterY += 2;
                }
                if (leftKeyPressed) {
                    characterX -= 2;
                }
                if (rightKeyPressed) {
                    characterX += 2;
                }
                repaint();
            }
        });
        timer.start();
    }
    
    
        private Image loadImage(String filename) {
            // Load an image from the resources directory
            // Assumes that the image file is located in the resources directory
            // under the same package as this class
            Image image = null;
            try {
                image = ImageIO.read(new File(filename));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return image;
        }
    
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            // Draw the current character image at the current position
            Image currentImage = characterImages.get(currentImageIndex);
            if (currentImage != null) {
                g.drawImage(currentImage, characterX, characterY, characterWidth, characterHeight, null);
            }
        }

        public static void main(String[] args) {
            JFrame frame = new JFrame("Character Panel");
            frame.setSize(640, 480);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new CharacterPanel(2));
            frame.setVisible(true);
        }
}



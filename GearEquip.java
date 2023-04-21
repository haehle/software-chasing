import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class GearEquip {

    Player player;
    private JFrame window;
    Container con;
    JPanel menu;
    JPanel titlePanel;


    private JButton equipButton1, equipButton2, equipButton3, unequipButton;
    private JLabel equipLabel1, equipLabel2, equipLabel3, equippedLabel, equippedImageLabel;
    private ImageIcon equipImage1, equipImage2, equipImage3, equippedImage;
    

    public Player equip(Player player) throws IOException{
        this.player = player;
         // Create a new JFrame
         JFrame frame = new JFrame("Label and Image Example");
        
         // Set the layout to GridBagLayout
         frame.setLayout(new GridBagLayout());
         
         // Create the three labels
         JLabel label1 = new JLabel("+10 Health (200 coins)");
         JLabel label2 = new JLabel("+10 Speed (100 coins)");
         JLabel label3 = new JLabel("+10 Stamina (50 coins)");
         
         // Create the three image icons
         ImageIcon icon1 = new ImageIcon("Images/Soap.png");
         Image image1 = icon1.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
         icon1 = new ImageIcon(image1);
         ImageIcon icon2 = new ImageIcon("Images/Laptop.png");
         Image image2 = icon2.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
         icon2 = new ImageIcon(image2);
         ImageIcon icon3 = new ImageIcon("Images/Glasses.png");
         Image image3 = icon3.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
         icon3 = new ImageIcon(image3);
         
         // Create the three buttons and associate each with a label and image
         JButton button1 = new JButton(icon1);
         button1.setText("Soap");

        button1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                player.setGold(player.getGold() - 200);
                Gear Soap = new Gear("Laptop", 10, 0,    0);
                player.buyGear(Soap); 
            }
            
        });

        JButton button2 = new JButton(icon2);
        button2.setText("Laptop");
        button2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                player.setGold(player.getGold() - 10);
                Gear Laptop = new Gear("Laptop", 0, 0,    10); 
                player.buyGear(Laptop);
            }
            
        });

         JButton button3 = new JButton(icon3);
         button3.setText("Glasses");
         button3.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                player.setGold(player.getGold() - 50);
                Gear glasses = new Gear("Glasses", 0, 10, 0);   
                player.buyGear(glasses);             
            }
            
        });
         
         // Add the buttons and labels to the JFrame
         GridBagConstraints c = new GridBagConstraints();
         c.gridx = 0;
         c.gridy = 0;
         frame.add(button1, c);
         c.gridx = 1;
         frame.add(button2, c);
         c.gridx = 2;
         frame.add(button3, c);
         c.gridy = 1;
         c.gridx = 0;
         frame.add(label1, c);
         c.gridx = 1;
         frame.add(label2, c);
         c.gridx = 2;
         frame.add(label3, c);
         
         // Set the size of the JFrame and make it visible
         frame.setSize(600, 400);
         frame.setVisible(true);

         Button equip = new Button("Equip Gear");
         equip.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                equipScreen();  
                System.out.println("HP:" + player.getHp());
                System.out.println("Speed:" + player.getSpeed());
                System.out.println("Stamina:" + player.getStamina());       
            }
            
        });

        frame.add(equip);
        return player;

    }

    public void equipScreen(){

        JFrame frame = new JFrame();

        ImageIcon icon1 = new ImageIcon("Images/Soap.png");
        Image image1 = icon1.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        icon1 = new ImageIcon(image1);
        ImageIcon icon2 = new ImageIcon("Images/Laptop.png");
        Image image2 = icon2.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        icon2 = new ImageIcon(image2);
        ImageIcon icon3 = new ImageIcon("Images/Glasses.png");
        Image image3 = icon3.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        icon3 = new ImageIcon(image3);
        
        JPanel equipmentPanel = new JPanel(new GridLayout(1, 3));
        equipImage1 = icon1;
        equipLabel1 = new JLabel("Soap");
        equipButton1 = new JButton("Equip");
        equipButton1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Gear Soap = new Gear("Soap", 10, 0,    0);                
                player.equipGear(Soap);
                player.setHp(player.getHp() + 10);
                System.out.println("HP:" + player.getHp());
                System.out.println("Speed:" + player.getSpeed());
                System.out.println("Stamina:" + player.getStamina());
                frame.dispose();
                equipScreen();
                //save to db
            }
            
        });
        JPanel equipPanel1 = new JPanel(new BorderLayout());
        equipPanel1.add(new JLabel(equipImage1), BorderLayout.CENTER);
        equipPanel1.add(equipLabel1, BorderLayout.NORTH);
        equipPanel1.add(equipButton1, BorderLayout.SOUTH);
        equipmentPanel.add(equipPanel1);
        equipImage2 = icon2;
        equipLabel2 = new JLabel("Laptop");
        equipButton2 = new JButton("Equip");
        equipButton2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Gear Laptop = new Gear("Laptop", 0, 0,    10);
                player.equipGear(Laptop);
                player.setSpeed(player.getSpeed() + 10);
                System.out.println("HP:" + player.getHp());
                System.out.println("Speed:" + player.getSpeed());
                System.out.println("Stamina:" + player.getStamina());
                frame.dispose();
                equipScreen();
                //save to db
            }
            
        });
        JPanel equipPanel2 = new JPanel(new BorderLayout());
        equipPanel2.add(new JLabel(equipImage2), BorderLayout.CENTER);
        equipPanel2.add(equipLabel2, BorderLayout.NORTH);
        equipPanel2.add(equipButton2, BorderLayout.SOUTH);
        equipmentPanel.add(equipPanel2);
        equipImage3 = icon3;
        equipLabel3 = new JLabel("Glasses");
        equipButton3 = new JButton("Equip");
        equipButton3.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Gear Glasses = new Gear("Glasses", 0, 10,    0);
                player.equipGear(Glasses);
                player.setStamina(player.getStamina() + 10);
                System.out.println("HP:" + player.getHp());
                System.out.println("Speed:" + player.getSpeed());
                System.out.println("Stamina:" + player.getStamina());
                frame.dispose();
                equipScreen();
                //save to db
            }
            
        });
        JPanel equipPanel3 = new JPanel(new BorderLayout());
        equipPanel3.add(new JLabel(equipImage3), BorderLayout.CENTER);
        equipPanel3.add(equipLabel3, BorderLayout.NORTH);
        equipPanel3.add(equipButton3, BorderLayout.SOUTH);
        equipmentPanel.add(equipPanel3);
        
        // Set up the equipped section
        JPanel equippedPanel = new JPanel(new BorderLayout());
        if(player.getEquippedGear() != null){
            if(player.getEquippedGear().getGearName() == "Soap"){
                equippedImage = icon1;
            } else if(player.getEquippedGear().getGearName() == "Laptop"){
                equippedImage = icon2;
            } else if(player.getEquippedGear().getGearName() == "Glasses"){
                equippedImage = icon3;
            }
        }
        
        equippedLabel = new JLabel("Equipped");
        unequipButton = new JButton("Unequip");
        unequipButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Gear equipped = player.getEquippedGear();
                player.setHp(player.getHp() - equipped.getHpBoost());
                player.setStamina(player.getStamina() - equipped.getStaminaBoost());
                player.setSpeed(player.getSpeed() - equipped.getSpeedBoost());
                player.unequipGear();
                System.out.println("HP:" + player.getHp());
                System.out.println("Speed:" + player.getSpeed());
                System.out.println("Stamina:" + player.getStamina());
                frame.dispose();
                try {
                    player = equip(player);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            
        });
        equippedImageLabel = new JLabel(equippedImage);
        equippedPanel.add(equippedImageLabel, BorderLayout.CENTER);
        equippedPanel.add(equippedLabel, BorderLayout.NORTH);
        equippedPanel.add(unequipButton, BorderLayout.SOUTH);
        
        // Add the equipment and equipped sections to the frame
        JPanel mainPanel = new JPanel(new GridLayout(2, 1));
        mainPanel.add(equipmentPanel);
        mainPanel.add(equippedPanel);
        frame.add(mainPanel);
        
        // Set up the frame
        frame.setTitle("Equipment");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    
    

    public static void main(String[] args) throws IOException {
        //player = new GearEquip();
    }
}

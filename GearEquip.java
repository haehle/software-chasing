import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;


public class GearEquip {

    private JFrame window;
    Container con;
    JPanel menu;
    JPanel titlePanel;
    
    public GearEquip(){
        //connect to database
        window = new JFrame();
        window.setSize(800,600);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        window.setVisible(true);
        con = window.getContentPane();

        titlePanel = new JPanel();
        titlePanel.setBounds(100, 100, 600, 100);
        titlePanel.setBackground(Color.black);
        JLabel titleName = new JLabel("Software Chasing: \n A BoilerMaker Saga");
        titleName.setForeground(Color.white);
        titleName.setFont(new Font("Times New Roman", Font.PLAIN, 35));;
        titlePanel.add(titleName);
        con.add(titlePanel);
    }

    public Player equipGear(Player player, Gear gear){
        player.setStamina(player.getStamina() + gear.getStaminaBoost());
        player.setHp(player.getHp() + gear.getHpBoost());
        player.setSpeed(player.getSpeed() + gear.getSpeedBoost());

        return player;
    }
}

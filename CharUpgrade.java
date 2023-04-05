import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CharUpgrade {
    Player player;
    JFrame window;
    Container con;
    JPanel upgradePanel;
    JPanel currentStatsPanel;
    BackgroundMusic bm;

    public CharUpgrade(Profile profile, Player player){
        bm = new BackgroundMusic("lobby");
        bm.play();
        this.player = player;
        window = new JFrame();
        window.setSize(800,600);
        //window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                window.dispose();
                bm.pause();
                // exitProcedure();
            }
        });
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        window.setVisible(true);
        con = window.getContentPane();

        upgradePanel = new JPanel();
        upgradePanel.setBounds(100, 100, 600, 90);
        upgradePanel.setBackground(Color.black);
        JLabel upgradePanelName = new JLabel("Upgrade your Player");
        upgradePanelName.setForeground(Color.white);
        upgradePanelName.setFont(new Font("Times New Roman", Font.PLAIN, 35));;
        upgradePanel.add(upgradePanelName);
        con.add(upgradePanel);


        currentStatsPanel = new JPanel();
        currentStatsPanel.setBounds(200, 400, 400, 100);
        currentStatsPanel.setBackground(Color.black);
        
        JLabel currentgoldPanelName = new JLabel("Gold: " + player.getGold());
        currentgoldPanelName.setForeground(Color.white);
        currentgoldPanelName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        JLabel currentstaminaPanelName = new JLabel("Stamina: " + player.getStamina());
        currentstaminaPanelName.setForeground(Color.white);
        currentstaminaPanelName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        JLabel currenthealthPanelName = new JLabel("Health: " + player.getHp());
        currenthealthPanelName.setForeground(Color.white);
        currenthealthPanelName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        JLabel currentSpeedPanelName = new JLabel("Speed: " + player.getSpeed());
        currentSpeedPanelName.setForeground(Color.white);
        currentSpeedPanelName.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        currentStatsPanel.add(currentgoldPanelName);
        currentStatsPanel.add(currentstaminaPanelName);
        currentStatsPanel.add(currenthealthPanelName);
        currentStatsPanel.add(currentSpeedPanelName);
        
        con.add(currentStatsPanel);

        JButton IncreaseStamina = new JButton();
        IncreaseStamina.setBounds(250, 250, 300, 40);
        IncreaseStamina.setBackground(Color.white);
        IncreaseStamina.setText("Increase Stamina(50 Gold)");
        IncreaseStamina.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                player.setGold(player.getGold() - 50);
                player.setStamina(player.getStamina() + 5);
                dbConnection.updatePlayer(player);
                dbConnection.logout(player.getUsername());
                window.dispose();
                bm.pause();
                new CharUpgrade(profile, player);
            }
            
        });

        con.add(IncreaseStamina);

        JButton IncreaseHealth = new JButton();
        IncreaseHealth.setBounds(250, 180, 300, 40);
        IncreaseHealth.setBackground(Color.white);
        IncreaseHealth.setText("Increase Health(80 Gold)");
        IncreaseHealth.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                player.setGold(player.getGold() - 80);
                player.setHp(player.getHp() + 5);
                dbConnection.updatePlayer(player);
                dbConnection.logout(player.getUsername());
                window.dispose();
                bm.pause();
                new CharUpgrade(profile, player);
            }
            
        });

        con.add(IncreaseHealth);

        JButton IncreaseSpeed = new JButton();
        IncreaseSpeed.setBounds(250, 320, 300, 40);
        IncreaseSpeed.setBackground(Color.white);
        IncreaseSpeed.setText("Increase Speed(25 Gold)");
        IncreaseSpeed.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                player.setGold(player.getGold() - 25);
                player.setSpeed(player.getSpeed() + 5);
                dbConnection.updatePlayer(player);
                dbConnection.logout(player.getUsername());
                window.dispose();
                bm.pause();
                new CharUpgrade(profile, player);
            }
            
        });

        con.add(IncreaseSpeed);

        JButton backButton = new JButton();
        backButton.setBounds(250, 460, 300, 40);
        backButton.setBackground(Color.white);
        backButton.setText("Back");
        backButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                dbConnection.updatePlayer(player);
                window.dispose();
                bm.pause();
                new CharSelect(profile);
            }
            
        });

        con.add(backButton);

        this.player = player;
    }
    public static void main(String[] args) {
        Profile profile = dbConnection.login("test", "Password123");
        Player player = dbConnection.getPlayers("test")[0];
        new CharUpgrade(profile,  player);
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {

    private static JFrame frame;
    private JPanel panel;

    private JButton backButton;

    private JLabel prompt, prompt2, prompt3, image;

    private ActionListener listener;

    //private ActionEvent e;

    private String username;
    private String name;
    private int type;
    private int[] location;
    private int hp;
    private int maxHP;
    private int speed;
    private int stamina;
    private int maxStamina;
    private int level;
    private int levelXP;
    private int initialLevelXP;
    private String[] skills;
    private ArrayList<String> abilities;

    public Player(String username, String name, int type) {
        this.username = username;
        this.name = name;
        this.type = type;
        this.location = new int[]{0, 0};
        this.level = 1;
        this.levelXP = 100;
        this.initialLevelXP = 100;
        if (type == 1) {
            this.hp = 100;
            this.maxHP = 100;
            this.speed = 15;
            this.stamina = 20;
            this.maxStamina = 20;
            this.skills = new String[]{};
            this.abilities = new ArrayList<>();
        } else if (type == 2) {
            this.hp = 60;
            this.maxHP = 100;
            this.speed = 25;
            this.stamina = 30;
            this.maxStamina = 30;
            this.skills = new String[]{};
            this.abilities = new ArrayList<>();;
        } else if (type == 3) {
            this.hp = 200;
            this.maxHP = 200;
            this.speed = 5;
            this.stamina = 5;
            this.maxStamina = 5;
            this.skills = new String[]{};
            this.abilities = new ArrayList<>();;
        }
    }

    //Constructor used to initialize Player object from database information
    public Player(String username, String name, int type, int locationX, int locationY, int hp, int maxHP,
                  int speed, int stamina, int maxStamina, int level, int levelXP, int initialLevelXP) {
        this.username = username;
        this.name = name;
        this.type = type;
        this.location = new int[]{locationX, locationY};
        this.level = level;
        this.levelXP = levelXP;
        this.initialLevelXP = initialLevelXP;
        this.hp = hp;
        this.maxHP = maxHP;
        this.speed = speed;
        this.stamina = stamina;
        this.maxStamina = maxStamina;

        //Need to get these from alternate table
        this.skills = new String[]{};
        this.abilities = new ArrayList<>();;
    }

        public String getUsername () {
            return this.username;
        }

        public void setUsername (String name){
            this.username = username;
        }

        public String getName () {
            return this.name;
        }

        public void setName (String name){
            this.name = name;
        }

        public int getType () {
            return this.type;
        }

        public void setType ( int type){
            this.type = type;
        }

        public int[] getLocation () {
            return this.location;
        }

        public void setLocation ( int[] location){
            this.location = location;
        }

        public int getHp () {
            return this.hp;
        }

        public void setHp ( int hp){
            this.hp = hp;
        }

        public int getMaxHP () {
            return this.maxHP;
        }

        public void setMaxHP ( int maxHP){
            this.maxHP = maxHP;
        }

        public int getSpeed () {
            return this.speed;
        }

        public void setSpeed ( int speed){
            this.speed = speed;
        }

        public int getStamina () {
            return this.stamina;
        }

        public void setStamina ( int stamina){
            this.stamina = stamina;
        }

        public int getMaxStamina () {
            return this.maxStamina;
        }

        public void setMaxStamina ( int maxStamina){
            this.maxStamina = maxStamina;
        }

        public int getLevel () {
            return this.level;
        }

        public void setLevel ( int level){
            this.level = level;
        }

        public int getLevelXP () {
            return this.levelXP;
        }

        public void setLevelXP ( int levelXP ){
            this.levelXP = levelXP;
        }

        public int getInitialLevelXP () {
            return this.initialLevelXP;
        }

        public void setInitialLevelXP ( int initialLevelXP ){
            this.initialLevelXP = initialLevelXP;
        }


        public String[] getSkills () {
            return this.skills;
        }

        public void addNewSkills (String[]skills){
            List<String> list = new ArrayList<String>(Arrays.asList(skills));
            list.addAll(Arrays.asList(this.skills));
            String[] updatedSkills = (String[]) list.toArray();
            this.skills = updatedSkills;
        }

        public ArrayList<String> getAbilities () {
            return this.abilities;
        }

        public void setAbilities (ArrayList<String> set_abilities){
            this.abilities = set_abilities;
        }

        // This function will add a new ability and display it for the user
        public void addAbilities(String ability) {

            if (!this.abilities.contains(ability)) {
                this.abilities.add(ability);

                frame = new JFrame("New Ability");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                panel = new JPanel();
                panel.setLayout(null);

                // Set the size of the panel
                panel.setPreferredSize(new Dimension(500, 400));
                panel.setBackground(Color.decode("#cfb991"));

                frame.getContentPane().add(panel);
                frame.pack();
                frame.setVisible(true);

                prompt = new JLabel("New ability unlocked: " + ability + "!");
                prompt.setBounds(150, 25, 200, 30);
                panel.add(prompt);

                String description = "holder";
                String description2 = "holder";

                // Get a specific description based on the ability achieved

                if (ability.equals("MULTISHOT")) {
                    description = "When firing your bow you can now shoot multiple arrows at once!";
                    description2 = "Your damage by your bow is now multiplied by 3!";
                }

                prompt2 = new JLabel("Description: " + description);
                prompt2.setBounds(25, 75, 500, 30);
                panel.add(prompt2);

                prompt3 = new JLabel(description2);
                prompt3.setBounds(100, 100, 400, 30);
                panel.add(prompt3);

                image = new JLabel(new ImageIcon("multi.png"));
                image.setBounds(125, 125, 200, 200);
                panel.add(image);

                backButton = new JButton("Back");
                backButton.setBounds(175, 325, 100, 50);
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                       if(e.getSource() == backButton) {
                           frame.dispose();
                       }
                    }
                });
                backButton.setBackground(Color.decode("#9d9795"));
                panel.add(backButton);
            }

        }




        public void gainXP ( int amt){
            //Check for level up
            if (amt >= this.levelXP) {
                int xpOver = (amt - this.levelXP);
                levelUp(xpOver);
            } else {
                //Subtract xp gained from remaining levelXP
                setLevelXP(this.levelXP - amt);
            }
        }

        public void levelUp ( int xpOver){
            while (true) {
                setMaxHP((int) ((this.maxHP * 0.2) + this.maxHP));
                setSpeed((int) ((this.speed * 0.2) + this.speed));
                setMaxStamina((int) ((this.maxStamina * 0.2) + this.maxStamina));
                setLevel(this.level + 1);
                setInitialLevelXP((int) ((this.initialLevelXP * 0.5) + this.initialLevelXP));

                //Check for more levels gained
                if (xpOver > this.initialLevelXP) {
                    xpOver = xpOver - this.initialLevelXP;
                    //Keep executing loop
                } else {
                    break;
                }
            }

            //Remove xp amt from current xp needed to level up
            setLevelXP(this.initialLevelXP - xpOver);

            //Later on, will also have to check for new skills/abilities unlocked at each level
        }
    }

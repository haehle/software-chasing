import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {

    private static JFrame frame;

    private JPanel panel;

    private int tilesWalked;

    private JButton backButton;

    private JLabel prompt, prompt2, prompt3, image;

    private ActionListener listener;

    private ArrayList<String> achievements;

    //private ActionEvent e;

    private String username;

    private int gold;
    private String name;
    private int type;

    private int enemiesDefeated;
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
    private int currentLevelNo;
    private int maxLevelNO;
    private Inventory inventory;
    int playerClass;
    private long timePlayed;
    private long points;
    private Gear[] availableGear;

    public Player(String username, String name, int playerClass) {
        this.timePlayed = 0;
        this.enemiesDefeated = 0;
        this.achievements = new ArrayList<>();
        this.tilesWalked = 0;
        this.username = username;
        this.name = name;
        this.playerClass = playerClass;
        this.location = new int[]{0, 0};
        this.level = 1;
        this.levelXP = 100;
        this.initialLevelXP = 100;
        this.gold = 400;
        this.currentLevelNo = 0;
        this.maxLevelNO = 0;
        this.timePlayed = 0;
        this.points = 0;
        //if (type == 1) {
            this.inventory = new Inventory();
            if (playerClass == 1) {
                this.hp = 100;
                this.maxHP = 100;
                this.speed = 15;
                this.stamina = 20;
                this.maxStamina = 20;
                this.skills = new String[]{};
                this.abilities = new ArrayList<>();
            } else if (playerClass == 2) {
                this.hp = 60;
                this.maxHP = 100;
                this.speed = 25;
                this.stamina = 30;
                this.maxStamina = 30;
                this.skills = new String[]{};
                this.abilities = new ArrayList<>();
            } else if (playerClass == 3) {
                this.hp = 200;
                this.maxHP = 200;
                this.speed = 5;
                this.stamina = 5;
                this.maxStamina = 5;
                this.skills = new String[]{};
                this.abilities = new ArrayList<>();

            }
        //}
    }//end constructor 1

    //Constructor used to initialize Player object from database information
    public Player(String username, String name, int playerClass, int locationX, int locationY, int hp, int maxHP,
                  int speed, int stamina, int maxStamina, int level, int levelXP, int initialLevelXP, Inventory inventory,
                  long timePlayed, long points, int currentLevelNo, int maxLevelNO) {
        this.timePlayed = 0;
        this.enemiesDefeated = 0;
        this.tilesWalked = 0;
        this.username = username;
        this.name = name;
        this.playerClass = playerClass;
        this.location = new int[]{locationX, locationY};
        this.level = level;
        this.levelXP = levelXP;
        this.initialLevelXP = initialLevelXP;
        this.hp = hp;
        this.maxHP = maxHP;
        this.speed = speed;
        this.stamina = stamina;
        this.maxStamina = maxStamina;
        this.inventory = inventory;
        this.currentLevelNo = currentLevelNo;
        this.maxLevelNO = maxLevelNO;
        this.timePlayed = timePlayed;
        this.points = points;


        //Need to get these from alternate table
        this.skills = new String[]{};
        this.abilities = new ArrayList<>();
    }
        public long getTimePlayed(){
            return this.timePlayed;
        }

        public void setTimePlayed(long timePlayed){
            this.timePlayed = timePlayed;
        }

    public int getCurrentLevelNo() {
        return currentLevelNo;
    }

    public int getTilesWalked() {
        return this.tilesWalked;
    }

    public int getEnemiesDefeated() { return this.enemiesDefeated;}

    public void setEnemiesDefeated(int enemiesDefeated) {
        this.enemiesDefeated = enemiesDefeated;
    }

    public void setTilesWalked(int tiles) {
        this.tilesWalked = tiles;
    }

    public void setCurrentLevelNo(int currentLevelNo) {
        this.currentLevelNo = currentLevelNo;
        }

    public int getMaxLevelNO() {
        return maxLevelNO;
    }

    public void setMaxLevelNO(int maxLevelNO) {
        this.maxLevelNO = maxLevelNO;
    }

    public int getGold() {
            return this.gold;
        }

        public void setGold(int gold) {
            this.gold = gold;
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

        public int getPlayerClass () {
            return this.playerClass;
        }

        public void setPlayerClass ( int playerClass){
            this.playerClass = playerClass;
        }

    public long getPoints () {
        return this.points;
    }

    public void setPoints ( long points){
        this.points = points;
    }


        public String getPlayerClassName () { return Util.getPlayerClassName(this.playerClass); }

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

        public Inventory getInventory () { return this.inventory; }

        public void setInventory (Inventory inventory ) { this.inventory = inventory; }

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
                frame.setLocationRelativeTo(null);
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

                image = new JLabel(new ImageIcon("Images/multi.png"));
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

    public void addAchievement(String achievement) {

        if(this.achievements.isEmpty()) {
            this.achievements.add("Holder");
        }

        if (!this.achievements.contains(achievement)) {
            achievements.add(achievement);

            frame = new JFrame("New Achievement");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            panel = new JPanel();
            panel.setLayout(null);

            // Set the size of the panel
            panel.setPreferredSize(new Dimension(1080, 720));
            panel.setBackground(Color.decode("#cfb991"));

            frame.getContentPane().add(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            Font font = new Font("Arial", Font.BOLD, 18);

            prompt = new JLabel("Achievement unlocked: " + achievement + "!");
            prompt.setFont(font);
            prompt.setBounds(350, 25, 500, 30);
            panel.add(prompt);

            String description = "holder";
            String description2 = "holder";

            // Get a specific description based on the ability achieved

            if (achievement.equals("10 Tiles Walked")) {
                description = "Get your bearings on this new world";
                image = new JLabel(new ImageIcon("Images/grass.jpg"));
                image.setBounds(125, 125, 800, 400);
            }

            else if (achievement.equals("100 Tiles Walked")) {
                description = "Starting to figure out your way";
                image = new JLabel(new ImageIcon("Images/rocks.jpg"));
                image.setBounds(125, 125, 800, 400);
            }

            else if (achievement.equals("1000 Tiles Walked")) {
                description = "You have traveled far and wide";
                image = new JLabel(new ImageIcon("Images/road.jpg"));
                image.setBounds(125, 125, 800, 400);
            }
            else if (achievement.equals("1 Enemy Defeated")) {
                description = "You have defeated your first enemy!";
                image = new JLabel(new ImageIcon("Images/enemydefeat.jpg"));
                image.setBounds(125, 125, 800, 400);
            }

            else if (achievement.equals("10 Enemy Defeated")) {
                description = "You are unstoppable!";
                image = new JLabel(new ImageIcon("Images/Rampage.jpg"));
                image.setBounds(125, 125, 800, 400);
            }

            prompt2 = new JLabel(description);
            prompt2.setBounds(350, 550, 500, 30);
            prompt2.setFont(font);
            panel.add(prompt2);

            // prompt3 = new JLabel(description2);
            // prompt3.setBounds(100, 100, 400, 30);
            // panel.add(prompt3);

            panel.add(image);

            backButton = new JButton("Back");
            backButton.setBounds(400, 625, 200, 75);
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
    }

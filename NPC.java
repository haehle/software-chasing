import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.*;


public class NPC {

    private static JFrame frame;
    private JPanel panel;

    private JButton backButton, button1, button2, button3;
    private JButton startButton, battleButton1, battleButton2, battleButton3;

    private JLabel start, q1, q2, q3;
    private JLabel prompt, item1_prompt, item1_cost, item2_prompt, item2_cost, item3_prompt, item3_cost, total_gold;
    private String name;
    private String NPCtype;
    private Color color;
    private String encounter;
    private int[] location;
    private int hp;
    private int maxHP;
    private int speed;
    private int stamina;
    private int maxStamina;

    private String item1;
    private String item2;
    private String item3;
    private int cost1;
    private int cost2;
    private int cost3;
    private String image1;
    private String image2;
    private String image3;
    private int stock1;
    private int stock2;
    private int stock3;


    private String[] skills;
    private ArrayList<String> abilities;


    public NPC(String name, String NPCtype) {
        this.NPCtype = NPCtype;
        this.name = name;
        this.location = new int[]{0, 0};
        switch (NPCtype) {
            case "Boss" -> {
                this.encounter = "You are about to encounter the boss, " + name;
                this.color = Color.RED;
                this.hp = 500;
                this.maxHP = 500;
                this.speed = 5;
                this.stamina = 20;
                this.maxStamina = 20;
                this.skills = new String[]{};
                this.abilities = new ArrayList<>();
            }
            case "Enemy" -> {
                this.encounter = "You are about to encounter the enemy, " + name;
                this.color = Color.ORANGE;
                this.hp = 60;
                this.maxHP = 100;
                this.speed = 5;
                this.stamina = 20;
                this.maxStamina = 20;
                this.skills = new String[]{};
                this.abilities = new ArrayList<>();
            }
            case "Neutral" -> {
                this.encounter = "Interact with " + name;
                this.color = Color.BLUE;
                this.speed = 5;
                this.stamina = 5;
                this.maxStamina = 5;
                this.skills = new String[]{};
                this.abilities = new ArrayList<>();
            }
        }
    }

    public String getName () {
        return this.name;
    }

    public void setNPCType (String type){
        this.NPCtype = type;
    }

    public String getNPCType () {
        return this.NPCtype;
    }

    public Color getColor() {
        return this.color;
    }

    public String getEncounter() {
        return this.encounter;
    }

    public int[] getLocation () {
        return this.location;
    }

    public void setLocation(int x, int y){
        this.location[0] = x;
        this.location[1] = y;
    }

    public int getHp () {
        return this.hp;
    }

    public void setHp (int hp){
        this.hp = hp;
    }

    public int getMaxHP () {
        return this.maxHP;
    }

    public void setMaxHP (int maxHP){
        this.maxHP = maxHP;
    }

    public int getSpeed () {
        return this.speed;
    }

    public void setSpeed (int speed){
        this.speed = speed;
    }

    public int getStamina () {
        return this.stamina;
    }

    public void setStamina (int stamina){
        this.stamina = stamina;
    }

    public int getMaxStamina () {
        return this.maxStamina;
    }

    public void setMaxStamina (int maxStamina){
        this.maxStamina = maxStamina;
    }

    public int getStock1 () {
        return this.stock1;
    }

    public int getStock2 () {
        return this.stock2;
    }

    public int getStock3 () {
        return this.stock3;
    }

    public void setStock1 (int stock1) {
        this.stock1 = stock1;
    }

    public void setStock2 (int stock2) {
        this.stock2 = stock2;
    }

    public void setStock3 (int stock3) {
        this.stock3 = stock3;
    }


    public void displayBattle (Player player) {
        frame = new JFrame("Battle against " + getName());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);

        // Set the size of the panel
        panel.setPreferredSize(new Dimension(1020, 780));
        panel.setBackground(Color.decode("#cfb991"));

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);

        start = new JLabel("You are about to engage in a battle with 'Hello'. To defeat him, answer the following CS related questions");
        start.setBounds(250, 400, 500, 100);
        Font font1 = new Font("Arial", Font.BOLD, 30);
        start.setForeground(Color.ORANGE);
        start.setFont(font1);
        panel.add(start);

        startButton = new JButton("Start");
        startButton.setBounds(300, 500, 100, 50);
        startButton.setBackground(Color.decode("#9d9795"));

        backButton = new JButton("Back");
        backButton.setBounds(800, 500, 100, 50);
        backButton.setBackground(Color.decode("#9d9795"));

        q1 = new JLabel("How would you print 'Hello World' in Python?");
        q1.setBounds(250, 400, 500, 100);
        q1.setForeground(Color.ORANGE);
        q1.setFont(font1);
        panel.add(q1);

        battleButton1 = new JButton("print(Hello World);");
        battleButton1.setBounds(300,500,100,50);
        battleButton1.setBackground(Color.decode("#9d9795"));


        battleButton2 = new JButton("('Hello World').print();");
        battleButton2.setBounds(300,500,100,50);
        battleButton2.setBackground(Color.decode("#9d9795"));

        battleButton3 = new JButton("print('Hello World')");
        battleButton3.setBounds(300,500,100,50);
        battleButton3.setBackground(Color.decode("#9d9795"));

        q2 = new JLabel("How would you print 'Hello World' in Java?");
        q2.setBounds(250, 400, 500, 100);
        q2.setForeground(Color.ORANGE);
        q2.setFont(font1);
        panel.add(q2);

        battleButton1.setText("System.out.println('Hello World');");
        battleButton2.setText("System.out.println('Hello World')");
        battleButton3.setText("class Hello {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"Hello World\"); \n" +
                "    }\n" +
                "}");

        q3 = new JLabel("How would you print 'Hello World' in C?");
        q3.setBounds(250, 400, 500, 100);
        q3.setForeground(Color.ORANGE);
        q3.setFont(font1);
        panel.add(q3);

        battleButton1.setText("int main()\n" +
                "{ " +
                "    printf(\"Hello World\");\n" +
                "  \n" +
                "    return 0;\n" +
                "}");
        battleButton2.setText("int main()\n" +
                "{ " +
                "    printf(\"Hello World\");\n" +
                "  \n" +
                "    return 'Hello World';\n" +
                "}");
        battleButton3.setText("int main()\n" +
                "{ " +
                " printf(\"Hello World\");\n" +
                "}");
    }

    // Method to display the shop for an individual NPC if they have one
    public void displayShop (Player player) {

        // Create frame for the shop

        frame = new JFrame(getName() + "'s" + " shop!");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);

        // Set the size of the panel
        panel.setPreferredSize(new Dimension(1920, 1080));
        panel.setBackground(Color.decode("#cfb991"));

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);

        total_gold = new JLabel("Gold: " + player.getGold());
        total_gold.setBounds(50, 900, 500, 100);
        Font font1 = new Font("Arial", Font.BOLD, 30);
        total_gold.setForeground(Color.YELLOW);
        total_gold.setFont(font1);
        panel.add(total_gold);

        // Intro message


        prompt = new JLabel("Welcome to " + getName() + "'s shop! Buy some items by clicking on an item. Make sure you have enough gold!");
        prompt.setBounds(550, 25, 1000, 100);
        Font font = new Font("Arial", Font.BOLD, 20);
        prompt.setFont(font);
        panel.add(prompt);

        // Setting items, costs, and images for each individual NPC that we will have

        item1 = "";
        item2 = "";
        item3 = "";
        cost1 = 0;
        cost2 = 0;
        cost3 = 0;
        image1 = "";
        image2 = "";
        image3 = "";

        if(getName().equals("Ron")) {
            item1 = "Iron Shield";
            item2 = "Wooden Sword";
            item3 = "Healing Potion";
            cost1 = 100;
            cost2 = 75;
            cost3 = 50;
            image1 = "Images/shield.png";
            image2 = "Images/Wooden_sword.png";
            image3 = "Images/healing.png";
        }

        else if(getName().equals("Natalie")) {
            item1 = "Chain Helmet";
            item2 = "Wooden Bow";
            item3 = "Mana Potion";
            cost1 = 125;
            cost2 = 100;
            cost3 = 75;
            image1 = "Images/chain.png";
            image2 = "Images/bow.png";
            image3 = "Images/mana.png";
        }

        // Adding the items, costs, and images to the frame

        item1_prompt = new JLabel(item1);
        item1_prompt.setBounds(275, 250, 500, 100);
        panel.add(item1_prompt);


        item1_cost = new JLabel(cost1 + " Gold             " + "Stock: " + getStock1());
        item1_cost.setBounds(250, 650, 500, 100);
        panel.add(item1_cost);

        item2_prompt = new JLabel(item2);
        item2_prompt.setBounds(875, 250, 500, 100);
        panel.add(item2_prompt);

        item2_cost = new JLabel(cost2 + " Gold             " + "Stock: " + getStock2());
        item2_cost.setBounds(850, 650, 500, 100);
        panel.add(item2_cost);

        item3_prompt = new JLabel(item3);
        item3_prompt.setBounds(1500, 250, 500, 100);
        panel.add(item3_prompt);

        item3_cost = new JLabel(cost3 + " Gold             " + "Stock: " + getStock3());
        item3_cost.setBounds(1475, 650, 500, 100);
        panel.add(item3_cost);

        backButton = new JButton("Back");
        backButton.setBounds(900, 900, 100, 50);
        backButton.setBackground(Color.decode("#9d9795"));

        button1 = new JButton(new ImageIcon(image1));
        button1.setBounds(175, 350, 300, 300);

        // Makes border invisible for rectangle (Not sure on this yet)

        //image.setBorder(BorderFactory.createEmptyBorder());
        //image.setContentAreaFilled(false);

        button2 = new JButton(new ImageIcon(image2));
        button2.setBounds(775, 350, 300, 300);

        // Makes border invisible for rectangle

        //image.setBorder(BorderFactory.createEmptyBorder());
        //image.setContentAreaFilled(false);

        button3 = new JButton(new ImageIcon(image3));
        button3.setBounds(1400, 350, 300, 300);

        // Makes border invisible for rectangle

        //image.setBorder(BorderFactory.createEmptyBorder());
        //image.setContentAreaFilled(false);

        // If the item request has no stock then they should get an out of stock message

        if(getStock1() == 0) {
            item1_cost.setText("OUT OF STOCK!");
            item1_cost.setForeground(Color.RED);
            item1_cost.setFont(font);
        }

        // Add all the commands and checks for each item to be clicked on in the shop
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == button1 && stock1 > 0) {
                    if(player.getGold() >= cost1) {
                        player.setGold(player.getGold() - cost1);
                        setStock1(getStock1() - 1);
                        total_gold.setText("Gold: " + player.getGold());
                        player.getInventory().addItem(item1);
                        if(getStock1() == 0) {
                            item1_cost.setText("OUT OF STOCK!");
                            item1_cost.setForeground(Color.RED);
                            item1_cost.setFont(font);
                        }
                        else {
                            item1_cost.setText(cost1 + " Gold             " + "Stock: " + getStock1());
                        }

                    }
                    else {
                        item1_cost.setText("Not enough gold!");
                        item1_cost.setForeground(Color.RED);
                        Font font = new Font("Arial", Font.BOLD, 14);
                        item1_cost.setFont(font);

                    }
                }
            }
        });

        // Repeat for the other items in the shop

        if(getStock2() == 0) {
            item2_cost.setText("OUT OF STOCK!");
            item2_cost.setForeground(Color.RED);
            item2_cost.setFont(font);
        }
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == button2 && stock2 > 0) {
                    if(player.getGold() >= cost2) {
                        player.setGold(player.getGold() - cost2);
                        setStock2(getStock2() - 1);
                        total_gold.setText("Gold: " + player.getGold());
                        player.getInventory().addItem(item2);
                        if(getStock2() == 0) {
                            item2_cost.setText("OUT OF STOCK!");
                            item2_cost.setForeground(Color.RED);
                            item2_cost.setFont(font);
                        }
                        else {
                            item2_cost.setText(cost2 + " Gold             " + " Stock: " + getStock2());
                        }

                    }

                    else {
                        item2_cost.setText("Not enough gold!");
                        item2_cost.setForeground(Color.RED);
                        Font font = new Font("Arial", Font.BOLD, 14);
                        item2_cost.setFont(font);

                    }

                }
            }
        });

        if(getStock3() == 0) {
            item3_cost.setText("OUT OF STOCK!");
            item3_cost.setForeground(Color.RED);
            item3_cost.setFont(font);
        }
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == button3 && stock3 > 0) {
                    if(player.getGold() >= cost3) {
                        player.setGold(player.getGold() - cost3);
                        total_gold.setText("Gold: " + player.getGold());
                        player.getInventory().addItem(item3);
                        setStock3(getStock3() - 1);
                        if(getStock3() == 0) {
                            item3_cost.setText("OUT OF STOCK!");
                            item3_cost.setForeground(Color.RED);
                            item3_cost.setFont(font);
                        }
                        else {
                            item3_cost.setText(cost3 + " Gold            " + " Stock: " + getStock3());
                        }

                    }
                    else {
                        item3_cost.setText("Not enough gold!");
                        item3_cost.setForeground(Color.RED);
                        Font font = new Font("Arial", Font.BOLD, 14);
                        item3_cost.setFont(font);

                    }
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == backButton) {
                    frame.dispose();
                }
            }
        });

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(backButton);


    }


}

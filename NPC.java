import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.*;


public class NPC {

    private static JFrame frame;
    private JPanel panel;

    private JButton fleeButton, backButton, button1, button2, button3;
    private JButton startButton, submitButton, battleButton1, battleButton2, battleButton3, trialerror, analysis, security;

    private JComboBox dropdown;
    private JTextField bugfix;

    private JLabel start, q1, q2, q3, congrats, loser, abilitylist, tryAgain;
    private Boolean q2checker, q3checker;
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

    private Item item1;
    private Item item2;
    private Item item3;
    private int cost1;
    private int cost2;
    private int cost3;
    private String image1;
    private String image2;
    private String image3;
    private int stock1;
    private int stock2;
    private int stock3;

    private boolean hello1 = false;
    private boolean hello2 = false;
    private boolean hello3 = false;
    private boolean loopy1 = false;
    private boolean loopy2 = false;
    private boolean loopy3 = false;
    private boolean jeff1 = false;
    private boolean jeff2 = false;
    private boolean jeff3 = false;
    private boolean ray1 = false;
    private boolean ray2 = false;
    private boolean ray3 = false;

    private int trial = 0;

    private int check = 0;

    private int count = 0;


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
                this.hp = 3;
                this.maxHP = 3;
                this.speed = 5;
                this.stamina = 20;
                this.maxStamina = 20;
                this.skills = new String[]{};
                this.abilities = new ArrayList<>();
            }
            case "Enemy" -> {
                this.encounter = "You are about to encounter the enemy, " + name;
                this.color = Color.ORANGE;
                this.hp = 3;
                this.maxHP = 3;
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
        panel.setPreferredSize(new Dimension(1220, 880));
        panel.setBackground(Color.decode("#cfb991"));

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);

        JLabel health = new JLabel("My Health: " + player.getHp() + "/" + player.getMaxHP());
        health.setBackground(Color.white);
        health.setBounds(650,70,250,50);
        health.setOpaque(false);
        health.setVisible(true);
        panel.add(health);

        JLabel NPChealth = new JLabel(getName() + " Health: " + getHp() + "/" + getMaxHP());
        NPChealth.setBackground(Color.white);
        NPChealth.setBounds(650,100,250,50);
        NPChealth.setOpaque(false);
        NPChealth.setVisible(true);
        panel.add(NPChealth);


        start = new JLabel("<html>You are about to engage in a battle with " + getName() + ". <br/> To defeat him, answer the following CS related questions</html>");
        start.setBounds(250, 100, 700, 300);
        Font font1 = new Font("Arial", Font.BOLD, 20);
        start.setForeground(Color.BLACK);
        start.setFont(font1);
        panel.add(start);
        start.setVisible(true);

        startButton = new JButton("Start");
        startButton.setBounds(250, 600, 100, 50);
        startButton.setBackground(Color.decode("#9d9795"));
        panel.add(startButton);
        startButton.setVisible(true);

        // Creating ability buttons

        trialerror = new JButton("Trial and Error");
        trialerror.setBounds(150, 600, 200, 50);
        trialerror.setBackground(Color.decode("#9d9795"));
        panel.add(trialerror);
        trialerror.setVisible(false);

        analysis = new JButton("Data Analysis");
        analysis.setBounds(400, 600, 200, 50);
        analysis.setBackground(Color.decode("#9d9795"));
        panel.add(analysis);
        analysis.setVisible(false);

        security = new JButton("Faulty Security");
        security.setBounds(275, 675, 200, 50);
        security.setBackground(Color.decode("#9d9795"));
        panel.add(security);
        security.setVisible(false);

        Font font2 = new Font("Arial", Font.BOLD, 20);

        abilitylist = new JLabel("Abilities:");
        abilitylist.setFont(font2);
        abilitylist.setBounds(325, 550, 200, 50);
        panel.add(abilitylist);
        abilitylist.setVisible(false);

        fleeButton = new JButton("Flee");
        fleeButton.setBounds(800, 600, 100, 50);
        fleeButton.setBackground(Color.decode("#9d9795"));
        panel.add(fleeButton);
        fleeButton.setVisible(true);

        backButton = new JButton("Back");
        backButton.setBounds(800, 600, 100, 50);
        backButton.setBackground(Color.decode("#9d9795"));


        q1 = new JLabel("");
        q1.setBounds(250, 100, 700, 100);
        q1.setForeground(Color.decode("#555960"));
        q1.setFont(font1);
        panel.add(q1);
        q1.setVisible(false);

        q2 = new JLabel("");
        q2.setBounds(250, 100, 700, 100);
        q2.setForeground(Color.decode("#555960"));
        q2.setFont(font1);
        panel.add(q2);
        q2.setVisible(false);

        q2.setVisible(false);
        q2checker = false;


        q3 = new JLabel("");
        q3.setBounds(250, 100, 700, 100);
        q3.setForeground(Color.decode("#555960"));
        q3.setFont(font1);
        panel.add(q3);

        q3.setVisible(false);
        q3checker = false;

        battleButton1 = new JButton("");
        battleButton1.setBounds(250,200,250,50);
        battleButton1.setBackground(Color.decode("#555960"));


        battleButton2 = new JButton("");
        battleButton2.setBounds(250,300,250,50);
        battleButton2.setBackground(Color.decode("#555960"));

        battleButton3 = new JButton("");
        battleButton3.setBounds(250,400,250,50);
        battleButton3.setBackground(Color.decode("#555960"));

        submitButton = new JButton("Submit");
        submitButton.setBounds(250, 600, 100, 50);
        panel.add(submitButton);
        submitButton.setVisible(false);

        String[] numbers = {"1", "2", "3"};
        dropdown = new JComboBox(numbers);
        dropdown.setBounds(250, 425, 100, 50);
        panel.add(dropdown);
        dropdown.setVisible(false);

        tryAgain = new JLabel("That's incorrect, try again!");
        tryAgain.setFont(new Font("Acumin Pro", Font.BOLD, 30));
        tryAgain.setForeground(Color.red);
        tryAgain.setSize(550, 50);
        tryAgain.setLocation(50, 50);
        panel.add(tryAgain);
        tryAgain.setVisible(false);

        trialerror.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e)
            {
                if(e.getSource() == trialerror) {
                    trial = 2;
                    trialerror.setVisible(false);
                }
            }
        });

        analysis.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e)
            {
                if(e.getSource() == analysis) {
                    if(check == 1) {
                        battleButton2.setVisible(false);
                    }
                    else if(check == 2) {
                        battleButton1.setVisible(false);
                    }
                    else {
                        battleButton1.setVisible(false);
                    }
                    analysis.setVisible(false);
                    check = 4;
                }
            }
        });

        security.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e)
            {
                if(e.getSource() == security) {
                    Random rand = new Random();
                    int randomNum = rand.nextInt((4 - 1) + 1) + 1;
                    System.out.println("Random num: " + randomNum);

                    if(randomNum == 4) {
                        q1.setVisible(false);
                        q2.setVisible(false);
                        q3.setVisible(false);
                        q3checker = false;
                        battleButton1.setVisible(false);
                        battleButton2.setVisible(false);
                        battleButton3.setVisible(false);
                        panel.add(congrats);
                        congrats.setVisible(true);
                        fleeButton.setVisible(false);
                        panel.add(backButton);
                        backButton.setVisible(true);
                        abilitylist.setVisible(false);
                        security.setVisible(false);
                        trialerror.setVisible(false);
                        analysis.setVisible(false);
                        player.setEnemiesDefeated(player.getEnemiesDefeated() + 1);
                        if(player.getEnemiesDefeated() == 1) {
                            player.addAchievement("1 Enemy Defeated");

                        }
                        else if(player.getEnemiesDefeated() == 2) {
                            if(player.getPlayerClass() == 1) {
                                player.addAbilities("Data Analysis");
                            }

                            if(player.getPlayerClass() == 2) {
                                player.addAbilities("Faulty Security");
                            }

                            if(player.getPlayerClass() == 3) {
                                player.addAbilities("Trial and Error");
                            }
                        }
                        else if(player.getEnemiesDefeated() == 4) {
                            if(player.getPlayerClass() == 1) {
                                player.addAbilities("Data Analysis");
                            }

                            if(player.getPlayerClass() == 2) {
                                player.addAbilities("Faulty Security");
                            }

                            if(player.getPlayerClass() == 3) {
                                player.addAbilities("Trial and Error");
                            }
                        }
                        else if(player.getEnemiesDefeated() == 10) {
                            player.addAchievement("10 Enemy Defeated");
                        }
                    }
                    security.setVisible(false);

                }


            }
        });


        if (getName().equals("Hello")) {

            q1.setText("How would you print 'Hello World' in Python?");
            q2.setText("How would you print 'Hello World' in Java?");
            q3.setText("How would you print 'Hello World' in C?");

            battleButton1.setText("print(Hello World);");
            battleButton2.setText("('Hello World').print();");
            battleButton3.setText("print('Hello World')");


            battleButton3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(trial > 0 || check == 4) {
                        battleButton1.setVisible(true);
                        battleButton2.setVisible(true);
                    }
                    if(e.getSource() == battleButton3 && !q2checker && !q3checker) {
                        if(!hello1) {
                            player.setQuestionsAnswered(player.getQuestionsAnswered() + 1);
                            if(player.getQuestionsAnswered() == 1) {
                                player.addAchievement("1 Question Answered");
                            }
                            else if(player.getQuestionsAnswered() == 10) {
                                player.addAchievement("10 Questions Answered");
                            }
                            else if(player.getQuestionsAnswered() == 25) {
                                player.addAchievement("25 Questions Answered");
                            }
                            hello1 = true;
                        }
                        setHp(getHp() - 1);
                        NPChealth.setText(getName() + " Health: " + getHp() + "/" + getMaxHP());
                        q1.setVisible(false);
                        q2.setVisible(true);
                        q2checker = true;
                        battleButton3.setBounds(250,400,250,150);
                        battleButton1.setText("System.out.println('Hello World');");
                        battleButton2.setText("System.out.println('Hello World')");
                        battleButton3.setText("<html> class Hello { <br/> public static void main(String[] args) {<br/> System.out.println('Hello World'); <br>  } <br> }</html>");
                    } else if (e.getSource() == battleButton3 && q2checker) {
                        if(!hello2) {
                            player.setQuestionsAnswered(player.getQuestionsAnswered() + 1);
                            if(player.getQuestionsAnswered() == 1) {
                                player.addAchievement("1 Question Answered");
                            }
                            else if(player.getQuestionsAnswered() == 10) {
                                player.addAchievement("10 Questions Answered");
                            }
                            else if(player.getQuestionsAnswered() == 25) {
                                player.addAchievement("25 Questions Answered");
                            }
                            hello2 = true;
                        }
                        setHp(getHp() - 1);
                        NPChealth.setText(getName() + " Health: " + getHp() + "/" + getMaxHP());
                        q2.setVisible(false);
                        q3.setVisible(true);
                        q2checker = false;
                        q3checker = true;
                        battleButton1.setBounds(250,200,250,150);
                        battleButton2.setBounds(250,400,250,150);
                        battleButton3.setBounds(600,200,250,150);
                        battleButton3.setText("<html>int main() <br/> {  <br/>   printf('Hello World'); <br/> return 0; <br/> } </html>");
                        battleButton2.setText("<html>int main() <br/> {  <br/>   printf('Hello World'); <br/> return 'hello world'; <br/> } </html>");
                        battleButton1.setText("<html>int main() <br/> { <br/>  printf('Hello World'); <br/> }</html>");
                    } else if (e.getSource() == battleButton3 && q3checker) {
                        if(!hello3) {
                            player.setQuestionsAnswered(player.getQuestionsAnswered() + 1);
                            if(player.getQuestionsAnswered() == 1) {
                                player.addAchievement("1 Question Answered");
                            }
                            else if(player.getQuestionsAnswered() == 10) {
                                player.addAchievement("10 Questions Answered");
                            }
                            else if(player.getQuestionsAnswered() == 25) {
                                player.addAchievement("25 Questions Answered");
                            }
                            hello3 = true;
                        }
                        setHp(getHp() - 1);
                        NPChealth.setText(getName() + " Health: " + getHp() + "/" + getMaxHP());
                        q3.setVisible(false);
                        q3checker = false;
                        battleButton1.setVisible(false);
                        battleButton2.setVisible(false);
                        battleButton3.setVisible(false);
                        panel.add(congrats);
                        player.setEnemiesDefeated(player.getEnemiesDefeated() + 1);
                        if(player.getEnemiesDefeated() == 1) {
                            player.addAchievement("1 Enemy Defeated");

                        }
                        else if(player.getEnemiesDefeated() == 2) {
                            if(player.getPlayerClass() == 1) {
                                player.addAbilities("Data Analysis");
                            }

                            if(player.getPlayerClass() == 2) {
                                player.addAbilities("Faulty Security");
                            }

                            if(player.getPlayerClass() == 3) {
                                player.addAbilities("Trial and Error");
                            }
                        }
                        else if(player.getEnemiesDefeated() == 4) {
                            if(player.getPlayerClass() == 1) {
                                player.addAbilities("Data Analysis");
                            }

                            if(player.getPlayerClass() == 2) {
                                player.addAbilities("Faulty Security");
                            }

                            if(player.getPlayerClass() == 3) {
                                player.addAbilities("Trial and Error");
                            }
                        }
                        else if(player.getEnemiesDefeated() == 10) {
                            player.addAchievement("10 Enemy Defeated");
                        }

                        congrats.setVisible(true);
                        fleeButton.setVisible(false);
                        panel.add(backButton);
                        backButton.setVisible(true);
                        abilitylist.setVisible(false);
                        security.setVisible(false);
                        trialerror.setVisible(false);
                        analysis.setVisible(false);
                    }
                }
            });

        }

        if (getName().equals("Jeff")) {
            q1.setText("What is synonymous with scrum?");
            battleButton1.setText("Waterfall");
            battleButton2.setText("Spiral");
            battleButton3.setText("Agile");



            battleButton1.setBounds(250,400,250,50);
            battleButton2.setBounds(250,300,250,50);
            battleButton3.setBounds(250,200,250,50);

            battleButton3.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if(trial > 0 || check == 4) {
                        battleButton1.setVisible(true);
                        battleButton2.setVisible(true);
                    }
                    if (e.getSource() == battleButton3 && !q2checker && !q3checker) {
                        if(!jeff1) {
                            player.setQuestionsAnswered(player.getQuestionsAnswered() + 1);
                            if(player.getQuestionsAnswered() == 1) {
                                player.addAchievement("1 Question Answered");
                            }
                            else if(player.getQuestionsAnswered() == 10) {
                                player.addAchievement("10 Questions Answered");
                            }
                            else if(player.getQuestionsAnswered() == 25) {
                                player.addAchievement("25 Questions Answered");
                            }
                            jeff1 = true;
                        }
                        setHp(getHp() - 1);
                        NPChealth.setText(getName() + " Health: " + getHp() + "/" + getMaxHP());
                        q1.setVisible(false);
                        q2.setText("Ideally we want to...");
                        q2.setVisible(true);
                        q2checker = true;
                        battleButton1.setBounds(250,200,350,50);
                        battleButton2.setBounds(250,400,350,50);
                        battleButton3.setBounds(250,300,350,50);
                        battleButton1.setText("Increase cohesion and Increase coupling");
                        battleButton2.setText("Decrease cohesion and Increase coupling");
                        battleButton3.setText("Increase cohesion and Decrease coupling");
                    } else if (e.getSource() == battleButton3 && q2checker) {
                        if(!jeff2) {
                            player.setQuestionsAnswered(player.getQuestionsAnswered() + 1);
                            if(player.getQuestionsAnswered() == 1) {
                                player.addAchievement("1 Question Answered");
                            }
                            else if(player.getQuestionsAnswered() == 10) {
                                player.addAchievement("10 Questions Answered");
                            }
                            else if(player.getQuestionsAnswered() == 25) {
                                player.addAchievement("25 Questions Answered");
                            }
                            jeff2 = true;
                        }
                        setHp(getHp() - 1);
                        NPChealth.setText(getName() + " Health: " + getHp() + "/" + getMaxHP());
                        q2.setVisible(false);
                        q3.setText("Which of the following is not a type of testing?");
                        q3.setVisible(true);
                        q2checker = false;
                        q3checker = true;
                        battleButton1.setBounds(250, 200, 250, 50);
                        battleButton2.setBounds(250,300,250,50);
                        battleButton3.setBounds(250,400,250,50);
                        battleButton1.setText("Unit");
                        battleButton2.setText("Sandwich");
                        battleButton3.setText("Potato Chip");
                    } else if(e.getSource() == battleButton3) {
                        if(!jeff3) {
                            player.setQuestionsAnswered(player.getQuestionsAnswered() + 1);
                            if(player.getQuestionsAnswered() == 1) {
                                player.addAchievement("1 Question Answered");
                            }
                            else if(player.getQuestionsAnswered() == 10) {
                                player.addAchievement("10 Questions Answered");
                            }
                            else if(player.getQuestionsAnswered() == 25) {
                                player.addAchievement("25 Questions Answered");
                            }
                            jeff3 = true;
                        }
                        setHp(getHp() - 1);
                        NPChealth.setText(getName() + " Health: " + getHp() + "/" + getMaxHP());
                        q3.setVisible(false);
                        q3checker = false;
                        battleButton1.setVisible(false);
                        battleButton2.setVisible(false);
                        battleButton3.setVisible(false);
                        panel.add(congrats);
                        congrats.setVisible(true);
                        player.setEnemiesDefeated(player.getEnemiesDefeated() + 1);
                        if(player.getEnemiesDefeated() == 1) {
                            player.addAchievement("1 Enemy Defeated");

                        }
                        else if(player.getEnemiesDefeated() == 2) {
                            if(player.getPlayerClass() == 1) {
                                player.addAbilities("Data Analysis");
                            }

                            if(player.getPlayerClass() == 2) {
                                player.addAbilities("Faulty Security");
                            }

                            if(player.getPlayerClass() == 3) {
                                player.addAbilities("Trial and Error");
                            }
                        }
                        else if(player.getEnemiesDefeated() == 4) {
                            if(player.getPlayerClass() == 1) {
                                player.addAbilities("Data Analysis");
                            }

                            if(player.getPlayerClass() == 2) {
                                player.addAbilities("Faulty Security");
                            }

                            if(player.getPlayerClass() == 3) {
                                player.addAbilities("Trial and Error");
                            }
                        }
                        else if(player.getEnemiesDefeated() == 10) {
                            player.addAchievement("10 Enemy Defeated");
                        }
                        congrats.setVisible(true);
                        fleeButton.setVisible(false);
                        panel.add(backButton);
                        backButton.setVisible(true);
                        abilitylist.setVisible(false);
                        security.setVisible(false);
                        trialerror.setVisible(false);
                        analysis.setVisible(false);
                    }
            }
        });
        }

        if (getName().equals("Loopy")) {
            q1.setText("Which loop is used most often?");
            battleButton1.setText("While");
            battleButton2.setText("Do While");
            battleButton3.setText("For");



            battleButton1.setBounds(250,200,250,50);
            battleButton2.setBounds(250,300,250,50);
            battleButton3.setBounds(250,400,250,50);


            battleButton3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(trial > 0 || check == 4) {
                        battleButton1.setVisible(true);
                        battleButton2.setVisible(true);
                    }
                    if (e.getSource() == battleButton3 && !q2checker && !q3checker) {
                        if(!loopy1) {
                            player.setQuestionsAnswered(player.getQuestionsAnswered() + 1);
                            if(player.getQuestionsAnswered() == 1) {
                                player.addAchievement("1 Question Answered");
                            }
                            else if(player.getQuestionsAnswered() == 10) {
                                player.addAchievement("10 Questions Answered");
                            }
                            else if(player.getQuestionsAnswered() == 25) {
                                player.addAchievement("25 Questions Answered");
                            }
                            loopy1 = true;
                        }
                        setHp(getHp() - 1);
                        NPChealth.setText(getName() + " Health: " + getHp() + "/" + getMaxHP());
                        q1.setVisible(false);
                        q2.setText("Which of the following is a valid for-loop in Python?");
                        q2.setVisible(true);
                        q2checker = true;
                        battleButton1.setBounds(250,200,250,150);
                        battleButton2.setBounds(550,300,250,150);
                        battleButton3.setBounds(250,400,250,150);
                        battleButton1.setText("<html> cars = ['Honda', 'Toyota', 'Ford']  <br/> for x in cars { <br/> print(x) <br/> } </html>");
                        battleButton2.setText("<html> cars = ['Honda', 'Toyota', 'Ford'];  <br/> for x in cars { <br/> print(x); <br/> } </html>");
                        battleButton3.setText("<html> cars = ['Honda', 'Toyota', 'Ford']  <br/> for x in cars: <br/> print(x) </html>");
                    } else if (e.getSource() == battleButton3 && q2checker) {
                        if(!loopy2) {
                            player.setQuestionsAnswered(player.getQuestionsAnswered() + 1);
                            if(player.getQuestionsAnswered() == 1) {
                                player.addAchievement("1 Question Answered");
                            }
                            else if(player.getQuestionsAnswered() == 10) {
                                player.addAchievement("10 Questions Answered");
                            }
                            else if(player.getQuestionsAnswered() == 25) {
                                player.addAchievement("25 Questions Answered");
                            }
                            loopy2 = true;
                        }
                        setHp(getHp() - 1);
                        NPChealth.setText(getName() + " Health: " + getHp() + "/" + getMaxHP());
                        q2.setVisible(false);
                        q3.setText("Which of the following is a valid for-loop in Java?");
                        q3.setVisible(true);
                        q2checker = false;
                        q3checker = true;
                        battleButton1.setText("<html> for (int i = 0, i < 5) { <br/> System.out.println(i); <br/> }");
                        battleButton2.setText("<html> for (int i = 0, i < 5, i++) { <br/> System.out.println(i); <br/> }");
                        battleButton3.setText("<html> for (int i = 0; i < 5; i++) { <br/> System.out.println(i); <br/> }");
                    } else if(e.getSource() == battleButton3) {
                        if(!loopy3) {
                            player.setQuestionsAnswered(player.getQuestionsAnswered() + 1);
                            if(player.getQuestionsAnswered() == 1) {
                                player.addAchievement("1 Question Answered");
                            }
                            else if(player.getQuestionsAnswered() == 10) {
                                player.addAchievement("10 Questions Answered");
                            }
                            else if(player.getQuestionsAnswered() == 25) {
                                player.addAchievement("25 Questions Answered");
                            }
                            loopy3 = true;
                        }
                        setHp(getHp() - 1);
                        NPChealth.setText(getName() + " Health: " + getHp() + "/" + getMaxHP());
                        q3.setVisible(false);
                        q3checker = false;
                        battleButton1.setVisible(false);
                        battleButton2.setVisible(false);
                        battleButton3.setVisible(false);
                        panel.add(congrats);
                        congrats.setVisible(true);
                        player.setEnemiesDefeated(player.getEnemiesDefeated() + 1);
                        if(player.getEnemiesDefeated() == 1) {
                            player.addAchievement("1 Enemy Defeated");

                        }
                        else if(player.getEnemiesDefeated() == 2) {
                            if(player.getPlayerClass() == 1) {
                                player.addAbilities("Data Analysis");
                            }

                            if(player.getPlayerClass() == 2) {
                                player.addAbilities("Faulty Security");
                            }

                            if(player.getPlayerClass() == 3) {
                                player.addAbilities("Trial and Error");
                            }
                        }
                        else if(player.getEnemiesDefeated() == 4) {
                            if(player.getPlayerClass() == 1) {
                                player.addAbilities("Data Analysis");
                            }

                            if(player.getPlayerClass() == 2) {
                                player.addAbilities("Faulty Security");
                            }

                            if(player.getPlayerClass() == 3) {
                                player.addAbilities("Trial and Error");
                            }
                        }
                        else if(player.getEnemiesDefeated() == 10) {
                            player.addAchievement("10 Enemy Defeated");
                        }
                        congrats.setVisible(true);
                        fleeButton.setVisible(false);
                        panel.add(backButton);
                        backButton.setVisible(true);
                        abilitylist.setVisible(false);
                        security.setVisible(false);
                        trialerror.setVisible(false);
                        analysis.setVisible(false);
                    }
                }
            });
        }

        if (getName().equals("Ray")) {
            q1.setText("Which of the following is an array in Python?");
            battleButton1.setText("cars = { car1: Honda, car2: 'Toyota', car3: 'Ford' }");
            battleButton2.setText("cars = ('Honda', 'Toyota', 'Ford')");
            battleButton3.setText("cars = ['Honda', 'Toyota', 'Ford']");



            battleButton1.setBounds(250,200,350,50);
            battleButton2.setBounds(250,400,350,50);
            battleButton3.setBounds(250,300,350,50);


            battleButton3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(trial > 0 || check == 4) {
                        battleButton1.setVisible(true);
                        battleButton2.setVisible(true);
                    }
                    if (e.getSource() == battleButton3 && !q2checker && !q3checker) {
                        if(!ray1) {
                            player.setQuestionsAnswered(player.getQuestionsAnswered() + 1);
                            if(player.getQuestionsAnswered() == 1) {
                                player.addAchievement("1 Question Answered");
                            }
                            else if(player.getQuestionsAnswered() == 10) {
                                player.addAchievement("10 Questions Answered");
                            }
                            else if(player.getQuestionsAnswered() == 25) {
                                player.addAchievement("25 Questions Answered");
                            }
                            ray1 = true;
                        }
                        setHp(getHp() - 1);
                        NPChealth.setText(getName() + " Health: " + getHp() + "/" + getMaxHP());
                        q1.setVisible(false);
                        q2.setText("Which of the following is an array in Java?");
                        q2.setVisible(true);
                        q2checker = true;
                        battleButton1.setBounds(650,300,350,150);
                        battleButton2.setBounds(250,400,350,150);
                        battleButton3.setBounds(250,200,350,150);
                        battleButton1.setText("cars = {'Honda', 'Toyota', 'Ford'};");
                        battleButton2.setText("String cars = {'Honda', 'Toyota', 'Ford'};");
                        battleButton3.setText("String[] cars = {'Honda', 'Toyota', 'Ford'};");
                    } else if (e.getSource() == battleButton3 && q2checker) {
                        if(!ray2) {
                            player.setQuestionsAnswered(player.getQuestionsAnswered() + 1);
                            if(player.getQuestionsAnswered() == 1) {
                                player.addAchievement("1 Question Answered");
                            }
                            else if(player.getQuestionsAnswered() == 10) {
                                player.addAchievement("10 Questions Answered");
                            }
                            else if(player.getQuestionsAnswered() == 25) {
                                player.addAchievement("25 Questions Answered");
                            }
                            ray2 = true;
                        }
                        setHp(getHp() - 1);
                        NPChealth.setText(getName() + " Health: " + getHp() + "/" + getMaxHP());
                        q2.setVisible(false);
                        q3.setText("Which of the following is a 2d array in Java?");
                        q3.setVisible(true);
                        q2checker = false;
                        q3checker = true;
                        battleButton1.setBounds(250,200,350,50);
                        battleButton2.setBounds(250,400,350,50);
                        battleButton3.setBounds(250,300,350,50);
                        battleButton1.setText("int[] 2darray = {1, 2, 3, 4}, {5, 6, 7, 8};");
                        battleButton2.setText("int[][] 1darray = {1, 2, 3, 4}, {5, 6, 7, 8};");
                        battleButton3.setText("int[][] 2darray = { {1, 2, 3, 4}, {5, 6, 7, 8} };");
                    } else if(e.getSource() == battleButton3) {
                        if(!ray3) {
                            player.setQuestionsAnswered(player.getQuestionsAnswered() + 1);
                            if(player.getQuestionsAnswered() == 1) {
                                player.addAchievement("1 Question Answered");
                            }
                            else if(player.getQuestionsAnswered() == 10) {
                                player.addAchievement("10 Questions Answered");
                            }
                            else if(player.getQuestionsAnswered() == 25) {
                                player.addAchievement("25 Questions Answered");
                            }
                            ray3 = true;
                        }
                        setHp(getHp() - 1);
                        NPChealth.setText(getName() + " Health: " + getHp() + "/" + getMaxHP());
                        q3.setVisible(false);
                        q3checker = false;
                        battleButton1.setVisible(false);
                        battleButton2.setVisible(false);
                        battleButton3.setVisible(false);
                        panel.add(congrats);
                        congrats.setVisible(true);
                        player.setEnemiesDefeated(player.getEnemiesDefeated() + 1);
                        if(player.getEnemiesDefeated() == 1) {
                            player.addAchievement("1 Enemy Defeated");

                        }
                        else if(player.getEnemiesDefeated() == 2) {
                            if(player.getPlayerClass() == 1) {
                                player.addAbilities("Data Analysis");
                            }

                            if(player.getPlayerClass() == 2) {
                                player.addAbilities("Faulty Security");
                            }

                            if(player.getPlayerClass() == 3) {
                                player.addAbilities("Trial and Error");
                            }
                        }
                        else if(player.getEnemiesDefeated() == 4) {
                            if(player.getPlayerClass() == 1) {
                                player.addAbilities("Data Analysis");
                            }

                            if(player.getPlayerClass() == 2) {
                                player.addAbilities("Faulty Security");
                            }

                            if(player.getPlayerClass() == 3) {
                                player.addAbilities("Trial and Error");
                            }
                        }
                        else if(player.getEnemiesDefeated() == 10) {
                            player.addAchievement("10 Enemy Defeated");
                        }
                        congrats.setVisible(true);
                        fleeButton.setVisible(false);
                        panel.add(backButton);
                        backButton.setVisible(true);
                        abilitylist.setVisible(false);
                        security.setVisible(false);
                        trialerror.setVisible(false);
                        analysis.setVisible(false);
                    }
                }
            });
        }

        if (getName().equals("Bug")) {
            setHp(1);
            setMaxHP(1);

            NPChealth.setText(getName() + " Health: " + getHp() + "/" + getMaxHP());

            fleeButton.setVisible(false);

            startButton.setVisible(false);

            bugfix = new JTextField();
            bugfix.setFont(new Font("Acumin Pro", Font.PLAIN, 12));
            bugfix.setSize(250, 25);
            bugfix.setLocation(600, 435);
            panel.add(bugfix);

            start.setText("<html>You have been ambushed by a bug! <br/> You must debug the following python code:</html>");

            start.setBounds(250, 100, 700, 300);

            q1.setBounds(250, 300, 700, 100);

            q1.setText("<html>nums = [1, 2, 3, 4, 5] <br/> for x in nums: <br/> &emsp print(x %% 3)</html>");
            q1.setVisible(true);

            q2.setBounds(150,370,700,100);
            q2.setText("On which line does the error occur?");
            q2.setForeground(Color.BLACK);
            q2.setVisible(true);

            q3.setBounds(600, 370, 700, 100);
            q3.setText("Rewrite the line of code to fix the error");
            q3.setForeground(Color.BLACK);
            q3.setVisible(true);

            submitButton.setVisible(true);

            dropdown.setVisible(true);

            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == submitButton && dropdown.getSelectedItem() == "3" && (bugfix.getText().equals("print(x % 3)") || bugfix.getText().equals("print(x%3)"))) {
                        setHp(getHp() - 1);
                        NPChealth.setText(getName() + " Health: " + getHp() + "/" + getMaxHP());
                        tryAgain.setVisible(false);
                        submitButton.setVisible(false);
                        dropdown.setVisible(false);
                        q1.setVisible(false);
                        q2.setVisible(false);
                        q3.setVisible(false);
                        start.setVisible(false);
                        bugfix.setVisible(false);
                        panel.add(backButton);
                        panel.add(congrats);
                        backButton.setVisible(true);
                        congrats.setVisible(true);
                        player.addAchievement("Bug Defeated");
                    } else {
                        player.setHp(player.getHp() - 5);
                        health.setText("My Health: " + player.getHp() + "/" + player.getMaxHP());
                        panel.add(tryAgain);
                        tryAgain.setVisible(true);
                        bugfix.setText("");
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
        }



        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == startButton) {
                    startButton.setVisible(false);
                    start.setVisible(false);
                    q1.setVisible(true);
                    panel.add(battleButton1);
                    battleButton1.setVisible(true);
                    panel.add(battleButton2);
                    battleButton2.setVisible(true);
                    panel.add(battleButton3);
                    battleButton3.setVisible(true);
                    abilitylist.setVisible(true);
                    if(player.getAbilities().contains("Trial and Error")) {
                        trialerror.setVisible(true);
                    }
                    if(player.getAbilities().contains("Data Analysis")) {
                        analysis.setVisible(true);
                    }
                    if(player.getAbilities().contains("Faulty Security")) {
                        security.setVisible(true);
                    }

                }
            }
        });

        battleButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trial -= 1;
                System.out.println(trial);
                if (e.getSource() == battleButton1 && trial <= 0) {
                    player.setHp(player.getHp() - 5);
                    if (getNPCType().equals("Boss")) {
                        player.setHp(player.getHp() - 5);
                    }
                    health.setText("My Health: " + player.getHp() + "/" + player.getMaxHP());
                    if (player.getHp() <= 0) {
                        security.setVisible(false);
                        trialerror.setVisible(false);
                        abilitylist.setVisible(false);
                        analysis.setVisible(false);
                        q1.setVisible(false);
                        q2.setVisible(false);
                        q3.setVisible(false);
                        battleButton1.setVisible(false);
                        battleButton2.setVisible(false);
                        battleButton3.setVisible(false);
                        loser.setVisible(true);
                        fleeButton.setVisible(false);
                        backButton.setVisible(true);
                    }
                }
                else if (trial > 0) {
                    check = 1;
                }
            }
        });

        battleButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trial -= 1;
                System.out.println(trial);
                if(e.getSource() == battleButton2 && trial <= 0) {
                    player.setHp(player.getHp() - 5);
                    if (getNPCType().equals("Boss")) {
                        player.setHp(player.getHp() - 5);
                    }
                    health.setText("My Health: " + player.getHp() + "/" + player.getMaxHP());
                    if (player.getHp() <= 0) {
                        security.setVisible(false);
                        trialerror.setVisible(false);
                        abilitylist.setVisible(false);
                        analysis.setVisible(false);
                        q1.setVisible(false);
                        q2.setVisible(false);
                        q3.setVisible(false);
                        battleButton1.setVisible(false);
                        battleButton2.setVisible(false);
                        battleButton3.setVisible(false);
                        loser.setVisible(true);
                        fleeButton.setVisible(false);
                        backButton.setVisible(true);
                    }
                }
                else if(trial > 0) {
                    check = 2;
                }
            }
        });



        loser = new JLabel("<html>You have been defeated by " + getName() + " in a battle, <br/> Better Luck Next time! </html>");
        loser.setBounds(250, 100, 700, 300);
        loser.setForeground(Color.BLACK);
        loser.setFont(font1);
        panel.add(loser);

        congrats = new JLabel("<html>You have defeated " + getName() + " in a battle, <br/> Congratulations! </html>");
        congrats.setBounds(250, 100, 700, 300);
        congrats.setForeground(Color.BLACK);
        congrats.setFont(font1);

        loser.setVisible(false);
        congrats.setVisible(false);


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == backButton) {
                    frame.dispose();
                }
            }
        });

        fleeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == fleeButton) {
                    frame.dispose();
                }
            }
        });

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
        cost1 = 0;
        cost2 = 0;
        cost3 = 0;
        image1 = "";
        image2 = "";
        image3 = "";

        if(getName().equals("Ron")) {
            item1 = new Item(dbConnection.getItemId("Gaming Laptop"), "Gaming Laptop", dbConnection.getItemDescription("Gaming Laptop"));
            item2 = new Item(dbConnection.getItemId("Algorithms Book"), "Algorithms Book", dbConnection.getItemDescription("Algorithms Book"));
            item3 = new Item(dbConnection.getItemId("Coffee"), "Coffee", dbConnection.getItemDescription("Coffee"));
            cost1 = 100;
            cost2 = 75;
            cost3 = 50;
            image1 = "Images/laptop.jpg";
            image2 = "Images/algo.png";
            image3 = "Images/coffee.jpg";
        }

        else if(getName().equals("Natalie")) {
            item1 = new Item(dbConnection.getItemId("Breadboard"), "Breadboard", dbConnection.getItemDescription("Breadboard"));
            item2 = new Item(dbConnection.getItemId("Deodorant"), "Deodorant", dbConnection.getItemDescription("Deodorant"));
            item3 = new Item(dbConnection.getItemId("Energy Drink"), "Energy Drink", dbConnection.getItemDescription("Energy Drink"));
            cost1 = 125;
            cost2 = 100;
            cost3 = 75;
            image1 = "Images/breadboard.jpg";
            image2 = "Images/deo.jpg";
            image3 = "Images/monster.jpg";
        }

        // Adding the items, costs, and images to the frame

        item1_prompt = new JLabel(item1.getName());
        item1_prompt.setBounds(275, 250, 500, 100);
        panel.add(item1_prompt);


        item1_cost = new JLabel(cost1 + " Gold             " + "Stock: " + getStock1());
        item1_cost.setBounds(250, 650, 500, 100);
        //Check if player already owns item
        if (dbConnection.playerHasItem(player.getUsername(), player.getName(), item1))
        {
            item1_cost.setText("Already own item");
            item1_cost.setForeground(Color.RED);
            item1_cost.setFont(font);
        }
        panel.add(item1_cost);

        item2_prompt = new JLabel(item2.getName());
        item2_prompt.setBounds(875, 250, 500, 100);
        panel.add(item2_prompt);

        item2_cost = new JLabel(cost2 + " Gold             " + "Stock: " + getStock2());
        item2_cost.setBounds(850, 650, 500, 100);
        //Check if player already owns item
        if (dbConnection.playerHasItem(player.getUsername(), player.getName(), item2))
        {
            item2_cost.setText("Already own item");
            item2_cost.setForeground(Color.RED);
            item2_cost.setFont(font);
        }
        panel.add(item2_cost);

        item3_prompt = new JLabel(item3.getName());
        item3_prompt.setBounds(1500, 250, 500, 100);
        panel.add(item3_prompt);

        item3_cost = new JLabel(cost3 + " Gold             " + "Stock: " + getStock3());
        item3_cost.setBounds(1475, 650, 500, 100);
        //Check if player already owns item
        if (dbConnection.playerHasItem(player.getUsername(), player.getName(), item3))
        {
            item3_cost.setText("Already own item");
            item3_cost.setForeground(Color.RED);
            item3_cost.setFont(font);
        }
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

                            player.getInventory().addItem(player.getUsername(), player.getName(), item1);
                            if (getStock1() == 0) {
                                item1_cost.setText("OUT OF STOCK!");
                                item1_cost.setForeground(Color.RED);
                                item1_cost.setFont(font);
                            } else {
                                //item1_cost.setText(cost1 + " Gold             " + "Stock: " + getStock1());
                                item1_cost.setText("Already own item");
                                item1_cost.setForeground(Color.RED);
                                item1_cost.setFont(font);
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

                            player.getInventory().addItem(player.getUsername(), player.getName(), item2);

                            if (getStock2() == 0) {
                                item2_cost.setText("OUT OF STOCK!");
                                item2_cost.setForeground(Color.RED);
                                item2_cost.setFont(font);
                            } else {
                                //item2_cost.setText(cost2 + " Gold             " + " Stock: " + getStock2());
                                item2_cost.setText("Already own item");
                                item2_cost.setForeground(Color.RED);
                                item2_cost.setFont(font);
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

                            player.getInventory().addItem(player.getUsername(), player.getName(), item3);
                            setStock3(getStock3() - 1);
                            if (getStock3() == 0) {
                                item3_cost.setText("OUT OF STOCK!");
                                item3_cost.setForeground(Color.RED);
                                item3_cost.setFont(font);
                            } else {
                                //item3_cost.setText(cost3 + " Gold            " + " Stock: " + getStock3());
                                item3_cost.setText("Already own item");
                                item3_cost.setForeground(Color.RED);
                                item3_cost.setFont(font);
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

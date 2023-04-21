import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.Random;

public class World{
    /*
     * Class Will load the world in ie the iterations of tiles and display it as a canvas
     * Enjoy :)
     * World map of tiles
     * Ability to check adjacent tiles (up down left right)
     *       when player moves it will check if it can go there first then if it returns true, the player will move tiles
     */

    /*
     * Java functionality Array[height/#rows][width/#col]
     * */
    private Tile[][] worldMap;

    private boolean checkMusic = false;
    private final int height; //y coord
    private final int length; // x coord
    private int[] spawnPoint;
    private int[] endPoint;
    private int[] backPoint;//where you go to go back a level
    private int[] currLoc;
    public int tileSize;
    public float numLevels;
    boolean pause;
    boolean complete;
    boolean goBack;
    private int[] checkPoint;
    public static int invOpen;
    public static int skillsOpen;
    BackgroundMusic bm;

    private JButton shop, shop2, battle, battle2, battle3, battle4, home, menubuttons;

    private boolean checker, checker2, checker3, checker4, checker5, checker6, checker7;
    private boolean bugCheck = true;

    private Player player;
    JFrame frame;
    JLabel playerLabel, gameProgress,NPC1label, NPC2label, NPC3label, NPC4label, NPC5label, NPC6label, NPC7label;
    Action moveUp = new upAction();
    Action moveDown = new downAction();
    Action moveLeft = new leftAction();
    Action moveRight = new rightAction();
    Action menuAction = new menuAction();
    Action pauseGame = new pauseAction();
    Action inventoryAction = new inventoryAction();
    Action skillsAction = new skillsAction();
    boolean exit;


    public World(int height, int length, int[][] tileType, Player player, float numLevels){ /*TODO: ADD PLAYER FIELD*/
        //super(player.getName());

       // bm = new BackgroundMusic("game");
       // bm.play();//plays music

        this.worldMap = new Tile[height][length];
        this.height = height; //y
        this.length = length; // x
        this.player = player;
        this.spawnPoint = player.getLocation(); //pick up where last left    //new int[]{0, 0};//spawn point in reference to world map
        this.currLoc = spawnPoint;//location in tiles (reference to current map
        this.endPoint = new int[] {27,43};
        checkPoint = new int[] {0,48};
        backPoint = new int[] {48,0};
        this.tileSize = 10;
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.length; x++) {
                worldMap[y][x] = new Tile(tileType[y][x]);
            }
        }
        exit = false; //exit the graphics loop
        complete = false; //level was completed
        goBack = false; //go back a level
        pause = false; //is game paused
        this.numLevels = numLevels;
    }//constructor of world

    public World(int height, int length, Tile[][] tileMap, Player player, float numLevels){ /*TODO: ADD PLAYER FIELD*/
        //super(player.getName());

      //  bm = new BackgroundMusic("game");
      //  bm.play();//plays music

        this.worldMap = new Tile[height][length];
        this.height = height; //y
        this.length = length; // x
        this.player = player;
        this.spawnPoint = player.getLocation(); //pick up where last left    //new int[]{0, 0};//spawn point in reference to world map
        this.currLoc = spawnPoint;//location in tiles (reference to current map
        this.endPoint = new int[] {27,43};
        checkPoint = new int[] {0,48};
        backPoint = new int[] {48,0};
        this.tileSize = 10;
        worldMap = tileMap;
        exit = false; //exit the graphics loop
        complete = false; //level was completed
        goBack = false; //go back a level
        pause = false; //is game paused
        this.numLevels = numLevels;
    }//constructor of world

    public int[] getAdjTiles(int x, int y){// will teturn the tile type to the player -1 if it doesnt exist up down left right
        int[] adjTiles = new int[4];
        adjTiles[0] = getUp(x, y);//up
        adjTiles[1] = getDown(x, y);//down
        adjTiles[2] = getLeft(x, y);//left
        adjTiles[3] = getRight(x, y);//right
        return adjTiles;
    } //get adj tiles

    public int getUp(int x, int y){ //returns type of tile above
        if (y-1 < 0){return  -1;} else {
            return worldMap[y - 1][x].getType();//up
        }
    }//getup
    public int getDown(int x, int y){
        if (y+1 > this.height){return  -1;} else {
            return worldMap[y + 1][x].getType();//down
        }
    }//getdown
    public int getLeft(int x, int y){
        if (x-1 < 0){return -1;} else {
            return worldMap[y][x-1].getType();//left
        }
    }//get left
    public int getRight(int x, int y){
        if (x+1 >= this.length){return -1;} else {
            return worldMap[y][x+1].getType();//right
        }
    }//get right

    public Tile getTile(int x, int y){return this.worldMap[y][x];}

    public JFrame getFrame() {
        return frame;
    }

    public void setSpawnPoint(int x, int y){
        this.spawnPoint[0] = x;
        this.spawnPoint[1] = y;
    }

    public void setCurrLoc(int x, int y){
        this.currLoc[0] = x;
        this.currLoc[1] = y;
    }

    void playSound(String soundFile) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        File f = new File(soundFile);
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
    }


    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int displayWorld() throws UnsupportedAudioFileException, LineUnavailableException, IOException { //tiles are tilesize x tilesize pixels generated from (0,0) to (8*length, 8*height) (x,y) respectively

        bm = new BackgroundMusic("game");
        bm.play();//plays music

        goBack = false; //go back a level

        long start = System.currentTimeMillis();//sets start time to calculate time played

        // Creating NPCs and shop windows

        MenuButtons a = new MenuButtons(player.getName());

        Home homer = new Home(player);

        homer.setImage("Images/house.jpg");


        NPC ron = new NPC("Ron", "Neutral");

        NPC natalie = new NPC("Natalie", "Neutral");

        NPC sam = new NPC("Sam", "Neutral");

        NPC hello = new NPC("Hello", "Enemy");

        NPC loopy = new NPC("Loopy", "Enemy");

        NPC ray = new NPC("Ray", "Enemy");

        NPC jeff = new NPC("Jeff", "Boss");

        NPC bug = new NPC("Bug", "Enemy");


        ron.setLocation(3, 3);


        ron.setStock1(1);
        ron.setStock2(1);
        ron.setStock3(3);

        natalie.setLocation(6, 6);


        natalie.setStock1(2);
        natalie.setStock2(2);
        natalie.setStock3(4);

        sam.setLocation(15, 25);


        hello.setLocation(10, 6);



        loopy.setLocation(35, 5);



        ray.setLocation(28, 45);




        jeff.setLocation(40,30);




        // Adding a base set of abilities

        if(player.getPlayerClass() == 1) {
            player.addAbilities("Trial and Error");
        }

        if(player.getPlayerClass() == 2) {
            player.addAbilities("Data Analysis");
        }

        if(player.getPlayerClass() == 3) {
            player.addAbilities("Faulty Security");
        }




        final String title = "Game World: Software Chasing";
        //final int frameWidth = 916;//(this.length + 1) * tileSize;
        //final int frameHeight = 1016;//(this.height+1) * tileSize;
        setTileSize(10);
        int frameWidth = tileSize * this.length;
        int frameHeight = frameWidth + 100;

        //Creating the frame.
        frame = new JFrame(title);

        frame.setSize(frameWidth+16, frameHeight + 39);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setVisible(true);

        // Test to see if adding the same ability does not show up in the world
        // player.addAbilities("MULTISHOT");


        //creating "player" label
        playerLabel = new JLabel();
        //playerLabel.setBackground(Color.green); //CHANGE PLAYER APPEARANCE
        playerLabel.setBounds(this.spawnPoint[0],this.spawnPoint[1],1,1);
        playerLabel.setOpaque(false);

        //make actions for player label
        playerLabel.getInputMap().put(KeyStroke.getKeyStroke('w'), "upAction");
        playerLabel.getActionMap().put("upAction", moveUp);
        playerLabel.getInputMap().put(KeyStroke.getKeyStroke('s'), "downAction");
        playerLabel.getActionMap().put("downAction", moveDown);
        playerLabel.getInputMap().put(KeyStroke.getKeyStroke('a'), "leftAction");
        playerLabel.getActionMap().put("leftAction", moveLeft);
        playerLabel.getInputMap().put(KeyStroke.getKeyStroke('d'), "rightAction");
        playerLabel.getActionMap().put("rightAction", moveRight);

        playerLabel.getInputMap().put(KeyStroke.getKeyStroke('p'),"pauseAction");
        playerLabel.getActionMap().put("pauseAction",pauseGame);

        /*TODO This is the input for the player label to access the MENU ACTION AJ */
        playerLabel.getInputMap().put(KeyStroke.getKeyStroke('m'), "menuAction");
        playerLabel.getActionMap().put("menuAction", menuAction);

        playerLabel.getInputMap().put(KeyStroke.getKeyStroke('i'), "inventoryAction");
        playerLabel.getActionMap().put("inventoryAction", inventoryAction);

        playerLabel.getInputMap().put(KeyStroke.getKeyStroke('k'), "skillsAction");
        playerLabel.getActionMap().put("skillsAction", skillsAction);

        //add player label to the frame
        frame.add(playerLabel);

        //create level display
        float currentLevel = player.getCurrentLevelNo();


        gameProgress = new JLabel("Level: " + (int) (currentLevel + 1),SwingConstants.CENTER);
        gameProgress.setBounds(0,frameHeight-50,100,50);
        if (((currentLevel + 1)/numLevels) < .4){gameProgress.setBackground(Color.red);} //first portion of levels
        else if (((currentLevel + 1)/numLevels) < .7){gameProgress.setBackground(Color.yellow);}//second portion of levels
        else {gameProgress.setBackground(Color.green);}//last portion of levels
        gameProgress.setOpaque(true);



        //create the logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(0,frameHeight-100,100,50);
        logoutButton.setBackground(Color.decode("#daaa00"));

        //add logout action
        logoutButton.addActionListener(new ActionListener() {
            //logout was hit, so we write player data out and return to caller
            @Override
            public void actionPerformed(ActionEvent e) {


                //calculate time played
                long end = System.currentTimeMillis();
                long played = end - start;
                player.setTimePlayed(player.getTimePlayed() + played);

                bm.pause();

                dbConnection.updatePlayer(player);
                System.out.println("timeplayed after: " + + (player.getTimePlayed() / 1000) % 60 + "seconds");
                dbConnection.logout(player.getUsername());
                exit = true;//break out of display loop
                frame.dispose();
                //System.exit(69); Nice
            }
        });

        //add the logout button to the frame
        frame.add(logoutButton);

        //add the level display to the frame
        frame.add(gameProgress);

        // Create pop up and shop button for interacting with NPCs

        NPC1label = new JLabel(ron.getEncounter());
        NPC1label.setFont(new Font("Acumin Pro", Font.PLAIN, 15));
        NPC1label.setSize(130, 25);
        NPC1label.setLocation(150, 380);
        NPC1label.setVisible(false);

        shop = new JButton("Shop");
        shop.setBounds(250, 400, 100, 50);
        shop.setBackground(Color.decode("#9d9795"));
        shop.setVisible(false);

        shop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == shop) {
                    ron.displayShop(player);
                    shop.setVisible(false);
                }
            }
        });

        frame.add(NPC1label);
        frame.add(shop);

        // Create pop up and shop button for interacting with NPCs

        NPC2label = new JLabel(natalie.getEncounter());
        NPC2label.setFont(new Font("Acumin Pro", Font.PLAIN, 15));
        NPC2label.setSize(150, 25);
        NPC2label.setLocation(130, 380);
        NPC2label.setVisible(false);

        // Create shop button for interacting with NPCs

        shop2 = new JButton("Shop");
        shop2.setBounds(250, 400, 100, 50);
        shop2.setBackground(Color.decode("#9d9795"));
        shop2.setVisible(false);

        shop2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == shop2) {
                    natalie.displayShop(player);
                    shop2.setVisible(false);
                }
            }
        });

        battle = new JButton("Battle");
        battle.setBounds(250, 400, 100, 50);
        battle.setBackground(Color.decode("#9d9795"));
        battle.setVisible(false);

        battle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == battle) {
                    hello.displayBattle(player);
                    battle.setVisible(false);
                }
            }
        });

        battle2 = new JButton("Battle");
        battle2.setBounds(250, 400, 100, 50);
        battle2.setBackground(Color.decode("#9d9795"));
        battle2.setVisible(false);

        battle2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == battle2) {
                    loopy.displayBattle(player);
                    battle2.setVisible(false);
                }
            }
        });

        battle3 = new JButton("Battle");
        battle3.setBounds(250, 400, 100, 50);
        battle3.setBackground(Color.decode("#9d9795"));
        battle3.setVisible(false);

        battle3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == battle3) {
                    ray.displayBattle(player);
                    battle3.setVisible(false);
                }
            }
        });

        battle4 = new JButton("Battle");
        battle4.setBounds(250, 400, 100, 50);
        battle4.setBackground(Color.decode("#9d9795"));
        battle4.setVisible(false);

        battle4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == battle4) {
                    bm.resume();
                    checkMusic = false;
                    jeff.displayBattle(player);
                    battle.setVisible(false);
                }
            }
        });

        frame.add(NPC1label);
        frame.add(shop);

        frame.add(NPC2label);
        frame.add(shop2);

        frame.add(battle);
        frame.add(battle2);
        frame.add(battle3);
        frame.add(battle4);

        home = new JButton("Home");
        home.setBounds(125, 400, 100, 50);
        home.setBackground(Color.decode("#9d9795"));
        home.setVisible(false);

        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == home) {
                    homer.changeVisibility(true);
                    home.setVisible(false);
                }
            }
        });

        frame.add(home);

        NPC3label = new JLabel(sam.getEncounter());
        NPC3label.setFont(new Font("Acumin Pro", Font.PLAIN, 15));
        NPC3label.setSize(150, 25);
        NPC3label.setLocation(130, 380);
        NPC3label.setVisible(false);

        frame.add(NPC3label);

        NPC4label = new JLabel(hello.getEncounter());
        NPC4label.setFont(new Font("Acumin Pro", Font.PLAIN, 15));
        NPC4label.setSize(350, 25);
        NPC4label.setLocation(50, 380);
        NPC4label.setVisible(false);

        frame.add(NPC4label);

        NPC5label = new JLabel(loopy.getEncounter());
        NPC5label.setFont(new Font("Acumin Pro", Font.PLAIN, 15));
        NPC5label.setSize(350, 25);
        NPC5label.setLocation(50, 380);
        NPC5label.setVisible(false);

        frame.add(NPC5label);

        NPC6label = new JLabel(ray.getEncounter());
        NPC6label.setFont(new Font("Acumin Pro", Font.PLAIN, 15));
        NPC6label.setSize(350, 25);
        NPC6label.setLocation(50, 380);
        NPC6label.setVisible(false);

        frame.add(NPC6label);

        NPC7label = new JLabel(jeff.getEncounter());
        NPC7label.setFont(new Font("Acumin Pro", Font.PLAIN, 15));
        NPC7label.setSize(350, 25);
        NPC7label.setLocation(50, 380);
        NPC7label.setVisible(false);

        frame.add(NPC7label);



        //create the stats bar
        JPanel statPanel = new JPanel();
        statPanel.setBackground(Color.decode("#ddb945"));
        statPanel.setSize(frameWidth-100,100);
        statPanel.setLocation(100,frameHeight-100);
        //statPanel.setBounds(100,frameHeight-tileSize,frameWidth-100,100);
        statPanel.setVisible(true);
        statPanel.setOpaque(true);

        // Create menu buttons for extra things like reporting and feedback

        menubuttons = new JButton("Menu");
        menubuttons.setBounds(100, frameHeight-100, 100, 50); //fix this later to make it pretty (riley)
        menubuttons.setBackground(Color.decode("#9d9795"));
        menubuttons.setVisible(true);

        menubuttons.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == menubuttons) {
                    a.changeVisibility();
                    menubuttons.setVisible(false);
                }
            }
        });
        statPanel.add(menubuttons);

        //add a pause button in the future?


        //TODO add player stat
        JLabel health = new JLabel("HP: " + player.getHp() + "/" + player.getMaxHP());
        health.setBackground(Color.white);
        //health.setBounds(20,20,50,50);
        health.setOpaque(false);
        health.setVisible(true);

        JLabel stamina = new JLabel("Stamina: " + player.getStamina() + "/" + player.getMaxStamina());
        stamina.setBackground(Color.white);
        //stamina.setBounds(20,frameHeight-(tileSize+20),50,50);
        stamina.setOpaque(false);
        stamina.setVisible(true);

        JLabel timeplayed = new JLabel("time played: " + (player.getTimePlayed() / 1000) % 60 + " seconds");
        timeplayed.setBackground(Color.white);
        //stamina.setBounds(20,frameHeight-(tileSize+20),50,50);
        timeplayed.setOpaque(false);
        timeplayed.setVisible(true);

        JLabel intellect = new JLabel("Intellect: " + 5);
        //health.setBackground(Color.white);
        //intellect.setBounds(20,frameHeight-(tileSize+40),50,50);
        intellect.setOpaque(false);
        intellect.setVisible(true);

        JLabel speed = new JLabel("Speed: " + player.getSpeed());
        //health.setBackground(Color.white);
        //intellect.setBounds(20,frameHeight-(tileSize+60),50,50);
        speed.setOpaque(false);
        speed.setVisible(true);

        JLabel level = new JLabel("Level: " + player.getLevel());
        //health.setBackground(Color.white);
        //intellect.setBounds(20,frameHeight-(tileSize+60),50,50);
        level.setOpaque(false);
        level.setVisible(true);

        JLabel xp = new JLabel("XP Needed: " + player.getLevelXP());
        //health.setBackground(Color.white);
        //intellect.setBounds(20,frameHeight-(tileSize+60),50,50);
        xp.setOpaque(false);
        xp.setVisible(true);

        JLabel gold = new JLabel("Gold: " + player.getGold());
        gold.setOpaque(false);
        gold.setVisible(true);

        //THIS LABEL CAUSES ISSUES LOOK INTO AT END IF THERES TIME- Riley
//        JLabel playerClass = new JLabel("Class: " + player.getPlayerClassName());
//        playerClass.setOpaque(false);
//        playerClass.setVisible(true);

        //Implement current time clock
        JLabel clockLabel = new JLabel();
        clockLabel.setOpaque(false);
        clockLabel.setVisible(true);

        Timer t = new Timer(1000, new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                long millis = System.currentTimeMillis();
                int hour = (int)(((millis / (1000*60*60)) % 24) - 4);

                if (hour == 0)
                {
                    hour = 12;
                }
                else if (hour == -1)
                {
                    hour = 11;
                }
                else if (hour == -2)
                {
                    hour = 10;
                }
                else if (hour == -3)
                {
                    hour = 9;
                }

                int minute = (int) ((millis / (1000*60)) % 60);
                String minString = "";

                if (minute == 0)
                {
                    minString = "00";
                }
                else if (minute == 1)
                {
                    minString = "01";
                }
                else if (minute == 2)
                {
                    minString = "02";
                }
                else if (minute == 3)
                {
                    minString = "03";
                }
                else if (minute == 4)
                {
                    minString = "04";
                }
                else if (minute == 5)
                {
                    minString = "05";
                }
                else if (minute == 6)
                {
                    minString = "06";
                }
                else if (minute == 7)
                {
                    minString = "07";
                }
                else if (minute == 8)
                {
                    minString = "08";
                }
                else if (minute == 9)
                {
                    minString = "09";
                }
                else
                {
                    minString = Integer.toString((int)((millis / (1000*60)) % 60));
                }


                clockLabel.setText(String.format("Current Time: %s %d:%s",
                        java.time.LocalDate.now(),
                        hour,
                        minString
                ));

            }
        });
        t.start();

        statPanel.add(health);
        statPanel.add(stamina);
        statPanel.add(timeplayed);
        //statPanel.add(intellect);
        statPanel.add(speed);
        statPanel.add(level);
        statPanel.add(xp);
        statPanel.add(gold);
//        statPanel.add(playerClass); Label didnt work see above coment out for more -Riley
        statPanel.add(clockLabel);

        frame.add(statPanel);



        //Creating the canvas.
        Canvas canvas = new Canvas();

        canvas.setSize(frameWidth, frameHeight);
        //canvas.setBackground(Color.BLACK);
        canvas.setVisible(true);
        canvas.setFocusable(false);

        //Putting it all together.
        frame.add(canvas);
        canvas.createBufferStrategy(3);


        BufferStrategy bufferStrategy;
        Graphics graphics;

        bufferStrategy = canvas.getBufferStrategy();
        graphics = bufferStrategy.getDrawGraphics();

        //frame.add(new CharacterPanel());

        while (true) { // will add movements in here and wait for certain motions to keep displaying this

            bufferStrategy = canvas.getBufferStrategy();
            graphics = bufferStrategy.getDrawGraphics();

            menubuttons.setVisible(true);

            // Make sure the home button can be seen on multiple NPCs

            if(currLoc[0] == ron.getLocation()[0] && currLoc[1] == ron.getLocation()[1] || currLoc[0] == natalie.getLocation()[0] && currLoc[1] == natalie.getLocation()[1]) {
                home.setVisible(true);
            }

            else {
                home.setVisible(false);
            }



            // Tester to see if when a player reaches a certain tile with an NPC they can open a shop

            if(currLoc[0] == ron.getLocation()[0] && currLoc[1] == ron.getLocation()[1]) {
                if(!checker) {
                    System.out.println(ron.getNPCType());
                    playSound("Music/doorbell.wav");
                    NPC1label.setVisible(true);
                    shop.setVisible(true);
                    checker = true;
                }
            }
            else {
                checker = false;
                NPC1label.setVisible(false);
                shop.setVisible(false);
            }

            // Now add another NPC and see if they can both work together

            if(currLoc[0] == natalie.getLocation()[0] && currLoc[1] == natalie.getLocation()[1]) {
                if(!checker2) {
                    playSound("Music/doorbell.wav");
                    NPC2label.setVisible(true);
                    shop2.setVisible(true);
                    checker2 = true;
                }
            }
            else {
                checker2 = false;
                NPC2label.setVisible(false);
                shop2.setVisible(false);
            }

            // Now add NPC3

            if(currLoc[0] == sam.getLocation()[0] && currLoc[1] == sam.getLocation()[1]) {
                if(!checker3) {
                    NPC3label.setVisible(true);
                    checker3 = true;

                    playSound("Music/doorbell.wav");
                }
            }
            else {
                checker3 = false;
                NPC3label.setVisible(false);
            }

            // Now add NPC4

            if(currLoc[0] == hello.getLocation()[0] && currLoc[1] == hello.getLocation()[1]) {
                if(!checker4) {
                    playSound("Music/drums.wav");
                    NPC4label.setVisible(true);
                    battle.setVisible(true);
                    checker4 = true;
                }
            }
            else {
                checker4 = false;
                NPC4label.setVisible(false);
                battle.setVisible(false);
            }


            // Now add NPC5

            if(currLoc[0] == loopy.getLocation()[0] && currLoc[1] == loopy.getLocation()[1]) {
                if(!checker5) {
                    playSound("Music/drums.wav");
                    NPC5label.setVisible(true);
                    battle2.setVisible(true);
                    checker5 = true;
                }
            }
            else {
                checker5 = false;
                NPC5label.setVisible(false);
                battle2.setVisible(false);
            }

            // Now add NPC6

            if(currLoc[0] == ray.getLocation()[0] && currLoc[1] == ray.getLocation()[1]) {
                if(!checker6) {
                    playSound("Music/drums.wav");
                    NPC6label.setVisible(true);
                    battle3.setVisible(true);
                    checker6 = true;
                }
            }
            else {
                checker6 = false;
                NPC6label.setVisible(false);
                battle3.setVisible(false);
            }

            // Now add NPC7

            if(currLoc[0] == jeff.getLocation()[0] && currLoc[1] == jeff.getLocation()[1]) {
                if(!checker7) {
                    bm.pause();
                    checkMusic = true;
                    playSound("Music/boss.wav");
                    NPC7label.setVisible(true);
                    battle4.setVisible(true);
                    checker7 = true;
                }
            }
            else {
                if(checkMusic) {
                    bm.resume();
                    checkMusic = false;
                }
                checker7 = false;
                NPC7label.setVisible(false);
                battle4.setVisible(false);
            }

            // Now add Random Encounter

            if(player.getTilesWalked() == 54 && bugCheck) {
                bugCheck = false;
                bug.displayBattle(player);
            }

            //graphics.clearRect(0, 0, width, height);

            for (int y = 0; y < this.height; y++) {
                for (int x = 0; x < this.length; x++) {
                    //get color of tile based on type
                    int type = this.worldMap[y][x].getType();
                    //0 is wall 1 is floor
                    // bm.play();//play backgrounf music if not paused
                    if (pause == true){
                        graphics.setColor(Color.BLACK);
                        bm.pause();
                    }//game is paused #5faae2
                    else if (currLoc[0] == x && currLoc[1] == y){graphics.setColor(Color.YELLOW);} //player is here
                    else if (x == endPoint[0] && y == endPoint[1]){graphics.setColor(Color.GREEN);}//end point color
                    else if (x == checkPoint[0] && y == checkPoint[1]){graphics.setColor(Color.decode("#5faae2"));}//end point color
                    else if (x == backPoint[0] && y == backPoint[1]){graphics.setColor(Color.decode("#d15793"));}//back point color
                    else if ((x == 3 && y == 3) | (x == 6 && y == 6) | (x == 15 && y == 25)) {graphics.setColor(Color.BLUE);}
                    else if (hello.getHp() > 0 && x == 10 && y == 6) {graphics.setColor(Color.ORANGE);}
                    else if (loopy.getHp() > 0 && x == 35 && y == 5) {graphics.setColor(Color.ORANGE);}
                    else if (ray.getHp() > 0 && x == 28 && y == 45) {graphics.setColor(Color.ORANGE);}
                    else if (jeff.getHp() > 0 && x == 40 && y == 30) {graphics.setColor(Color.RED);}
                    else if (type == 0) {graphics.setColor(Color.black);}else {graphics.setColor(Color.white);}
                    /*TODO load a tile image*/
                    graphics.fillRect(x*tileSize,y*tileSize,tileSize,tileSize); //TILE IMAGE WILL GO HERE LATER
                }
            }

            //Keep health updated
            health.setText("HP: " + player.getHp() + "/" + player.getMaxHP());
            stamina.setText("Stamina: " + player.getStamina() + "/" + player.getMaxStamina());
            speed.setText("Speed: " + player.getSpeed());
            level.setText("Level: " + player.getLevel());
            xp.setText("XP Needed: " + player.getLevelXP());
            gold.setText("Gold: " + player.getGold());

            bufferStrategy.show();
            graphics.dispose();
            if (exit){break;}
        }//DISPLAY LOOP
        frame.dispose();

        //return values for game.java
        //1 go to the next level
        //0 stay the same level
        //-1 go back a level

        System.out.println("COMPLETE: " + complete + "GO BACK: " + goBack);
        if (goBack){return -1;}
        if (complete || (player.getCurrentLevelNo() < player.getMaxLevelNO())){return 1;} else {return 0;}
    }//END DISPLAY WORLD

    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        int[][] tiles = new int[50][50];
        int count = 1;
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                //tiles[i][j] = 1;
                if ((i % 4 ==1 && j % 4 ==1) || i == 49){tiles[i][j] = 0;} else {tiles[i][j] =1;}
//                tiles[i][j] = (count + i)% 2;
//                count++;
            }
        }
        Player player = new Player("RILEY6215","Riley",1);
        
//        Player player = dbConnection.getPlayers("RILEY6215")[0];
        System.out.println("timeplayed before: " + (player.getTimePlayed() / 1000) % 60);
        World test = new World(50,50,tiles,player,1);
        //test.setPlayer(player);
        test.displayWorld();
    }

    public class upAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (pause == true){return;}
            if (getUp(currLoc[0],currLoc[1])!= 1) {return;} //illegal movement cant move because it isn't a walkable tile
            playerLabel.setLocation(playerLabel.getX(),playerLabel.getY() - tileSize);//x then y
            setCurrLoc(currLoc[0], currLoc[1] - 1 );
            player.setLocation(currLoc);
            player.setTilesWalked(player.getTilesWalked() + 1);
            if(player.getTilesWalked() == 10) {
                player.addAchievement("10 Tiles Walked");
            }
            else if(player.getTilesWalked() == 100) {
                player.addAchievement("100 Tiles Walked");
            }
            else if(player.getTilesWalked() == 1000) {
                player.addAchievement("1000 Tiles Walked");
            }
            System.out.println("Tiles walked " + player.getTilesWalked());
            System.out.println(" UP LOCATION" + currLoc[0] +"  Y: "+ currLoc[1]);

            checkLevelComplete(currLoc);
            try {
                checkLevelChange(currLoc);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }

            try {
                playSound("Music/footstep.wav");
            } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }//up action
    public class downAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (pause == true){return;}
            if (getDown(currLoc[0],currLoc[1])!= 1) {return;} //illegal movement cant move because it isn't a walkable tile
            playerLabel.setLocation(playerLabel.getX(),playerLabel.getY() + tileSize);//x then y
            setCurrLoc(currLoc[0], currLoc[1] + 1 );
            player.setLocation(currLoc);
            player.setTilesWalked(player.getTilesWalked() + 1);
            System.out.println("Tiles walked " + player.getTilesWalked());
            if(player.getTilesWalked() == 10) {
                player.addAchievement("10 Tiles Walked");
            }
            else if(player.getTilesWalked() == 100) {
                player.addAchievement("100 Tiles Walked");
            }
            else if(player.getTilesWalked() == 1000) {
                player.addAchievement("1000 Tiles Walked");
            }
            System.out.println(" DOWN LOCATION" + " X: "+ currLoc[0] +"  Y: "+ currLoc[1]);

            checkLevelComplete(currLoc);
            try {
                checkLevelChange(currLoc);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }

            try {
                playSound("Music/footstep.wav");
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            }

        }
    }//down action
    public class leftAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (pause == true){return;}

            if (getLeft(currLoc[0],currLoc[1])!= 1) {return;} //illegal movement cant move because it isn't a walkable tile
            playerLabel.setLocation(playerLabel.getX() - tileSize,playerLabel.getY());//x then y
            setCurrLoc(currLoc[0] - 1, currLoc[1] );
            player.setLocation(currLoc);
            player.setTilesWalked(player.getTilesWalked() + 1);
            if(player.getTilesWalked() == 10) {
                player.addAchievement("10 Tiles Walked");
            }
            else if(player.getTilesWalked() == 100) {
                player.addAchievement("100 Tiles Walked");
            }
            else if(player.getTilesWalked() == 1000) {
                player.addAchievement("1000 Tiles Walked");
            }
            System.out.println("Tiles walked " + player.getTilesWalked());
            System.out.println("LEFT LOCATION X: " + currLoc[0] +"  Y: "+ currLoc[1]);

            checkLevelComplete(currLoc);
            try {
                checkLevelChange(currLoc);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }

            try {
                playSound("Music/footstep.wav");
            } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }//left action
    public class rightAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (pause == true){return;}
            if (getRight(currLoc[0],currLoc[1])!= 1) {return;} //illegal movement cant move because it isn't a walkable tile
            playerLabel.setLocation(playerLabel.getX() + tileSize,playerLabel.getY());//x then y
            setCurrLoc(currLoc[0] + 1, currLoc[1] );
            player.setLocation(currLoc);
            player.setTilesWalked(player.getTilesWalked() + 1);
            if(player.getTilesWalked() == 10) {
                player.addAchievement("10 Tiles Walked");
            }
            else if(player.getTilesWalked() == 100) {
                player.addAchievement("100 Tiles Walked");
            }
            else if(player.getTilesWalked() == 1000) {
                player.addAchievement("1000 Tiles Walked");
            }
            System.out.println("Tiles walked " + player.getTilesWalked());
            System.out.println("Right LOCATION X: " + currLoc[0] +"  Y: "+ currLoc[1]);

            checkLevelComplete(currLoc);
            try {
                checkLevelChange(currLoc);
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
                throw new RuntimeException(ex);
            }

            try {
                playSound("Music/footstep.wav");
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            }

        }
    }//up action

    public class pauseAction extends AbstractAction{ //hit p to pause and resume
        @Override
        public void actionPerformed(ActionEvent e) {
            pause = !pause;
            if(pause == true){//pause or play music with game pause
                bm.pause();;
            } else{
                bm.resume();
            }
            System.out.println("pause");
        }
    }//pause action


    /*TODO THIS IS THE MENU ACTION AJ*/
    public class menuAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("MENU:");
            MenuButtons menu = new MenuButtons(player.getUsername());
            menu.actionPerformed(e);
        }
    }//up action

    public class inventoryAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (invOpen == 1)
            {
                //Inventory already open

            }
            else {
                System.out.println("INVENTORY");
                invOpen = 1;
                InventoryDisplay inventoryDisplay = new InventoryDisplay(player);
                //inventoryDisplay.actionPerformed(e);
            }
        }
    }

    public class skillsAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (skillsOpen == 1)
            {
                //Skills panel already open
            }
            else {
                System.out.println("SKILLS PANEL");
                skillsOpen = 1;
                SkillsDisplay skillsDisplay = new SkillsDisplay(player);
            }
        }
    }

    public void resetPlayer() {
        this.player.setLocation(new int[]{0,0});
        this.currLoc = new int[]{0,0};
    }

    public void checkLevelChange(int[] currLoc1) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if ((currLoc1[0] == endPoint[0] && currLoc1[1] == endPoint[1]) && (complete || (player.getCurrentLevelNo() < player.getMaxLevelNO())) ){//player got to the end point
            playSound("Music/level.wav");
            System.out.println("Next Level!");
            exit = true;
        } //player got to the end point

        if (currLoc1[0] == backPoint[0] && currLoc1[1] == backPoint[1] ){//player got to the end point
            System.out.println("Previous Level!");
            goBack = true;
            exit = true;
        }//player is at back point

    }//end level change

    public void checkLevelComplete(int[] currLoc1){
        if (currLoc1[0] == checkPoint[0] && currLoc1[1] == checkPoint[1]) {
            complete = true;
        }
    }

    public Tile[][] getWorldMap() {
        return worldMap;
    }
}// END CLASS
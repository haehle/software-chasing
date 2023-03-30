import javax.imageio.ImageIO;
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
    private final int height; //y coord
    private final int length; // x coord
    private int[] spawnPoint;
    private int[] endPoint;
    private int[] currLoc;
    public int tileSize;
    boolean pause;
    boolean complete;

    private JButton shop, shop2, battle, battle2, battle3, battle4, home, menubuttons,pauseButton;

    private boolean checker, checker2, checker3, checker4, checker5, checker6, checker7;

    private Player player;
    JFrame frame;
    JLabel playerLabel, NPC1label, NPC2label, NPC3label, NPC4label, NPC5label, NPC6label, NPC7label;

    Action moveUp = new upAction();
    Action moveDown = new downAction();
    Action moveLeft = new leftAction();
    Action moveRight = new rightAction();
    Action menuAction = new menuAction();
    Action pauseGame = new pauseAction();
    Action inventoryAction = new inventoryAction();
    boolean exit;



    public World(int height, int length, int[][] tileType, Player player){ /*TODO: ADD PLAYER FIELD*/
        //super(player.getName());

        BackgroundMusic bm = new BackgroundMusic("game");//plays music

        this.worldMap = new Tile[height][length];
        this.height = height; //y
        this.length = length; // x
        this.player = player;
        this.spawnPoint = player.getLocation(); //pick up where last left    //new int[]{0, 0};//spawn point in reference to world map
        this.currLoc = spawnPoint;//location in tiles (reference to current map
        this.endPoint = new int[] {27,43};
        this.tileSize = 10;
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.length; x++) {
                worldMap[y][x] = new Tile(tileType[y][x]);
            }
        }
        exit = false; //exit the graphics loop
        complete = false; //level was completed
        pause = false; //is game paused
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


    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int displayWorld()   { //tiles are tilesize x tilesize pixels generated from (0,0) to (8*length, 8*height) (x,y) respectively

        long start = System.currentTimeMillis();//sets start time to calculate time played

        // Creating NPCs and shop windows

        MenuButtons a = new MenuButtons(player.getName());

        Home tester = new Home(player);

        tester.setImage("Images/house.jpg");


        NPC ron = new NPC("Ron", "Neutral");

        NPC natalie = new NPC("Natalie", "Neutral");

        NPC sam = new NPC("Sam", "Neutral");

        NPC hello = new NPC("Hello", "Enemy");

        NPC loopy = new NPC("Loopy", "Enemy");

        NPC ray = new NPC("Ray", "Enemy");

        NPC jeff = new NPC("Jeff", "Boss");


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

        ArrayList<String> test= new ArrayList<String>();

        test.add("Swiftness");

        player.setAbilities(test);


        // Testing to see if adding a new ability shows up in the world
        player.addAbilities("MULTISHOT");

        // Test to see if adding the same ability does not show up in the world
        // player.addAbilities("MULTISHOT");



        final String title = "Game World: Software Chasing";
        //final int frameWidth = 916;//(this.length + 1) * tileSize;
        //final int frameHeight = 1016;//(this.height+1) * tileSize;
        setTileSize(10);
        int frameWidth = tileSize * this.length;
        int frameHeight = frameWidth + 100;

        //Creating the frame.
        frame = new JFrame(title);

        frame.setSize(frameWidth, frameHeight );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setVisible(true);


//KEY LISTEN
//        frame.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
//                    frame.changeVisibility();
//                }
//            }
//        });

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

        //add player label to the frame
        frame.add(playerLabel);

        //create the logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(0,frameHeight-100,100,100);
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

                /*TODO HUNTER: WRITE OUT PLAYER INFO HERE*/
                dbConnection.updatePlayer(player);
                exit = true;//break out of display loop
                frame.dispose();
                //System.exit(69); Nice
            }
        });

        //add the logout button to the frame
        frame.add(logoutButton);

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
                if(e.getSource() == battle) {
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
                    tester.changeVisibility(true);
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
        JLabel health = new JLabel("MAX HEALTH: "+player.getMaxHP()+" Current Health: " + player.getHp());
        health.setBackground(Color.white);
        //health.setBounds(20,20,50,50);
        health.setOpaque(false);
        health.setVisible(true);

        JLabel stamina = new JLabel("MAX STAMINA: "+player.getMaxStamina()+" Stamina: " + player.getStamina());
        stamina.setBackground(Color.white);
        //stamina.setBounds(20,frameHeight-(tileSize+20),50,50);
        stamina.setOpaque(false);
        stamina.setVisible(true);

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

        //JLabel playerClass = new JLabel("Class: " + player.getPlayerClassName());
        //playerClass.setOpaque(false);
        //playerClass.setVisible(true);

        //Implement current time clock
        JLabel clockLabel = new JLabel();
        clockLabel.setOpaque(false);
        clockLabel.setVisible(true);

        Timer t = new Timer(1000, new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                long millis = System.currentTimeMillis();

                clockLabel.setText(String.format("Current Time: %s %d:%d",
                        java.time.LocalDate.now(),
                        (int) (((millis / (1000*60*60)) % 24) - 4),
                        (int) ((millis / (1000*60)) % 60)
                ));

            }
        });
        t.start();

        statPanel.add(health);
        statPanel.add(stamina);
        //statPanel.add(intellect);
        statPanel.add(speed);
        statPanel.add(level);
        statPanel.add(xp);
        //statPanel.add(playerClass);
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
                }
            }
            else {
                checker3 = false;
                NPC3label.setVisible(false);
            }

            // Now add NPC4

            if(currLoc[0] == hello.getLocation()[0] && currLoc[1] == hello.getLocation()[1]) {
                if(!checker4) {
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
                    NPC7label.setVisible(true);
                    battle4.setVisible(true);
                    checker7 = true;
                }
            }
            else {
                checker7 = false;
                NPC7label.setVisible(false);
                battle4.setVisible(false);
            }

            //graphics.clearRect(0, 0, width, height);

            for (int y = 0; y < this.height; y++) {
                for (int x = 0; x < this.length; x++) {
                    //get color of tile based on type
                    int type = this.worldMap[y][x].getType();
                    //0 is wall 1 is floor

                    if (pause == true){graphics.setColor(Color.BLACK);}//game is paused
                    else if (currLoc[0] == x && currLoc[1] == y){graphics.setColor(Color.YELLOW);} //player is here
                    else if (x == endPoint[0] && y == endPoint[1]){graphics.setColor(Color.GREEN);}//end point color
                    else if ((x == 3 && y == 3) | (x == 6 && y == 6) | (x == 15 && y == 25)) {graphics.setColor(Color.BLUE);}
                    else if ((x == 10 && y == 6) | (x == 35 && y == 5) | (x == 28 && y == 45)) {graphics.setColor(Color.ORANGE);}
                    else if (x == 40 && y == 30) {graphics.setColor(Color.RED);}
                    else if (type == 0) {graphics.setColor(Color.black);}else {graphics.setColor(Color.white);}
                    /*TODO load a tile image*/
                    graphics.fillRect(x*tileSize,y*tileSize,tileSize,tileSize); //TILE IMAGE WILL GO HERE LATER
                }
            }

            //Keep health updated
            health.setText("MAX HEALTH: " + player.getMaxHP() + " Current Health: " + player.getHp());
            stamina.setText("MAX STAMINA: " + player.getMaxStamina() + " Stamina: " + player.getStamina());
            level.setText("Level: " + player.getLevel());
            xp.setText("XP Needed: " + player.getLevelXP());


            bufferStrategy.show();
            graphics.dispose();
            if (exit){break;}
        }//DISPLAY LOOP
        frame.dispose();
        if (complete){return 1;} else {return 0;}
    }//END DISPLAY WORLD

    public static void main(String[] args)  {
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
//        Player player = new Player("RILEY6215","Riley",1);
        Player player = dbConnection.getPlayers("RILEY6215")[0];
        World test = new World(50,50,tiles,player);
        //test.setPlayer(player);
        test.displayWorld();

    }

    public class upAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (pause == true){return;}
            System.out.println(" UP LOCATION" + currLoc[0] +"  Y: "+ currLoc[1]);
            if (getUp(currLoc[0],currLoc[1])!= 1) {return;} //illegal movement cant move because it isn't a walkable tile
            playerLabel.setLocation(playerLabel.getX(),playerLabel.getY() - tileSize);//x then y
            setCurrLoc(currLoc[0], currLoc[1] - 1 );
            player.setLocation(currLoc);
            checkLevelFinish(currLoc);
            System.out.println("UP");
        }
    }//up action
    public class downAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (pause == true){return;}
            System.out.println(" DOWN LOCATION" + " X: "+ currLoc[0] +"  Y: "+ currLoc[1]);
            if (getDown(currLoc[0],currLoc[1])!= 1) {return;} //illegal movement cant move because it isn't a walkable tile
            playerLabel.setLocation(playerLabel.getX(),playerLabel.getY() + tileSize);//x then y
            setCurrLoc(currLoc[0], currLoc[1] + 1 );
            player.setLocation(currLoc);
            checkLevelFinish(currLoc);
            System.out.println("down");
            //player.gainXP(1);
            //System.out.println("XP = " + player.getLevelXP());
        }
    }//down action
    public class leftAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (pause == true){return;}
            System.out.println("LOCATION X: " + currLoc[0] +"  Y: "+ currLoc[1]);
            if (getLeft(currLoc[0],currLoc[1])!= 1) {return;} //illegal movement cant move because it isn't a walkable tile
            playerLabel.setLocation(playerLabel.getX() - tileSize,playerLabel.getY());//x then y
            setCurrLoc(currLoc[0] - 1, currLoc[1] );
            player.setLocation(currLoc);
            //player.setHp((player.getHp() - 1));
            //player.setStamina((player.getStamina() + 1));
            checkLevelFinish(currLoc);
            System.out.println("left");
        }
    }//left action
    public class rightAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (pause == true){return;}
            System.out.println("LOCATION X: " + currLoc[0] +"  Y: "+ currLoc[1]);
            if (getRight(currLoc[0],currLoc[1])!= 1) {return;} //illegal movement cant move because it isn't a walkable tile
            playerLabel.setLocation(playerLabel.getX() + tileSize,playerLabel.getY());//x then y
            setCurrLoc(currLoc[0] + 1, currLoc[1] );
            player.setLocation(currLoc);
            //player.setStamina((player.getStamina() - 1));
            //player.setHp((player.getHp() + 1));
            checkLevelFinish(currLoc);
            System.out.println("right");
        }
    }//up action

    public class pauseAction extends AbstractAction{ //hit p to pause and resume
        @Override
        public void actionPerformed(ActionEvent e) {
            pause = !pause;
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
            System.out.println("INVENTORY:");
            InventoryDisplay inventoryDisplay = new InventoryDisplay();
            inventoryDisplay.actionPerformed(e);
        }
    }

    public void resetPlayer() {
        this.player.setLocation(new int[]{0,0});
        this.currLoc = new int[]{0,0};
    }

    public void checkLevelFinish(int[] currLoc1){
        if (currLoc1[0] == endPoint[0] && currLoc1[1] == endPoint[1]){//player got to the end point
            System.out.println("ENDPOINT!");
            complete = true;
            exit = true;
        } //player got to the end point

    }


}// END CLASS
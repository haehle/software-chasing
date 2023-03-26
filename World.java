import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
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
     * Jave functionality Array[height/#rows][width/#col]
     * */
    private Tile[][] worldMap;
    private final int height; //y coord
    private final int length; // x coord
    private int[] spawnPoint;
    private int[] currLoc;
    public int tileSize;

    private JButton shop, shop2, menubuttons;

    private boolean checker;

    private boolean checker2;
    private Player player;
    JFrame frame;
    JLabel playerLabel;

    Action moveUp = new upAction();
    Action moveDown = new downAction();
    Action moveLeft = new leftAction();
    Action moveRight = new rightAction();
    Action menuAction = new menuAction();
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
        this.tileSize = 10;
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.length; x++) {
                worldMap[y][x] = new Tile(tileType[y][x]);
            }
        }
        exit = false;
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

    public void displayWorld()   { //tiles are tilesize x tilesize pixels generated from (0,0) to (8*length, 8*height) (x,y) respectively

        // Testing a shop window

        MenuButtons a = new MenuButtons(player.getName());


        NPC ron = new NPC("Ron", "Neutral");

        NPC natalie = new NPC("Natalie", "Neutral");

        ron.setStock1(1);
        ron.setStock2(1);
        ron.setStock3(3);

        natalie.setStock1(2);
        natalie.setStock2(2);
        natalie.setStock3(4);

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

        /*TODO This is the input for the player label to access the MENU ACTION AJ */
        playerLabel.getInputMap().put(KeyStroke.getKeyStroke('m'), "menuAction");
        playerLabel.getActionMap().put("menuAction", menuAction);

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
                /*TODO HUNTER: WRITE OUT PLAYER INFO HERE*/
                dbConnection.updatePlayer(player);
                exit = true;//break out of display loop
                frame.dispose();
                //System.exit(69); Nice
            }
        });

        //add the logout button to the frame
        frame.add(logoutButton);

        // Create shop button for interacting with NPCs

        shop = new JButton("Shop");
        shop.setBounds(175, 400, 100, 50);
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

        frame.add(shop);

        // Create shop button for interacting with NPCs

        shop2 = new JButton("Shop");
        shop2.setBounds(175, 400, 100, 50);
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

        frame.add(shop2);

        // Create menu buttons for extra things like reporting and feedback

        menubuttons = new JButton("Menu");
        menubuttons.setBounds(25, 450, 100, 50);
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

        frame.add(menubuttons);

        //create the stats bar
        JPanel statPanel = new JPanel();
        statPanel.setBackground(Color.decode("#ddb945"));
        statPanel.setSize(frameWidth-100,100);
        statPanel.setLocation(100,frameHeight-100);
        //statPanel.setBounds(100,frameHeight-tileSize,frameWidth-100,100);
        statPanel.setVisible(true);
        statPanel.setOpaque(true);

        //TODO add player stat
        JLabel health = new JLabel("MAX HEALTH: "+player.getMaxHP()+" Current Health: " + player.getHp());
        health.setBackground(Color.white);
        //health.setBounds(20,20,50,50);
        health.setOpaque(false);
        health.setVisible(true);

        JLabel stamina = new JLabel("MAX STAMINA: "+player.getMaxStamina()+" Stamina: " + player.getStamina());
        stamina.setBackground(Color.white);
        //stamina.setBounds(20,frameHeight-(tileSize+20),50,50);
        health.setOpaque(false);
        stamina.setVisible(true);

        JLabel intellect = new JLabel("Intellect: " + 5);
        //health.setBackground(Color.white);
        //intellect.setBounds(20,frameHeight-(tileSize+40),50,50);
        intellect.setOpaque(false);
        intellect.setVisible(true);

        JLabel speed = new JLabel("Speed: " + player.getSpeed());
        //health.setBackground(Color.white);
        //intellect.setBounds(20,frameHeight-(tileSize+60),50,50);
        intellect.setOpaque(false);
        intellect.setVisible(true);

        JLabel level = new JLabel("Level: " + player.getLevel());
        //health.setBackground(Color.white);
        //intellect.setBounds(20,frameHeight-(tileSize+60),50,50);
        intellect.setOpaque(false);
        intellect.setVisible(true);

        JLabel xp = new JLabel("XP Needed: " + player.getLevelXP());
        //health.setBackground(Color.white);
        //intellect.setBounds(20,frameHeight-(tileSize+60),50,50);
        intellect.setOpaque(false);
        intellect.setVisible(true);

        statPanel.add(health);
        statPanel.add(stamina);
        //statPanel.add(intellect);
        statPanel.add(speed);
        statPanel.add(level);
        statPanel.add(xp);

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


            // Tester to see if when a player reaches a certain tile with an NPC they can open a shop

            if(currLoc[0] == 3 && currLoc[1] == 3) {
                if(!checker) {
                    shop.setVisible(true);
                    checker = true;
                }
            }
            else {
                checker = false;
                shop.setVisible(false);
            }

            // Now add another NPC and see if they can both work together

            if(currLoc[0] == 6 && currLoc[1] == 6) {
                if(!checker2) {
                    shop2.setVisible(true);
                    checker2 = true;
                }
            }
            else {
                checker2 = false;
                shop2.setVisible(false);
            }

            //graphics.clearRect(0, 0, width, height);

            for (int y = 0; y < this.height; y++) {
                for (int x = 0; x < this.length; x++) {
                    //get color of tile based on type
                    int type = this.worldMap[y][x].getType();
                    //0 is wall 1 is floor

                    if (currLoc[0] == x && currLoc[1] == y){graphics.setColor(Color.YELLOW);}
                    else if ((x == 3 && y == 3) | (x == 6 && y == 6) | (x == 15 && y == 25)) {graphics.setColor(Color.BLUE);}
                    else if ((x == 10 && y == 30) | (x == 35 && y == 5) | (x == 28 && y == 45)) {graphics.setColor(Color.ORANGE);}
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
        Player player = new Player("RILEY6215","Riley",1);
        World test = new World(50,50,tiles,player);
        //test.setPlayer(player);
        test.displayWorld();

    }

    public class upAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(" UP LOCATION" + currLoc[0] +"  Y: "+ currLoc[1]);
            if (getUp(currLoc[0],currLoc[1])!= 1) {return;} //illegal movement cant move because it isn't a walkable tile
            playerLabel.setLocation(playerLabel.getX(),playerLabel.getY() - tileSize);//x then y
            setCurrLoc(currLoc[0], currLoc[1] - 1 );
            player.setLocation(currLoc);
            System.out.println("UP");
        }
    }//up action
    public class downAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(" DOWN LOCATION" + " X: "+ currLoc[0] +"  Y: "+ currLoc[1]);
            if (getDown(currLoc[0],currLoc[1])!= 1) {return;} //illegal movement cant move because it isn't a walkable tile
            playerLabel.setLocation(playerLabel.getX(),playerLabel.getY() + tileSize);//x then y
            setCurrLoc(currLoc[0], currLoc[1] + 1 );
            player.setLocation(currLoc);
            System.out.println("down");
            //player.gainXP(1);
            //System.out.println("XP = " + player.getLevelXP());
        }
    }//down action
    public class leftAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("LOCATION X: " + currLoc[0] +"  Y: "+ currLoc[1]);
            if (getLeft(currLoc[0],currLoc[1])!= 1) {return;} //illegal movement cant move because it isn't a walkable tile
            playerLabel.setLocation(playerLabel.getX() - tileSize,playerLabel.getY());//x then y
            setCurrLoc(currLoc[0] - 1, currLoc[1] );
            player.setLocation(currLoc);
            //player.setHp((player.getHp() - 1));
            //player.setStamina((player.getStamina() + 1));
            System.out.println("left");
        }
    }//left action
    public class rightAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("LOCATION X: " + currLoc[0] +"  Y: "+ currLoc[1]);
            if (getRight(currLoc[0],currLoc[1])!= 1) {return;} //illegal movement cant move because it isn't a walkable tile
            playerLabel.setLocation(playerLabel.getX() + tileSize,playerLabel.getY());//x then y
            setCurrLoc(currLoc[0] + 1, currLoc[1] );
            player.setLocation(currLoc);
            //player.setStamina((player.getStamina() - 1));
            //player.setHp((player.getHp() + 1));
            System.out.println("right");
        }
    }//up action
    /*TODO THIS IS THE MENU ACTION AJ*/
    public class menuAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("MENU:");
            MenuButtons menu = new MenuButtons(player.getUsername());
            menu.actionPerformed(e);
        }
    }//up action

}// END CLASS
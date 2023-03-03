import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.io.*;

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
    private Player player;
    JFrame frame;
    JLabel playerLabel;

    Action moveUp = new upAction();
    Action moveDown = new downAction();
    Action moveLeft = new leftAction();
    Action moveRight = new rightAction();
    Action menuAction = new menuAction();
    boolean exit;

    private JPanel panel;

    private JFrame frame2;
    private JButton reportButton, feedbackButton, creditsButton, backButton, submitButton;
    private JTextArea textArea;
    private JLabel prompt, prompt2, prompt3, prompt4, prompt5, prompt6, prompt7, prompt8, prompt9;
    private String userName;
    public int identifier = 0;

    private ActionListener holder;

    private boolean visibility = false;



    public World(int height, int length, int[][] tileType, Player player){ /*TODO: ADD PLAYER FIELD*/
        //super(player.getName());
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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reportButton) {
            identifier = 1;
            // Remove the report and feedback buttons
            panel.remove(reportButton);
            panel.remove(feedbackButton);
            panel.remove(creditsButton);

            // Create the prompt label and add it to the panel
            prompt = new JLabel("Please report any bugs that you have encountered during the game.");
            prompt.setBounds(750, 150, 600, 30);
            panel.add(prompt);

            // Create the text area and add it to the panel
            textArea = new JTextArea();
            textArea.setBounds(650, 200, 600, 300);
            textArea.setLineWrap(true);
            textArea.setBackground(Color.decode("#c4bfc0"));
            panel.add(textArea);

            // Create the back button and add it to the panel
            backButton = new JButton("Back");
            backButton.setBounds(650, 550, 100, 30);
            backButton.addActionListener(holder);
            backButton.setBackground(Color.decode("#9d9795"));
            panel.add(backButton);

            // Create the submit button and add it to the panel
            submitButton = new JButton("Submit");
            submitButton.setBounds(1150, 550, 100, 30);
            submitButton.addActionListener(holder);
            submitButton.setBackground(Color.decode("#daaa00"));
            panel.add(submitButton);

            // Refresh the panel to display the new components
            panel.revalidate();
            panel.repaint();
        }
        else if (e.getSource() == feedbackButton) {
            identifier = 2;
            // Remove the report button and feedback buttons
            panel.remove(feedbackButton);
            panel.remove(reportButton);
            panel.remove(creditsButton);

            // Create the prompt label and add it to the panel
            prompt = new JLabel("Please give any positive or negative feedback about the game.");
            prompt.setBounds(775, 150, 600, 30);
            panel.add(prompt);

            // Create the text area and add it to the panel
            textArea = new JTextArea();
            textArea.setBounds(650, 200, 600, 300);
            textArea.setLineWrap(true);
            textArea.setBackground(Color.decode("#c4bfc0"));
            panel.add(textArea);

            // Create the back button and add it to the panel
            backButton = new JButton("Back");
            backButton.setBounds(650, 550, 100, 30);
            backButton.addActionListener(holder);
            backButton.setBackground(Color.decode("#9d9795"));
            panel.add(backButton);

            // Create the submit button and add it to the panel
            submitButton = new JButton("Submit");
            submitButton.setBounds(1150, 550, 100, 30);
            submitButton.addActionListener(holder);
            submitButton.setBackground(Color.decode("#daaa00"));
            panel.add(submitButton);

            // Refresh the panel to display the new components
            panel.revalidate();
            panel.repaint();
        }
        else if (e.getSource() == creditsButton) {
            identifier = 3;
            // Remove the report button and feedback buttons
            panel.remove(feedbackButton);
            panel.remove(reportButton);
            panel.remove(creditsButton);

            // Create the prompt label and add it to the panel
            Font font = new Font("Impact", Font.BOLD, 80);
            prompt = new JLabel("Credits");
            prompt.setFont(font);
            prompt.setBounds(800, 100, 600, 90);
            panel.add(prompt);

            Font font2 = new Font("Georgia", Font.BOLD, 40);
            prompt2 = new JLabel("Developers:");
            prompt2.setFont(font2);
            prompt2.setBounds(200, 250, 600, 50);
            panel.add(prompt2);
            prompt7 = new JLabel("Special Thanks:");
            prompt7.setFont(font2);
            prompt7.setBounds(1400, 250, 600, 50);
            panel.add(prompt7);


            Font font3 = new Font("Verdana", Font.BOLD, 20);
            prompt3 = new JLabel("Saheer Ahmad (Add explanation of contribution)");
            prompt3.setFont(font3);
            prompt3.setBounds(200, 350, 600, 30);
            panel.add(prompt3);
            prompt4 = new JLabel("Jacob Brooks (Add explanation of contribution)");
            prompt4.setFont(font3);
            prompt4.setBounds(200, 400, 600, 30);
            panel.add(prompt4);
            prompt5 = new JLabel("Hunter Ehle (Add explanation of contribution)");
            prompt5.setFont(font3);
            prompt5.setBounds(200, 450, 600, 30);
            panel.add(prompt5);
            prompt6 = new JLabel("AJ Wheatley (Add explanation of contribution)");
            prompt6.setFont(font3);
            prompt6.setBounds(200, 500, 600, 30);
            panel.add(prompt6);

            prompt8 = new JLabel("Jakob Hale");
            prompt8.setFont(font3);
            prompt8.setBounds(1400, 350, 600, 30);
            panel.add(prompt8);
            prompt9 = new JLabel("Jeff Turkstra");
            prompt9.setFont(font3);
            prompt9.setBounds(1400, 400, 600, 30);
            panel.add(prompt9);


            // Create the back button and add it to the panel
            backButton = new JButton("Back");
            backButton.setBounds(800, 800, 300, 50);
            backButton.addActionListener(holder);
            backButton.setBackground(Color.decode("#9d9795"));
            panel.add(backButton);

            // Refresh the panel to display the new components
            panel.revalidate();
            panel.repaint();
        }
        else if (e.getSource() == backButton) {
            // Remove the prompt, text area, back button, and submit button
            panel.remove(prompt);
            panel.remove(backButton);

            if(identifier != 3) {
                panel.remove(textArea);
                panel.remove(submitButton);
            }
            else {
                panel.remove(prompt2);
                panel.remove(prompt3);
                panel.remove(prompt4);
                panel.remove(prompt5);
                panel.remove(prompt6);
                panel.remove(prompt7);
                panel.remove(prompt8);
                panel.remove(prompt9);
            }

            // Add the report and feedback buttons back to the panel
            panel.add(reportButton);
            panel.add(feedbackButton);
            panel.add(creditsButton);

            // Refresh the panel to display the report button
            panel.revalidate();
            panel.repaint();

            identifier = 0;

        } else if (e.getSource() == submitButton) {
            // Remove the prompt, text area, back button, and submit button
            panel.remove(prompt);
            panel.remove(textArea);
            panel.remove(backButton);
            panel.remove(submitButton);

            // Add the report and feedback buttons back to the panel
            reportButton.setBounds(350, 250, 300, 100);
            panel.add(reportButton);
            panel.add(feedbackButton);
            panel.add(creditsButton);

            // Refresh the panel to display the report button
            panel.revalidate();
            panel.repaint();

            // Store response in proper feedback or reports files

            if(identifier == 1) {
                try {
                    storeReport(userName, textArea.getText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else if(identifier == 2) {
                try {
                    storeFeedback(userName, textArea.getText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            identifier = 0;
        }
    }

    // Method to store reports in a text file called Reports.txt

    public static void storeReport(String name, String message) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter("Reports.txt", true));

        out.write("Name: " + name + " Message: " + message + "\n");
        out.close();


    }

    // Method to store feedback in a text file called Feedback.txt

    public static void storeFeedback(String name, String message) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter("Feedback.txt", true));

        out.write("Name: " + name + " Message: " + message + "\n");
        out.close();
    }

    public void displayWorld(){ //tiles are tilesize x tilesize pixels generated from (0,0) to (8*length, 8*height) (x,y) respectively
        final String title = "Game World: Software Chasing";
        final int frameWidth = 916;//(this.length + 1) * tileSize;
        final int frameHeight = 1016;//(this.height+1) * tileSize;
        setTileSize(18);

        frame2 = new JFrame("Buttons");
        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);

        // Set the size of the panel
        panel.setPreferredSize(new Dimension(1920, 1080));
        panel.setBackground(Color.decode("#cfb991"));

        // Create the report button
        reportButton = new JButton("Report");
        reportButton.setBounds(350, 250, 300, 100);
        reportButton.addActionListener(holder);
        reportButton.setBackground(Color.decode("#9d9795"));
        panel.add(reportButton);

        feedbackButton = new JButton("Feedback");
        feedbackButton.setBounds(700, 250, 300, 100);
        feedbackButton.addActionListener(holder);
        feedbackButton.setBackground(Color.decode("#daaa00"));
        panel.add(feedbackButton);

        creditsButton = new JButton("Credits");
        creditsButton.setBounds(1050, 250, 300, 100);
        creditsButton.addActionListener(holder);
        creditsButton.setBackground(Color.decode("#ddb945"));
        panel.add(creditsButton);



        frame2.getContentPane().add(panel);
        frame2.pack();
        frame2.setVisible(false);













        //Creating the frame.
        frame = new JFrame(title);


        frame.setSize(frameWidth, frameHeight );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);


        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    frame2.setVisible(!visibility);
                }
            }
        });

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
        logoutButton.setBounds(0,900,100,100);
        logoutButton.setBackground(Color.decode("#daaa00"));
        //add logout action
        logoutButton.addActionListener(new ActionListener() {
            //logout was hit, so we write player data out and return to caller
            @Override
            public void actionPerformed(ActionEvent e) {
                /*TODO HUNTER: WRITE OUT PLAYER INFO HERE*/
                exit = true;//break out of display loop
                frame.dispose();
                //System.exit(69); Nice
            }
        });

        //add the logout button to the frame
        frame.add(logoutButton);

        //create the stats bar
        JPanel statPanel = new JPanel();
        statPanel.setBackground(Color.decode("#ddb945"));
        statPanel.setSize(frameWidth-100,100);
        statPanel.setLocation(100,900);
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

        statPanel.add(health);
        statPanel.add(stamina);
        statPanel.add(intellect);
        statPanel.add(speed);

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
            //graphics.clearRect(0, 0, width, height);

            for (int y = 0; y < this.height; y++) {
                for (int x = 0; x < this.length; x++) {
                    //get color of tile based on type
                    int type = this.worldMap[y][x].getType();
                    //0 is wall 1 is floor

                    if (currLoc[0] == x && currLoc[1] == y){graphics.setColor(Color.YELLOW);}
                    else if (type == 0) {graphics.setColor(Color.black);}else {graphics.setColor(Color.white);}
                    /*TODO load a tile image*/
                    graphics.fillRect(x*tileSize,y*tileSize,tileSize,tileSize); //TILE IMAGE WILL GO HERE LATER
                }
            }


            bufferStrategy.show();
            graphics.dispose();
            if (exit){break;}
        }//DISPLAY LOOP

    }//END DISPLAY WORLD

    public static void main(String[] args) {
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
            System.out.println("right");
        }
    }//up action
    /*TODO THIS IS THE MENU ACTION AJ*/
    public class menuAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("MENU:");
        }
    }//up action

}// END CLASS

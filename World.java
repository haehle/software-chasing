import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;

public class World {
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



    public World(int height, int length, int[][] tileType){ /*TODO: ADD PLAYER FIELD*/
        this.worldMap = new Tile[height][length];
        this.height = height; //y
        this.length = length; // x
        this.spawnPoint = new int[]{0, 0};//spawn point in reference to world map
        this.currLoc = spawnPoint;//location in tiles (reference to current map
        this.tileSize = 16;
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.length; x++) {
                worldMap[y][x] = new Tile(tileType[y][x]);
            }
        }
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


    public void displayWorld(){ //tiles are tilesize x tilesize pixels generated from (0,0) to (8*length, 8*height) (x,y) respectively
        final String title = "Game World: Software Chasing";
        final int frameWidth = (this.length + 1) * tileSize;
        final int frameHeight = (this.height+1) * tileSize;


        //Creating the frame.
        frame = new JFrame(title);

        frame.setSize(frameWidth, frameHeight + 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        //creating "player" label
        playerLabel = new JLabel();
        playerLabel.setBackground(Color.green); //CHANGE PLAYER APPEARANCE
        //playerLabel.setBounds(0,0,length*tileSize,height*tileSize);
        //playerLabel.setBounds(this.spawnPoint[0],this.spawnPoint[1],tileSize,tileSize);//start x, start y, width, height
        playerLabel.setBounds(this.spawnPoint[0],this.spawnPoint[1],1,1);
        playerLabel.setOpaque(true);

        //make actions for player label
        playerLabel.getInputMap().put(KeyStroke.getKeyStroke('w'), "upAction");
        playerLabel.getActionMap().put("upAction", moveUp);
        playerLabel.getInputMap().put(KeyStroke.getKeyStroke('s'), "downAction");
        playerLabel.getActionMap().put("downAction", moveDown);
        playerLabel.getInputMap().put(KeyStroke.getKeyStroke('a'), "leftAction");
        playerLabel.getActionMap().put("leftAction", moveLeft);
        playerLabel.getInputMap().put(KeyStroke.getKeyStroke('d'), "rightAction");
        playerLabel.getActionMap().put("rightAction", moveRight);

        //add player label to the frame
        frame.add(playerLabel);


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

        }
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
        Profile profile = new Profile("test@gmail.com", "test", "password");
        CharSelect charSelect = new CharSelect(profile);
        Player currentPlayer = charSelect.currentPlayer;
        World test = new World(50,50,tiles);
        test.displayWorld();
    }

    public class upAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(" UP LOCATION" + currLoc[0] +"  Y: "+ currLoc[1]);
            if (getUp(currLoc[0],currLoc[1])!= 1) {return;} //illegal movement cant move because it isn't a walkable tile
            playerLabel.setLocation(playerLabel.getX(),playerLabel.getY() - tileSize);//x then y
            setCurrLoc(currLoc[0], currLoc[1] - 1 );
            //player.setLocation(currLoc);
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
            //player.setLocation(currLoc);
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
            //player.setLocation(currLoc);
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
            //player.setLocation(currLoc);
            System.out.println("right");
        }
    }//up action


}// END CLASS

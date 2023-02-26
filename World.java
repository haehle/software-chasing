import javax.swing.*;
import java.awt.*;
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


    public World(int height, int length, int[][] tileType){
        this.worldMap = new Tile[height][length];
        this.height = height; //y
        this.length = length; // x
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
        if (x+1 > this.length){return -1;} else {
            return worldMap[y][x+1].getType();//right
        }
    }//get right

    public Tile getTile(int x, int y){return this.worldMap[y][x];}

    public void displayWorld(){ //tiles are 8x8 pixels generated from (0,0) to (8*length, 8*height) (x,y) respectively
        final String title = "Game World: Software Chasing";
        final int width = this.length * 16;
        final int height = this.height * 16;

        //Creating the frame.
        JFrame frame = new JFrame(title);

        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        //Creating the canvas.
        Canvas canvas = new Canvas();

        canvas.setSize(width, height);
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

        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.length; x++) {
                //get color of tile based on type
                int type = this.worldMap[y][x].getType();
                if (type == 0) {graphics.setColor(Color.CYAN);}else {graphics.setColor(Color.ORANGE);}

                /*TODO*/
                graphics.fillRect(x*16,y*16,16,16); //TILE IMAGE WILL GO HERE LATER
            }
        }

        while (true) { // will add movements in here and wait for certain motions to keep displaying this
            bufferStrategy = canvas.getBufferStrategy();
            graphics = bufferStrategy.getDrawGraphics();
//            graphics.clearRect(0, 0, width, height);

//            for (int y = 0; y < this.height; y++) {
//                for (int x = 0; x < this.length; x++) {
//                    //get color of tile based on type
//                    int type = this.worldMap[y][x].getType();
//                    if (type == 0) {graphics.setColor(Color.CYAN);}else {graphics.setColor(Color.ORANGE);}
//                    graphics.fillRect(x*16,y*16,16,16);
//                }
//            }


            bufferStrategy.show();
            graphics.dispose();

        }
    }

    public static void main(String[] args) {
        int[][] tiles = new int[50][50];
        int count = 1;
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                tiles[i][j] = (count + i)% 2;
                count++;
            }
        }
        World test = new World(50,50,tiles);
        test.displayWorld();
    }

}// END CLASS

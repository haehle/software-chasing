import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferStrategy;
import java.io.*;

public class Game {//used to load in the game to start levels from the main menu interface

    //FUTURE TODO: when we have multiple levels we ccan store an array of Worlds and then just load based off of last one
    //maybe preload a level?
    private World level;
    private World[] levels;//user levels can move to the next one when it is complete
    private Player player;//character used by player with its stats

    private int numLevels;



    /** CONSTRUCTOR IN FUTURE IT WILL BE A LEVEL NO ie (0-4) */
    public Game(World[] levels,Player player1){
        /*TODO ADD MAIN MENU TO GET PLAYER*/
        this.levels = levels;//FUTURE THIS WILL BE levels[WlevelNo]
        this.numLevels = levels.length;
        this.player = player1;

    }




    /*TODO MOVE DISPLAY OF WORLD HERE (RILEY)*/
    /** THis will handle displaying the world as well as player movements*/
    public void run() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        //DISPLAY THE WORLD
        //this.level.setTileSize(tileSize);//resize per game specification
        int levelNo = player.getCurrentLevelNo(); //make this saved in the player class as current level
        int complete;
        boolean endGame = false;
        //System.out.println("RETURN" + this.level.displayWorld());
        while (true){
            if (this.numLevels == levelNo){break;}
            complete = new World(50,50,levels[levelNo].getWorldMap(),player).displayWorld();
            //complete = this.levels[levelNo].displayWorld();
            //return values for game.java
            //1 go to the next level
            //0 stay the same level
            //-1 go back a level
            //if (complete != 1  || levelNo == (this.numLevels-1)){break;} //map was not completed or it finished last level

            switch (complete){
                case 1:
                    levelNo++;//move to the next level since the last was completed
                    if (levelNo == this.numLevels){
                        endGame = true;
                        break;
                    }// the max level was completed
                    player.setCurrentLevelNo(levelNo);
                    player.setMaxLevelNO(levelNo);
                    //new world was completed and there is another, reset the player to the spawn location of the next level
                    levels[levelNo].resetPlayer();
                    break;
                case 0: //we are logging out or not making new progress in the game (staying at same level)
                    break;
                case -1:
                    if (levelNo > 0){ //go back a level if you can if not stay as is
                        levelNo--;
                        player.setCurrentLevelNo(levelNo);
                        levels[levelNo].resetPlayer();
                    }else {
                        //stays on same level
                        //player.setLocation(new int[] {player.getLocation()[0]-1,player.getLocation()[1]});
                        levels[levelNo].resetPlayer();
                    }
            }//end switch

            if (endGame){break;}

//            levelNo++;//move to the next level since the last was completed
//            player.setCurrentLevelNo(levelNo);
//            player.setMaxLevelNO(levelNo);
//            //new world was completed and there is another, reset the player to the spawn location of the next level
//            levels[levelNo].resetPlayer();
//            player.setLocation(levels[levelNo].getSpawnPoint());
        }//while loop (game loop)

        //TODO if endgame display victory screen

//        this.level.displayWorld();
        if (endGame){}

        /*TODO UPON EXIT WRITE THE DATA OF PLAYER TO DB*/


    }//END RUN

    /*TODO Actions such as the report button need to go in here and be hooked into the run() function*/

    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        /*TODO FILL THIS OUT*/

        //TODO GET PLAYER
//        Profile currentProfile = dbConnection.login("RILEY6215", "password");
//        Player currentPlayer = dbConnection.getPlayers("RILEY6215")[0];
//
//        if (currentProfile == null)
//        {
//            Util.createProfile("riley@test.com", "RILEY6215", "password");
//
//        }
//
//        if (currentPlayer == null)
//        {
//            Util.createPlayer("RILEY6215", "Riley", 1);
//        }
        Player currentPlayer = new Player("Riley6215","Riley",1);

        World[] levels = generateWorlds(currentPlayer);

        //make game object and run it
        Game game = new Game(levels,currentPlayer);
        game.run();
        System.out.println("HOPE YOU ENJOYED!");
        /**IF CODE GETS HERE .run has completed/window was closed*/
        System.exit(0);

    }//END MAIN

    public static World[] generateWorlds(Player currentPlayer){
        //make tile type map for the world
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
        int[][] tiles2 = new int[50][50];
        count = 1;
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                //tiles[i][j] = 1;
                if ((i % 7 ==1 && j % 7 ==1) || i == 49){tiles2[i][j] = 0;} else {tiles2[i][j] =1;}
//                tiles[i][j] = (count + i)% 2;
//                count++;
            }
        }

        int[][] tiles3 = new int[50][50];
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                //tiles[i][j] = 1;
                if (i == 49 || j == 49){tiles3[i][j] = 0;} else {tiles3[i][j] =1;}
//                tiles[i][j] = (count + i)% 2;
//                count++;
            }
        }


        World[]levels = new World[3];
//        Player currentPlayer = new Player("Riley6215","Riley",1);
        levels[0] = new World(50,50,tiles,currentPlayer);
        levels[1] = new World(50,50,tiles2,currentPlayer);
        levels[2] = new World(50,50,tiles3,currentPlayer);

        return levels;
    }

    public static void StartGame(Profile profile, Player player)  {
        //make tile type map for the world
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


        //make the world
        World test = new World(50,50,tiles,player);

        //make game object and run it
        //Game game = new Game(test);
        //game.run();
        System.out.println("HOPE YOU ENJOYED!");
        /**IF CODE GETS HERE .run has completed/window was closed*/
    }
}// END GAME CLASS
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferStrategy;

public class Game {//used to load in the game to start levels from the main menu interface

    //FUTURE TODO: when we have multiple levels we ccan store an array of Worlds and then just load based off of last one
    //maybe preload a level?
    private World level;
    private Player player;//character used by player with its stats
    int tileSize;



    /** CONSTRUCTOR IN FUTURE IT WILL BE A LEVEL NO ie (0-4) */
    public Game(World level, int tileSize){
        /*TODO ADD MAIN MENU TO GET PLAYER*/
        this.level = level;//FUTURE THIS WILL BE levels[WlevelNo]
        this.tileSize = tileSize;
    }




    /*TODO MOVE DISPLAY OF WORLD HERE (RILEY)*/
    /** THis will handle displaying the world as well as player movements*/
    public void run(){
        //DISPLAY THE WORLD
        this.level.setTileSize(tileSize);//resize per game specification
        this.level.displayWorld();

        /*TODO UPON EXIT WRITE THE DATA OF PLAYER TO DB*/


    }//END RUN

    /*TODO Actions such as the report button need to go in here and be hooked into the run() function*/


}// END GAME CLASS

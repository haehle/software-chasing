import java.awt.*;

public class Tile { // the fundamental type of the map can be various types
    //Can add enemies to the tile and access them through this.
    //Interactable : Boss, NPC, Enemy
    //Walkable: floor
    //Solid/cant go through/on: Wall

    private int type;
    private Boolean walkable;
    private Color tileColor;
/**
* For use later
 * private escape (will change color from white to green)
* Private Boss (type 4) (RED)
* private enemy (type 3) (Pink)
* private NPC (type 2) (orange?)
* Floors will be white (type 1)
* Walls will be black until we get images to load into these locations. (type 0)
* Player will be yellow?
* These classes will have their own info, but we can use it to generate the other classes/ set the boss enemy as needed
* */

    public Tile(int tileType){ // 0 is floor 1 is wall 2:NPC 3:enemy 4:Boss
        this.type = tileType;

        switch (tileType){
            case 0: //wall
                tileColor = Color.black;
                walkable = false;
                break;
            case 1://floor
                tileColor = Color.white;
                walkable = true;
                break;
            case 2://npc insert NPC object
                tileColor = Color.orange;
                walkable = false;
                break;
            case 3: //enemy insert enemy object
                tileColor = Color.pink;
                walkable = false;
                break;
            case 4://boss insert boss
                tileColor = Color.red;
                walkable = false;
                break;
            //case 5: //escape time

        }//end switch
    }//end constructor
    public int getType() {
        return type;
    }

    public Boolean getWalkable() {
        return walkable;
    }

    public Color getTileColor() {
        return tileColor;
    }
} //END CLAS

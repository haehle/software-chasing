public class Tile { // the fundamental type of the map can be various types
    //Can add enemies to the tile and access them through this.
    //Interactable : Boss, NPC, Enemy
    //Walkable: floor
    //Solid/cant go through/on: Wall

    private int type;
/*
* For use later
* Private Boss (type 4) (RED)
* private enemy (type 3) (Pink)
* private NPC (type 2) (green)
* Floors will be white
* Walls will be black until we get images to load into these locations.
* Player will be yellow?
* These classes will have their own info, but we can use it to generate the other classes/ set the boss enemy as needed
* */

    public Tile(int tileType){ // 0 is floor 1 is wall 2:NPC 3:enemy 4:Boss
        this.type = tileType;
    }
    public int getType() {
        return type;
    }

} //END CLAS

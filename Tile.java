public class Tile { // the fundamental type of the map can be various types
    //Can add enemies to the tile and access them through this.
    //Interactable : Boss, NPC, Enemy
    //Walkable: floor
    //Solid/cant go through/on: Wall

    private int type;


    public Tile(int tileType){ // 0 is floor 1 is wall 2:NPC 3:enemy 4:Boss
        this.type = tileType;
    }
    public int getType() {
        return type;
    }

} //END CLAS

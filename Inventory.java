import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items = new ArrayList<Item>();
    public static final int MAX_ITEM_NUM = 5;

    public Inventory() {
        this.items = new ArrayList<Item>();
    }

    public Inventory(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public void setItems (List<Item> items) {
        this.items = items;
    }

    public void addItem(String username, String name, Item item) {
        //Check to see if item is marked in database yet for player - STILL NEED TO MAKE SURE THAT REPEAT PROCESS WORKS FOR EXISTING ITEMS
        dbConnection.addInventoryItem(username, name, item);
        items.add(item);
    }

    //Only used when initializing player's Inventory object from database; DO NOT USE IN GAME/WORLD CODE
    public void simpleAddItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

}

import java.util.ArrayList;
import java.util.List;

public class Item {
    private int id;
    private String name;

    public Item(String name) {
        //Get id number to be used for item in database
        this.id = Util.getNextId();
        this.name = name;
    }

    public Item (int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName (String name) {
        this.name = name;
    }
}

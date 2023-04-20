import java.util.ArrayList;
import java.util.List;

public class Item {
    private int id;
    private String name;
    private String description;

    public Item(String name, String description) {
        //Get id number to be used for item in database
        this.id = Util.getNextId();
        this.name = name;
        this.description = description;
    }

    public Item (int id, String name, String description)
    {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() { return this.description; }

    public void setDescription (String description) { this.description = description; }
}

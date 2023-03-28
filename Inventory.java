import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<String> items = new ArrayList<String>();

    public Inventory() {
        this.items = new ArrayList<String>();
    }

    public Inventory(List<String> items) {
        this.items = items;
    }

    public List<String> getItems() {
        return this.items;
    }

    public void setItems (List<String> items) {
        this.items = items;
    }

    public void addItem(String item) {
        items.add(item);
    }

    public void removeItem(String item) {
        if (items.contains(item)) {
            items.remove(item);
        }
    }
}

import java.util.ArrayList;

/**
 * Created by krejcir on 1.5.14.
 */
public class Schedule {
    private ArrayList<Item> items;

    public Schedule() {
        this.items = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public Item popItem() {
        Item item = this.items.get(this.items.size() - 1);
        this.items.remove(this.items.size() - 1);
        return item;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}

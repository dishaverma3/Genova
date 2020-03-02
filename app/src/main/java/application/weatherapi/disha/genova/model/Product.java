
package application.weatherapi.disha.genova.model;

import java.util.List;

public class Product
{

    private List<Item> items = null;

    public Product(List<Item> items) {
        super();
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}

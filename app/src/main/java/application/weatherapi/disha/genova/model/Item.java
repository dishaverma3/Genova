
package application.weatherapi.disha.genova.model;

import java.io.Serializable;
import java.util.List;

public class Item implements Serializable
{

    private String name;
    private Integer price;
    private String type;
    private String discount;
    private String gender;
    private int quantity = 1;
    private List<String> sizeAvailable = null;
    private String details;

    public Item(String name, Integer price, String type, String discount, String gender, List<String> sizeAvailable, String details) {
        super();
        this.name = name;
        this.price = price;
        this.type = type;
        this.discount = discount;
        this.gender = gender;
        this.sizeAvailable = sizeAvailable;
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getSizeAvailable() {
        return sizeAvailable;
    }

    public void setSizeAvailable(List<String> sizeAvailable) {
        this.sizeAvailable = sizeAvailable;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

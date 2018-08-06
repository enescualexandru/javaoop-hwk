/**
 * A Java class meant to define the main features of a product in the store.
 * It defines the type of the products the store can handle.
 */

import java.util.ArrayList;
import java.util.List;

public class Product {
    private double price;
    private String validity;
    private int weight;
    private String name;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

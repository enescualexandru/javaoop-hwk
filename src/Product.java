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
    private List<String> animalProducts = new ArrayList<>();
    private List<String> vegetalProducts = new ArrayList<>();

    public Product() {
        this.animalProducts.add("milk");
        this.animalProducts.add("eggs");
        this.animalProducts.add("cheese");
        this.animalProducts.add("yogurt");
        this.animalProducts.add("cream");
        this.vegetalProducts.add("carrot");
        this.vegetalProducts.add("tomato");
        this.vegetalProducts.add("cucumber");
        this.vegetalProducts.add("peach");
        this.vegetalProducts.add("apple");
    }

    public List<String> getAnimalProducts() {
        return animalProducts;
    }

    public List<String> getVegetalProducts() {
        return vegetalProducts;
    }

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

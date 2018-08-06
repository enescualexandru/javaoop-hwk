import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A Java class meant to further extend the product, adding particular property of the veg. products.
 */

public class Vegetal extends Product {
    static private final List<String> vegetalVitaminList = Arrays.asList("A", "B1", "B2", "B6", "C", "E", "K");
    private String vitamins;

    public List<String> getVegetalVitaminList() {
        return vegetalVitaminList;
    }

    public String getVitamins() {
        return vitamins;
    }

    public void setVitamins(String vitamins) {
        this.vitamins = vitamins;
    }
}

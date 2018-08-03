/**
 * A Java class meant to provide the mechanism behind all Store's operations.
 * It contains the sub-class Order, which is instantiated whenever an order is made.
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Store {
    private Scanner scanner = new Scanner(System.in);
    private List<Product> productList = new ArrayList<>();
    private List<Order> orderList = new ArrayList<>();


    class Order {
        /**
         * class Order is instantiated each time an order is made. It is populated with relevant information about
         * transaction, through constructor.
         *
         * @param date    the date when the order is placed
         * @param productID id(name) of the product being processed.
         * @param quantity  the quantity of the product being processed.
         * @param price     the price of the product being processed.
         */
        private String date;
        private String productID;
        private int quantity;
        private double totalPrice;

        public Order(String date, String productID, int quantity, double price) {
            this.date = date;
            this.productID = productID;
            this.quantity = quantity;
            this.totalPrice = this.quantity * price;
        }

        public double getTotalPrice() {

            return totalPrice;
        }

        public String getDate() {

            return date;
        }

        public String getProductID() {

            return productID;
        }

        public int getQuantity() {

            return quantity;
        }
    }


    public void displaySalesReport() {

        /**
         * A method which displays the daily sales report.
         * Tests if any order is made, if any, checks the reference date from user's input,
         * validates it, finally searching through orderList and displaying the order's made for
         * that date, if any.
         */

        if (orderList.size() == 0) {
            System.err.println("\nNo orders yet ! ");
            return;
        }
        boolean sold = false;
        System.out.println("Please enter the reference date, as yyyy-mm-dd: ");
        String refDate = scanner.next();
        while (!isThisDateValid(refDate, "yyyy-mm-dd")) {
            System.err.println("Wrong date, please enter again: ");
            refDate = scanner.next();
        }
        for (Order order : orderList) {
            if (order.getDate().equals(refDate)) {
                System.out.println("Item sold on " + order.getDate() + "  product: " + order.getProductID() + ", quantity: " +
                        order.getQuantity() + ", total price: " + order.getTotalPrice());
                sold = true;
            }
        }
        if (!sold) {
            System.err.println("No items sold on " + refDate);
        }

    }

    public void sellProduct() {

        /**
         * A method which simulates the sales of a product.
         * Tests if any product is in the store, if any, asking and testing the user input for the relevant
         * fields, placing the order to the orderList
         */

        if (!productList.isEmpty()) {
            System.out.println("Products available:");
            for (Product product : productList) {
                System.out.println("ID: " + (productList.indexOf(product) + 1) + " ---> " +
                        product.getName() + ". stock: " + product.getWeight() + ". price per unit: " + product.getPrice());

            }
            System.out.println("Please enter product ID: ");
            int productID = askInputInt();
            while ((productList.size() <= (productID - 1)) || (productID <= 0)) {
                System.err.println("Invalid ID ! Enter again !");
                productID = askInputInt();
            }

            int productQuantity = askInputInt("Please enter quantity: ");
            while (productQuantity <= 0 || productQuantity > productList.get(productID - 1).getWeight()) {
                System.err.println("Invalid quantity ! Enter again");
                productQuantity = askInputInt();
            }
            if (productList.get(productID - 1).getWeight() <= 0) {
                System.err.println("Product out of stock ! Returning to menu..");
                return;
            }
            productList.get(productID - 1).setWeight(productList.get(productID - 1).getWeight() - productQuantity);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();

            /**
             * If all inputs are correct, add a new order to the orderList array.
             */
            orderList.add(new Store.Order(dateFormat.format(date), productList.get(productID - 1).getName(), productQuantity, productList.get(productID - 1).getPrice()));
            System.out.println("\nItem " + productList.get(productID - 1).getName() + " sold");

            /**
             * Removes the product from the list, if the stock is 0.
             */
            if (productList.get(productID - 1).getWeight() <= 0) {
                productList.remove(productID - 1);
            }
        } else {
            System.err.println("No products in the store !");
        }

    }

    public void inputProduct() {

        /**
         * A method which implements the addition of the product in the store.
         * Display the list of objects which can be chosen.
         * Tests for the correct user input, and finally adding it to the store.
         * Checks if the product already exists, adding only the top-up quantity, if so.
         */

        Product product;
        System.out.println(" - - - - - - - - - - - - - - - - - - - - - - ");
        System.out.println("Product type (1 - animal;  2 - vegetable) ");
        System.out.println(" - - - - - - - - - - - - - - - - - - - - - - ");
        int action = askInputInt();
        while (action != 1 && action != 2) {
            System.out.println("Please enter 1 or 2");
            action = askInputInt();
        }
        if (action == 1) {
            product = new Animal();
            System.out.println("Following products can be added: " + product.getAnimalProducts().toString());
        } else {
            product = new Vegetal();
            System.out.println("Following products can be added: " + product.getVegetalProducts().toString());

        }
        System.out.println("Enter name:");
        String opt = scanner.next().toLowerCase();
        while (!(product.getAnimalProducts().contains(opt) || product.getVegetalProducts().contains(opt))) {
            System.err.println("Invalid product! Enter again: ");
            opt = scanner.next().toLowerCase();
        }

        for (Product checkedProduct : productList) {
            if (checkedProduct.getName().equals(opt)) {
                int topUp = askInputInt("Product already exists in the store, please add the top-up quantity: ");
                while (topUp <= 0) {
                    topUp = askInputInt("Wrong top up quantity ! Try again");
                }
                checkedProduct.setWeight(checkedProduct.getWeight() + topUp);
                System.out.println("\n Added to the product " + checkedProduct.getName() + " the quantity of " + topUp);
                return;
            }

        }
        product.setName(opt);

        int quantity = askInputInt("Enter quantity:");
        while (!(quantity > 0)) {
            quantity = askInputInt("Invalid quantity. Enter again !");
        }
        product.setWeight(quantity);

        double price = askInputDouble("Enter price per unit:");
        while (!(price > 0)) {
            price = askInputDouble("Invalid price. Enter again !");
        }
        product.setPrice(price);

        System.out.println("Enter validity: yyyy-mm-dd");
        opt = scanner.next();
        while (!isThisDateValid(opt, "yyyy-mm-dd")) {
            System.err.println("Wrong date, please enter again: ");
            opt = scanner.next();
        }
        product.setValidity(opt);

        /**
         * Calling the relevant method for the respective product.
         */
        if (action == 1) {
            ((Animal) product).setStorageTemperature(askInputDouble("Enter storage temperature:"));
        } else {
            System.out.println("Enter the vitamins it contains:");
            ((Vegetal) product).setVitamins(scanner.next());
        }

        productList.add(product);
        System.out.println("\n" + product.getName() + " successful added !\n");
    }


    public int askInputInt() {
        /**
         * @Overloaded.
         * A method used for repeatedly asking the user for input until
         * the input is valid. If condition is used,
         * input is measured against it.
         *
         * @return Returns the final value of the accepted input, as an integer.
         */
        Boolean error;
        String userInp;
        do {
            userInp = scanner.next();
            if (!this.isType(userInp, "int")) {
                error = true;
                System.err.println("Not valid: must be a whole number.");
            } else {
                error = false;
            }
        } while (error);
        return Integer.parseInt(userInp);
    }

    private int askInputInt(String informationText) {
        /**
         * @Overloaded
         * A method to repeatedly ask the user for input until
         * the input is valid. If condition is used,
         * input is measured against it.
         *
         * @param informationText    The information text to prompt
         * 							to the user.
         * @return Returns the final value of the accepted
         * 							input, as an integer.
         */
        Boolean error;
        String userInp;
        do {
            System.out.println(informationText);
            userInp = scanner.next();
            if (!this.isType(userInp, "int")) {
                error = true;
                System.err.println("Not valid: must be a whole number.");
            } else {
                error = false;
            }
        } while (error);
        return Integer.parseInt(userInp);
    }

    private double askInputDouble(String informationText) {
        /**
         * A method to repeatedly ask the user for input until
         * the input is valid. If condition is used,
         * input is measured against it.
         *
         * @param informationText    The information text to prompt
         * 							to the user.
         * @return Returns the final value of the accepted
         * 							input, as a double.
         */
        Boolean error;
        String userInp;
        do {
            System.out.println(informationText);
            userInp = scanner.next();
            if (!this.isType(userInp, "double")) {
                System.err.println("Not valid: must be a number.");
                error = true;
            } else {
                error = false;
            }

        } while (error);
        return Double.parseDouble(userInp);
    }

    private Boolean isType(String testStr, String type) {
        /**
         * Tests if a specific input can be converted to a specific type.
         *
         * @param input The input to test. Accepts String, int or double.
         * @param type    Which type to test against. Accepts 'int' or 'double'.
         * @return Boolean    True if can be transformed to requested type. False otherwise.
         */
        try {
            if (type.equalsIgnoreCase("int")) {
                Integer.parseInt(testStr);
            } else if (type.equalsIgnoreCase("double")) {
                Double.parseDouble(testStr);
            }
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    private boolean isThisDateValid(String dateToValidate, String dateFromat) {

        /**
         * Tests if a specific input can be converted to a valid date.
         *
         * @param dateToValidate The input to test. Accepts String.
         * @param dateFormat    Which format type to test against. Accepts String.
         * @return Boolean    True if can be transformed to a valid date. False otherwise.
         */

        if (dateToValidate == null) {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
        sdf.setLenient(false);

        try {
            sdf.parse(dateToValidate);

        } catch (ParseException e) {

            return false;
        }
        return true;
    }
}

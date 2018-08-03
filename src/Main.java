/**
 * @author Alexandru Enescu
 * @version 1.0
 *
 * A Java program meant to fulfill the requirements of the S.I.T./Java OOP Homework.
 *
 */


public class Main {

    public static void main(String[] args) {
    /**
    * Repeatedly ask the user for input until
    * the user chooses to exit.
    * Method listOptions() is called after each valid operation
     */
        Store store = new Store();
        boolean quit = false;
        listOptions();
        while (!quit) {
            int option = store.askInputInt();
            switch (option) {
                case 1:
                    store.inputProduct();
                    listOptions();
                    break;
                case 2:
                    store.sellProduct();
                    listOptions();
                    break;
                case 3:
                    store.displaySalesReport();
                    listOptions();
                    break;
                case 4:
                    quit = true;
                    break;
                default:
                    System.err.println("Option doesn't exists !");
                    listOptions();
                    break;
            }
        }
    }

    /**
     * A method to display the user's options
     */
    private static void listOptions() {
        System.out.println("\n=========================================== ");
        System.out.println("Available options: ");
        System.out.println("1. Create product and add it to stock\n" +
                "2. Sell product\n" +
                "3. Display daily sales report\n" +
                "4. Exit\n" +
                "\nChoose an option");
        System.out.println("=========================================== ");
    }
}

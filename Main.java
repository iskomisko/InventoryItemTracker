
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Item> items = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        items = FileManager.loadFromFile();

        while (true) {
            System.out.println("\n-|[Inventory Menu]|-\n");
            System.out.println("1. Add Item");
            System.out.println("2. View Items");
            System.out.println("3. Update Item");
            System.out.println("4. Delete Item");
            System.out.println("5. Save to file");
            System.out.println("6. Load from file");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            int choice = readInt();

            switch (choice) {
                case 1 -> addItem();
                case 2 -> viewItems();
                case 3 -> updateItem();
                case 4 -> deleteItem();
                case 5 -> FileManager.saveToFile(items);
                case 6 -> items = FileManager.loadFromFile();
                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // ➕ CREATE
    public static void addItem() {
        System.out.print("Enter ID: ");
        int id = readInt();

        String name = readNonEmptyString("Enter name: ");

        int quantity;
        do {
            System.out.print("Enter quantity: ");
            quantity = readInt();
            if (quantity < 0) System.out.println("Quantity cannot be negative!");
        } while (quantity < 0);

        double price;
        do {
            System.out.print("Enter price: ");
            price = readDouble();
            if (price < 0) System.out.println("Price cannot be negative!");
        } while (price < 0);

        System.out.print("Is it perishable? (yes/no): ");
        String type = scanner.nextLine();

        if (type.equalsIgnoreCase("yes")) {
            String date = readNonEmptyString("Enter expiry date (dd-MM-yyyy): ");
            items.add(new PerishableItem(id, name, quantity, price, date));
        } else {
            items.add(new Item(id, name, quantity, price));
        }

        System.out.println("Item added!");
    }

    // 📋 READ
    public static void viewItems() {
        if (items.isEmpty()) {
            System.out.println("No items found.");
            return;
        }
        for (Item item : items) {
            item.display();
        }
    }

    // ✏️ UPDATE
    public static void updateItem() {
        System.out.print("Enter ID to update: ");
        int id = readInt();

        for (Item item : items) {
            if (item.getId() == id) {
                item.setName(readNonEmptyString("New name: "));

                int quantity;
                do {
                    System.out.print("New quantity: ");
                    quantity = readInt();
                    if (quantity < 0) System.out.println("Quantity cannot be negative!");
                } while (quantity < 0);
                item.setQuantity(quantity);

                double price;
                do {
                    System.out.print("New price: ");
                    price = readDouble();
                    if (price < 0) System.out.println("Price cannot be negative!");
                } while (price < 0);
                item.setPrice(price);

                System.out.println("Updated!");
                return;
            }
        }
        System.out.println("Item not found.");
    }

    // ❌ DELETE
    public static void deleteItem() {
        System.out.print("Enter ID to delete: ");
        int id = readInt();

        for (Item item : items) {
            if (item.getId() == id) {
                items.remove(item);
                System.out.println("Deleted!");
                return;
            }
        }
        System.out.println("Item not found.");
    }
    // 🛡️ VALIDATION METHODS

    public static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.print("Invalid number, try again: ");
            }
        }
    }

    public static double readDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (Exception e) {
                System.out.print("Invalid number, try again: ");
            }
        }
    }

    public static String readNonEmptyString(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();
            if (!input.trim().isEmpty()) return input;
            System.out.println("Input cannot be empty!");
        }
    }
}


import java.io.*;
import java.util.ArrayList;

public class FileManager {

    public static void saveToFile(ArrayList<Item> items) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("items.csv"))) {

            for (Item item : items) {

                if (item instanceof PerishableItem p) {
                    writer.write(item.getId() + "," +
                            item.getName() + "," +
                            item.getQuantity() + "," +
                            item.getPrice() + "," +
                            p.getExpiryDate());
                } else {
                    writer.write(item.getId() + "," +
                            item.getName() + "," +
                            item.getQuantity() + "," +
                            item.getPrice());
                }

                writer.newLine();
            }

            System.out.println("Saved to file!");

        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    public static ArrayList<Item> loadFromFile() {
        ArrayList<Item> items = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("items.csv"))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                int id = Integer.parseInt(data[0]);
                String name = data[1];
                int quantity = Integer.parseInt(data[2]);
                double price = Double.parseDouble(data[3]);

                if (data.length == 5) {
                    String expiry = data[4];
                    items.add(new PerishableItem(id, name, quantity, price, expiry));
                } else {
                    items.add(new Item(id, name, quantity, price));
                }
            }

        } catch (IOException e) {
            System.out.println("File not found. Starting fresh.");
        }

        return items;
    }
}

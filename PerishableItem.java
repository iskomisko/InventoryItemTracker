
public class PerishableItem extends Item {
    private String expiryDate;

    public PerishableItem(int id, String name, int quantity, double price, String expiryDate) {
        super(id, name, quantity, price);
        this.expiryDate = expiryDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    // Полиморфизм (переопределение)
    @Override
    public void display() {
        System.out.println("ID: " + getId() +
                ", Name: " + getName() +
                ", Quantity: " + getQuantity() +
                ", Price: " + getPrice() +
                ", Expiry Date: " + expiryDate);
    }
}

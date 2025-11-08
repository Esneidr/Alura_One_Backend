public class Purchase implements Comparable<Purchase> {
    private double value;
    private String description;

    public Purchase(double value, String description) {
        this.value = value;
        this.description = description;
    }

    public double getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Compra: valor = " + value +
                ", descripción='" + description;
    }

    @Override
    public int compareTo(Purchase anotherPurchase) {
        return Double.valueOf(this.value).compareTo(Double.valueOf(anotherPurchase.getValue()));
    }
}

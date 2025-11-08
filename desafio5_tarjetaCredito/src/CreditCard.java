import java.util.ArrayList;
import java.util.List;

public class CreditCard {
    private String cardType;
    private double limit;
    private double balance;
    private List<Purchase> purchaseList;

    public CreditCard(String cardType) {
        this.cardType = cardType;
        this.limit = defineLimit(cardType);
        this.balance = limit;
        this.purchaseList = new ArrayList<>();
    }

    private double defineLimit(String cardType) {
        return switch (cardType.toLowerCase()) {
            case "gold" -> 10000000.0;
            case "platinum" -> 7000000.0;
            case "black" -> 4000000.0;
            default -> 1000000.0;
        };
    }

    public boolean launchPurchase(Purchase purchase) {
        if (this.balance >= purchase.getValue()) {
            this.balance -= purchase.getValue();
            this.purchaseList.add(purchase);
            return true;
        }

        return false;
    }

    public String getCardType() {
        return cardType;
    }

    public double getLimit() {
        return limit;
    }

    public double getBalance() {
        return balance;
    }

    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }
}

import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingresa tu nombre: ");
        String name = scanner.next();

        System.out.printf("Hola %s, cual es tu tipo de tarjeta: %n", name);
        String type = scanner.next();

        var card = new CreditCard(type);

        System.out.printf("%s el límite de tu tarjeta %s es: $%.1f%n", name,type,card.getLimit());

        int exit = 1;
        while(exit != 0) {
            System.out.println("Ingrese la descripción de su compra:");
            String description = scanner.next();

            System.out.println("Ingrese el valor ($) de su compra:");
            double value = Double.valueOf(scanner.next());

            var purchase = new Purchase(value, description);
            boolean purchaseCompleted = card.launchPurchase(purchase);

            if (purchaseCompleted) {
                System.out.println("¡Compra realizada con éxito!");
                System.out.printf("Saldo actual: $%.1f%n", card.getBalance());
            } else {
                System.out.println("¡Saldo insuficiente para realizar la compra!");
                System.out.printf("Saldo disponible: $%.1f%n", card.getBalance());
            }

            if (card.getBalance() <= 0) {
                System.out.println("Tu saldo es 0. No puedes realizar más compras.");
                break;
            }

            System.out.println("Ingrese 0 para salir o 1 para continuar:");
            exit = scanner.nextInt();
        }

        System.out.println("********************");
        System.out.println("Compras Realizadas:\n");
        Collections.sort(card.getPurchaseList());

        for (Purchase purchase : card.getPurchaseList()) {
            System.out.println(purchase.getDescription() + " - " + purchase.getValue() + "$");
        }

        System.out.println("\n********************");
        System.out.printf("\nSaldo en la tarjeta %s: $%.1f", type, card.getBalance());
    }
}

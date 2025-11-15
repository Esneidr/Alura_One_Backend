import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RetrieveMovie retrieve = new RetrieveMovie();
        List<Historic> history = new ArrayList<>();

        List<String> currencies = List.of(
                "USD – Dólar estadounidense",
                "EUR – Euro",
                "JPY – Yen japonés",
                "GBP – Libra esterlina",
                "AUD – Dólar australiano",
                "CAD – Dólar canadiense",
                "CHF – Franco suizo",
                "CNY – Yuan chino (Renminbi)",
                "NZD – Dólar neozelandés",
                "MXN – Peso mexicano",
                "SEK – Corona sueca",
                "NOK – Corona noruega",
                "KRW – Won surcoreano",
                "SGD – Dólar de Singapur",
                "HKD – Dólar de Hong Kong",
                "INR – Rupia india",
                "BRL – Real brasileño",
                "ZAR – Rand sudafricano",
                "TRY – Lira turca",
                "RUB – Rublo ruso"
        );

        System.out.println("********** GUIA DE MONEDAS (TOP 20) **********");
        currencies.forEach(System.out::println);
        System.out.println("**********************************************");

        int exit = 1;
        while (exit != 0) {
            try {
                System.out.println("1. Realizar conversión");
                System.out.println("2. Ver historial de conversiones");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
                int option = scanner.nextInt();
                scanner.nextLine();

                if (option == 2) {
                    System.out.println("---------- HISTORIAL ----------");
                    if (history.isEmpty()) {
                        System.out.println("No hay conversiones registradas.");
                    } else {
                        history.forEach(System.out::println);
                    }
                    System.out.println("-------------------------------");
                    continue;
                }

                if (option == 0) break;

                System.out.print("Ingrese la moneda base (ej: USD): ");
                String money = scanner.nextLine().trim();

                Converter converter = retrieve.converter(money);

                if (converter.result().equals("success")) {
                    System.out.printf("Ingrese la moneda a comparar con %s: ", money);
                    String money2 = scanner.nextLine();

                    System.out.printf("Ingrese la cantidad de %s a convertir en %s: ", money, money2);
                    double amount = scanner.nextDouble();
                    scanner.nextLine();

                    Double rate = converter.conversion_rates().get(money2);
                    if (rate != null) {
                        double converted = amount * rate;
                        System.out.printf("%.2f %s = %.2f %s%n", amount, money, converted, money2);
                        history.add(new Historic(money, money2, amount, converted));
                    } else {
                        System.out.printf("La moneda %s no existe.", money2);
                    }
                } else {
                    System.out.printf("La moneda %s no existe.", money);
                }
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine();
            }
        }
        System.out.println("Finalizando el programa.");
    }
}

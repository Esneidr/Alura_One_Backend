package com.cursoalura.conversormonedas.root;

import com.cursoalura.conversormonedas.models.Converter;
import com.cursoalura.conversormonedas.models.Historic;
import com.cursoalura.conversormonedas.services.RetrieveCurrency;
import com.cursoalura.conversormonedas.utils.TopMoneys;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RetrieveCurrency retrieve = new RetrieveCurrency();
        List<Historic> history = new ArrayList<>();

        while (true) {
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
                String money = scanner.nextLine().trim().toUpperCase();

                if (!money.matches("[A-Z]{3}")) {
                    System.out.println("Código inválido. Debe ser de 3 letras (ej: USD).");
                    continue;
                }

                Converter converter = retrieve.converter(money);

                if (converter.result().equals("success")) {
                    Map<String, Double> topCurrencies = TopMoneys.top(converter.conversion_rates(), money);

                    System.out.printf("Para la moneda base %s (actualizado el %s)%n", money, converter.time_last_update_utc()
                            .replace(" +0000", ""));
                    System.out.printf("********** Top 15 de monedas con mayor valor frente a %s **********%n", money);
                    topCurrencies.forEach((code, value) -> System.out.printf("%s: %.4f$%n", code,value));
                    System.out.println("***********************************************************");

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
                        System.out.printf("La moneda %s no existe.%n", money2);
                    }
                } else {
                    System.out.printf("La moneda %s no existe.%n", money);
                }
            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar un número válido: ");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine();
            }
        }
        System.out.println("Finalizando el programa.");
    }
}

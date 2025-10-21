import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int intentos = 0;
        int number = new Random().nextInt(100) + 1;

        System.out.println("Encuantra un número entres 1 y 100.");

        while (intentos < 5) {
            System.out.printf("Tienes %d de 5 intentos.", intentos + 1);
            System.out.println("Adivina el número: ");
            int dato = scanner.nextInt();

            if (number == dato) {
                System.out.printf("¡Magnifico! Encontraste el número en %d intento(s).%n", intentos + 1);
                System.out.printf("El número era: %d.%n", number);
                scanner.close();
                return;
            }

            if (intentos < 4) {
                if (number > dato) {
                    System.out.println("Debes ser más precis@, el número es mayor.");
                } else {
                    System.out.println("Debes ser más precis@, el número es menor.");
                }
            }

            intentos++;
        }
        System.out.printf("%n Jajaja eres muy mal@, el número era: %d.%n", number);
    }
}
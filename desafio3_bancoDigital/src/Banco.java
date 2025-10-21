import java.util.Scanner;

public class Banco {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = "Tony Stark";
        String accountType = "Corriente";
        double balance = 9999.99;
        int option = 0;

        System.out.println("***************** Wesley Bank ********************");
        System.out.printf("Hola %s.%n", name);
        System.out.printf("Tipo de cuenta: %s.%n", accountType);
        System.out.printf("Saldo disponible: $%.2f%n", balance);
        System.out.println("**************************************************");

        String menu = """
                * ⋮ Que desea realizar hoy ***********************
                1 - Consultar Saldo.
                2 - Retirar.
                3 - Depositar.
                9 - Salir.
                opción:
                """;

        while (option != 9) {
            System.out.printf("%n%s", menu);
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.printf("%s tu saldo actual es de: $%.2f%n", name, balance);
                    break;
                case 2:
                    System.out.println("Ingresa el monto a retirar: ");
                    double withdrawAmount = scanner.nextDouble();

                    if (withdrawAmount > balance ) {
                        System.out.println("Error! saldo insufuciente.");
                    } else {
                        balance -= withdrawAmount;
                        System.out.printf("El saldo actualizado es: $%.2f%n", balance);
                    }
                    break;
                case 3:
                    System.out.println("Ingresa el monto a depositar: ");
                    double depositAmount = scanner.nextDouble();
                    balance += depositAmount;
                    System.out.printf("El saldo actualizado es: $%.2f%n", balance);
                    break;
                case 9:
                    System.out.println("Finalizando, gracias por creer en Wesley Bank.");
                    break;
                default:
                    System.out.println("Opción incorrecta, vuelve a intentarlo.");
                    break;
            }
        }


    }
}

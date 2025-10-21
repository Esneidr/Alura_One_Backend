import java.util.Scanner;

public class Evaluaciones {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double nota = 0;
        double suma = 0;
        int contador = 0;

        while (true) {
            System.out.printf("User %d escribe la nota que le daras a la película:%n", contador + 1);
            nota = scanner.nextDouble();

            if (nota == 0) {
                break; // Detiene el ciclo si el usuario ingresa -1
            }

            suma += nota;
            contador++;
        }

        if (contador > 0) {
            double media = suma / contador;
            System.out.printf("%nCantidad de votos: %d%n", contador);
            System.out.printf("La película obtuvo una calificación promedio de: %.2f%n", media);
        } else {
            System.out.println("No se ingresaron calificaciones.");
        }
    }
}

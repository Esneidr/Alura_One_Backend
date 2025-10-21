import java.util.Scanner;

public class Lectura {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el título de la película:");
        String titulo = scanner.nextLine();

        System.out.println("Ingrese fecha de lanzamiento:");
        int fecha = scanner.nextInt();

        System.out.println("Califica esta película:");
        double nota = scanner.nextDouble();

        System.out.printf("Buscando película %s con fecha de lanzamiento %d y una calificación %.1f.%n",
                titulo, fecha, nota
        );
    }
}

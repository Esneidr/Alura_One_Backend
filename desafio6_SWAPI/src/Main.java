import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RetrieveMovie retrieve = new RetrieveMovie();
        System.out.println("Escriba el número de la película de Star Wars: ");

        try {
            var number = Integer.valueOf(scanner.nextLine());
            Movie movie = retrieve.searchMovie(number);
            System.out.println("Película: " + movie);

            FileGenerator generator = new FileGenerator();
            generator.saveJson(movie);
        } catch (NumberFormatException e) {
            System.out.println("Número no encontrado " + e.getMessage());
        } catch (RuntimeException | IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Finalizando la aplicación.");
        }
    }
}

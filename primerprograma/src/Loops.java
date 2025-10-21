import java.util.Scanner;

public class Loops {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int user = 1;
        double nota = 0;
        double media = 0;

        for (int i = 0; i < 3; i++) {
            System.out.printf("User %d escribe la nota que le daras a la película:%n", user);
            nota = scanner.nextDouble();
            media += nota;
            user++;
        }
        System.out.println("La película obtuvo una calificación de: " + media / 3);
    }
}

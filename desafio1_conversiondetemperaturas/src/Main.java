public class Main {
    public static void main(String[] args) {
        double celsius = 25.5;
        double fahrenheit = (celsius * 1.8) + 32;
        System.out.printf("La temperatura de %.2f °C equivale a %.2f °F.%n", celsius, fahrenheit);

        int temperaturaEntera = (int) fahrenheit;
        System.out.printf("En valores enteros, la temperatura es aproximadamente %d °F.%n", temperaturaEntera);
    }
}

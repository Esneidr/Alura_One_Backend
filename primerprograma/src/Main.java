public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenido(a) a Screen Match");
        System.out.println("Película: Matrix");

        int fechaDeLanzamiento = 1999;
        boolean incluidoEnElPlan = true;
        double notaDelaPelicula = 8.2;

        double media = (8.2 + 6.0 + 9.2) / 3;
        System.out.printf("Media %s %n", media);

        String sinopsis = """
                Lorem ipsum dolor sit amet,
                consectetur adipiscing elit,
                sed do eiusmod tempor incididunt
                ut labore et dolore magna aliqua.
                Fue lanzada en:
                """ + fechaDeLanzamiento;
        System.out.print(sinopsis);

        int clasificacion = (int) (media / 2);
        System.out.println(clasificacion);
    }
}
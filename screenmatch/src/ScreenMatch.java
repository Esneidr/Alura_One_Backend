import com.aluracursos.sreenmatch.models.Movie;

import java.time.Year;

public class ScreenMatch {
    public static void main(String[] args) {
        Movie movie = new Movie();

        movie.setTitle("Encanto");
        movie.setReleaseDate(Year.of(2021));
        movie.setDurationInMinutes(120);
        movie.setIncludedInPlan(true);

        movie.rating(8.3);
        movie.rating(6.05);
        movie.rating(9.3);

        movie.technicalInfo();
        System.out.printf("Rating: %.2f", movie.averageRating());
    }
}

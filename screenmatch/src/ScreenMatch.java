import com.aluracursos.screenmatch.calculations.RecommendationFilter;
import com.aluracursos.screenmatch.calculations.TimeCalculator;
import com.aluracursos.sreenmatch.models.Episode;
import com.aluracursos.sreenmatch.models.Movie;
import com.aluracursos.sreenmatch.models.Series;

import java.time.Year;

public class ScreenMatch {
    public static void main(String[] args) {
        Movie movie = new Movie();
        Movie movie1 = new Movie();
        Series serie = new Series();
        TimeCalculator calculator = new TimeCalculator();
        RecommendationFilter filter = new RecommendationFilter();
        Episode episode = new Episode();

        // película
        movie.setTitle("Encanto");
        movie.setReleaseDate(Year.of(2021));
        movie.setDurationInMinutes(120);
        movie.setIncludedInPlan(true);

        movie.rating(8.3);
        movie.rating(6.05);
        movie.rating(9.3);

        movie.technicalInfo();
        System.out.printf("Rating: %.2f%n", movie.averageRating());

        // película 2
        movie1.setTitle("Matrix");
        movie1.setReleaseDate(Year.of(1998));
        movie1.setDurationInMinutes(180);

        // serie
        serie.setTitle("La casa del dragón");
        serie.setReleaseDate(Year.of(2022));
        serie.setSeason(1);
        serie.setMinutesPerEpisode(50);
        serie.setEpisodesPerSeason(10);
        serie.technicalInfo();

        // Calculo
        calculator.includes(movie);
        calculator.includes(movie1);
        calculator.includes(serie);
        System.out.printf("Total tiempo para ver tus titulos: %d min.%n", calculator.getTimeTotal());

        // filtro de recomendación
        filter.filer(movie);

        // Eposodio
        episode.setNumber(1);
        episode.setName("La casa Targaryen");
        episode.setSerie(serie);
        episode.setTotalViews(50);
        filter.filer(episode);
    }
}

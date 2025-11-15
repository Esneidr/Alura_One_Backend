import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RetrieveMovie {
    public Movie searchMovie(int numberMovie) {
        URI adress = URI.create("https://swapi.py4e.com/api/films/"+numberMovie+"/");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(adress)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Movie.class);
        } catch (Exception e) {
            throw new RuntimeException("No se encontro esa película.");
        }
    }
}

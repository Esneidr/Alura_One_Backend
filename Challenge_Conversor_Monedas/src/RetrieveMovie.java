import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RetrieveMovie {
    public Converter converter(String code) {
        String ApiKey = "3545e1ab0e150c8fcce9308d";

        URI adress = URI.create("https://v6.exchangerate-api.com/v6/"+ApiKey+"/latest/"+code);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(adress)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Converter.class);
        } catch (Exception e) {
            throw new RuntimeException("No se encontro esa moneda: " + code);
        }
    }
}

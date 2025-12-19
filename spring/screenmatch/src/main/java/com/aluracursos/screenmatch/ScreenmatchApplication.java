package com.aluracursos.screenmatch;

import com.aluracursos.screenmatch.main.Main;
import com.aluracursos.screenmatch.models.EpisodeInfo;
import com.aluracursos.screenmatch.models.SeasonInfo;
import com.aluracursos.screenmatch.models.SeriesData;
import com.aluracursos.screenmatch.repository.SerieRepository;
import com.aluracursos.screenmatch.services.ApiConsumer;
import com.aluracursos.screenmatch.services.MapData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

    @Autowired
    private SerieRepository serieRepository;
	public static void main(String[] args) {
        SpringApplication.run(ScreenmatchApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        Main main = new Main(serieRepository);
        main.mainMenu();
    }
}

package com.sandeeprai.query;

import org.springframework.boot.ApplicationRunner;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Flux;

@Configuration
@EnableCaching
public class Config {
	
	@Bean
    ApplicationRunner init(QueryRepository repository) {

        Object[][] data = {
            {"when is my joining date"},
            {"where is my joining location"},
            {"am i selected"},
            {"Can my joining date be changed"}
        };

        return args -> {
            repository
                .deleteAll()
                .thenMany(
                    Flux
                        .just(data)
                        .map(array -> {
                            return new Queries((String) array[0]);
                        })
                        .flatMap(repository::save)
                )
                .thenMany(repository.findAll())
                .subscribe(query -> System.out.println("saving " + query.toString()));

        };
    }
}

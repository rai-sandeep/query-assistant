package com.sandeeprai.query;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class QueryService {
	
	private final QueryRepository repo;

	public Flux<Queries> getQueriesMatchingIn(@PathVariable List<String> keys) {
		return repo.findByQuestionIgnoreCaseIn(keys);
	}
	
	//TODO: See if mismatch between flux cache interval and cacheable causes issues
	@Cacheable("queries")
	public Flux<Queries> getQueries(String key) {
		return repo.findByQuestionContainingIgnoreCase(key).cache();
	}
}

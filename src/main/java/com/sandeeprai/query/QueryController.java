package com.sandeeprai.query;

import java.util.List;
import java.util.function.Function;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("queries")
@RequiredArgsConstructor
public class QueryController {
	
	private final QueryService service;

	@GetMapping("/all/{keys}")
	public Flux<Queries> getQueriesMatchingAll(@PathVariable List<String> keys) {
		return Flux.fromStream(keys.stream().map(service::getQueries))
				.flatMap(Function.identity())
				.groupBy(Function.identity())
				.filterWhen(q -> q.count().map(i -> i==keys.size()))
				.map(g -> g.key());
	}

	@GetMapping("/in/{keys}")
	public Flux<Queries> getQueriesMatchingIn(@PathVariable List<String> keys) {
		return service.getQueriesMatchingIn(keys);
	}
}

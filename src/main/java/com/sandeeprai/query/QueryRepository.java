package com.sandeeprai.query;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;

@Repository
public interface QueryRepository extends ReactiveMongoRepository<Queries, Long> {

	Flux<Queries> findByQuestionContainingIgnoreCase(String infix);

	//@Query(value = "{ question: { $all: [ 'when' , 'is' ] } }")
	//@Query(value = "{ \"question\" : { \"$all\" : [{ \"$regularExpression\" : { \"pattern\" : \"\\\\Qwhen\\\\E\", \"options\" : \"i\"}}, { \"$regularExpression\" : { \"pattern\" : \"\\\\Qis\\\\E\", \"options\" : \"i\"}}]}}")
	//@Query(value = "{ \"question\" : { \"$all\" : [{ \"$regularExpression\" : { \"pattern\" : \"\\\\Q?0\\\\E\", \"options\" : \"i\"}}]}}")
	Flux<Queries> findByQuestionIgnoreCaseIn(List<String> infix);
}

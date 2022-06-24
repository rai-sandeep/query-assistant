package com.sandeeprai.query;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document  
@Data  
@AllArgsConstructor  
@NoArgsConstructor 
public class Queries {

	private String question;
}

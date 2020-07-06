package br.com.score.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Score extends User {
	
	Score(Integer userId, Integer score) {
		super(userId, score);
	}

	private Integer points;

}

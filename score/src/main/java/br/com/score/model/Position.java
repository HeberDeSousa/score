package br.com.score.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Position extends User {
	
	public Position(Integer userId, Integer score) {
		super(userId, score);
	}
	
	public Position(User user, Integer position) {
		super(user.getUserId(), user.getScore());
		this.position = position;
	}

	private Integer position;

}

package br.com.score.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

	private Integer userId;
	private Integer score;
	
	public User(Integer userId) {
		this.userId = userId;
	}
	
	public User(Integer userId, Integer score) {
		this.userId = userId;
		this.score = score;
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof User && this.userId.equals(((User) o).getUserId());

	}

}

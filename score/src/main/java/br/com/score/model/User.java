package br.com.score.model;

import lombok.Getter;
import lombok.Setter;

/**
* User is the main entity we'll be using to set scores
* 
* It has two sub classes {@link br.com.score.Score} and {@link br.com.score.Position}
* @author Heber de Sousa
* 
*/
@Getter
@Setter
public class User {

	/**
	 * User's unique identity
	 */
	private Integer userId;
	
	/**
	 * User's score
	 */
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

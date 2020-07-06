package br.com.score.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
* Score is a wrapper class for returning User's relative position to the score board
* 
* Please see the {@link br.com.score.User} for the superclass
* @author Heber de Sousa
* 
*/
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

	@ApiModelProperty(value = "User's relative position to the score board")
	/**
	 * User's relative position to the score board
	 */
	private Integer position;

}

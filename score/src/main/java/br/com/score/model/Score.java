package br.com.score.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
* Score is a wrapper class for inserting/updating User's scores
* 
* Please see the {@link br.com.score.User}  for the superclass
* @author Heber de Sousa
* 
*/
@Getter
@Setter
public class Score extends User {
	
	Score(Integer userId, Integer score) {
		super(userId, score);
	}

	@ApiModelProperty(value = "User's points to be added")
	/**
	 * Points to be added to User's current score 
	 * (in case it's a new user this will be the user's first score)
	 */
	private Integer points;

}

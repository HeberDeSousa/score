package br.com.score.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.score.model.Position;
import br.com.score.model.Score;
import br.com.score.service.ScoreBoard;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class ScoreController {
	
	@ApiOperation(value = "This method can be called several times per user and not return anything.\r\n" + 
			"The points should be added to the user’s current score (score = current score + new points).")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Score inserted/updated"),
	    @ApiResponse(code = 403, message = "Forbidden"),
	    @ApiResponse(code = 400, message = "Client side error"),
	    @ApiResponse(code = 500, message = "Server side error"),
	})
	@PostMapping(value = "/score")
	public void setScore(@RequestBody Score score) {
		ScoreBoard.insertScore(score);
	}
	
	@ApiOperation(value = "Retrieves the current position of a specific user, considering the score for all users.\r\n" + 
			"If a user hasn't submitted a score, the response must be empty.")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Current User's position"),
	    @ApiResponse(code = 403, message = "Forbidden"),
	    @ApiResponse(code = 400, message = "Client side error"),
	    @ApiResponse(code = 500, message = "Server side error"),
	})
	@GetMapping(value = "/{userId}/position")
	public Position getPosition(@PathVariable(name = "userId") Integer userId) {
		return ScoreBoard.getPosition(userId);
	}
	
	@ApiOperation(value = "Retrieves the high scores list, in order, limited higher scores\r\n" + 
			"A request for a high score list without any scores submitted shall be an empty list.")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Current Higher Scores"),
	    @ApiResponse(code = 403, message = "Forbidden"),
	    @ApiResponse(code = 400, message = "Client side error"),
	    @ApiResponse(code = 500, message = "Server side error"),
	})
	@GetMapping(value = "/highscore")
	public List<Position> getHighScore() {
		return ScoreBoard.getHighScore();
	}

}

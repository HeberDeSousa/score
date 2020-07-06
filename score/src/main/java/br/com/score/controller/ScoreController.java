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

@RestController
public class ScoreController {
	
	@PostMapping(value = "/score")
	public void setScore(@RequestBody Score score) {
		ScoreBoard.insertScore(score);
	}
	
	@GetMapping(value = "/{userId}/position")
	public Position getPosition(@PathVariable(name = "userId") Integer userId) {
		return ScoreBoard.getPosition(userId);
	}
	
	@GetMapping(value = "/highscore")
	public List<Position> getHighScore() {
		return ScoreBoard.getHighScore();
	}

}

package br.com.score.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

import br.com.score.model.Position;
import br.com.score.model.Score;
import br.com.score.model.User;

public class ScoreBoard {

	// Keeps the collection always sorted by score on insertion
	private static ConcurrentSkipListSet<User> highscores = new ConcurrentSkipListSet<>(
			Comparator.comparing((User u) -> -u.getScore()).thenComparing(u -> u.getUserId()));

	public static void insertScore(Score s) {
		User user = new User(s.getUserId(), s.getPoints());
		if (highscores.parallelStream().anyMatch(u -> u.equals(user))) {
			// Find user
			User oldScore = highscores.parallelStream().filter(u -> u.getUserId().equals(s.getUserId())).findFirst().get();
			user.setScore(oldScore.getScore() + user.getScore());
			// Reinsert to keep score order
			highscores.remove(oldScore);
		}
		highscores.add(user);
	}

	public static List<Position> getHighScore() {
		// retrieves top 20000 current positions
		List<User> top = highscores.parallelStream().limit(20000).collect(Collectors.toList());
		
		// adds a position value to each entry
		List<Position> highscore = new ArrayList<>();
		int i = 1;
		for (User u : top) {
			highscore.add(new Position(u, i));
			i++;
		}
		return highscore;
	}

	public static Position getPosition(Integer userId) {
		// Find user
		User user = highscores.parallelStream().filter(u -> u.getUserId().equals(userId)).findFirst().get();
		// Return collection size which is the user's current position
		return new Position(user, highscores.headSet(user, true).size());
	}

}

package br.com.score.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

import br.com.score.model.Position;
import br.com.score.model.Score;
import br.com.score.model.User;

public class ScoreBoard {
	
	/**
	 * Max number of positions to be returned by high core
	 */
	private static final Integer LIMIT = 20000;

	// Keeps the collection always sorted by score on insertion
	// Trade-off: gain performance by never having to fully sort the collection but have slower insert and fetching data 
	// (which is still faster than having to sort every time a position or high score request came in)
	// we never have to go through the whole collection when it's higher than LIMIT this way
	private static ConcurrentSkipListSet<User> highscores = new ConcurrentSkipListSet<>(
			Comparator.comparing((User u) -> -u.getScore()).thenComparing(u -> u.getUserId()));

	/**
	 * <p>This method can be called several times per user and not return anything
	 * The points should be added to the user’s current score (score = current score + new points).
	 * </p>
	 * @param <b>Score</b> contains the unique User identification <b>userId</b> and <b>points</b> to be added to current score
	 * @return nothing
	 * @see {@link br.com.score.Score}
	 * @since 1.0.0-RELEASE
	 */
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

	/**
	 * <p>Retrieves the high scores list, in order, limited to the 20000 higher scores
	 * A request for a high score list without any scores submitted shall be an empty list.
	 * </p>
	 * @param none
	 * @return List of {@link br.com.score.Position} containing top LIMIT higher scores
	 * @see {@link br.com.score.Position}
	 * @since 1.0.0-RELEASE
	 */
	public static List<Position> getHighScore() {
		// retrieves top LIMIT current positions
		List<User> top = highscores.parallelStream().limit(LIMIT).collect(Collectors.toList());
		
		// adds a position value to each entry 
		// (this is the only time we have to go though the list but it's limited by LIMIT)
		List<Position> highscore = new ArrayList<>();
		int i = 1;
		for (User u : top) {
			highscore.add(new Position(u, i));
			i++;
		}
		return highscore;
	}

	/**
	 * <p>Retrieves the current position of a specific user, considering the score for all users.
	 * If a user hasn't submitted a score, the response must be empty.
	 * </p>
	 * @param userId unique {@link br.com.score.User} identification
	 * @return A {@link br.com.score.Position} containing the current position of {@link br.com.score.User} in the score board.
	 * @see {@link br.com.score.Position}
	 * @since 1.0.0-RELEASE
	 */
	public static Position getPosition(Integer userId) {
		// Find user
		Optional<User> user = highscores.parallelStream().filter(u -> u.getUserId().equals(userId)).findFirst();
		// Return empty if User is not found
		if (!user.isPresent()) {
			return null;
		}
		// Return the collection size which is the user's current position
		return new Position(user.get(), highscores.headSet(user.get(), true).size());
	}

}

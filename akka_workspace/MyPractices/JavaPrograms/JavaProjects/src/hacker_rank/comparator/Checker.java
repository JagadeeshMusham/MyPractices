package hacker_rank.comparator;

public class Checker implements Comparable<Player> {

	Player player1;

	public Checker() {
	}

	public Checker(Player player) {
		player1 = new Player(player.name, player.score);
	}

	@Override
	public int compareTo(Player player) {
		if (player1.score == player.score) {
			if (player1.name.compareTo(player.name) > 0) {
				return -1;
			} else {
				return 1;
			}
		} else if (player1.score > player.score) {
			return -1;
		} else {
			return 1;
		}
	}
}

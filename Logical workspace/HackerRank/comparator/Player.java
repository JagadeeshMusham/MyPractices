package hacker_rank.comparator;

public class Player implements Comparable<Player>{
	String name;
	int score;

	Player(String name, int score) {
		this.name = name;
		this.score = score;
	}

	public int compareTo(Player player) {
		if (this.score == player.score) {
			if (this.name.compareTo(player.name) > 0) {
				return -1;
			} else {
				return 1;
			}
		} else if (this.score > player.score) {
			return -1;
		} else {
			return 1;
		}
	}

}

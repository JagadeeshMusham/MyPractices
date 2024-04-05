package pramati.codility;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class WhichSweet {

	public static void main(String [] args) {
		String votes[] = new String[4];
		votes[0] = "abc";
		votes[1] = "bca";
		votes[2] = "abc";
		votes[3] = "bca";
		whichSweetShouldIBring(votes);
	}
	
	 static String whichSweetShouldIBring(String[] votes) {
		 Map<String, Integer> voteMap = new HashMap<>();
		 
		 for(String vote : votes) {
			 int nVotes = 0;
			 if (voteMap.containsKey(vote)) {
				 nVotes = voteMap.get(vote);
				 voteMap.remove(vote);
			 }
			 
			 ++nVotes;
			 voteMap.put(vote, nVotes);
		 }
		 
		 String maxVotedSweet = getMaxVotedSweet(voteMap);
		 
		 return maxVotedSweet;
	 }

	private static String getMaxVotedSweet(Map<String, Integer> voteMap) {
		String maxVote = "";
		int nMaxVotes = 0;
		for (Entry<String, Integer> entry : voteMap.entrySet()) {
			if (nMaxVotes == entry.getValue()) {
				if (maxVote.compareTo(entry.getKey()) < 0) {
					maxVote = entry.getKey();
				}
			} else if (nMaxVotes < entry.getValue()) {
				maxVote = entry.getKey();
				nMaxVotes = entry.getValue();
			}
			
		}
		return maxVote;
	}
}

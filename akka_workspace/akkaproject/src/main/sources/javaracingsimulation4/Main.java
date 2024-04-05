package javaracingsimulation4;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * This is new example with Racing simulator by using Java and threads concept
 * in this package we are not using akka framework
 */
public class Main {

	static int raceLength = 100;
	static int displayLength = 160;
	static long start;

	private static void displayRace(Map<Integer, Integer> currentPositions) {
		int displayLength = 160;

		for (int i = 0; i < 50; ++i)
			System.out.println();
		System.out.println("Race has been running for " + ((System.currentTimeMillis() - start) / 1000) + " seconds.");
		System.out.println("    " + new String(new char[displayLength]).replace('\0', '='));
		for (int currentPosition = 0; currentPosition < 10; currentPosition++) {
			System.out.println(currentPosition + " : "
					+ new String(new char[currentPositions.get(currentPosition) * displayLength / 100]).replace('\0',
							'*'));
		}
	}

	public static void main(String[] args) throws InterruptedException {

		Map<Integer, Integer> currentPositions = new ConcurrentHashMap<Integer, Integer>();
		Map<Integer, Long> results = new ConcurrentHashMap<Integer, Long>();

		start = System.currentTimeMillis();

		ExecutorService threadPool = Executors.newFixedThreadPool(10);

		for (int currentPosition = 0; currentPosition < 10; currentPosition++) {
			Racer racer = new Racer(currentPosition, raceLength, currentPositions, results);
			currentPositions.put(currentPosition, 0);
			threadPool.execute(racer);
		}

		boolean finished = false;
		while (!finished) {
			Thread.sleep(1000);
			displayRace(currentPositions);
			finished = results.size() == 10;
		}

		threadPool.shutdownNow();

		System.out.println("Results");
		results.values().stream().sorted().forEach(it -> {
			for (Integer key : results.keySet()) {
				if (results.get(key) == it) {
					System.out.println("Racer " + key + " finished in " + ((double) it - start) / 1000 + " seconds.");
				}
			}
		});
	}
}

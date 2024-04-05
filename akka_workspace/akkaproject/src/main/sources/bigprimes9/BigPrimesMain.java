package bigprimes9;

import java.math.BigInteger;
import java.time.Duration;
import java.util.SortedSet;
import java.util.concurrent.CompletionStage;

import akka.actor.typed.ActorSystem;
import akka.actor.typed.javadsl.AskPattern;;

/**
 * 
 * In this package we will fetch the responses from the akka framework to main
 * method
 * 
 */

public class BigPrimesMain {
	public static void main(String[] args) {
		ActorSystem<ManagerBehavior.Command> actorSystem = ActorSystem.create(ManagerBehavior.create(),
				"BigPrimeInteger");

		CompletionStage<SortedSet<BigInteger>> results = AskPattern.ask(actorSystem,
				(me) -> new ManagerBehavior.InstructionCommand("start", me), Duration.ofSeconds(40),
				actorSystem.scheduler());

		results.whenComplete((reply, failure) -> {
			if (reply != null) {
				System.out.println("Following are received BigPrimes:");
				reply.forEach(System.out::println);
			} else {
				System.out.println("The System didn't responded in time");
			}

			// with this stmt, from now the application will close once it completes the
			// execution
			actorSystem.terminate();
		});
	}
}

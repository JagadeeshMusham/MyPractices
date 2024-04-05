package primenumberswithbehavior6;

import akka.actor.typed.ActorSystem;

/**
 * 
 * This is Big primes example by using the Akka framework
 *
 */

public class BigPrimesMain {
	public static void main(String[] args) {
		ActorSystem<ManagerBehavior.Command> actorSystem = ActorSystem.create(ManagerBehavior.create(), "BigPrimeInteger");
		
		actorSystem.tell(new ManagerBehavior.InstructionCommand("start"));
	}
}

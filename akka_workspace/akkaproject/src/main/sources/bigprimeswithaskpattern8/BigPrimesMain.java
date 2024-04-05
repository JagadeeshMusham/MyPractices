package bigprimeswithaskpattern8;

import akka.actor.typed.ActorSystem;

/**
 * 
 * In this package we will resend command to worker if they not responded
 * earlier request and we will keep on resending till we receive data to the
 * master
 * 
 * We use ask pattern in this case
 * 
 * Chapter 42
 *
 */

public class BigPrimesMain {
	public static void main(String[] args) {
		ActorSystem<ManagerBehavior.Command> actorSystem = ActorSystem.create(ManagerBehavior.create(),
				"BigPrimeInteger");

//		ManagerBehavior.InstructionCommand ic = new ManagerBehavior.InstructionCommand("start");
		actorSystem.tell(new ManagerBehavior.InstructionCommand("start"));
	}
}

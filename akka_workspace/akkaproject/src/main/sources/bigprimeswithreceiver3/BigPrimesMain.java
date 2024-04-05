package bigprimeswithreceiver3;

import akka.actor.typed.ActorSystem;

/*
 * In previous package, the managers send string messages and here by using static inner classes the messages sent to worker.
 * 
 * And 
 * 
 * Worker also send response to manager
 */
public class BigPrimesMain {
	public static void main(String[] args) {
		ActorSystem<ManagerBehavior.Command> actorSystem = ActorSystem.create(ManagerBehavior.create(), "BigPrimeInteger");
		
		actorSystem.tell(new ManagerBehavior.InstructionCommand("start"));
	}
}

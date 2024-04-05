package akkaracingsimulatorwithbehavior7;

import akka.actor.typed.ActorSystem;

/**
 * 
 * It displays the race position
 * 
 * Stopping the Racer thread whenever it receives Signal from the controller
 *
 */

public class AkkaRacingSimulatorMain {
	public static void main(String [] args) {
		ActorSystem<RaceController.Command> raceController = ActorSystem.create(RaceController.create(), "AkkaRaceSimulator");
		raceController.tell(new RaceController.StartCommand());
	}
}

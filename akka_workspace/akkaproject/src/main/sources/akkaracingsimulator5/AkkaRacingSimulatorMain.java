package akkaracingsimulator5;

import akka.actor.typed.ActorSystem;

/*
 * Racer example with akka framework
 * 
 * it uses timers to get the response from the Racer
 */

public class AkkaRacingSimulatorMain {
	public static void main(String [] args) {
		ActorSystem<RaceController.Command> raceController = ActorSystem.create(RaceController.create(), "AkkaRaceSimulator");
		raceController.tell(new RaceController.StartCommand());
	}
}

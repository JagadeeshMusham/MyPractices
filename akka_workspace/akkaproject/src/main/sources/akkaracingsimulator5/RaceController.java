package akkaracingsimulator5;

import java.io.Serializable;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class RaceController extends AbstractBehavior<RaceController.Command> {

	public static interface Command extends Serializable {
	}

	public static class StartCommand implements Command {
		private static final long serialVersionUID = 1L;
	}

	//This command will update the racer position 
	public static class RacerUpdateCommand implements Command {
		private static final long serialVersionUID = 1L;

		private ActorRef<Racer.Command> racer;
		private int position;

		public RacerUpdateCommand(ActorRef<akkaracingsimulator5.Racer.Command> racer, int position) {
			super();
			this.racer = racer;
			this.position = position;
		}

		public ActorRef<Racer.Command> getRacer() {
			return racer;
		}

		public int getPosition() {
			return position;
		}
	}

	// it will return position of the all racers
	private class GetPositionsCommand implements Command {
		private static final long serialVersionUID = 1L;

	}

	private Object TIMER_KEY = null;

	private RaceController(ActorContext<Command> context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public static Behavior<Command> create() {
		return Behaviors.setup(RaceController::new);
	}

	private Map<ActorRef<Racer.Command>, Integer> currentPosition;
	private long start;
	private int raceLength = 100;

	//Displays the current race
	private void displayRace() {
		int displayLength = 160;

		for (int counter = 0; counter < 50; ++counter)
			System.out.println();
		System.out.println("Race has been running for " + ((System.currentTimeMillis() - start) / 1000) + " seconds.");
		System.out.println("    " + new String(new char[displayLength]).replace('\0', '='));

		int counter = 0;
		for (ActorRef<Racer.Command> racer : currentPosition.keySet()) {
			System.out.println(counter + " : "
					+ new String(new char[currentPosition.get(racer) * displayLength / 100]).replace('\0', '*'));
			counter++;
		}
	}

	@Override
	public Receive<Command> createReceive() {
		return newReceiveBuilder().onMessage(StartCommand.class, message -> {
			start = System.currentTimeMillis();
			currentPosition = new HashMap<>();
			for (int counter = 0; counter < 10; counter++) {
				ActorRef<Racer.Command> racer = getContext().spawn(Racer.create(), "Racer" + counter);
				currentPosition.put(racer, 0);
				racer.tell(new Racer.StartCommand(raceLength));
			}

			return Behaviors.withTimers(timer -> {
				timer.startTimerAtFixedRate(TIMER_KEY, new GetPositionsCommand(), Duration.ofSeconds(1));
				return this;
			});
		}).onMessage(GetPositionsCommand.class, message -> {
			for (ActorRef<Racer.Command> racer : currentPosition.keySet()) {
				racer.tell(new Racer.PositionCommand(getContext().getSelf()));
				displayRace();
			}
			return this;
		}).onMessage(RacerUpdateCommand.class, message -> {
			currentPosition.put(message.getRacer(), message.getPosition());
			return this;
		}).build();
	}
}

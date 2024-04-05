package akkaracingsimulatorwithbehavior7;

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

	public static class RacerUpdateCommand implements Command {
		private static final long serialVersionUID = 1L;

		private ActorRef<Racer.Command> racer;
		private int position;

		public RacerUpdateCommand(ActorRef<Racer.Command> racer, int position) {
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

	private class GetPositionsCommand implements Command {
		private static final long serialVersionUID = 1L;

	}

	public static class RacerFinishedCommand implements Command {
		private static final long serialVersionUID = 1L;

		private ActorRef<Racer.Command> racer;

		public RacerFinishedCommand(ActorRef<akkaracingsimulatorwithbehavior7.Racer.Command> racer) {
			super();
			this.racer = racer;
		}

		public ActorRef<Racer.Command> getRacer() {
			return racer;
		}

	}

	private Object TIMER_KEY = null;

	private RaceController(ActorContext<Command> context) {
		super(context);
	}

	public static Behavior<Command> create() {
		return Behaviors.setup(RaceController::new);
	}

	private Map<ActorRef<Racer.Command>, Integer> currentPositions;
	private Map<ActorRef<Racer.Command>, Long> finishingTimes;
	private long start;
	private int raceLength = 100;

	private void displayRace() {
		int displayLength = 160;

		for (int counter = 0; counter < 50; ++counter)
			System.out.println();
		System.out.println("Race has been running for " + ((System.currentTimeMillis() - start) / 1000) + " seconds.");
		System.out.println("    " + new String(new char[displayLength]).replace('\0', '='));

		for (ActorRef<Racer.Command> racer : currentPositions.keySet()) {
			System.out.println(racer.path().name() + " : "
					+ new String(new char[currentPositions.get(racer) * displayLength / 100]).replace('\0', '*'));
		}
	}

	@Override
	public Receive<Command> createReceive() {
		return newReceiveBuilder().onMessage(StartCommand.class, message -> {
			start = System.currentTimeMillis();
			currentPositions = new HashMap<>();
			finishingTimes = new HashMap<>();
			for (int counter = 0; counter < 10; counter++) {
				ActorRef<Racer.Command> racer = getContext().spawn(Racer.create(), "Racer" + counter);
				currentPositions.put(racer, 0);
				racer.tell(new Racer.StartCommand(raceLength));
			}

			return Behaviors.withTimers(timer -> {
				timer.startTimerAtFixedRate(TIMER_KEY, new GetPositionsCommand(), Duration.ofSeconds(1));
				return Behaviors.same();
			});
		}).onMessage(GetPositionsCommand.class, message -> {
			for (ActorRef<Racer.Command> racer : currentPositions.keySet()) {
				racer.tell(new Racer.PositionCommand(getContext().getSelf()));
				displayRace();
			}
			return Behaviors.same();
		}).onMessage(RacerUpdateCommand.class, message -> {
			currentPositions.put(message.getRacer(), message.getPosition());
			return this;
		}).onMessage(RacerFinishedCommand.class, message -> {
			finishingTimes.put(message.getRacer(), System.currentTimeMillis());
			if (finishingTimes.size() == 10) {
				return raceCompleteMessageHandler();
			} else {
				return Behaviors.same();
			}
		}).build();
	}

	public Receive<Command> raceCompleteMessageHandler() {
		return newReceiveBuilder().onMessage(GetPositionsCommand.class, message -> {
			for (ActorRef<Racer.Command> racer : currentPositions.keySet()) {
				getContext().stop(racer);
			}

			displayPositions();

			return Behaviors.withTimers(timers -> {
				timers.cancelAll();
				return Behaviors.stopped();
			});
		}).build();
	}

	private void displayPositions() {
		System.out.println("Results");
		finishingTimes.values().stream().sorted().forEach(finishedTime -> {
			for (ActorRef<Racer.Command> key : finishingTimes.keySet()) {
				if (finishingTimes.get(key) == finishedTime) {
					String raceID = key.path().toString().substring(key.path().toString().length() - 1);
					System.out.println(
							"Racer " + raceID + " finished in " + ((double) finishedTime - start) / 1000 + " seconds.");
				}
			}
		});
	}

}

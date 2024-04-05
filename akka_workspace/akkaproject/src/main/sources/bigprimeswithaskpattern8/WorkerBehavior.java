package bigprimeswithaskpattern8;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Random;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class WorkerBehavior extends AbstractBehavior<WorkerBehavior.Command> {

	public static class Command implements Serializable {
		private static final long serialVersionUID = 1L;
		private String message;
		private ActorRef<ManagerBehavior.Command> sender;

		public Command(String message, ActorRef<ManagerBehavior.Command> sender) {
			this.message = message;
			this.sender = sender;
		}

		public String getMessage() {
			return message;
		}

		public ActorRef<ManagerBehavior.Command> getSender() {
			return sender;
		}
	}

	private WorkerBehavior(ActorContext<Command> context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public static Behavior<Command> create() {
		return Behaviors.setup(WorkerBehavior::new);
	}

	@Override
	public Receive<Command> createReceive() {
		return handleMessageWhenWeDontHavePrimeNumber();
	}

	public Receive<Command> handleMessageWhenWeDontHavePrimeNumber() {
		return newReceiveBuilder().onAnyMessage(command -> {
			BigInteger bigInteger = new BigInteger(2000, new Random());
			BigInteger nextProbablePrime = bigInteger.nextProbablePrime();

			Random random = new Random();
			int temp = random.nextInt(5);
			if (temp < 2) {
				command.getSender().tell(new ManagerBehavior.ResultCommand(nextProbablePrime));
			}

			return handleMessageWhenWeHavePrimeNumber(nextProbablePrime);
		}).build();
	}

	public Receive<Command> handleMessageWhenWeHavePrimeNumber(BigInteger nextProbablePrime) {
		return newReceiveBuilder().onAnyMessage(command -> {
			Random random = new Random();
			int temp = random.nextInt(5);
			if (temp < 2) {
				command.getSender().tell(new ManagerBehavior.ResultCommand(nextProbablePrime));
			}

			return Behaviors.same();
		}).build();
	}
}

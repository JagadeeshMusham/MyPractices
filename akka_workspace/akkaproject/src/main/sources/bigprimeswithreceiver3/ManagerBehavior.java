package bigprimeswithreceiver3;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.SortedSet;
import java.util.TreeSet;

import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class ManagerBehavior extends AbstractBehavior<ManagerBehavior.Command> {

	public interface Command extends Serializable {

	}

	public static class InstructionCommand implements Command {
		public static final long serialVersionUID = 1L;

		private String message;

		public InstructionCommand(String message) {
			super();
			this.message = message;
		}

		public String getMessage() {
			return message;
		}
	}

	public static class ResultCommand implements Command {
		public static final long serialVersionUID = 1L;

		private BigInteger prime;

		public ResultCommand(BigInteger prime) {
			super();
			this.prime = prime;
		}

		public BigInteger getPrime() {
			return prime;
		}
	}

	public ManagerBehavior(ActorContext<Command> context) {
		super(context);
	}

	public static Behavior<Command> create() {
		return Behaviors.setup((ManagerBehavior::new));
	}

	private SortedSet<BigInteger> primes = new TreeSet<BigInteger>();

	@Override
	public Receive<Command> createReceive() {
		return newReceiveBuilder().onMessage(InstructionCommand.class, command -> {
			if (command.getMessage().equals("start")) {
				for (int counter = 0; counter < 30; counter++) {
					ActorSystem<WorkerBehavior.command> actorSystem = ActorSystem.create(WorkerBehavior.create(),
							"worker" + counter);
					actorSystem.tell(new WorkerBehavior.command("start", getContext().getSelf()));
				}
			}
			return this;
		}).onMessage(ResultCommand.class, command -> {
			primes.add(command.getPrime());
			System.out.println("The received " + primes.size() + " number of primes");
			if (primes.size() == 30) {
				primes.forEach(System.out::println);
			}
			return this;
		}).build();
	}

}

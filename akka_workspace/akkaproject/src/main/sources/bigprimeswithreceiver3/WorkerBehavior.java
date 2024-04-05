package bigprimeswithreceiver3;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Random;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class WorkerBehavior extends AbstractBehavior<WorkerBehavior.command> {
	
	public static class command implements Serializable {
		private static final long serialVersionUID = 1L;
		private String message;
		private ActorRef<ManagerBehavior.Command> sender;
		
		public command(String message, ActorRef<ManagerBehavior.Command> sender) {
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

	private WorkerBehavior(ActorContext<command> context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public static Behavior<command> create() {
		return Behaviors.setup(WorkerBehavior::new);
	}

	@Override
	public Receive<command> createReceive() {
		return newReceiveBuilder()
				.onAnyMessage(command -> {
					BigInteger nextProbablePrime;
					if (command.getMessage().equals("start")) {
						BigInteger bigInteger = new BigInteger(2000, new Random());
						nextProbablePrime = bigInteger.nextProbablePrime();
						
						command.getSender().tell(new ManagerBehavior.ResultCommand(nextProbablePrime));
					}
					
					return this;
				})
				.build();
	}
}

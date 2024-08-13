package com.musham.akkaproject.bigprimes2;

import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class ManagerBehavior extends AbstractBehavior<String> {

	public ManagerBehavior(ActorContext<String> context) {
		super(context);
	}

	public static Behavior<String> create() {
		return Behaviors.setup((ManagerBehavior::new));
	}

	@Override
	public Receive<String> createReceive() {
		return newReceiveBuilder()
				.onMessageEquals("start", () -> {
					for (int counter = 0; counter < 30; counter++) {
						ActorSystem<String> actorSystem = ActorSystem.create(WorkerBehavior.create(), "worker" + counter);
						actorSystem.tell("start");
					}
					return this;
				})
				.build();
	}

}

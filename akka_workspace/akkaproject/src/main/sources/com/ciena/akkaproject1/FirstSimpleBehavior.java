package com.ciena.akkaproject1;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class FirstSimpleBehavior extends AbstractBehavior<String> {

	private FirstSimpleBehavior(ActorContext<String> context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

//	public static Behavior<String> create()	//todo j, This is same as below, only signature difference
//	{
//		Behaviors.setup(context -> {
//			return new FirstSimpleBehavior(context);
//		});
//	}

	// above commented method extended form of this method
	public static Behavior<String> create() {
		return Behaviors.setup(FirstSimpleBehavior::new);
	}

	@Override
	public Receive<String> createReceive() {
		return newReceiveBuilder().onMessageEquals("say hello", () -> {
			System.out.println("Hello");
			return this;
		}).onMessageEquals("who are you", () -> {
			System.out.println("My Path is: " + getContext().getSelf().path());
			return this;
		}).onMessageEquals("create a child", () -> {
			ActorRef<String> secondActor = getContext().spawn(FirstSimpleBehavior.create(), "SecondActor");
			secondActor.tell("who are you");
			return this;
		}).onAnyMessage(message -> {
			System.out.println("Received message is: " + message);
			return this;
		}).build();
	}

}

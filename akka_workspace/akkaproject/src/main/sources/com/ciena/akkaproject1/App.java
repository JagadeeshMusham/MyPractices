package com.ciena.akkaproject1;

import akka.actor.typed.ActorSystem;

/**
 * 
 * This is simple program which explains basics of akka framework
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");

		ActorSystem<String> actorSystem = ActorSystem.create(FirstSimpleBehavior.create(), "FirstActorSystem");
		actorSystem.tell("Hello are you there?");
		actorSystem.tell("who are you");
		actorSystem.tell("say hello");
		actorSystem.tell("create a child");
	}
}

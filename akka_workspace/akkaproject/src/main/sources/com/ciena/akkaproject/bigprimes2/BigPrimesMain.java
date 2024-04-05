package com.ciena.akkaproject.bigprimes2;

import akka.actor.typed.ActorSystem;

/*
 * In this example we have included Manager and worker classes to generate 30 big integer primes
 */
public class BigPrimesMain {
	public static void main(String[] args) {
		ActorSystem<String> actorSystem = ActorSystem.create(ManagerBehavior.create(), "BigPrimeInteger");
		actorSystem.tell("start");
	}
}

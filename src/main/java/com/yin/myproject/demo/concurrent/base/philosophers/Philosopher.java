package com.yin.myproject.demo.concurrent.base.philosophers;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Philosopher implements Runnable {
	private Chopstick left;
	private Chopstick right;
	private final int id;
	private final int ponderFactor;
	private Random rand = new Random(47);

	private void pause() throws InterruptedException {
		if (ponderFactor == 0)
			return;
		TimeUnit.MILLISECONDS.sleep(rand.nextInt(ponderFactor * 250));
	}

	public Philosopher(Chopstick left, Chopstick right, int ponderFactor, int id) {
		this.left = left;
		this.right = right;
		this.ponderFactor = ponderFactor;
		this.id = id;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				System.out.println(this + " thinking");
				pause();
				System.out.println(this + " grabbing right");
				right.taken();
				System.out.println(this + " gtabbing left");
				left.taken();
				System.out.println(this + " eating");
				pause();
				right.drop();
				left.drop();
			}
		} catch (Exception e) {
			System.out.println(this + " " + "exiting via interrupt");
		}
	}

	@Override
	public String toString() {
		return "Philosopher " + id;
	}
}

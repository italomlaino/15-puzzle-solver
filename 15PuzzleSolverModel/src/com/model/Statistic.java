package com.model;

public class Statistic {

	private long generated;
	private long explored;
	private long startTime;
	private long endTime;

	public Statistic() {
		generated = 0;
		explored = 0;
		startTime = 0;
		endTime = 0;
	}

	public void registerGenerated() {
		generated++;
	}

	public void registerExplored() {
		explored++;
	}

	public void registerStartTime() {
		startTime = System.nanoTime();
	}

	public void registerEndTime() {
		endTime = System.nanoTime();
	}

	public long getGenerated() {
		return generated;
	}

	public long getExplored() {
		return explored;
	}

	public long getStartTime() {
		return startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public long getElapsedTime() {
		return endTime - startTime;
	}
}

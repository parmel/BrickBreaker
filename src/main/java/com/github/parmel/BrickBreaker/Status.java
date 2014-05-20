package com.github.parmel.BrickBreaker;

public class Status {

	private int points;
	private int lives;

	public Status(int points, int lives) {
		this.points = points;
		this.lives = lives;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

}

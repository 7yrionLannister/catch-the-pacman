package model;

import java.io.Serializable;


public class Pacman implements Serializable {
	public final static int UP = 1;
	public final static int DOWN = 2;
	public final static int LEFT = 3;
	public final static int RIGHT = 4;
	
	private int radius;
	private int posX;
	private int posY;
	private int waitingTime;
	private int direction;
	private int bounces;
	private boolean caught;
	private int startAngle;
	private int length;
	
	public Pacman(int radius, int posX, int posY, int waitingTime, int direction, int bounces, boolean caught) {
		this.radius = radius;
		this.posX = posX;
		this.posY = posY;
		this.waitingTime = waitingTime;
		this.direction = direction;
		this.bounces = bounces;
		this.caught = caught;
		startAngle = 45;
		length = 270;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getBounces() {
		return bounces;
	}

	public void setBounces(int bounces) {
		this.bounces = bounces;
	}

	public boolean isCaught() {
		return caught;
	}

	public void setCaught(boolean caught) {
		this.caught = caught;
	}

	public int getStartAngle() {
		return startAngle;
	}

	public void setStartAngle(int startAngle) {
		this.startAngle = startAngle;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
}

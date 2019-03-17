package model;

import java.io.Serializable;


public class Pacman implements Serializable {
	public final static String UP = "UP";
	public final static String DOWN = "DOWN";
	public final static String LEFT = "LEFT";
	public final static String RIGHT = "RIGHT";
	
	private int radius;
	private int posX;
	private int posY;
	private int waitingTime;
	private String direction;
	private int bounces;
	private boolean caught;
	
	public Pacman(int radius, int posX, int posY, int waitingTime, String direction, int bounces, boolean caught) {
		this.radius = radius;
		this.posX = posX;
		this.posY = posY;
		this.waitingTime = waitingTime;
		this.direction = direction;
		this.bounces = bounces;
		this.caught = caught;
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

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
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
}

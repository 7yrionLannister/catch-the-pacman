package model;

import java.io.Serializable;


public class Pacman implements Serializable {
	/**The constant indicates the upward movement
	 * */
	public final static String UP = "UP";
	/**The constant indicates the downward movement
	 * */
	public final static String DOWN = "DOWN";
	/**The constant indicates the movement to the left
	 * */
	public final static String LEFT = "LEFT";
	/**The constant indicates the movement to the right
	 * */
	public final static String RIGHT = "RIGHT";
	
	/**The attribute represents the radius of the pacman
	 * */
	private int radius;
	/**The attribute represents the x layout of the pacman
	 * */
	private int posX;
	/**The attribute represents the y layout of the pacman
	 * */
	private int posY;
	/**The attribute represents the speed of the pacman
	 * */
	private int waitingTime;
	/**The attribute represents the direction where the pacman is moving
	 * */
	private String direction;
	/**The total amount of times the pacman has touched a wall or another pacman
	 * */
	private int bounces;
	/**The flag to know whether a pacman has been caught or not
	 * */
	private boolean caught;
	
	/**The constructor allows to create a new pacman with the specified characteristics
	 * @param radius The radius that the pacman will have
	 * @param posX The x coordinate of the pacman
	 * @param posY The y coordinate of the pacman
	 * @param waitingTime The speed of the pacman
	 * @param direction The initial direction of the pacman
	 * @param bounces The bounces that the pacman starts with
	 * @param caught Whether a pacman has been caught or not
	 * */
	public Pacman(int radius, int posX, int posY, int waitingTime, String direction, int bounces, boolean caught) {
		this.radius = radius;
		this.posX = posX;
		this.posY = posY;
		this.waitingTime = waitingTime;
		this.direction = direction;
		this.bounces = bounces;
		this.caught = caught;
	}

	/**The method allows to obtain the radius of the pacman
	 * @return The radius of the pacman
	 * */
	public int getRadius() {
		return radius;
	}

	/**The method allows to modify the radius of the pacman
	 * @param radius The new radius of the pacman
	 * */
	public void setRadius(int radius) {
		this.radius = radius;
	}

	/**The method allows to obtain the x coordinate of the pacman
	 * @return The x coordinate of the pacman
	 * */
	public int getPosX() {
		return posX;
	}

	/**The method allows to modify the x coordinate of the pacman
	 * @param posX The new x coordinate for the pacman
	 * */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**The method allows to obtain the y coordinate of the pacman
	 * @return The y coordinate of the pacman
	 * */
	public int getPosY() {
		return posY;
	}

	/**The method allows to modify the y coordinate of the pacman
	 * @param posY The new x coordinate for the pacman
	 * */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	/**The method allows to obtain the speed of the pacman
	 * @return The speed of the pacman in milliseconds
	 * */
	public int getWaitingTime() {
		return waitingTime;
	}

	/**The method allows to modify the speed of the pacman
	 * @param waitingTime The new speed for the pacman
	 * */
	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}

	/**The method allows to obtain the direction that this pacman is going to
	 * @return A String with the current direction
	 * */
	public String getDirection() {
		return direction;
	}

	/**The method allows to modify the direction that this pacman is going to
	 * @param direction The new direction for the pacman
	 * */
	public void setDirection(String direction) {
		this.direction = direction;
	}

	/**The method allows to obtain the total bounces that this pacman has made
	 * @return The total amount of bounces
	 * */
	public int getBounces() {
		return bounces;
	}

	/**The method allows to modify the total bounces that the pacman has made
	 * @param bounces The new amount of bounces
	 * */
	public void setBounces(int bounces) {
		this.bounces = bounces;
	}

	/**The emthod allows to know whether or not the pacman has been caught
	 * @return A boolean that is true when the pacman has been caught and false if it has not
	 * */
	public boolean isCaught() {
		return caught;
	}

	/**The method allows to modify the flag that allows to know whether or not the pacman has been caught
	 * @param caught The new value for the flag
	 * */
	public void setCaught(boolean caught) {
		this.caught = caught;
	}
}

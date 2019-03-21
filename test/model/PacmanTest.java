package model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.security.SecureRandom;

import org.junit.jupiter.api.Test;

class PacmanTest {

	private Pacman pacman;
	private SecureRandom sr;
	
	public void setupScenary1() {
		sr = new SecureRandom();
		pacman = null;
	}
	
	@Test
	public void createPacmanTest() {
		setupScenary1();
		int radius = sr.nextInt();
		int posX = sr.nextInt();
		int posY = sr.nextInt();
		int waitingTime = sr.nextInt();
		String direction = Pacman.DOWN;
		int bounces = sr.nextInt();
		boolean caught = sr.nextBoolean();
		pacman = new Pacman(radius, posX, posY, waitingTime, direction, bounces, caught);
		assertTrue("The radius was not assigned correctly", radius == pacman.getRadius());
		assertTrue("The posX was not assigned correctly", posX == pacman.getPosX());
		assertTrue("The posY was not assigned correctly", posY == pacman.getPosY());
		assertTrue("The waitingTime was not assigned correctly", waitingTime == pacman.getWaitingTime());
		assertTrue("The direction was not assigned correctly", direction.equals(pacman.getDirection()));
		assertTrue("The bounces were not assigned correctly", bounces == pacman.getBounces());
		assertTrue("The flag \"caught\" was not correctly assigned", caught == pacman.isCaught());
	}

}

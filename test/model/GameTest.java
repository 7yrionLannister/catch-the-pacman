package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;


public class GameTest {
	
	private Game game;
	
	public void setupScenary1() {
		game = null;
	}
	
	@Test
	public void createGameTest() {
		setupScenary1();
		try {
			game = new Game("path");
			fail("The game should not have been created because \"path\" is not a valid file path");
		} catch (ClassNotFoundException | IOException e) {
			assertTrue(true);
		}
		setupScenary1();
		try {
			game = new Game(Game.HARD_LEVEL_PATH);
			assertTrue(true);
		} catch (ClassNotFoundException | IOException e) {
			fail("The game should have been initialized since Game.HARD_LEVEL_PATH is a valid file path");
		}
	}
}

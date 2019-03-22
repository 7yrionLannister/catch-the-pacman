package model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.security.SecureRandom;

import org.junit.jupiter.api.Test;


public class HallMemberTest {
	private SecureRandom sr;
	private HallMember hm;
	
	public void setupScenary1() {
		sr = new SecureRandom();
		hm = null;
	}
	
	@Test
	public void createHallMemberTest() {
		setupScenary1();
		String nickname = "Rubiu5"; 
		int score = sr.nextInt();
		hm = new HallMember(nickname, score);
		assertTrue("The name was not assigned correctly", nickname.equals(hm.getNickname()));
		assertTrue("The score was not assigned correctly", score == hm.getScore());
	}
}

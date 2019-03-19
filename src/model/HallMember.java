package model;

import java.io.Serializable;


public class HallMember implements Serializable {
	private String nickname;
	private int score;
	
	public HallMember(String nickname, int score) {
		this.nickname = nickname;
		this.score = score;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}

package model;

import java.io.Serializable;


public class HallMember implements Serializable {
	/**The attribute is a way to represent or identify a member of the hall of fame
	 * */
	private String nickname;
	/**The attribute is the count of all the bounces that the pacmans made before the player caught them
	 * */
	private int score;
	
	/**The constructor allows to initialize a new hall member with its corresponding nickname and score
	 * @param nickname The player nickname
	 * @param score The total bounces of the caught pacmans
	 * */
	public HallMember(String nickname, int score) {
		this.nickname = nickname;
		this.score = score;
	}

	/**The method allows to obtain the player's nickname
	 * @return A String with the player's score
	 * */
	public String getNickname() {
		return nickname;
	}

	/**The method allows to modify the player's nickname
	 * @param nickname The new nickname for the player
	 * */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**The method allows to obtain the score that the player made in his game
	 * @return An int with  the count of the bounces that the pacmans made before the player caught them
	 * */
	public int getScore() {
		return score;
	}

	/**The method allows to modify the player's score
	 * @param score The new score for the player
	 * */
	public void setScore(int score) {
		this.score = score;
	}
}

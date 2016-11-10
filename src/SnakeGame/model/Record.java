package SnakeGame.model;

import java.io.Serializable;

/*
 * record
 */
public class Record implements Serializable {

	private static final long serialVersionUID = -545152110065949242L;
	
	//player name
	private String player = null;
	
	//player score
	private int score = 0;
	
	public Record(String player, int score) {
		this.player = player;
		this.score = score;
	}
	
	/**
	 * @return player
	 */
	public String getPlayer() {
		return player;
	}
	
	/**
	 * @param player
	 * set player
	 */
	public void setPlayer(String player) {
		this.player = player;
	}
	
	/**
	 * @return score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * @param score
	 * set score
	 */
	public void setScore(int score) {
		this.score = score;
	}
}

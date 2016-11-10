package SnakeGame.enums;

/*
 * direction
 */
public enum Direction {
	UP, DOWN, LEFT, RIGHT;
	
	public boolean isUpDirection() {
		return UP == this;
	}
	
	public boolean isDownDirection() {
		return DOWN == this;
	}
	
	public boolean isLeftDirection() {
		return LEFT == this;
	}
	
	public boolean isRightDirection() {
		return RIGHT == this;
	}
	
}

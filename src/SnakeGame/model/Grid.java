package SnakeGame.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import SnakeGame.constants.SnakeGameConstant;

/*
 * Grid
 */

public class Grid implements Serializable {

	private static final long serialVersionUID = 5105993927776028563L;
	
	private int x;
	
	private int y;
	
	//square color
	private Color color;
	
	public Grid() {
		
	}
	
	public Grid(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	/**
	 * Draw Grid
	 * 
	 * @param g2
	 */
	public void draw(Graphics2D g2) {
		int clientX = SnakeGameConstant.SNAKE_GAME_PANEL_LEFT + x * SnakeGameConstant.GRID_SIZE;
		int clientY = SnakeGameConstant.SNAKE_GAME_PANEL_TOP + y * SnakeGameConstant.GRID_SIZE;
		Rectangle2D.Double rect = new Rectangle2D.Double(clientX, clientY, SnakeGameConstant.GRID_SIZE, SnakeGameConstant.GRID_SIZE);
		g2.setPaint(color);
		g2.fill(rect);
		g2.setPaint(Color.BLACK);
		g2.draw(rect);
	}
	
	/**
	 * @return color
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * @param color
	 * set color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * @return x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @param x
	 * set x
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * @return y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * @param y
	 * set y
	 */
	public void setY(int y) {
		this.y = y;
	}
}

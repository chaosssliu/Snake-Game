package SnakeGame.model;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Barriers implements Serializable {

	private static final long serialVersionUID = -7101579102934745505L;
	
	private List<Grid> barriersList = null;
	
	public Barriers() {
		barriersList = new ArrayList<Grid>();
	}
	
	public void draw(Graphics2D g2) {
		for (Grid grid : barriersList) {
			grid.draw(g2);
		}
	}
	
	/**
	 * @return barriersList
	 */
	public List<Grid> getBarriersList() {
		return barriersList;
	}
	
	/**
	 * @param barriersList
	 * set barriersList
	 */
	public void setBarriersList(List<Grid> barriersList) {
		this.barriersList = barriersList;
	}
}

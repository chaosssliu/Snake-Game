package SnakeGame.enums;

import java.util.HashMap;
import java.util.Map;

/*
 * game state
 */
public enum GameState {

	INITIALIZE("I", "Initial Game State"), RUN("R", "Run State"), PAUSE("P",
			"Pause State"), OVER("O", "Over State"), UNKNOWN("U", "UNKNOWN");
	
	private String gameStateCode = null;
	
	private String gameStateValue = null;
	
	private static Map<String, GameState> MAP = new HashMap<String, GameState>();
	
	static {
		for (GameState gameState : GameState.values()) {
			MAP.put(gameState.getGameStateCode(), gameState);
		}
	}
	
	private GameState(String gameStateCode, String gameStateValue) {
		this.gameStateCode = gameStateCode;
		this.gameStateValue = gameStateValue;
	}
	
	public static GameState getGameStateByCode(String gameStateCode) {
		return MAP.containsKey(gameStateCode) ? MAP.get(gameStateCode) : UNKNOWN;
	}
	
	/*
	 * @return gameStateCode
	 */
	public String getGameStateCode() {
		return gameStateCode;
	}
	
	/*
	 * @param gameStateCode
	 * set gameStateCode
	 */
	public void setGameStateCode(String gameStateCode) {
		this.gameStateCode = gameStateCode;
	}
	
	/*
	 * @return gameStateValue
	 */
	public String getGameStateValue() {
		return gameStateValue;
	}
	/*
	 * @param gameStateValue
	 * set gameStateValue
	 */
	public void setGameStateValue(String gameStateValue) {
		this.gameStateValue = gameStateValue;
	}
	
	public boolean isInitializedState() {
		return this == INITIALIZE;
	}
	
	public boolean isRunState() {
		return this == RUN;
	}
	
	public boolean isPausedState() {
		return this == PAUSE;
	}
	
	public boolean isOverState() {
		return this == OVER;
	}
}

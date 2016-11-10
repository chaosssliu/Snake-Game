package SnakeGame.application;

import javax.swing.JFrame;

import SnakeGame.ui.SnakeGameFrame;

public class MySnakeGameApplication {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		SnakeGameFrame frame = new SnakeGameFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.show();

	}

}

package SnakeGame.ui;

import java.awt.Container;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;

import SnakeGame.constants.SnakeGameConstant;
import SnakeGame.enums.GameState;
import SnakeGame.model.SnakeGameRecords;
import SnakeGame.model.record.ReadRecord;

public class SnakeGameFrame extends JFrame {

	private static final long serialVersionUID = 998014032682506026L;
	
	private SnakeGamePanel panel;
	
	private Container contentPane;
	
	private JMenuItem startMI = new JMenuItem("Start");
	
	private JMenuItem pauseMI = new JMenuItem("Pause");
	
	private JMenu speedMenu = new JMenu("Speed");
	
	private JMenuItem exitMI = new JMenuItem("Exit");
	
	private JMenuItem aboutMI = new JMenuItem("About");
	
	private JMenuItem loadMI = new JMenuItem("Open");
	
	public JMenuItem saveMI = new JMenuItem("Save");
	
	private JMenuItem recordMI = new JMenuItem("Record");
	
	private JRadioButtonMenuItem speedMI1 = new JRadioButtonMenuItem("Speed1", true);
	
	private JRadioButtonMenuItem speedMI2 = new JRadioButtonMenuItem("Speed2", false);
	
	private JRadioButtonMenuItem speedMI3 = new JRadioButtonMenuItem("Speed3", false);
	
	private JRadioButtonMenuItem speedMI4 = new JRadioButtonMenuItem("Speed4", false);
	
	private JRadioButtonMenuItem speedMI5 = new JRadioButtonMenuItem("Speed5", false);
	
	public int speedFlag = 1;
	
	public SnakeGameFrame() {
		setTitle(SnakeGameConstant.SNAKE_GAME);
		setSize(SnakeGameConstant.SNAKE_GAME_FRAME_WIDTH, SnakeGameConstant.SNAKE_GAME_FRAME_HEIGHT);
		setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu setMenu = new JMenu("Set");
		JMenu helpMenu = new JMenu("Help");
		
		setMenu.setMnemonic('s');
		setMenu.setMnemonic('H');
		
		menuBar.add(setMenu);
		menuBar.add(helpMenu);
		
		setMenu.add(startMI);
		setMenu.add(pauseMI);
		setMenu.addSeparator();
		
		setMenu.addSeparator();
		setMenu.add(speedMenu);
		setMenu.addSeparator();
		setMenu.add(exitMI);
		
		setMenu.add(loadMI);
		setMenu.add(saveMI);
		
		setMenu.add(recordMI);
		
		ButtonGroup group = new ButtonGroup();
		group.add(speedMI1);
		group.add(speedMI2);
		group.add(speedMI3);
		group.add(speedMI4);
		group.add(speedMI5);
		
		speedMenu.add(speedMI1);
		speedMenu.add(speedMI2);
		speedMenu.add(speedMI3);
		speedMenu.add(speedMI4);
		speedMenu.add(speedMI5);
		
		startMI.addActionListener(new StartAction());
		pauseMI.addActionListener(new PauseAction());
		exitMI.addActionListener(new ExitAction());
		speedMI1.addActionListener(new SpeedAction());
		speedMI2.addActionListener(new SpeedAction());
		speedMI3.addActionListener(new SpeedAction());
		speedMI4.addActionListener(new SpeedAction());
		speedMI5.addActionListener(new SpeedAction());
		
		recordMI.addActionListener(new RecordAction());
		
		loadMI.addActionListener(new LoadAction());
		saveMI.addActionListener(new SaveAction());
		
		helpMenu.add(aboutMI);
		aboutMI.addActionListener(new AboutAction());
		
		contentPane = getContentPane();
		panel = new SnakeGamePanel(this);
		contentPane.add(panel);
		
		startMI.setEnabled(true);
		pauseMI.setEnabled(false);
		saveMI.setEnabled(false);
		
		//initialize game
		panel.setGameState(GameState.INITIALIZE);
	}
	
	private class StartAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			startMI.setEnabled(false);
			pauseMI.setEnabled(true);
			saveMI.setEnabled(true);
			panel.setGameState(GameState.RUN);
			panel.getTimer().start();
		}
	}
	
	private class PauseAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			pauseMI.setEnabled(false);
			startMI.setEnabled(true);
			panel.setGameState(GameState.PAUSE);
			if (panel.getTimer().isRunning()) {
				panel.getTimer().stop();
			}
		}
	}
	
	private class SpeedAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			Object speed = event.getSource();
			if (speed == speedMI1) {
				speedFlag = 1;
			}else if (speed == speedMI2) {
				speedFlag = 2;
			}else if (speed == speedMI3) {
				speedFlag = 3;
			}else if (speed == speedMI4) {
				speedFlag = 4;
			}else if (speed == speedMI5) {
				speedFlag = 5;
			}
			
			panel.getTimer().setDelay(1000 - 200 * (speedFlag - 1));
		}
	}
	
	private class ExitAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			int result = JOptionPane.showConfirmDialog(SnakeGameFrame.this, SnakeGameConstant.QUIT_GAME,
					SnakeGameConstant.SNAKE_GAME, JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}
	
	private class AboutAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String string = SnakeGameConstant.KEYBOARDS_DESCRIPTION;
			JOptionPane.showMessageDialog(SnakeGameFrame.this, string);
		}
	}
	
	private class LoadAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			FileDialog dialog = new FileDialog(SnakeGameFrame.this, "Open", FileDialog.LOAD);
			dialog.setVisible(true);
			String dir = dialog.getDirectory();
			String fileName = dialog.getFile();
			String filePath = dir + fileName;
			
			if (fileName != null && fileName.trim().length() != 0) {
				File file = new File(filePath);
				panel.loadGameDataFromFile(file);
				startMI.setEnabled(false);
				pauseMI.setEnabled(true);
			}else {
				JOptionPane.showConfirmDialog(SnakeGameFrame.this,
						"Empty File Name\nFail to Load", "Snake Game", JOptionPane.DEFAULT_OPTION);
			}
		}
	}
	
	private class SaveAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (panel.getGameState() == GameState.INITIALIZE) {
				JOptionPane.showConfirmDialog(SnakeGameFrame.this, "Game Is Not Running\nFail to Save",
						"Snake Game", JOptionPane.DEFAULT_OPTION);
				return;
			}
			
			FileDialog dialog = new FileDialog(SnakeGameFrame.this, "Save", FileDialog.SAVE);
			dialog.setVisible(true);
			String dir = dialog.getDirectory();
			String fileName = dialog.getFile();
			String filePath = dir + fileName;
			if (fileName != null && fileName.trim().length() != 0) {
				File file = new File(filePath);
				panel.saveGameDataToFile(file);
			} else {
				JOptionPane.showConfirmDialog(SnakeGameFrame.this,
						"Empty File Name\nFail to Save", "Snake Game", JOptionPane.DEFAULT_OPTION);
			}
		}
	}
	
	private class RecordAction implements ActionListener {
		
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent event) {
			File file = new File("file.dat");
			SnakeGameRecords records = new ReadRecord().readRecordsFromFile(file);
			records.sortRecords();
			JScrollPane panel = new RecordScrollPane().getReadScrollPane(records, file);
			
			JDialog recordDialog = new JDialog(SnakeGameFrame.this, "Snake Game");
			recordDialog.setBounds(300, 300, 300, 219);
			
			Container container = recordDialog.getContentPane();
			container.add(panel);
			recordDialog.show();
		}
	}
}

package windowpkg;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import systempkg.GameContext;

import panelpkg.MainMenuPanel;
import panelpkg.PlayerCustomPanel;
import panelpkg.LoadSlotPanel;

public class MainWindow extends JFrame {
	
	public static final String PANEL_MAIN_MENU = "MAIN_MENU";
	public static final String PANEL_LOAD_SLOTS = "LOAD_SLOTS";
	public static final String PANEL_PLAYER_CUSTOM = "PLAYER_CUSTOM";
//	public static final String PANEL_GAME_MAIN = "GAME_MAIN";
	public static final String PANEL_GAME_ALL_CHAR_INFO = "GAME_ALL_CHAR_INFO";
	
	private CardLayout layout;
	private JPanel container;
	
	public MainWindow(GameContext context) {
		layout = new CardLayout();
		container = new JPanel(layout);
		
		initPanels(context);
		
		add(container);
		setSize(1024, 768);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void initPanels(GameContext context) {
		container.add(new MainMenuPanel(this, context), "MAIN_MENU");
		container.add(new LoadSlotPanel(this, context), "LOAD_SLOTS");
		container.add(new PlayerCustomPanel(this, context), "PLAYER_CUSTOM");
//		container.add(new GameMainPanel(this, context), "GAME_MAIN");
	}
	public void showPanel(String name) {
	    layout.show(container, name);
	}
	
	public void showPanel(String name, JPanel panel) {
		container.add(panel, name);
		layout.show(container, name);
	}
}

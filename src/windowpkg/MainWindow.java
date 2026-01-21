package windowpkg;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import systempkg.GameContext;

public class MainWindow extends JFrame {
	
	public static final String PANEL_MAIN_MENU = "MAIN_MENU";
	public static final String PANEL_LOAD_SLOTS = "LOAD_SLOTS";
	public static final String PANEL_GAME_LOADING = "GAME_LOADING";
	public static final String PANEL_PLAYER_CUSTOM = "PLAYER_CUSTOM";
	public static final String PANEL_GAME_MAIN = "GAME_MAIN";
	public static final String PANEL_CHAR_STATS = "CHAR_STATS_W";
	
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
		container.add(new GameloadingPanel(this, context), "GAME_LOADING");
		container.add(new PlayerCustomPanel(this, context), "PLAYER_CUSTOM");
		container.add(new GameMainPanel(this, context), "GAME_MAIN");
		container.add(new CharacterStatsPanel(this, context), "CHAR_STATS_W");
	}
	
	public void showPanel(String name) {
		layout.show(container, name);
	}
}

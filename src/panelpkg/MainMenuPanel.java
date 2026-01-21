package panelpkg;

import javax.swing.*;
import java.awt.*;

import windowpkg.MainWindow;
import systempkg.GameContext;

public class MainMenuPanel extends JPanel {

	private final MainWindow window;
	private final GameContext context;
	
	public MainMenuPanel(MainWindow window, GameContext context) {
		this.window = window;
		this.context = context;
		
		initUI();
	}
	
	private void initUI() {
		setLayout(new BorderLayout());
		
		// title
		JLabel title = new JLabel("POST-CYBER : FORGOTTEN PLANET", SwingConstants.CENTER);
		title.setFont(new Font("Serif", Font.BOLD, 32));
		title.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
		
		add(title, BorderLayout.NORTH);
		
		// button area
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(3, 1, 10, 15));
		
		JButton btnNewGame = new JButton("New Game");
		JButton btnLoadGame = new JButton("Load Game");
		JButton btnExit = new JButton("Exit");
		
		buttonPanel.add(btnNewGame);
		buttonPanel.add(btnLoadGame);
		buttonPanel.add(btnExit);
		
		add(buttonPanel, BorderLayout.CENTER);
		
		// button event
		btnNewGame.addActionListener(e ->
				window.showPanel(MainWindow.PANEL_PLAYER_CUSTOM)
		);
		
		btnLoadGame.addActionListener(e ->
				window.showPanel(MainWindow.PANEL_LOAD_SLOTS)
		);

		btnExit.addActionListener(e -> {
			int result = JOptionPane.showConfirmDialog(
					this, "Exit the Game?", 
					"Confirm", 
					JOptionPane.YES_NO_OPTION
			);
			if (result == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
		);
		
	}

}

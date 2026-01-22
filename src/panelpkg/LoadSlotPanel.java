package panelpkg;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import windowpkg.MainWindow;
import systempkg.GameContext;

public class LoadSlotPanel extends JPanel {
	
	private final MainWindow window;
	private final GameContext context;
	
	public LoadSlotPanel(MainWindow window, GameContext context) {
		this.window = window;
		this.context = context;
		initUI();
	}
	
	public void initUI() {
		setLayout(new BorderLayout(10, 10));
		
		add(createTopPanel(), BorderLayout.NORTH);
		add(createSlotListPanel(), BorderLayout.CENTER);
	}
	
	private JPanel createTopPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		
		JLabel title = new JLabel("Load Game", SwingConstants.CENTER);
		title.setFont(new Font("Serif", Font.BOLD, 28));
		title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(e ->
				window.showPanel(MainWindow.PANEL_MAIN_MENU)
		);
		
		panel.add(backBtn, BorderLayout.WEST);
		panel.add(title, BorderLayout.CENTER);
		
		return panel;
	}
	
	// for dummy data
	
	private static class SaveSlotDummy {
		String playerName;
		String lastPlayed;
		
		SaveSlotDummy(String playerName, String lastPlayed) {
			this.playerName = playerName;
			this.lastPlayed = lastPlayed;
		}
	}
	
	private JScrollPane createSlotListPanel() {
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
		listPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		
		// dummy data
		List<SaveSlotDummy> dummySlots = List.of(
				new SaveSlotDummy("Aiden", "2026-01-19 21:12"),
				new SaveSlotDummy("Beta", "2026-01-19 22:05"),
				new SaveSlotDummy("Shalfy", "2026-01-20 01:17")
		);
		
		for (int i = 0; i < dummySlots.size(); i++) {
			SaveSlotDummy slot = dummySlots.get(i);
			listPanel.add(createSlotButton(i, slot));
			listPanel.add(Box.createVerticalStrut(10));
		}
		
		JScrollPane scrollPane = new JScrollPane(listPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		return scrollPane;
	}
	
	private JButton createSlotButton(int index, SaveSlotDummy slot) {
		JButton button = new JButton();
		button.setLayout(new BorderLayout());
		button.setPreferredSize(new Dimension(600, 80));
		button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
		
		JLabel nameLabel = new JLabel(slot.playerName);
		nameLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		
		JLabel lastPlayed = new JLabel(slot.lastPlayed);
		lastPlayed.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
		textPanel.add(nameLabel);
		textPanel.add(lastPlayed);
		
		button.add(textPanel, BorderLayout.CENTER);
		
		button.addActionListener(e -> {
			JOptionPane.showMessageDialog(
					this, 
					"Load Slot " + index + "\nPlayer: " + slot.playerName,
					"Load Game", 
					JOptionPane.INFORMATION_MESSAGE);
			// TODO:
			// context.setCurrentSaveSlot(index);
			// window.showPanel(MainWindow.PANEL_GAME_MAIN);
		});
		
		return button;
	}
}

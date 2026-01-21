package panelpkg;

import javax.swing.*;
import java.awt.*;

import windowpkg.MainWindow;

import systempkg.GameContext;

public class PlayerCustomPanel extends JPanel {
	
	private final MainWindow window;
	private final GameContext context;
	
	private JTextField nameField;
	private JComboBox<String> raceBox;
	
	private JRadioButton genderNone;
	private JRadioButton genderMale;
	private JRadioButton genderFemale;
	
	// Start label (프리뷰 용)
	private JLabel strVal, conVal, agiVal, dexVal, intVal, wisVal, lukVal;
	
	public PlayerCustomPanel(MainWindow window, GameContext context) {
		this.window = window;
		this.context = context;
		initUI();
	}
	
	private void initUI() {
		setLayout(new BorderLayout(10, 10));
		
		add(createTopPanel(), BorderLayout.NORTH);
		add(createStatPreviewPanel(), BorderLayout.CENTER);
		add(createBottomPanel(), BorderLayout.SOUTH);
	}
	
	private JPanel createTopPanel() {
		JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
		
		// name
		panel.add(new JLabel("Name"));
		nameField = new JTextField();
		panel.add(nameField);
		
		// gender
		panel.add(new JLabel("Gender"));
		JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		genderNone = new JRadioButton("None", true);
		genderMale = new JRadioButton("Male");
		genderFemale = new JRadioButton("Female");
		
		ButtonGroup group = new ButtonGroup();
		group.add(genderNone);
		group.add(genderMale);
		group.add(genderFemale);
		
		genderPanel.add(genderNone);
		genderPanel.add(genderMale);
		genderPanel.add(genderFemale);
		
		panel.add(genderPanel);
		
		// race
		panel.add(new JLabel("Race"));
		raceBox = new JComboBox<>(new String[] {
				"Human", "Elf", "Dwarf"
		});
		
		raceBox.addActionListener(e -> updateRacePreview());
		
		panel.add(raceBox);
		
		return panel;
	}
	
	// race stat preview
	private JPanel createStatPreviewPanel() {
		JPanel panel = new JPanel(new GridLayout(7, 2, 8, 8));
		panel.setBorder(BorderFactory.createTitledBorder("Race Basic Stats"));
		
		strVal = addStatRow(panel, "STR");
		conVal = addStatRow(panel, "CON");
		agiVal = addStatRow(panel, "AGI");
		dexVal = addStatRow(panel, "DEX");
		intVal = addStatRow(panel, "INT");
		wisVal = addStatRow(panel, "WIS");
		lukVal = addStatRow(panel, "LUK");
		
		updateRacePreview();
		
		return panel;
	}
	
	private JLabel addStatRow(JPanel panel, String name) {
		panel.add(new JLabel(name));
		JLabel val = new JLabel("0");
		panel.add(val);
		return val;
	}
	
	// updateRacePreview
	private void updateRacePreview() {
		String race = (String) raceBox.getSelectedItem();
		
		int str = 0, con = 0, agi = 0, dex = 0, intel = 0, wis = 0, luk = 0;
		
		switch (race) {
			case "Human" -> {
				str = 10; con = 10; agi = 10; dex = 10; intel = 10; wis = 10; luk = 10;
			}
			case "Elf" -> {
				str = 7; con = 8; agi = 12; dex = 11; intel = 12; wis = 11; luk = 9;
			}
			case "Dwarf" -> {
				str = 12; con = 12; agi = 8; dex = 11; intel = 12; wis = 10; luk = 5;
			}
		}
		
		strVal.setText(String.valueOf(str));
		conVal.setText(String.valueOf(con));
		agiVal.setText(String.valueOf(agi));
		dexVal.setText(String.valueOf(dex));
		intVal.setText(String.valueOf(intel));
		wisVal.setText(String.valueOf(wis));
		lukVal.setText(String.valueOf(luk));
	}
	
	// 승인 / 취소
	private JPanel createBottomPanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		JButton back = new JButton("Back");
		JButton confirm = new JButton("Confirm");
		
		back.addActionListener(e ->
				window.showPanel(MainWindow.PANEL_MAIN_MENU)
		);
		
		confirm.addActionListener(e -> onConfirm());
		
		panel.add(back);
		panel.add(confirm);
		
		return panel;
	}
	
	// confirm Progress
	private void onConfirm() {
		if (nameField.getText().isBlank()) {
			JOptionPane.showMessageDialog(this, "Enter character name");
			return;
		}
		
		int gender =
				genderNone.isSelected() ? 0 :
	            genderMale.isSelected() ? 1 : 2;
		
//		context.setPlayerName(nameField.getText());
//		context.setPlayerRace((String) raceBox.getSelectedItem());
//		context.setPlayerGender(gender);
		
		window.showPanel(MainWindow.PANEL_GAME_MAIN);
	}
}

package panelpkg;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import vopkg.CharacterFinalStatsVO;
import vopkg.CharacterCombatStatsVO;
import vopkg.CharacterSurvivalStatsVO;
import systempkg.CharacterDeriverdStatsCalculator;
import windowpkg.MainWindow;

public class GameAllCharInfoPanel extends JPanel {

	private final MainWindow window;
	private final List<CharacterFinalStatsVO> characters;

	private JComboBox<String> characterBox;

	// Basic info
	private JLabel nameVal, raceVal, levelVal, expVal, spVal;

	// Progress bars
	private JProgressBar hpBar, mpBar, staminaBar;

	// Base stats
	private JLabel strVal, conVal, agiVal, dexVal, intVal, wisVal, lukVal;

	// Derived stats (labels)
	private JLabel atkVal, defVal, magVal, resVal, crtVal, crrVal, spdVal;
	private JLabel carryVal, hungerVal, healVal, immuneVal, craftVal, envVal;

	public GameAllCharInfoPanel(MainWindow window, List<CharacterFinalStatsVO> characters) {
		this.window = window;
		this.characters = characters;
		initUI();
		loadCharacters();
	}

	private void initUI() {
		setLayout(new BorderLayout(10, 10));

		add(createTopPanel(), BorderLayout.NORTH);
		add(createMainPanel(), BorderLayout.CENTER);
		add(createBottomPanel(), BorderLayout.SOUTH);
	}

	// TOP

	private JPanel createTopPanel() {
		JPanel panel = new JPanel(new BorderLayout(5, 5));

		JPanel select = new JPanel(new FlowLayout(FlowLayout.LEFT));
		select.add(new JLabel("Character"));
		characterBox = new JComboBox<>();
		characterBox.addActionListener(e -> onCharacterSelected());
		select.add(characterBox);

		panel.add(select, BorderLayout.NORTH);
		panel.add(createInfoPanel(), BorderLayout.CENTER);

		return panel;
	}

	// BASIC INFO

	private JPanel createInfoPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 5));
		panel.setBorder(BorderFactory.createTitledBorder("Basic Info"));

		nameVal = addInfoCell(panel, "Name");
		raceVal = addInfoCell(panel, "Race");
		levelVal = addInfoCell(panel, "Level");
		expVal = addInfoCell(panel, "EXP");
		spVal = addInfoCell(panel, "SP");

		return panel;
	}

	private JLabel addInfoCell(JPanel parent, String title) {
		JPanel cell = new JPanel(new BorderLayout());
		cell.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.GRAY));

		JLabel t = new JLabel(title, SwingConstants.CENTER);
		JLabel v = new JLabel("-", SwingConstants.CENTER);

		t.setFont(t.getFont().deriveFont(Font.BOLD));
		cell.add(t, BorderLayout.NORTH);
		cell.add(v, BorderLayout.CENTER);

		parent.add(cell);
		return v;
	}

	// MAIN

	private JPanel createMainPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
		panel.add(createLeftPanel());
		panel.add(createRightPanel());
		return panel;
	}

	// LEFT

	private JPanel createLeftPanel() {
		JPanel panel = new JPanel(new BorderLayout(5, 5));
		panel.add(createStatusPanel(), BorderLayout.NORTH);
		panel.add(createBaseStatPanel(), BorderLayout.CENTER);
		return panel;
	}

	private JPanel createStatusPanel() {
		JPanel panel = new JPanel(new GridLayout(3, 1, 5, 5));
		panel.setBorder(BorderFactory.createTitledBorder("Status"));

		hpBar = createBar("HP", Color.RED);
		mpBar = createBar("MP", Color.BLUE);
		staminaBar = createBar("Stamina", Color.GREEN);

		panel.add(hpBar);
		panel.add(mpBar);
		panel.add(staminaBar);
		return panel;
	}

	private JProgressBar createBar(String name, Color color) {
		JProgressBar bar = new JProgressBar();
		bar.setStringPainted(true);
		bar.setForeground(color);
		bar.setBorder(BorderFactory.createTitledBorder(name));
		return bar;
	}

	private JPanel createBaseStatPanel() {
		JPanel panel = new JPanel(new GridLayout(7, 2, 8, 4));
		panel.setBorder(BorderFactory.createTitledBorder("Base Stats"));

		strVal = addStat(panel, "STR");
		conVal = addStat(panel, "CON");
		agiVal = addStat(panel, "AGI");
		dexVal = addStat(panel, "DEX");
		intVal = addStat(panel, "INT");
		wisVal = addStat(panel, "WIS");
		lukVal = addStat(panel, "LUK");

		return panel;
	}

	private JLabel addStat(JPanel panel, String name) {
		panel.add(new JLabel(name));
		JLabel v = new JLabel("0");
		panel.add(v);
		return v;
	}

	// RIGHT

	private JPanel createRightPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 1, 5, 5));

		panel.add(createCombatPanel());
		panel.add(createSurvivalPanel());

		return panel;
	}

	private JPanel createCombatPanel() {
		JPanel panel = new JPanel(new GridLayout(7, 2, 6, 4));
		panel.setBorder(BorderFactory.createTitledBorder("Combat Derived Stats"));

		atkVal = addStat(panel, "ATK");
		defVal = addStat(panel, "DEF");
		magVal = addStat(panel, "MAG");
		resVal = addStat(panel, "RES");
		crtVal = addStat(panel, "CRT");
		crrVal = addStat(panel, "CRR");
		spdVal = addStat(panel, "SPD");

		return panel;
	}

	private JPanel createSurvivalPanel() {
		JPanel panel = new JPanel(new GridLayout(6, 2, 6, 4));
		panel.setBorder(BorderFactory.createTitledBorder("Survival Derived Stats"));

		carryVal = addStat(panel, "Carry");
		hungerVal = addStat(panel, "HungerRes");
		healVal = addStat(panel, "HealRate");
		immuneVal = addStat(panel, "Immune");
		craftVal = addStat(panel, "CraftEff");
		envVal = addStat(panel, "EnvTol");

		return panel;
	}

	// BOTTOM

	private JPanel createBottomPanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton back = new JButton("Back");
		back.setFont(back.getFont().deriveFont(11f));
		back.addActionListener(e -> window.showPanel(MainWindow.PANEL_MAIN_MENU));
		panel.add(back);
		return panel;
	}

	// DATA

	private void loadCharacters() {
		for (CharacterFinalStatsVO vo : characters) {
			characterBox.addItem(vo.getName());
		}
		if (!characters.isEmpty()) {
			characterBox.setSelectedIndex(0);
			displayCharacter(characters.get(0));
		}
	}

	private void onCharacterSelected() {
		int idx = characterBox.getSelectedIndex();
		if (idx >= 0)
			displayCharacter(characters.get(idx));
	}

	private void displayCharacter(CharacterFinalStatsVO vo) {

		nameVal.setText(vo.getName());
		raceVal.setText(vo.getRaceName());
		levelVal.setText(String.valueOf(vo.getLevel()));
		expVal.setText(String.valueOf(vo.getExp()));
		spVal.setText(String.valueOf(vo.getStatPoint()));

		strVal.setText(String.valueOf(vo.getFinalStr()));
		conVal.setText(String.valueOf(vo.getFinalCon()));
		agiVal.setText(String.valueOf(vo.getFinalAgi()));
		dexVal.setText(String.valueOf(vo.getFinalDex()));
		intVal.setText(String.valueOf(vo.getFinalInt()));
		wisVal.setText(String.valueOf(vo.getFinalWis()));
		lukVal.setText(String.valueOf(vo.getFinalLuk()));

		CharacterCombatStatsVO c = 
				CharacterDeriverdStatsCalculator.calculateCombat(
						vo.getFinalStr(), 
						vo.getFinalCon(), 
						vo.getFinalAgi(), 
						vo.getFinalDex(), 
						vo.getFinalInt(), 
						vo.getFinalWis(), 
						vo.getFinalLuk()
				);

		CharacterSurvivalStatsVO s = 
				CharacterDeriverdStatsCalculator.calculateSurvival(
						vo.getFinalStr(), 
						vo.getFinalCon(), 
						vo.getFinalAgi(), 
						vo.getFinalDex(), 
						vo.getFinalInt(), 
						vo.getFinalWis(), 
						vo.getFinalLuk()
				);

		updateBar(hpBar, c.getHp(), c.getHp());
		updateBar(mpBar, c.getMp(), c.getMp());
		updateBar(staminaBar, s.getStamina(), s.getStamina());

		atkVal.setText(String.valueOf(c.getAtk()));
		defVal.setText(String.valueOf(c.getDef()));
		magVal.setText(String.valueOf(c.getMag()));
		resVal.setText(String.valueOf(c.getRes()));
		crtVal.setText(c.getCrt() + "%");
		crrVal.setText(c.getCrr() + "%");
		spdVal.setText(String.valueOf(c.getSpd()));

		carryVal.setText(String.valueOf(s.getCarryCapacity()));
		hungerVal.setText(String.valueOf(s.getHungerResistance()));
		healVal.setText(String.valueOf(s.getHealRate()));
		immuneVal.setText(String.valueOf(s.getImmuneStrength()));
		craftVal.setText(String.valueOf(s.getCraftingEfficiency()));
		envVal.setText(String.valueOf(s.getEnviromentTolerance()));
	}

	private void updateBar(JProgressBar bar, int current, int max) {
		bar.setMaximum(max);
		bar.setValue(current);
		bar.setString(current + " / " + max);
	}
}

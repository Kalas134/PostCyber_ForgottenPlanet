package panelpkg;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.util.List;

import windowpkg.MainWindow;
import systempkg.GameContext;
import systempkg.GameStartData;
import systempkg.PlayerCustomize;

import daopkg.RaceInfoDAO;
import daopkg.DefaultCharacterLoader;

import testbuild.SaveSlotGenerator;
import testbuild.SaveXmlLoader;
import testbuild.CharacterXmlGenerator;

import vopkg.RaceBasicStatsVO;
import vopkg.CharacterVO;
import vopkg.DefaultCharacterVO;

public class PlayerCustomPanel extends JPanel {

	private final MainWindow window;
	private final GameContext context;

	private JTextField nameField;
	private JComboBox<String> raceBox;

	private JRadioButton genderNone;
	private JRadioButton genderMale;
	private JRadioButton genderFemale;

	private JLabel strVal, conVal, agiVal, dexVal, intVal, wisVal, lukVal;

	public PlayerCustomPanel(MainWindow window, GameContext context) {
		this.window = window;
		this.context = context;
		initUI();
		loadRaceList();
	}

	private void initUI() {
		setLayout(new BorderLayout(10, 10));

		add(createTopPanel(), BorderLayout.NORTH);
		add(createStatPreviewPanel(), BorderLayout.CENTER);
		add(createBottomPanel(), BorderLayout.SOUTH);
	}

	private JPanel createTopPanel() {
		JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

		panel.add(new JLabel("Name"));
		nameField = new JTextField();
		panel.add(nameField);

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

		panel.add(new JLabel("Race"));
		raceBox = new JComboBox<>();
		raceBox.addActionListener(e -> updateRacePreview());
		panel.add(raceBox);

		return panel;
	}

	private void loadRaceList() {
		try {
			for (String id : RaceInfoDAO.loadRaceIds()) {
				raceBox.addItem(id);
			}
			if (raceBox.getItemCount() > 0) {
				raceBox.setSelectedIndex(0);
				updateRacePreview();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Failed to load race list");
		}
	}

	private JPanel createStatPreviewPanel() {
		JPanel panel = new JPanel(new GridLayout(7, 2, 8, 8));
		panel.setBorder(BorderFactory.createTitledBorder("Race Basic Stats"));

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

	private void updateRacePreview() {
		String raceId = (String) raceBox.getSelectedItem();
		if (raceId == null) return;

		try {
			RaceBasicStatsVO stats = RaceInfoDAO.loadRaceStats(raceId);

			strVal.setText(String.valueOf(stats.getStr()));
			conVal.setText(String.valueOf(stats.getCon()));
			agiVal.setText(String.valueOf(stats.getAgi()));
			dexVal.setText(String.valueOf(stats.getDex()));
			intVal.setText(String.valueOf(stats.getIntel()));
			wisVal.setText(String.valueOf(stats.getWis()));
			lukVal.setText(String.valueOf(stats.getLuk()));

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Failed to load race stats");
		}
	}

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

	private void onConfirm() {
		if (nameField.getText().isBlank()) {
			JOptionPane.showMessageDialog(this, "Enter character name");
			return;
		}

		try {
			// 1. Save slot 생성
			Path slotDir = SaveSlotGenerator.createNewSaveSlot(Path.of("save"));

			// 2. 디폴트 캐릭터 전체 로드
			DefaultCharacterLoader loader = new DefaultCharacterLoader();
			List<DefaultCharacterVO> characters =
					loader.loadAllDefaultCharacters();

			// 3. 입력값 수집
			String name = nameField.getText().trim();
			String race = (String) raceBox.getSelectedItem();
			int gender =
					genderMale.isSelected() ? 1 :
					genderFemale.isSelected() ? 2 : 0;

			// 4. 플레이어(Char_ID = 1)만 치환
			for (int i = 0; i < characters.size(); i++) {
				characters.set(
					i,
					PlayerCustomize.apply(
						characters.get(i),
						name,
						race,
						gender
					)
				);
			}

			// 5. XML 생성
			Path xmlPath = slotDir.resolve("characters.xml");
			CharacterXmlGenerator.generateNewGameCharacterXml(
					xmlPath, characters);

			// 6. 콘솔과 동일: 방금 만든 세이브 로드 → 게임 시작
			List<CharacterVO> loadedCharacters =
					SaveXmlLoader.loadCharacters(xmlPath);

			// 콘솔 출력이 떠도 상관 없음
			GameStartData.run(loadedCharacters);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Save failed");
			e.printStackTrace();
		}
	}
}

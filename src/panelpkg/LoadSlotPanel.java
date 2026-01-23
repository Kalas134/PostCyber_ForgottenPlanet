package panelpkg;

import javax.swing.*;
import java.awt.*;
import java.nio.file.*;
import java.io.IOException;
import java.util.List;

import windowpkg.MainWindow;
import systempkg.GameContext;
import systempkg.GameStartData;

import testbuild.SaveXmlLoader;

import vopkg.CharacterVO;
import vopkg.CharacterFinalStatsVO;

public class LoadSlotPanel extends JPanel {

	private static final Path SAVE_ROOT = Path.of("save");

	private final MainWindow window;
	private final GameContext context;

	public LoadSlotPanel(MainWindow window, GameContext context) {
		this.window = window;
		this.context = context;
		initUI();
	}

	private void initUI() {
		setLayout(new BorderLayout(10, 10));

		add(createTopPanel(), BorderLayout.NORTH);
		add(createSlotListPanel(), BorderLayout.CENTER);
	}

	/* ================= 상단 ================= */

	private JPanel createTopPanel() {
		JPanel panel = new JPanel(new BorderLayout());

		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(e ->
				window.showPanel(MainWindow.PANEL_MAIN_MENU)
		);

		JLabel title = new JLabel("Load Game", SwingConstants.CENTER);
		title.setFont(new Font("Dialog", Font.BOLD, 26));
		title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

		panel.add(backBtn, BorderLayout.WEST);
		panel.add(title, BorderLayout.CENTER);

		return panel;
	}

	/* ================= 슬롯 목록 ================= */

	private JScrollPane createSlotListPanel() {
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
		listPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

		if (!Files.exists(SAVE_ROOT)) {
			listPanel.add(new JLabel("No save directory found."));
			return new JScrollPane(listPanel);
		}

		try {
			List<Path> slots = Files.list(SAVE_ROOT)
					.filter(Files::isDirectory)
					.sorted()
					.toList();

			if (slots.isEmpty()) {
				listPanel.add(new JLabel("No save slots available."));
			}

			for (Path slotDir : slots) {
				listPanel.add(createSlotButton(slotDir));
				listPanel.add(Box.createVerticalStrut(10));
			}

		} catch (IOException e) {
			JOptionPane.showMessageDialog(
					this,
					"Failed to load save slots",
					"Error",
					JOptionPane.ERROR_MESSAGE
			);
		}

		JScrollPane scrollPane = new JScrollPane(listPanel);
		scrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
		);

		return scrollPane;
	}

	/* ================= 슬롯 버튼 ================= */

	private JButton createSlotButton(Path slotDir) {
		JButton button = new JButton();
		button.setLayout(new BorderLayout());
		button.setPreferredSize(new Dimension(600, 80));
		button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

		String slotName = slotDir.getFileName().toString();

		JLabel slotLabel = new JLabel("Save Slot " + slotName);
		slotLabel.setFont(new Font("Dialog", Font.BOLD, 16));

		JLabel pathLabel = new JLabel(slotDir.toString());
		pathLabel.setFont(new Font("Dialog", Font.PLAIN, 12));

		JPanel textPanel = new JPanel();
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
		textPanel.add(slotLabel);
		textPanel.add(pathLabel);

		button.add(textPanel, BorderLayout.CENTER);

		button.addActionListener(e -> loadSlot(slotDir));

		return button;
	}

	/* ================= 로드 처리 ================= */

	private void loadSlot(Path slotDir) {
		try {
			Path xmlPath = slotDir.resolve("characters.xml");

			if (!Files.exists(xmlPath)) {
				JOptionPane.showMessageDialog(
						this,
						"characters.xml not found in this slot",
						"Load Failed",
						JOptionPane.ERROR_MESSAGE
				);
				return;
			}

			// 1. XML 로드
			List<CharacterVO> characters =
					SaveXmlLoader.loadCharacters(xmlPath);

			// 2. 콘솔과 동일한 게임 시작 처리
			List<CharacterFinalStatsVO> finalStats =
					GameStartData.run(characters);

			// 3. 컨텍스트 설정
			context.setInGame(true);
			context.setCurrentSaveSlot(
					Integer.parseInt(slotDir.getFileName().toString())
			);

			// 4. 다음 패널로 이동
			window.showPanel(
					MainWindow.PANEL_GAME_ALL_CHAR_INFO,
					new GameAllCharInfoPanel(window, finalStats)
			);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(
					this,
					"Failed to load save slot",
					"Error",
					JOptionPane.ERROR_MESSAGE
			);
			e.printStackTrace();
		}
	}
}
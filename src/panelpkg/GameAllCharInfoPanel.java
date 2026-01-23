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
    private JTextArea infoArea;

    public GameAllCharInfoPanel(MainWindow window,
                                List<CharacterFinalStatsVO> characters) {
        this.window = window;
        this.characters = characters;
        initUI();
        loadCharacters();
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));

        // 상단 콤보박스
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Character"));

        characterBox = new JComboBox<>();
        characterBox.addActionListener(e -> onCharacterSelected());
        top.add(characterBox);

        add(top, BorderLayout.NORTH);

        // 출력 영역
        infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        JScrollPane scroll = new JScrollPane(infoArea);
        add(scroll, BorderLayout.CENTER);
    }

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
        if (idx >= 0) {
            displayCharacter(characters.get(idx));
        }
    }

    private void displayCharacter(CharacterFinalStatsVO vo) {
        StringBuilder sb = new StringBuilder();

        sb.append("Name: ").append(vo.getName()).append("\n");
        sb.append("Race: ").append(vo.getRaceName()).append("\n");
        sb.append("Level: ").append(vo.getLevel()).append("\n");
        sb.append("EXP: ").append(vo.getExp()).append("\n");
        sb.append("SP: ").append(vo.getStatPoint()).append("\n\n");

        sb.append("<[ BASE STATS ]>\n");
        sb.append("STR: ").append(vo.getFinalStr()).append("\n");
        sb.append("CON: ").append(vo.getFinalCon()).append("\n");
        sb.append("AGI: ").append(vo.getFinalAgi()).append("\n");
        sb.append("DEX: ").append(vo.getFinalDex()).append("\n");
        sb.append("INT: ").append(vo.getFinalInt()).append("\n");
        sb.append("WIS: ").append(vo.getFinalWis()).append("\n");
        sb.append("LUK: ").append(vo.getFinalLuk()).append("\n\n");

        CharacterCombatStatsVO combat =
                CharacterDeriverdStatsCalculator.calculateCombat(
                        vo.getFinalStr(),
                        vo.getFinalCon(),
                        vo.getFinalAgi(),
                        vo.getFinalDex(),
                        vo.getFinalInt(),
                        vo.getFinalWis(),
                        vo.getFinalLuk()
                );

        CharacterSurvivalStatsVO survival =
                CharacterDeriverdStatsCalculator.calculateSurvival(
                        vo.getFinalStr(),
                        vo.getFinalCon(),
                        vo.getFinalAgi(),
                        vo.getFinalDex(),
                        vo.getFinalInt(),
                        vo.getFinalWis(),
                        vo.getFinalLuk()
                );

        sb.append("<[ COMBAT STATS ]>\n");
        sb.append("HP: ").append(combat.getHp()).append("\n");
        sb.append("MP: ").append(combat.getMp()).append("\n");
        sb.append("ATK: ").append(combat.getAtk()).append("\n");
        sb.append("DEF: ").append(combat.getDef()).append("\n");
        sb.append("MAG: ").append(combat.getMag()).append("\n");
        sb.append("RES: ").append(combat.getRes()).append("\n");
        sb.append("CRT: ").append(combat.getCrt()).append("%\n");
        sb.append("CRR: ").append(combat.getCrr()).append("%\n");
        sb.append("P.CRIT MULT: x").append(combat.getPcr()).append("\n");
        sb.append("M.CRIT MULT: x").append(combat.getMcr()).append("\n");
        sb.append("SPD: ").append(combat.getSpd()).append("\n\n");

        sb.append("<[ SURVIVAL STATS ]>\n");
        sb.append("Stamina: ").append(survival.getStamina()).append("\n");
        sb.append("Carry Cap: ").append(survival.getCarryCapacity()).append("\n");
        sb.append("Hunger Res: ").append(survival.getHungerResistance()).append("\n");
        sb.append("Heal Rate: ").append(survival.getHealRate()).append("\n");
        sb.append("Immune Str: ").append(survival.getImmuneStrength()).append("\n");
        sb.append("Crafting Eff: ").append(survival.getCraftingEfficiency()).append("\n");
        sb.append("Env Tolerance: ").append(survival.getEnviromentTolerance()).append("\n");
        sb.append("Scavenging: ").append(survival.getScavenging()).append("\n");
        sb.append("Instinct: ").append(survival.getInstinct()).append("\n");

        infoArea.setText(sb.toString());
        infoArea.setCaretPosition(0);
    }
}

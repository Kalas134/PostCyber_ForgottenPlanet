package testbuild;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

import vopkg.CharacterVO;

public class SaveXmlLoader {
	public static List<CharacterVO> loadCharacters(Path xmlPath) {
		try {
			List<CharacterVO> characters = new ArrayList<>();
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(xmlPath.toFile());
			
			doc.getDocumentElement().normalize();
			
			NodeList characterNodes = doc.getElementsByTagName("Character");
			
			for (int i = 0; i < characterNodes.getLength(); i++) {
				Element characterEl = (Element) characterNodes.item(i);
				
				Element identity = (Element) characterEl
						.getElementsByTagName("Identity")
						.item(0);
				
				Integer charId = Integer.parseInt(
						identity.getElementsByTagName("CharID").item(0).getTextContent());
				String presetName = 
						identity.getElementsByTagName("PresetName").item(0).getTextContent();
				String raceId =
						identity.getElementsByTagName("RaceID").item(0).getTextContent();
				Integer gender = Integer.parseInt(
						identity.getElementsByTagName("Gender").item(0).getTextContent());
				
				
				Element progress = (Element) characterEl
						.getElementsByTagName("Progress")
						.item(0);
				
				Integer level = Integer.parseInt(
						progress.getElementsByTagName("level").item(0).getTextContent());
				Integer experience = Integer.parseInt(
						progress.getElementsByTagName("experience").item(0).getTextContent());
				
				
				Element stats = (Element) characterEl
						.getElementsByTagName("Char_Stats")
						.item(0);
				
				Integer statPoint = Integer.parseInt(stats.getElementsByTagName("StatPoint").item(0).getTextContent());
				Integer str = Integer.parseInt(stats.getElementsByTagName("STR").item(0).getTextContent());
				Integer con = Integer.parseInt(stats.getElementsByTagName("CON").item(0).getTextContent());
				Integer agi = Integer.parseInt(stats.getElementsByTagName("AGI").item(0).getTextContent());
				Integer dex = Integer.parseInt(stats.getElementsByTagName("DEX").item(0).getTextContent());
				Integer intel = Integer.parseInt(stats.getElementsByTagName("INT").item(0).getTextContent());
				Integer wis = Integer.parseInt(stats.getElementsByTagName("WIS").item(0).getTextContent());
				Integer luk = Integer.parseInt(stats.getElementsByTagName("LUK").item(0).getTextContent());
				
				CharacterVO vo = new CharacterVO(
						charId,
						presetName,
						raceId,
						gender,
						level,
						experience,
						statPoint,
						str,
						con,
						agi,
						dex,
						intel,
						wis,
						luk
				);
				
				characters.add(vo);
			}
			
			return characters;
		} catch (Exception e) {
			throw new RuntimeException("Failed to load character XML: " + xmlPath, e);
		}
	}
}

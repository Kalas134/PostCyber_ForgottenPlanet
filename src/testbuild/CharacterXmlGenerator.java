package testbuild;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.nio.file.Path;
import java.util.List;

import vopkg.DefaultCharacterVO;

public class CharacterXmlGenerator {
	
	public static void generateNewGameCharacterXml(
			Path xmlPath,
			List<DefaultCharacterVO> Characters) {
		try {
			// 1. XML Document Generate
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();
			
			// 2. Root
			Element root = doc.createElement("Characters");
			doc.appendChild(root);
			
			// 3. Character Unit Process
			for (DefaultCharacterVO vo : Characters) {
				Element character = doc.createElement("Character");
				root.appendChild(character);
				
				// identity
				Element identity = doc.createElement("Identity");
				character.appendChild(identity);
				
				append(doc, identity, "CharID", vo.getChar_ID());
				append(doc, identity, "PresetName", vo.getChar_PresetName());
				append(doc, identity, "RaceID", vo.getChar_RaceID());
				append(doc, identity, "Gender", vo.getChar_Gender());
				
				// progress
				Element progress = doc.createElement("Progress");
				character.appendChild(progress);
				
				append(doc, progress, "Level", vo.getChar_DefaultLevel());
				append(doc, progress, "Experience", 0); // 새로 생성시 항상 0
				
				// Character Stats
				Element char_stats = doc.createElement("Char_Stats");
				character.appendChild(char_stats);
				
				append(doc, char_stats, "StatPoint", 0); // 새로 생성시 항상 0
				append(doc, char_stats, "STR", vo.getChar_DefAddedSTR());
				append(doc, char_stats, "CON", vo.getChar_DefAddedCON());
				append(doc, char_stats, "AGI", vo.getChar_DefAddedAGI());
				append(doc, char_stats, "DEX", vo.getChar_DefAddedDEX());
				append(doc, char_stats, "INT", vo.getChar_DefAddedINT());
				append(doc, char_stats, "WIS", vo.getChar_DefAddedWIS());
				append(doc, char_stats, "LUK", vo.getChar_DefAddedLUK());
			
			}
			
			// 4. xml File Export
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
			transformer.transform(
					new DOMSource(doc), 
					new StreamResult(xmlPath.toFile())
					);
		} catch (Exception e) {
			throw new RuntimeException("Character XML generation failed", e);
		}
	}
	
	// common util
	
	private static void append(Document doc, Element parent, String tag, Object value) {
		Element e = doc.createElement(tag);
		e.setTextContent(String.valueOf(value));
		parent.appendChild(e);
	}
}

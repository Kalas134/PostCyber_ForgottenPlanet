package systempkg;

import vopkg.RaceTextVO;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class RaceTextXmlLoader {
	public static Map<String, RaceTextVO> loadRaceTexts(String lang) {
		Map<String, RaceTextVO> map = new HashMap<>();
		
		String filePath = "/textXML/" + lang + "/Races_" + lang + ".xml";
		
		try (InputStream is = RaceTextXmlLoader.class.getResourceAsStream(filePath)) {
			if (is == null) {
				throw new RuntimeException("XML file not Found: " + filePath);
			}
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(is);
			
			NodeList raceNodes = doc.getElementsByTagName("race");
			
			for (int i = 0; i < raceNodes.getLength(); i++) {
				Element raceElem = (Element) raceNodes.item(i);
				
				Element idElem = (Element) raceElem
						.getElementsByTagName("RACE_ID")
						.item(0);
				
				String RACE_ID = idElem.getAttribute("id").trim();
				String RaceName = raceElem.getElementsByTagName("RaceName")
						.item(0).getTextContent().trim();
				String RaceDesc =raceElem.getElementsByTagName("RaceDesc")
						.item(0).getTextContent().trim();
				
				map.put(RACE_ID, new RaceTextVO(RACE_ID, RaceName, RaceDesc));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
}

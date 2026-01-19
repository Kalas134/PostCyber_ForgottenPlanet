package daopkg;

import systempkg.RaceTextXmlLoader;

import vopkg.RaceIndexVO;
import vopkg.RaceTextVO;

import java.util.Map;

public class RaceIndexDAO {
	public static RaceIndexVO loadRaceIndex(String raceId) throws Exception {
		
		String lang = "ko_KR";
		
		Map<String, RaceTextVO> raceTextMap = RaceTextXmlLoader.loadRaceTexts(lang);
		
		RaceTextVO text = raceTextMap.get(raceId);
		
		if (text == null) {
			throw new RuntimeException("Race text not found: " + raceId);
		}
		
		return new RaceIndexVO(
				raceId,
				text.getRaceName()
		);
	}
}

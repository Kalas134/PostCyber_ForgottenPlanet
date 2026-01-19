package systempkg;

import daopkg.RaceInfoDAO;
import daopkg.RaceIndexDAO;

import vopkg.CharacterVO;
import vopkg.CharacterFinalStatsVO;
import vopkg.RaceBasicStatsVO;
import vopkg.RaceIndexVO;

public class CharacterStatsService {
	
	public static CharacterFinalStatsVO buildFinalStats(CharacterVO charVo) throws Exception {
		// 1. character Race ID read
		String raceId = charVo.getRaceId();
		// 2. RaceBasicStats in DB
		RaceBasicStatsVO raceStats = RaceInfoDAO.loadRaceStats(raceId);
		// 3. Race Description in text XML
		RaceIndexVO raceIndex = RaceIndexDAO.loadRaceIndex(raceId);
		// 4. RaceBasicStats + CharacterStats
		int finalStr = charVo.getStr() + raceStats.getStr();
		int finalCon = charVo.getCon() + raceStats.getCon();
		int finalAgi = charVo.getAgi() + raceStats.getAgi();
		int finalDex = charVo.getDex() + raceStats.getDex();
		int finalInt = charVo.getIntel() + raceStats.getIntel();
		int finalWis = charVo.getWis() + raceStats.getWis();
		int finalLuk = charVo.getLuk() + raceStats.getLuk();
		// 5. final VO Generate
		return new CharacterFinalStatsVO(
				charVo.getName(), 
				raceIndex.getRaceName(), 
				charVo.getLevel(), 
				charVo.getExperience(),
				charVo.getStatPoint(), 
				finalStr, 
				finalCon, 
				finalAgi, 
				finalDex, 
				finalInt, 
				finalWis,
				finalLuk
		);
	}
}

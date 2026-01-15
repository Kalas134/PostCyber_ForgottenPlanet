package testbuild;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class SaveSlotGenerator {
	
	public static Path createNewSaveSlot(Path saveDir) {
		
		// 1. saveDir Generate
		try {
			
			// 1-1. dir Generate
			Files.createDirectories(saveDir);
			
			// 1-2. search save slot number
			int slot = 1;
			Path slotDir;
			
			
			while (true) {
				String folderName = String.format("%03d", slot);
				slotDir = saveDir.resolve(folderName);
				
				if (!Files.exists(slotDir)) {
					// 1-3. empty save slot searched --> generate
					Files.createDirectories(slotDir);
					break;
				}
				slot++;
	 		}
		
			return slotDir;
		} catch (IOException e) {
			throw new RuntimeException("Save directory creation failed", e);
		}
	}
	
	// 2. XML Document Generate
	// 3. vo value to XML fill
	// 4. experience = 0
	// 5. file save
}

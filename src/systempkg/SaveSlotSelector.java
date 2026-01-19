package systempkg;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class SaveSlotSelector {
	private static final Path SAVE_ROOT = Path.of("save");
	
	public static Optional<Path> selectSlot(Scanner sc) {
		try {
			if (!Files.exists(SAVE_ROOT)) {
				System.out.println("No save directory found.");
			}
			
			List<Path> slots = Files.list(SAVE_ROOT)
					.filter(Files::isDirectory)
					.sorted()
					.toList();
			if (slots.isEmpty()) {
				System.out.println("No Save slots available.");
			}
			
			System.out.println("[Load Game]");
			for (int i = 0; i <slots.size(); i++) {
				System.out.printf("%d. %s%n", i + 1, slots.get(i).getFileName());
			}
			System.out.println("0, Cancel");
			
			System.out.println("Select Slot: ");
			int choice = Integer.parseInt(sc.nextLine());
			
			if (choice == 0) return Optional.empty();
			if (choice < 1 || choice > slots.size()) {
				System.out.println("Invaild slot.");
				return Optional.empty();
			}
			
			return Optional.of(slots.get(choice - 1));
			
		} catch (IOException | NumberFormatException e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}
}

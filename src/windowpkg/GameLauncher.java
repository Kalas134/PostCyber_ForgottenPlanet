package windowpkg;

import javax.swing.SwingUtilities;
import systempkg.GameContext;

public class GameLauncher {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			GameContext context = new GameContext();
			new MainWindow(context);
		});
	}
}

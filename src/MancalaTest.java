/**
 * Mancala Project
 * @author Group 10
 * @version 1.0 5/5/2026
 */

import javax.swing.SwingUtilities;

/**
 * MancalaTest contains the main function by which the GUI of the game is launched from.
 */
public class MancalaTest {
    /**
     * Launches graphical user interface that allows users to play game
     * @param args default string array argument
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MancalaModel model = new MancalaModel();
            MancalaView view = new MancalaView(model, new RoundedStyle());
            MancalaController controller = new MancalaController(model, view);
            view.setController(controller);
            model.addChangeListener(view);
            view.showOpeningScreen();
        });
    }
}

import javax.swing.SwingUtilities;

public class MancalaTest {
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

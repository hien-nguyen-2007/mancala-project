import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class MancalaView extends JFrame implements ChangeListener {

    private MancalaModel mancalaModel;
    private BoardStyle boardStyle;
    private JPanel[] pits;
    private JButton undoButton;
    private JButton styleButton1;
    private JButton styleButton2;
    private JLabel currentPlayerLabel;
    private MancalaController mancalaController;

    public MancalaView(MancalaModel model, BoardStyle style) {
        this.mancalaModel = model;
        this.boardStyle = style;
        this.pits = new JPanel[14];
        setTitle("Mancala");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
    }

    /**
    * Shows the opening screen with style selection buttons.
    */
    public void showOpeningScreen() {
        getContentPane().removeAll();
    
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Select a board style:");
    
        styleButton1 = new JButton("Rounded");
        styleButton2 = new JButton("Rectangle");
    
        panel.add(label);
        panel.add(styleButton1);
        panel.add(styleButton2);
    
        add(panel);
        revalidate();
        repaint();
        setVisible(true);
    }

    /**
    * Shows the main game board after style and stone count are selected.
    */
    public void showGameBoard() {
        getContentPane().removeAll();
        add(createBoardPanel(), BorderLayout.CENTER);
        add(createControlPanel(), BorderLayout.SOUTH);
        revalidate();
        repaint();
        mancalaController.attachPitListeners(); // attach after pits are created
        mancalaController.attachButtonListeners();
    }

    // builds the board panel with pits and mancalas
    private JPanel createBoardPanel() {
       JPanel board = new JPanel(new BorderLayout());
        JPanel playerBRow = new JPanel(new GridLayout(1, 6));
        JPanel playerARow = new JPanel(new GridLayout(1, 6));

        // create player B's pits (indices 7-12)
        for (int i = 7; i <= 12; i++) {
            pits[i] = new JPanel();
            pits[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            playerBRow.add(pits[i]);
        }

        // create player A's pits (indices 0-5)
        for (int i = 0; i <= 5; i++) {
            pits[i] = new JPanel();
            pits[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            playerARow.add(pits[i]);
        }

        board.add(playerBRow, BorderLayout.NORTH);
        board.add(playerARow, BorderLayout.SOUTH);
        return board;
    }

    private JPanel createControlPanel() {
        // TODO
        return new JPanel();
    }

    // redraws the board to reflect current model state
    public void drawBoard() {
        repaint();
    }

    public void showGameOverDialog() {
        // TODO
    }

    public void updatePlayerLabel() {
        // TODO
    }

    public void setStyle(BoardStyle style) {
        // TODO
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        //TODO
    }

    public void setController(MancalaController controller) {
        this.mancalaController = controller;
    }

    public JPanel[] getPitPanels() { return pits; }

    public JButton getUndoButton() { return undoButton; }

    public BoardStyle getStyle() { return boardStyle; }
}

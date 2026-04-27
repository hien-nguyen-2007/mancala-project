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

    public void showGameBoard() {
        // TODO
    }

    // builds the undo button and player turn label at the bottom
    private JPanel createBoardPanel() {
        JPanel panel = new JPanel();
        undoButton = new JButton("Undo");
        currentPlayerLabel = new JLabel("Player A's Turn");
        panel.add(currentPlayerLabel);
        panel.add(undoButton);
        return panel;
    }

    private JPanel createControlPanel() {
        // TODO
        return new JPanel();
    }

    public void drawBoard() {
        // TODO
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

    public JPanel[] getPitPanels() { return pits; }

    public JButton getUndoButton() { return undoButton; }

    public BoardStyle getStyle() { return boardStyle; }
}

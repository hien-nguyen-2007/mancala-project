import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;


public class MancalaController {

    private MancalaModel mancalaModel;
    private MancalaView mancalaView;

    /**
     * Creates controller and attaches listeners to all interactive components.
     * @param model the game model
     * @param view the game view
     */
    public MancalaController(MancalaModel model, MancalaView view) {
        this.mancalaModel = model;
        this.mancalaView = view;
        attachPitListeners();
        attachButtonListeners();
    }

    /**
     * Attaches this controller as a MouseListener to all pit panels.
     */
    private void attachPitListeners() {
        Jpanel[] pits = mancalaView.getPits();
        for (int i = 0; i< pits.length; i++) {
            pits[i].addMouseListener(this);
        }
    }

    /**
     * Attaches this controller as an ActionListener to the undo button.
     */
    private void attachButtonListeners() {
        view.getUndoButton().addActionListener(this);
    }

    /**
    * Determines which pit was clicked and calls pitClicked().
    */
    @Override
    public void mouseClicked(MouseEvent e) {
        JPanel[] pits = view.getPitPanels();
        for (int i = 0; i < pits.length; i++) {
            if (e.getSource() == pits[i]) {
                pitClicked(i);
                return;
            }
        }
    }

    /**
    * Validates the move then tells the model to execute it.
    * @param pitIndex board array index of the clicked pit
    */
    public void pitClicked(int pitIndex) {
        int[] board = model.getBoard();

        // check pit belongs to current player
        if (model.getCurrentPlayer() == 0) {
            if (pitIndex < 0 || pitIndex > 5) return;
        } else {
            if (pitIndex < 7 || pitIndex > 12) return;
        }

        // cant click an empty pit
        if (board[pitIndex] == 0) return;

        model.makeMove(pitIndex);

        // redraw board after move
        view.drawBoard();

        // check if game ended
        if (model.isGameOver()) {
            model.collectRemainingStones();
            view.showGameOverDialog();
        }
    }

    /**
    * Handles undo button click, checks eligibility then restores previous state.
    */
    public void undoClicked() {
        if (model.canUndo() && model.getUndoCount() < 3) {
            model.undoMove();
            view.drawBoard();
        }
    }

    /**
    * Swaps in a new board style and redraws.
    * @param style the selected BoardStyle
    */
    public void styleSelected(BoardStyle style) {
        view.setStyle(style);
        view.drawBoard();
    }

    /**
    * Validates stone count input and initializes the board.
    * @param input raw string entered by player
    */
    public void stonesEntered(String input) {
        try {
            int stones = Integer.parseInt(input.trim());
            if (stones == 3 || stones == 4) {
                model.initBoard(stones);
                view.showGameBoard();
            } else {
                System.out.println("must be 3 or 4");
            }
        } catch (NumberFormatException e) {
            System.out.println("invalid input");
        }
    }

    /**
    * Routes button presses to the correct handler.
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getUndoButton()) {
            undoClicked();
        }
    }

    //  Unused methods from interface.
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}

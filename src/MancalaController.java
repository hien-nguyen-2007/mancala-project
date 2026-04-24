import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;


public class MancalaController implements MouseListener, ActionListener{

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
        JPanel[] pits = mancalaView.getPitPanels();
        for (int i = 0; i< pits.length; i++) {
            pits[i].addMouseListener(this);
        }
    }

    /**
     * Attaches this controller as an ActionListener to the undo button.
     */
    private void attachButtonListeners() {
        mancalaView.getUndoButton().addActionListener(this);
    }

    /**
    * Determines which pit was clicked and calls pitClicked().
    */
    @Override
    public void mouseClicked(MouseEvent e) {
        JPanel[] pits = mancalaView.getPitPanels();
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
        int[] board = mancalaModel.getBoard();

        // check pit belongs to current player
        if (mancalaModel.getCurrentPlayer() == 0) {
            if (pitIndex < 0 || pitIndex > 5) return;
        } else {
            if (pitIndex < 7 || pitIndex > 12) return;
        }

        // cant click an empty pit
        if (board[pitIndex] == 0) return;

        mancalaModel.makeMove(pitIndex);

        // redraw board after move
        mancalaView.drawBoard();

        // check if game ended
        if (mancalaModel.isGameOver()) {
            mancalaModel.collectRemainingStones();
            mancalaView.showGameOverDialog();
        }
    }

    /**
    * Handles undo button click, checks eligibility then restores previous state.
    */
    public void undoClicked() {
        if (mancalaModel.canUndo() && mancalaModel.getUndoCount() < 3) {
            mancalaModel.undoMove();
            mancalaView.drawBoard();
        }
    }

    /**
    * Swaps in a new board style and redraws.
    * @param style the selected BoardStyle
    */
    public void styleSelected(BoardStyle style) {
        mancalaView.setStyle(style);
        mancalaView.drawBoard();
    }

    /**
    * Validates stone count input and initializes the board.
    * @param input raw string entered by player
    */
    public void stonesEntered(String input) {
        try {
            int stones = Integer.parseInt(input.trim());
            if (stones == 3 || stones == 4) {
                mancalaModel.initBoard(stones);
                mancalaView.showGameBoard();
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
        if (e.getSource() == mancalaView.getUndoButton()) {
            undoClicked();
        }
    }

    //  Unused methods from interface.
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}

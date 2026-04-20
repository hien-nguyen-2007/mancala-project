import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * MancalaModel - Stores and manages all game state for Mancala.
 * Follows the Model role in the MVC pattern.
 */
public class MancalaModel {

    // Fields 
    private int[] board;          // 14-slot array: indices 0-5 = Player A pits,
                                  // index 6 = Player A Mancala,
                                  // indices 7-12 = Player B pits,
                                  // index 13 = Player B Mancala
    private int[] savedState;     // snapshot of board before last move (for undo)
    private int currentPlayer;    // 0 = Player A, 1 = Player B
    private int savedPlayer;      // snapshot of currentPlayer before last move
    private int undoCount;        // number of undos used this turn (max 3)
    private boolean canUndo;      // false after an undo, true after a move
    private int stonesPerPit;     // initial stones per pit (3 or 4)
    private ArrayList<ChangeListener> listeners; // views listening for updates

    //Constructor
    public MancalaModel() {
        // TODO: initialize board array, listeners list, and default values
        this.board = new int[14];
        this.savedState = new int[14];
        this.listeners = new ArrayList<>();
    }

    // Game Setup

    /**
     * Initializes the board with the given number of stones per pit.
     * @param stones number of stones per pit (3 or 4)
     */
    public void initBoard(int stones) {
        // TODO
        for (int i = 0; i < 14; i++) {
            if (i != 6 && i != 13) {
                this.board[i] = stones;
            } else {
                this.board[i] = 0;
            }
        }
    }

    // Core Game Logic 

    /**
     * Executes a move for the current player picking up stones from the given pit index.
     * Handles stone distribution, free turn, and capture rules.
     * Saves state before moving to support undo.
     * @param pitIndex the index in the board array of the selected pit
     */
    public void makeMove(int pitIndex) {
        // TODO
    }

    /**
     * Distributes stones counter-clockwise from the given pit.
     * Skips the opponent's Mancala.
     * @param pitIndex starting pit index
     * @return index of the last pit a stone was placed in
     */
    private int distributeStones(int pitIndex) {
        // TODO
        return -1;
    }

    /**
     * Checks if the last stone landed in the current player's own Mancala (free turn).
     * @param lastIndex index where the last stone was placed
     * @return true if the player earns a free turn
     */
    private boolean isFreeTurn(int lastIndex) {
        // TODO
        return false;
    }

    /**
     * Checks and applies capture rule: if last stone lands in an empty pit
     * on the current player's side, capture that stone and all opponent stones
     * in the opposite pit.
     * @param lastIndex index where the last stone was placed
     */
    private void checkCapture(int lastIndex) {
        // TODO
    }

    /**
     * Returns the index of the pit directly opposite to the given pit index.
     * @param pitIndex a pit index (0-5 or 7-12)
     * @return the opposite pit index
     */
    private int getOppositePit(int pitIndex) {
        // TODO
        return -1;
    }

    /**
     * Switches the current player. Called after a non-free-turn move.
     */
    private void switchPlayer() {
        // TODO
    }

    // Undo

    /**
     * Restores the board to its state before the last move.
     * Only allowed if canUndo is true and undoCount < 3.
     */
    public void undoMove() {
        // TODO
    }

    /**
     * Saves the current board state and player turn before a move is made.
     */
    private void saveState() {
        // TODO
    }

    // Game Over

    /**
     * Checks if the game is over (all pits on one side are empty).
     * @return true if the game has ended
     */
    public boolean isGameOver() {
        // TODO
        return false;
    }

    /**
     * When game is over, moves all remaining stones on each side
     * into the respective player's Mancala.
     */
    public void collectRemainingStones() {
        // TODO
    }

    /**
     * Returns the index of the winning player (0 or 1), or -1 for a tie.
     * @return winning player index, or -1 for tie
     */
    public int getWinner() {
        // TODO
        return -1;
    }

    // Listeners (MVC)

    /**
     * Registers a ChangeListener (typically the view) to be notified on state changes.
     * @param l the listener to add
     */
    public void addChangeListener(ChangeListener l) {
        // TODO
        this.listeners.add(l);
    }

    /**
     * Notifies all registered listeners that the model has changed.
     */
    private void notifyListeners() {
        // TODO
        ChangeEvent event = new ChangeEvent(this);
        for (ChangeListener l: this.listeners) {
            l.stateChanged(event);
        }
    }

    // Getters

    public int[] getBoard() { return board; }

    public int getCurrentPlayer() { return currentPlayer; }

    public boolean canUndo() { return canUndo; }

    public int getUndoCount() { return undoCount; }

    public int getStonesPerPit() { return stonesPerPit; }

    public int getMancalaA() { return board[6]; }

    public int getMancalaB() { return board[13]; }
}
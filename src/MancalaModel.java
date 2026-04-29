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
    private int[] undoCount;        // number of undos used this turn (max 3)
    private boolean canUndo;      // false after an undo, true after a move
    private int stonesPerPit;     // initial stones per pit (3 or 4)
    private ArrayList<ChangeListener> listeners; // views listening for updates

    //Constructor
    public MancalaModel() {
        // TODO: initialize board array, listeners list, and default values
        this.board = new int[14];
        this.savedState = new int[14];
        this.listeners = new ArrayList<>();
        this.undoCount = new int[2];
        this.currentPlayer = 0;
        this.savedPlayer = 0;
        this.canUndo = false;
    }

    // Game Setup

    /**
     * Initializes number of stones per pit.
     * @param stones number of stones per pit
     */
    public void setStones(int stones) {
        this.stonesPerPit = stones;
    }

    /**
     * Initializes the board with the given number of stones per pit.
     * @param stones number of stones per pit (3 or 4)
     */
    public void initBoard(int stones) {
        // Fill pits with number of stones (3 or 4)
        for (int i = 0; i < 14; i++) {
            this.board[i] = stones;
        }

        // Set respective mancalas to 0
        this.board[6] = 0;
        this.board[13] = 0;
    }

    // Core Game Logic 

    /**
     * Executes a move for the current player picking up stones from the given pit index.
     * Handles stone distribution, free turn, and capture rules.
     * Saves state before moving to support undo.
     * @param pitIndex the index in the board array of the selected pit
     */
    public void makeMove(int pitIndex) {
        saveState();

        int lastIndex = distributeStones(pitIndex);
        checkCapture(lastIndex);


        if (!isFreeTurn(lastIndex)) {
            switchPlayer();
        }

        this.canUndo = true;

        notifyListeners();
    }

    /**
     * Distributes stones counter-clockwise from the given pit.
     * Skips the opponent's Mancala.
     * @param pitIndex starting pit index
     * @return index of the last pit a stone was placed in
     */
    private int distributeStones(int pitIndex) {
        int stones = this.board[pitIndex];
        int current = pitIndex + 1;

        this.board[pitIndex] = 0;

        // While there are still stones to distribute
        while (stones > 0) {
            if (current == 14) {
                current = 0;
            }

            // Gives a stone to the current player's pit if applicable and skips opponent's
            if (current == 6 && this.currentPlayer == 0) {
                this.board[current]++;
            } else if (current == 13 && this.currentPlayer == 1) {
                this.board[current]++;
            } else if (current == 6 || current == 13) {
                current++;
                continue;
            } else {
                this.board[current]++;import java.util.ArrayList;
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
                    private int[] undoCount;        // number of undos used this turn (max 3)
                    private boolean canUndo;      // false after an undo, true after a move
                    private int stonesPerPit;     // initial stones per pit (3 or 4)
                    private ArrayList<ChangeListener> listeners; // views listening for updates

                    //Constructor
                    public MancalaModel() {
                        // TODO: initialize board array, listeners list, and default values
                        this.board = new int[14];
                        this.savedState = new int[14];
                        this.listeners = new ArrayList<>();
                        this.undoCount = new int[2];
                        this.currentPlayer = 0;
                        this.savedPlayer = 0;
                        this.canUndo = false;
                    }

                    // Game Setup

                    /**
                     * Initializes number of stones per pit.
                     * @param stones number of stones per pit
                     */
                    public void setStones(int stones) {
                        this.stonesPerPit = stones;
                    }

                    /**
                     * Initializes the board with the given number of stones per pit.
                     * @param stones number of stones per pit (3 or 4)
                     */
                    public void initBoard(int stones) {
                        // Fill pits with number of stones (3 or 4)
                        for (int i = 0; i < 14; i++) {
                            this.board[i] = stones;
                        }

                        // Set respective mancalas to 0
                        this.board[6] = 0;
                        this.board[13] = 0;
                    }

                    // Core Game Logic

                    /**
                     * Executes a move for the current player picking up stones from the given pit index.
                     * Handles stone distribution, free turn, and capture rules.
                     * Saves state before moving to support undo.
                     * @param pitIndex the index in the board array of the selected pit
                     */
                    public void makeMove(int pitIndex) {
                        saveState();

                        int lastIndex = distributeStones(pitIndex);
                        checkCapture(lastIndex);


                        if (!isFreeTurn(lastIndex)) {
                            switchPlayer();
                        }

                        this.canUndo = true;

                        notifyListeners();
                    }

                    /**
                     * Distributes stones counter-clockwise from the given pit.
                     * Skips the opponent's Mancala.
                     * @param pitIndex starting pit index
                     * @return index of the last pit a stone was placed in
                     */
                    private int distributeStones(int pitIndex) {
                        int stones = this.board[pitIndex];
                        int current = pitIndex + 1;

                        this.board[pitIndex] = 0;

                        // While there are still stones to distribute
                        while (stones > 0) {
                            if (current == 14) {
                                current = 0;
                            }

                            // Gives a stone to the current player's pit if applicable and skips opponent's
                            if (current == 6 && this.currentPlayer == 0) {
                                this.board[current]++;
                            } else if (current == 13 && this.currentPlayer == 1) {
                                this.board[current]++;
                            } else if (current == 6 || current == 13) {
                                current++;
                                continue;
                            } else {
                                this.board[current]++;
                            }

                            // Move current pit forward and remove one stone
                            current++;
                            stones--;
                        }

                        return current - 1;
                    }

                    /**
                     * Checks if the last stone landed in the current player's own Mancala (free turn).
                     * @param lastIndex index where the last stone was placed
                     * @return true if the player earns a free turn
                     */
                    private boolean isFreeTurn(int lastIndex) {
                        if (lastIndex == 6 && this.currentPlayer == 0) {
                            return true;
                        } else if (lastIndex == 13 && this.currentPlayer == 1) {
                            return true;
                        } else {
                            return false;
                        }
                    }

                    /**
                     * Checks and applies capture rule: if last stone lands in an empty pit
                     * on the current player's side, capture that stone and all opponent stones
                     * in the opposite pit.
                     * @param lastIndex index where the last stone was placed
                     */
                    private void checkCapture(int lastIndex) {
                        int opposite = getOppositePit(lastIndex);

                        if (this.board[lastIndex] == 1 && this.board[opposite] != 0) {
                            if (lastIndex >= 0 && lastIndex < 6 && this.currentPlayer == 0) {
                                this.board[6] += 1 + this.board[opposite];
                                this.board[opposite] = 0;
                                this.board[lastIndex] = 0;
                            } else if (lastIndex >= 7 && lastIndex < 13 && this.currentPlayer == 1) {
                                this.board[13] += 1 + this.board[opposite];
                                this.board[opposite] = 0;
                                this.board[lastIndex] = 0;
                            }
                        }
                    }

                    /**
                     * Returns the index of the pit directly opposite to the given pit index.
                     * @param pitIndex a pit index (0-5 or 7-12)
                     * @return the opposite pit index
                     */
                    private int getOppositePit(int pitIndex) {
                        int opposite = 0;

                        // Hardcode values
                        switch (pitIndex) {
                            case 0:
                                opposite = 12;
                                break;
                            case 1:
                                opposite = 11;
                                break;
                            case 2:
                                opposite = 10;
                                break;
                            case 3:
                                opposite = 9;
                                break;
                            case 4:
                                opposite = 8;
                                break;
                            case 5:
                                opposite = 7;
                                break;
                            case 7:
                                opposite = 5;
                                break;
                            case 8:
                                opposite = 4;
                                break;
                            case 9:
                                opposite = 3;
                                break;
                            case 10:
                                opposite = 2;
                                break;
                            case 11:
                                opposite = 1;
                                break;
                            case 12:
                                opposite = 0;
                                break;
                        }

                        return opposite;
                    }

                    /**
                     * Switches the current player. Called after a non-free-turn move.
                     */
                    private void switchPlayer() {
                        if (this.currentPlayer == 1) {
                            this.currentPlayer = 0;
                        } else {
                            this.currentPlayer = 1;
                        }
                    }

                    // Undo

                    /**
                     * Restores the board to its state before the last move.
                     * Only allowed if canUndo is true and undoCount < 3.
                     */
                    public void undoMove() {
                        // Checks if undo is done on correct turn or if exceeded undo count for the current move
                        if (!this.canUndo || this.undoCount[this.savedPlayer] >= 3) {
                            return;
                        }

                        this.board = this.savedState.clone();
                        this.currentPlayer = this.savedPlayer;
                        this.undoCount[this.currentPlayer]++;
                        this.canUndo = false;

                        notifyListeners();
                    }

                    /**
                     * Saves the current board state and player turn before a move is made.
                     */
                    private void saveState() {
                        this.savedState = this.board.clone();
                        this.savedPlayer = this.currentPlayer;
                    }

                    // Game Over

                    /**
                     * Checks if the game is over (all pits on one side are empty).
                     * @return true if the game has ended
                     */
                    public boolean isGameOver() {
                        boolean gameOver = true;

                        // Checks Player A's side
                        for (int i = 0; i < 6; i++) {
                            if (this.board[i] != 0) {
                                gameOver = false;
                            }
                        }

                        if (gameOver) {
                            return true;
                        }

                        // Checks Player B's side
                        for (int i = 7; i < 13; i++) {
                            if (this.board[i] != 0) {
                                return false;
                            }
                        }

                        return true;
                    }

                    /**
                     * When game is over, moves all remaining stones on each side
                     * into the respective player's Mancala.
                     */
                    public void collectRemainingStones() {
                        // Moves Player A's stones
                        for (int i = 0; i < 6; i++) {
                            this.board[6] += this.board[i];
                            this.board[i] = 0;
                        }

                        // Moves Player B's stones
                        for (int i = 7; i < 13; i++) {
                            this.board[13] += this.board[i];
                            this.board[i] = 0;
                        }
                    }

                    /**
                     * Returns the index of the winning player (0 or 1), or -1 for a tie.
                     * @return winning player index, or -1 for tie
                     */
                    public int getWinner() {
                        // Checks for a winner
                        if (this.board[6] > this.board[13]) {
                            return 0;
                        } else if (this.board[6] < this.board[13]) {
                            return 1;
                        }

                        // Return tie
                        return -1;
                    }

                    // Listeners (MVC)

                    /**
                     * Registers a ChangeListener (typically the view) to be notified on state changes.
                     * @param l the listener to add
                     */
                    public void addChangeListener(ChangeListener l) {
                        this.listeners.add(l);
                    }

                    /**
                     * Notifies all registered listeners that the model has changed.
                     */
                    private void notifyListeners() {
                        ChangeEvent event = new ChangeEvent(this);
                        for (ChangeListener l: this.listeners) {
                            l.stateChanged(event);
                        }
                    }

                    // Getters

                    public int[] getBoard() { return board.clone(); }

                    public int getCurrentPlayer() { return currentPlayer; }

                    public boolean canUndo() { return canUndo; }

                    public int getUndoCount() { return undoCount; }

                    public int getStonesPerPit() { return stonesPerPit; }

                    public int getMancalaA() { return board[6]; }

                    public int getMancalaB() { return board[13]; }
                }
            }

            // Move current pit forward and remove one stone
            current++;
            stones--;
        }

        return current - 1;
    }

    /**
     * Checks if the last stone landed in the current player's own Mancala (free turn).
     * @param lastIndex index where the last stone was placed
     * @return true if the player earns a free turn
     */
    private boolean isFreeTurn(int lastIndex) {
        if (lastIndex == 6 && this.currentPlayer == 0) {
            return true;
        } else if (lastIndex == 13 && this.currentPlayer == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks and applies capture rule: if last stone lands in an empty pit
     * on the current player's side, capture that stone and all opponent stones
     * in the opposite pit.
     * @param lastIndex index where the last stone was placed
     */
    private void checkCapture(int lastIndex) {
        int opposite = getOppositePit(lastIndex);

        if (this.board[lastIndex] == 1 && this.board[opposite] != 0) {
            if (lastIndex >= 0 && lastIndex < 6 && this.currentPlayer == 0) {
                this.board[6] += 1 + this.board[opposite];
                this.board[opposite] = 0;
                this.board[lastIndex] = 0;
            } else if (lastIndex >= 7 && lastIndex < 13 && this.currentPlayer == 1) {
                this.board[13] += 1 + this.board[opposite];
                this.board[opposite] = 0;
                this.board[lastIndex] = 0;
            }
        }
    }

    /**
     * Returns the index of the pit directly opposite to the given pit index.
     * @param pitIndex a pit index (0-5 or 7-12)
     * @return the opposite pit index
     */
    private int getOppositePit(int pitIndex) {
        int opposite = 0;

        // Hardcode values
        switch (pitIndex) {
            case 0:
                opposite = 12;
                break;
            case 1:
                opposite = 11;
                break;
            case 2:
                opposite = 10;
                break;
            case 3:
                opposite = 9;
                break;
            case 4:
                opposite = 8;
                break;
            case 5:
                opposite = 7;
                break;
            case 7:
                opposite = 5;
                break;
            case 8:
                opposite = 4;
                break;
            case 9:
                opposite = 3;
                break;
            case 10:
                opposite = 2;
                break;
            case 11:
                opposite = 1;
                break;
            case 12:
                opposite = 0;
                break;
        }

        return opposite;
    }

    /**
     * Switches the current player. Called after a non-free-turn move.
     */
    private void switchPlayer() {
        if (this.currentPlayer == 1) {
            this.currentPlayer = 0;
        } else {
            this.currentPlayer = 1;
        }
    }

    // Undo

    /**
     * Restores the board to its state before the last move.
     * Only allowed if canUndo is true and undoCount < 3.
     */
    public void undoMove() {
        // Checks if undo is done on correct turn or if exceeded undo count for the current move
        if (!this.canUndo || this.undoCount[this.savedPlayer] >= 3) {
            return;
        }

        this.board = this.savedState.clone();
        this.currentPlayer = this.savedPlayer;
        this.undoCount[this.currentPlayer]++;
        this.canUndo = false;

        notifyListeners();
    }

    /**
     * Saves the current board state and player turn before a move is made.
     */
    private void saveState() {
        this.savedState = this.board.clone();
        this.savedPlayer = this.currentPlayer;
    }

    // Game Over

    /**
     * Checks if the game is over (all pits on one side are empty).
     * @return true if the game has ended
     */
    public boolean isGameOver() {
        boolean gameOver = true;

        // Checks Player A's side
        for (int i = 0; i < 6; i++) {
            if (this.board[i] != 0) {
                gameOver = false;
            }
        }

        if (gameOver) {
            return true;
        }

        // Checks Player B's side
        for (int i = 7; i < 13; i++) {
            if (this.board[i] != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * When game is over, moves all remaining stones on each side
     * into the respective player's Mancala.
     */
    public void collectRemainingStones() {
        // Moves Player A's stones
        for (int i = 0; i < 6; i++) {
            this.board[6] += this.board[i];
            this.board[i] = 0;
        }

        // Moves Player B's stones
        for (int i = 7; i < 13; i++) {
            this.board[13] += this.board[i];
            this.board[i] = 0;
        }
    }

    /**
     * Returns the index of the winning player (0 or 1), or -1 for a tie.
     * @return winning player index, or -1 for tie
     */
    public int getWinner() {
        // Checks for a winner
        if (this.board[6] > this.board[13]) {
            return 0;
        } else if (this.board[6] < this.board[13]) {
            return 1;
        }

        // Return tie
        return -1;
    }

    // Listeners (MVC)

    /**
     * Registers a ChangeListener (typically the view) to be notified on state changes.
     * @param l the listener to add
     */
    public void addChangeListener(ChangeListener l) {
        this.listeners.add(l);
    }

    /**
     * Notifies all registered listeners that the model has changed.
     */
    private void notifyListeners() {
        ChangeEvent event = new ChangeEvent(this);
        for (ChangeListener l: this.listeners) {
            l.stateChanged(event);
        }
    }

    // Getters

    public int[] getBoard() { return board.clone(); }

    public int getCurrentPlayer() { return currentPlayer; }

    public boolean canUndo() { return canUndo; }

    public int getUndoCount() { return undoCount; }

    public int getStonesPerPit() { return stonesPerPit; }

    public int getMancalaA() { return board[6]; }

    public int getMancalaB() { return board[13]; }
}
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MancalaView extends JFrame implements ChangeListener {

    private MancalaModel mancalaModel;
    private BoardStyle boardStyle;
    private JPanel[] pits;
    private JButton undoButton;
    private JButton styleButton1;
    private JButton styleButton2;
    private JLabel currentPlayerLabel;
    private MancalaController mancalaController;

    /**
     * Builds the view shell (no widgets yet) for the given model and starting style.
     * @param model the game model the view will observe
     * @param style the initial board style
     */
    public MancalaView(MancalaModel model, BoardStyle style) {
        this.mancalaModel = model;
        this.boardStyle = style;
        this.pits = new JPanel[14];
        setTitle("Mancala");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLayout(new BorderLayout());
    }

    /**
     * Shows the opening screen with the two style buttons.
     * Clicking a style triggers the stone-count prompt and starts the game.
     */
    public void showOpeningScreen() {
        getContentPane().removeAll();

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Select a board style:");

        styleButton1 = new JButton("Rounded");
        styleButton2 = new JButton("Rectangle");

        styleButton1.addActionListener(e -> beginGame(new RoundedStyle()));
        styleButton2.addActionListener(e -> beginGame(new RectangleStyle()));

        panel.add(label);
        panel.add(styleButton1);
        panel.add(styleButton2);

        add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();
        setVisible(true);
    }

    // Style chosen -> ask the controller to swap the strategy, then prompt for
    // stone count (use case: Prompt for number of stones), then hand off to the
    // controller to start the game.
    private void beginGame(BoardStyle chosen) {
        mancalaController.styleSelected(chosen);

        Object[] options = { "3", "4" };
        int choice = JOptionPane.showOptionDialog(
                this,
                "Number of stones per pit?",
                "Mancala",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);
        if (choice == JOptionPane.CLOSED_OPTION) {
            return;
        }
        mancalaController.stonesEntered(options[choice].toString());
    }

    /**
     * Builds and shows the playable board after a style and stone count are chosen.
     * Listeners are attached by the controller after the panels exist.
     */
    public void showGameBoard() {
        getContentPane().removeAll();
        add(createBoardPanel(), BorderLayout.CENTER);
        add(createControlPanel(), BorderLayout.SOUTH);
        revalidate();
        repaint();
        mancalaController.attachPitListeners();
        mancalaController.attachButtonListeners();
        updatePlayerLabel();
    }

    // Builds the central board: B's row on top (12..7 left-to-right), A's row
    // on the bottom (0..5 left-to-right), B Mancala on the WEST, A Mancala on
    // the EAST. Stones travel counter-clockwise to match the model.
    private JPanel createBoardPanel() {
        JPanel board = new JPanel(new BorderLayout());

        JPanel playerBRow = new JPanel(new GridLayout(1, 6));
        JPanel playerARow = new JPanel(new GridLayout(1, 6));

        for (int i = 12; i >= 7; i--) {
            pits[i] = makePitPanel(i);
            playerBRow.add(pits[i]);
        }
        for (int i = 0; i <= 5; i++) {
            pits[i] = makePitPanel(i);
            playerARow.add(pits[i]);
        }

        pits[13] = makeMancalaPanel(13); // Player B store (left)
        pits[6]  = makeMancalaPanel(6);  // Player A store (right)

        board.add(pits[13], BorderLayout.WEST);
        board.add(pits[6],  BorderLayout.EAST);
        board.add(playerBRow, BorderLayout.NORTH);
        board.add(playerARow, BorderLayout.SOUTH);
        board.setBackground(boardStyle.getBackgroundColor());
        return board;
    }

    // Pit panel that delegates rendering to the current BoardStyle each repaint,
    // reading the live stone count from the model.
    private JPanel makePitPanel(final int index) {
        JPanel p = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                int stones = mancalaModel.getBoard()[index];
                boardStyle.drawPit(g2, 0, 0, getWidth(), getHeight(), stones);
            }
        };
        p.setPreferredSize(new Dimension(80, 100));
        p.setBackground(boardStyle.getBackgroundColor());
        return p;
    }

    private JPanel makeMancalaPanel(final int index) {
        JPanel p = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                int stones = mancalaModel.getBoard()[index];
                boardStyle.drawMancala(g2, 0, 0, getWidth(), getHeight(), stones);
            }
        };
        p.setPreferredSize(new Dimension(100, 220));
        p.setBackground(boardStyle.getBackgroundColor());
        return p;
    }

    // Bottom control panel with current-player label and Undo button.
    private JPanel createControlPanel() {
        JPanel controls = new JPanel(new FlowLayout());
        currentPlayerLabel = new JLabel("Player A's turn");
        undoButton = new JButton("Undo");
        controls.add(currentPlayerLabel);
        controls.add(undoButton);
        return controls;
    }

    /**
     * Notification method (paints the view from model data).
     * Triggers a repaint on every pit, which causes each pit's paintComponent
     * to read the current stone count from the model and delegate drawing to
     * the active BoardStyle (Strategy).
     */
    public void drawBoard() {
        for (JPanel p : pits) {
            if (p != null) p.repaint();
        }
        getContentPane().repaint();
    }

    /**
     * Game-over pop-up (use case: End game). Reads winner from the model,
     * shows the result via JOptionPane, then exits.
     */
    public void showGameOverDialog() {
        int winner = mancalaModel.getWinner();
        int a = mancalaModel.getMancalaA();
        int b = mancalaModel.getMancalaB();
        String message;
        if (winner == 0) {
            message = "Player A wins!  " + a + " - " + b;
        } else if (winner == 1) {
            message = "Player B wins!  " + b + " - " + a;
        } else {
            message = "Tie game.  " + a + " - " + b;
        }
        JOptionPane.showMessageDialog(this, message, "Game Over",
                JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    /** Refreshes the current-player label and undo counter from the model. */
    public void updatePlayerLabel() {
        if (currentPlayerLabel == null) return;
        int p = mancalaModel.getCurrentPlayer();
        String name = (p == 0 ? "Player A" : "Player B");
        currentPlayerLabel.setText(name + "'s turn   (undos used: "
                + mancalaModel.getUndoCount() + "/3)");
    }

    /**
     * Strategy plug-in: swap in a new BoardStyle and repaint everything.
     * @param style the new style to use
     */
    public void setStyle(BoardStyle style) {
        this.boardStyle = style;
        for (JPanel p : pits) {
            if (p != null) p.setBackground(style.getBackgroundColor());
        }
        getContentPane().setBackground(style.getBackgroundColor());
        drawBoard();
    }

    /**
     * MVC notification entry point: refresh label + repaint pits when the
     * model changes.
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        updatePlayerLabel();
        drawBoard();
    }

    /** Wires the controller after construction. */
    public void setController(MancalaController controller) {
        this.mancalaController = controller;
    }

    public JPanel[] getPitPanels() { return pits; }

    public JButton getUndoButton() { return undoButton; }

    public BoardStyle getStyle() { return boardStyle; }
}

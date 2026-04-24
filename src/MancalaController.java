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
    }

    // remaining methods stay as TODO for now
    public void styleSelected(BoardStyle style) { // TODO }
    public void stonesEntered(String input) { // TODO }
    private void attachPitListeners() { // TODO }

    @Override public void mouseClicked(MouseEvent e) { // TODO }
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void actionPerformed(ActionEvent e) { // TODO }
}

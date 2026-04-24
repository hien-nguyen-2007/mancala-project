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

    // remaining methods stay as TODO for now
    public void pitClicked(int pitIndex) { // TODO }
    public void undoClicked() { // TODO }
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

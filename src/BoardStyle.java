import java.awt.*;

public interface BoardStyle {
    /**
     * Draws pit in which stones will be collected at
     * @param g graphics context
     * @param x starting respective x-coordinate
     * @param y starting respective y-coordinate
     * @param width width of pit
     * @param height height of pit
     * @param stones number of stones in pit
     */
    void drawPit(java.awt.Graphics2D g, int x, int y, int width, int height, int stones);

    /**
     * Draws mancala in which stones will be collected at for each player
     * @param g graphics context
     * @param x starting respective x-coordinate
     * @param y starting respective y-coordinate
     * @param width width of mancala
     * @param height height of mancala
     * @param stones number of stones in mancala
     */
    void drawMancala(java.awt.Graphics2D g, int x, int y, int width, int height, int stones);

    /**
     * Draws a highlight at location of pit
     * @param g graphics context
     * @param x starting respective x-coordinate
     * @param y starting respective y-coordinate
     * @param width width of highlight
     * @param height height of highlight
     */
    void drawHighlight(java.awt.Graphics2D g, int x, int y, int width, int height);

    /**
     * Draws stones inside the pit
     * @param g graphics context
     * @param x starting respective x-coordinate
     * @param y starting respective y-coordinate
     * @param stones number of stones
     */
    void drawStones(Graphics2D g, int x, int y, int stones);

    /**
     * Returns background color of current board style
     * @return background color of current board style
     */
    java.awt.Color getBackgroundColor();

    /**
     * Returns pit background color of current board style
     * @return pit background color of current board style
     */
    java.awt.Color getPitColor();

}

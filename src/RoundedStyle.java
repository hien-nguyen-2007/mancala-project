import java.awt.*;

/**
 * Concrete strategy: a warm wood-tone board with circular pits and rounded
 * Mancala stores. Used by {@link MancalaView} via the {@link BoardStyle}
 * interface.
 *
 * @author Group 10
 */
public class RoundedStyle implements BoardStyle {

    /**
     * Draws oval pit in which stones will be collected at
     * @param g graphics context
     * @param x starting respective x-coordinate
     * @param y starting respective y-coordinate
     * @param width width of pit
     * @param height height of pit
     * @param stones number of stones in pit
     */
    @Override
    public void drawPit(Graphics2D g, int x, int y, int width, int height, int stones) {
        g.setColor(getPitColor());
        int pad = 6;
        g.fillOval(x + pad, y + pad, width - 2 * pad, height - 2 * pad);
        drawStones(g, x, y, stones);
    }

    /**
     * Draws oval mancala in which stones will be collected at for each player
     * @param g graphics context
     * @param x starting respective x-coordinate
     * @param y starting respective y-coordinate
     * @param width width of mancala
     * @param height height of mancala
     * @param stones number of stones in mancala
     */
    @Override
    public void drawMancala(Graphics2D g, int x, int y, int width, int height, int stones) {
        g.setColor(getPitColor());
        int pad = 8;
        g.fillRoundRect(x + pad, y + pad, width - 2 * pad, height - 2 * pad, width, width);
        drawStones(g, x, y, stones);
    }

    /**
     * Draws an oval highlight at location of pit
     * @param g graphics context
     * @param x starting respective x-coordinate
     * @param y starting respective y-coordinate
     * @param width width of highlight
     * @param height height of highlight
     */
    @Override
    public void drawHighlight(Graphics2D g, int x, int y, int width, int height) {
        g.setColor(new Color(255, 215, 0));
        g.setStroke(new BasicStroke(3f));
        int pad = 4;
        g.drawOval(x + pad, y + pad, width - 2 * pad - 1, height - 2 * pad - 1);
    }

    /**
     * Draws circle stones inside the pit
     * @param g graphics context
     * @param x starting respective x-coordinate
     * @param y starting respective y-coordinate
     * @param stones number of stones
     */
    @Override
    public void drawStones(Graphics2D g, int x, int y, int stones) {
        g.setColor(Color.WHITE);

        if (stones <= 20) {
            int size = 10;
            int padding = 20;

            for (int i = 0; i < stones; i++) {
                int px = x + padding + (i % 4) * (size + 5);
                int py = y + padding + (i / 4) * (size + 5);

                g.fillOval(px, py, size, size);
            }
        } else if (stones <= 30){
            int size = 5;
            int padding = 30;

            for (int i = 0; i < stones; i++) {
                int px = x + padding + (i % 4) * (size + 5);
                int py = y + padding + (i / 4) * (size + 5);

                g.fillOval(px, py, size, size);
            }
        } else {
            int size = 3;
            int padding = 40;

            for (int i = 0; i < stones; i++) {
                int px = x + padding + (i % 4) * (size + 5);
                int py = y + padding + (i / 4) * (size + 5);

                g.fillOval(px, py, size, size);
            }
        }
    }

    /**
     * Returns background color of board style
     * @return background color of board style
     */
    @Override
    public Color getBackgroundColor() { return new Color(210, 180, 140); }

    /**
     * Returns pit background color of board style
     * @return pit background color of board style
     */
    @Override
    public Color getPitColor() { return new Color(139, 69, 19); }
}

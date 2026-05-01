import java.awt.*;

/**
 * Concrete strategy: a warm wood-tone board with circular pits and rounded
 * Mancala stores. Used by {@link MancalaView} via the {@link BoardStyle}
 * interface.
 *
 * @author Group 10
 */
public class RoundedStyle implements BoardStyle {

    @Override
    public void drawPit(Graphics2D g, int x, int y, int width, int height, int stones) {
        g.setColor(getPitColor());
        int pad = 6;
        g.fillOval(x + pad, y + pad, width - 2 * pad, height - 2 * pad);
        drawCount(g, x, y, width, height, stones);
    }

    @Override
    public void drawMancala(Graphics2D g, int x, int y, int width, int height, int stones) {
        g.setColor(getPitColor());
        int pad = 8;
        g.fillRoundRect(x + pad, y + pad, width - 2 * pad, height - 2 * pad, width, width);
        drawCount(g, x, y, width, height, stones);
    }

    private void drawCount(Graphics2D g, int x, int y, int width, int height, int stones) {
        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(Font.BOLD, 16f));
        String s = String.valueOf(stones);
        FontMetrics fm = g.getFontMetrics();
        int tx = x + (width - fm.stringWidth(s)) / 2;
        int ty = y + (height + fm.getAscent()) / 2 - 4;
        g.drawString(s, tx, ty);
    }

    @Override
    public Color getBackgroundColor() { return new Color(210, 180, 140); }

    @Override
    public Color getPitColor() { return new Color(139, 69, 19); }
}

import java.awt.*;

/**
 * Concrete strategy: a dark, geometric board with sharp rectangular pits.
 * Used by {@link MancalaView} via the {@link BoardStyle} interface.
 *
 * @author Group 10
 */
public class RectangleStyle implements BoardStyle {

    @Override
    public void drawPit(Graphics2D g, int x, int y, int width, int height, int stones) {
        g.setColor(getPitColor());
        int pad = 6;
        g.fillRect(x + pad, y + pad, width - 2 * pad, height - 2 * pad);
        drawCount(g, x, y, width, height, stones);
    }

    @Override
    public void drawMancala(Graphics2D g, int x, int y, int width, int height, int stones) {
        g.setColor(getPitColor());
        int pad = 8;
        g.fillRect(x + pad, y + pad, width - 2 * pad, height - 2 * pad);
        drawCount(g, x, y, width, height, stones);
    }

    @Override
    public void drawHighlight(Graphics2D g, int x, int y, int width, int height) {
        g.setColor(new Color(255, 215, 0));
        g.setStroke(new BasicStroke(3f));
        int pad = 4;
        g.drawRect(x + pad, y + pad, width - 2 * pad - 1, height - 2 * pad - 1);
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
    public Color getBackgroundColor() { return Color.DARK_GRAY; }

    @Override
    public Color getPitColor() { return Color.BLUE; }

    @Override
    public void drawStones(Graphics2D g, int x, int y, int width, int height, int stones) {

    }
}

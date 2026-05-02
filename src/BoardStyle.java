import java.awt.*;

public interface BoardStyle {
    void drawPit(java.awt.Graphics2D g, int x, int y, int width, int height, int stones);
    void drawMancala(java.awt.Graphics2D g, int x, int y, int width, int height, int stones);
    void drawHighlight(java.awt.Graphics2D g, int x, int y, int width, int height);
    java.awt.Color getBackgroundColor();
    java.awt.Color getPitColor();
}

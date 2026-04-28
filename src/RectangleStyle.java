import java.awt.*;

public class RectangleStyle implements BoardStyle{
    public void drawPit(java.awt.Graphics2D g, int x, int y, int width, int height, int stones) {}
    public void drawMancala(java.awt.Graphics2D g, int x, int y, int width, int height, int stones) {}
    public java.awt.Color getBackgroundColor() { return java.awt.Color.DARK_GRAY; }
    public java.awt.Color getPitColor() { return java.awt.Color.BLUE; }
}

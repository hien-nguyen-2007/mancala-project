import java.awt.*;

public class RoundedStyle implements BoardStyle{
    public void drawPit(java.awt.Graphics2D g, int x, int y, int width, int height, int stones) {}
    public void drawMancala(java.awt.Graphics2D g, int x, int y, int width, int height, int stones) {}
    public java.awt.Color getBackgroundColor() { return new Color(210, 180, 140); }
    public java.awt.Color getPitColor() { return new Color(139, 69, 19); }
}

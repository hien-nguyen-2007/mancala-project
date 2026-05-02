import java.awt.*;

public interface BoardStyle {
    void drawPit(Graphics2D g, int x, int y, int width, int height, int stones);
    void drawMancala(Graphics2D g, int x, int y, int width, int height, int stones);
    java.awt.Color getBackgroundColor();
    java.awt.Color getPitColor();
    void drawStones(Graphics2D g, int x, int y, int width, int hieght, int stones);
}

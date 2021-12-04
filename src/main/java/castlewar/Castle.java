package castlewar;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.geom.Rectangle2D;
import java.io.File;

public class Castle {
    private Image castle;
    private double x, y;
    private boolean horizontally;
    private Rectangle2D rect;

    public Castle(double x, double y, boolean horizontally) {
        castle = new Image(new File("img/castle.png").toURI().toString());
        this.x = x;
        this.y = y;
        this.horizontally = horizontally;
        rect = new Rectangle2D.Double(x, y, 200, 580);
    }

    public boolean intersect(Rectangle2D rect) {
        return this.rect.intersects(rect);
    }

    public void render(GraphicsContext graphic) {
        if (horizontally) {
            graphic.drawImage(castle, x + castle.getWidth(), y, -castle.getWidth(), castle.getHeight());
        } else {
            graphic.drawImage(castle, x, y);
        }
    }
}

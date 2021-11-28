package castlewar;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.io.File;

public class Castle {
    private Image castle;
    private double x, y;
    private boolean horizontally;

    public Castle(double x, double y, boolean horizontally) {
        castle = new Image(new File("img/castle.png").toURI().toString());
        this.x = x;
        this.y = y;
        this.horizontally = horizontally;

    }

    public void render(GraphicsContext graphic) {
        if (horizontally) {
            graphic.drawImage(castle, x, y, -castle.getWidth(), castle.getHeight());
        } else {
            graphic.drawImage(castle, x, y);
        }
    }
}

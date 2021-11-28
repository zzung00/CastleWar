package castlewar;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.io.File;

public class Player {
    private Image image;
    private double x, y;
    private boolean horizontally;

    public Player(double x, double y, boolean horizontally) {
        this.x = x;
        this.y = y;
        this.horizontally = horizontally;
        image = new Image(new File("img/player1.png").toURI().toString());
    }

    public void render(GraphicsContext graphic) {
        if (horizontally) {
            graphic.drawImage(image, x, y, -image.getWidth(), image.getHeight());
        } else {
            graphic.drawImage(image, x, y);
        }
    }


}

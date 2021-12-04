package castlewar;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.geom.Rectangle2D;
import java.io.File;

public class FireBall {
    private long x, y;
    private Image image;
    private boolean horizontally;
    private int speed = 3;
    private Rectangle2D rect;

    public FireBall(long x, long y, boolean horizontally) {
        this.x = x;
        this.y = y;
        this.horizontally = horizontally;
        image = new Image(new File("img/fire.png").toURI().toString());
        rect = new Rectangle2D.Double(x, y, 32, 16);
    }

    public void setX(long x) {
        this.x = x;
    }

    public long getX() {
        return x;
    }

    public void setY(long y) {
        this.y = y;
    }

    public long getY() {
        return y;
    }

    public void setHorizontally() {
        this.horizontally = horizontally;
    }

    public Rectangle2D getRect() {
        return rect;
    }

    public void update(double delta) {
        if (horizontally) {
            x -= speed;
        }else {
            x += speed;
        }
        rect.setRect(x, y, 32, 16);
    }

    public void render(GraphicsContext graphic) {
        if (!horizontally) {
            graphic.drawImage(image, x + image.getWidth(), y, -image.getWidth(), image.getHeight());
        } else {
            graphic.drawImage(image, x, y);
        }
    }
}

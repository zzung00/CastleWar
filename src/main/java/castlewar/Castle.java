package castlewar;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;

public class Castle {
    private double x, y;
    private boolean horizontally;
    private int hp = 300;
    private Rectangle2D rect;
    private ArrayList<Image> images = new ArrayList<>();
    private int frame;

    public Castle(double x, double y, boolean horizontally) {
        this.x = x;
        this.y = y;
        this.horizontally = horizontally;
        rect = new Rectangle2D.Double(x, y, 200, 580);

        for (int i = 0; i < 5; i++) {
            images.add(new Image(new File("img/castle" + i + ".png").toURI().toString()));
        }
    }

    public boolean intersect(Rectangle2D rect) {
        return this.rect.intersects(rect);
    }

    public int getHp() {
        return hp;
    }

    public void hit() {
        hp -= 5;
        System.out.println((double) hp / 300);

        if ((double) hp / 300 >= 0.9) {
            frame = 0;
        }else if ((double) hp / 300 >= 0.8) {
            frame = 1;
        }else if ((double) hp / 300 >= 0.6) {
            frame = 2;
        }else if ((double) hp / 300 >= 0.4) {
            frame = 3;
        } else {
            frame = 4;
        }
    }

    public void render(GraphicsContext graphic) {
        if (horizontally) {
            graphic.drawImage(images.get(frame), x + images.get(frame).getWidth(), y, -images.get(frame).getWidth(), images.get(frame).getHeight());
        } else {
            graphic.drawImage(images.get(frame), x, y);
        }
    }
}

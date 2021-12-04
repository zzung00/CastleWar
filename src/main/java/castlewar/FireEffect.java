package castlewar;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;

public class FireEffect {
    private long x, y;
    private ArrayList<Image> images = new ArrayList<>();
    private int frame;
    private long startTime;
    private boolean horizontally;
    private int delay;
    private boolean show;

    public FireEffect() {
        for (int i = 0; i < 6; i++) {
            images.add(new Image(new File("img/effect" + i + ".png").toURI().toString()));
        }
        delay = 60;
        show = false;
    }

    public void setHorizontally(boolean horizontally) {
        this.horizontally = horizontally;
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

    public void show() {
        show = true;
    }

    public void render(GraphicsContext graphic) {
        if (show) {
            if (horizontally) {
                graphic.drawImage(images.get(frame), x + images.get(frame).getWidth(), y, -images.get(frame).getWidth(), images.get(frame).getHeight());
            } else {
                graphic.drawImage(images.get(frame), x, y);
            }
        }
    }

    public void update(double delta, Player player) {
        if (show) {
            if (delta * delay >= delay) {
                frame++;
                startTime = System.nanoTime();
            }
            if (frame == images.size()) {
                frame = 0;
                show = false;
            }
            x = player.getX();
            y = player.getY();
            horizontally = player.isHorizontally();
        }
    }
}

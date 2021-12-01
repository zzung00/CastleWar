package castlewar;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;

public class Player {
    private Image image;
    private int type;
    private double x, y;
    private boolean horizontally;
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private boolean shooting;
    private double speed = 1.5;

    private ArrayList<PlayerAttack> playerAttacks = new ArrayList<>();
    private PlayerAttack playerAttack;

    public Player(int type) {
        this.type = type;

        if (type == 0) {
            image = new Image(new File("img/player1.png").toURI().toString());
            x = 20;
            y = 500;
            horizontally = false;
        } else if (type == 1) {
            image = new Image(new File("img/player2.png").toURI().toString());
            x = 680;
            y = 500;
            horizontally = true;
        }
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }

    public void setHorizontally(boolean horizontally) {
        this.horizontally = horizontally;
    }

    public void update(double delta) {
        if (up) {
            y -= speed;
        }
        if (down) {
            y += speed;
        }
        if (left) {
            x -= speed;
        }
        if (right) {
            x += speed;
        }
    }

    public void render(GraphicsContext graphic) {
        if (horizontally) {
            graphic.drawImage(image, x + image.getWidth(), y, -image.getWidth(), image.getHeight());
        } else {
            graphic.drawImage(image, x, y);
        }
    }

    private void playerAttackProcess() {
        for (int i = 0; i < playerAttacks.size(); i++) {
            playerAttack = playerAttacks.get(i);
            playerAttack.fire();
        }
    }

}

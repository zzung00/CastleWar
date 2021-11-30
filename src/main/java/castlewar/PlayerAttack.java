package castlewar;

import javafx.scene.image.Image;

import java.io.File;

public class PlayerAttack {
    private Image attack;
    private int x, y, width, height;
    private int damage = 5;

    public PlayerAttack(int x, int y) {
        attack = new Image(new File("img/fireAttack.png").toURI().toString());
        this.x = x;
        this.y = y;
    }

    public void fire() {
        this.x += 15;
    }
}

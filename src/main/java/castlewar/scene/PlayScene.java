package castlewar.scene;

import castlewar.Castle;
import castlewar.CastleWar;
import castlewar.Player;
import castlewar.network.PacketReader;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

import java.io.File;

public class PlayScene extends CastleWarScene{
    private ImageView background;
    private Canvas canvas;
    private Castle leftCastle;
    private Castle rightCastle;
    private Player me;

    public PlayScene(CastleWar castleWar, int width, int height) {
        super(castleWar, width, height);
        background = new ImageView();
        background.setImage(new Image(new File("img/PlayScene.png").toURI().toString()));
        root.getChildren().add(background);
        canvas = new Canvas();
        canvas.setWidth(800);
        canvas.setHeight(600);
        root.getChildren().add(canvas);
        leftCastle = new Castle(-15, 50, false);
        rightCastle = new Castle(815, 50, true);
        me = new Player(15, 500, false);

        setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.RIGHT) {
                    me.setRight(false);
                }else if (event.getCode() == KeyCode.LEFT) {
                    me.setLeft(false);
                }else if (event.getCode() == KeyCode.DOWN) {
                    me.setDown(false);
                }else if (event.getCode() == KeyCode.UP) {
                    me.setUp(false);
                }
            }
        });

        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.RIGHT) {
                    me.setRight(true);
                    me.setHorizontally(false);
                }else if (event.getCode() == KeyCode.LEFT) {
                    me.setLeft(true);
                    me.setHorizontally(true);
                }else if (event.getCode() == KeyCode.DOWN) {
                    me.setDown(true);
                }else if (event.getCode() == KeyCode.UP) {
                    me.setUp(true);
                }
            }
        });
    }

    @Override
    public void update(double delta) {
        me.update(delta);
    }

    @Override
    public void render() {
        GraphicsContext graphic = canvas.getGraphicsContext2D();
        graphic.clearRect(0, 0, 800, 600);
        graphic.strokeRect(0, 0, 800, 600);
        leftCastle.render(graphic);
        rightCastle.render(graphic);
        me.render(graphic);
    }

    @Override
    public void receive(PacketReader reader) {

    }
}

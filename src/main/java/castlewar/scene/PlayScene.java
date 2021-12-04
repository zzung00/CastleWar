package castlewar.scene;

import castlewar.Castle;
import castlewar.CastleWar;
import castlewar.FireEffect;
import castlewar.Player;
import castlewar.network.PacketCreator;
import castlewar.network.PacketReader;
import castlewar.network.PacketWriter;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class PlayScene extends CastleWarScene{
    private int id;
    private ImageView background;
    private Canvas canvas;
    private Castle leftCastle;
    private Castle rightCastle;
    private HashMap<Integer, Player> players = new HashMap<>();

    public PlayScene(CastleWar castleWar, int width, int height) {
        super(castleWar, width, height);

        id = castleWar.getId();
        System.out.println(id);
        background = new ImageView();
        background.setImage(new Image(new File("img/PlayScene.png").toURI().toString()));
        root.getChildren().add(background);
        canvas = new Canvas();
        canvas.setWidth(800);
        canvas.setHeight(600);
        root.getChildren().add(canvas);
        leftCastle = new Castle(-15, 50, false);
        rightCastle = new Castle(815, 50, true);
        players.put(0, new Player(0));
        players.put(1, new Player(1));

        setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.RIGHT) {
                    players.get(id).setRight(false);
                }else if (event.getCode() == KeyCode.LEFT) {
                    players.get(id).setLeft(false);
                }else if (event.getCode() == KeyCode.DOWN) {
                    players.get(id).setDown(false);
                }else if (event.getCode() == KeyCode.UP) {
                    players.get(id).setUp(false);
                }
            }
        });

        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.RIGHT) {
                    players.get(id).setRight(true);
                    players.get(id).setHorizontally(false);
                }else if (event.getCode() == KeyCode.LEFT) {
                    players.get(id).setLeft(true);
                    players.get(id).setHorizontally(true);
                }else if (event.getCode() == KeyCode.DOWN) {
                    players.get(id).setDown(true);
                }else if (event.getCode() == KeyCode.UP) {
                    players.get(id).setUp(true);
                }else if (event.getCode() == KeyCode.ESCAPE) {
                    System.exit(0);
                }else if (event.getCode() == KeyCode.SPACE) {
                    attack();
                }
            }
        });
    }

    public void attack() {
        players.get(id).attack();
        castleWar.sendPacket(PacketCreator.attack(players.get(id)));
    }

    @Override
    public void update(double delta) {
        players.get(0).update(delta);
        players.get(1).update(delta);
        if (players.get(id).isMove()) {
            castleWar.sendPacket(PacketCreator.movePlayer(players.get(id)));
        }
    }

    @Override
    public void render() {
        GraphicsContext graphic = canvas.getGraphicsContext2D();
        graphic.clearRect(0, 0, 800, 600);
        graphic.strokeRect(0, 0, 800, 600);
        leftCastle.render(graphic);
        rightCastle.render(graphic);
        players.get(0).render(graphic);
        players.get(1).render(graphic);
    }

    @Override
    public void resume() {
        id = castleWar.getId();
    }

    @Override
    public void pause() {

    }

    @Override
    public void receive(PacketReader reader) {
        short packetId = reader.readShort();

        switch (packetId) {
            case 1: {
                int oid = reader.readInt();
                byte horizon = reader.readByte();
                int x = reader.readInt();
                int y = reader.readInt();
                if (horizon == 0) {
                    players.get(oid).setHorizontally(false);
                }else {
                    players.get(oid).setHorizontally(true);
                }
                players.get(oid).setX(x);
                players.get(oid).setY(y);
            }break;

            case 2 : {
                int oid = reader.readInt();
                players.get(oid).attack();
            }
        }
    }
}

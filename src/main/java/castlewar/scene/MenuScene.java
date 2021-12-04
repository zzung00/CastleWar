package castlewar.scene;

import castlewar.CastleWar;
import castlewar.network.PacketCreator;
import castlewar.network.PacketReader;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class MenuScene extends CastleWarScene {
    private Button btnPlay;
    private Button btnExit;
    private ImageView background;
    private ImageView logo;
    private Label waitingLabel;

    public MenuScene(CastleWar castleWar, int width, int height) {
        super(castleWar, width, height);

        getStylesheets().add(getClass().getResource("/ui/button.css").toExternalForm());
        background = new ImageView();
        background.setImage(new Image(new File("img/CastleWar_Menu.png").toURI().toString()));
        root.getChildren().add(background);

        logo = new ImageView();
        logo.setImage(new Image(new File("img/CastleWar-logo-black.png").toURI().toString()));
        logo.setLayoutX(400 - 150);
        logo.setLayoutY(70);
        logo.requestFocus();
        root.getChildren().add(logo);

        btnPlay = new Button();
        btnPlay.setPrefSize(150, 50);
        btnPlay.setText("PLAY");
        btnPlay.setLayoutX(400 - 80);
        btnPlay.setLayoutY(320);
        root.getChildren().add(btnPlay);
        btnPlay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                waitingLabel = new Label("Waiting for game...");
                waitingLabel.setLayoutX(500);
                waitingLabel.setLayoutY(10);
                waitingLabel.setPrefSize(100, 100);
                root.getChildren().add(waitingLabel);
                castleWar.sendPacket(PacketCreator.startGame());
            }
        });

        btnExit = new Button();
        btnExit.setPrefSize(150, 50);
        btnExit.setText("EXIT");
        btnExit.setLayoutX(400 - 80);
        btnExit.setLayoutY(380);
        root.getChildren().add(btnExit);
        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void update(double delta) {

    }

    @Override
    public void render() {

    }

    @Override
    public void receive(PacketReader reader) {
        short packetId = reader.readShort();

        switch (packetId) {
            case 0: {
                int id = reader.readInt();
                castleWar.setId(id);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        castleWar.changeScene(1);
                    }
                });

            }
            break;
        }
    }
}

package castlewar.scene;

import castlewar.CastleWar;
import castlewar.network.PacketReader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class MenuScene extends CastleWarScene {
    private int currentStep;
    private Label gameName;
    private Button btnPlay;
    private Button btnExit;
    private ImageView background;


    public MenuScene(CastleWar castleWar, int i, int i1) {
        super(castleWar, i, i1);

        background = new ImageView();
        background.setImage(new Image(new File("img/CastleWar_Menu.png").toURI().toString()));
        root.getChildren().add(background);

        gameName = new Label("Castle War");
        gameName.setStyle("-fx-background-color: transparent;" + "-fx-text-fill: white");
        gameName.setPrefWidth(150);
        gameName.setLayoutX(440);
        gameName.setLayoutY(240);
        gameName.requestFocus();
        root.getChildren().add(gameName);

        btnPlay = new Button();
        btnPlay.setLayoutX(350);
        btnPlay.setLayoutY(350);
        root.getChildren().add(btnPlay);
        btnPlay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        btnExit = new Button();
        btnExit.setLayoutX(580);
        btnExit.setLayoutY(350);
        root.getChildren().add(btnExit);
        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

    }

    @Override
    public void initialize() {

    }

    @Override
    public void finalization() {

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
        System.out.println(packetId);
    }
}

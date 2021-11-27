package castleWar;

import castleWar.scene.CastleWarScene;
import castleWar.scene.MenuScene;
import castleWar.scene.PlayScene;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.Socket;
import java.util.ArrayList;

public class CastleWar extends Application {
    private Stage stage;
    private ArrayList<CastleWarScene> scenes = new ArrayList<>();
    private int currentScene = 0;

    private Socket socket;
    private AnimationTimer animationTimer;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("성벽 부시기");
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
        scenes.add(new MenuScene(this, 800, 600));
        //scenes.add(new PlayScene(this, 800, 600));

    }

    public static void main(String[] args) {
        launch(args);
    }
}

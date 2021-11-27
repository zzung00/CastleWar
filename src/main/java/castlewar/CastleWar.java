package castlewar;

import castlewar.scene.CastleWarScene;
import castlewar.scene.MenuScene;
import castlewar.scene.PlayScene;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class CastleWar extends Application {
    private Stage stage;
    private ArrayList<CastleWarScene> scenes = new ArrayList<>();
    private int currentScene = 0;

    private Socket socket;
    private AnimationTimer animationTimer;

    @Override
    public void start(Stage primaryStage) throws Exception {
        scenes.add(new MenuScene(this, 800, 600));
        scenes.add(new PlayScene(this, 800, 600));
        stage = primaryStage;
        stage.setTitle("Castle War");
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
        stage.setScene(scenes.get(currentScene));
        stage.show();

    }

    public Stage getStage() {
        return stage;
    }

    public void changeScene(int sceneNum) {
        currentScene = sceneNum;
        scenes.get(currentScene);
        stage.setScene(scenes.get(currentScene));
    }

    public static void main(String[] args) {
        launch(args);
    }
}

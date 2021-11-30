package castlewar;

import castlewar.network.PacketReader;
import castlewar.scene.CastleWarScene;
import castlewar.scene.MenuScene;
import castlewar.scene.PlayScene;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class CastleWar extends Application {
    private Stage stage;
    private ArrayList<CastleWarScene> scenes = new ArrayList<>();
    private int currentScene = 0;

    private Socket socket;
    private AnimationTimer animationTimer;
    private Receiver receiver;

    @Override
    public void start(Stage primaryStage) {
        try {
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
            socket = new Socket();
            socket.connect(new InetSocketAddress("localhost", 8888));
            receiver = new Receiver();
            receiver.start();

            animationTimer = new AnimationTimer() {
                private long start = System.nanoTime();

                @Override
                public void handle(long now) {
                    double delta = (now - start) / 1000000000.0 * 6;
                    scenes.get(currentScene).update(delta);
                    scenes.get(currentScene).render();
                    if (delta >= 1) {
                        start = now;
                    }
                }
            };
            animationTimer.start();

        }catch (ConnectException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Castle War");
            alert.setHeaderText(null);
            alert.setContentText("unconnected server");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                System.exit(0);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void sendPacket (byte[] data) {
        OutputStream outputStream;
        try {
            outputStream = socket.getOutputStream();
            outputStream.write(data);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Receiver extends Thread {
        private InputStream inputStream;
        private byte[] data;

        public Receiver() throws IOException {
            super();
            inputStream = socket.getInputStream();
        }

        @Override
        public void run() {
            try {
                while (true) {
                    if (socket.isClosed()) {
                        break;
                    }
                    data = new byte[1024];
                    int count = inputStream.read(data);
                    if (count <= 0) {
                        break;
                    }
                    System.out.println(Arrays.toString(data));
                    inputStream.read(data);
                    PacketReader reader = new PacketReader(data);
                    scenes.get(currentScene).receive(reader);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}


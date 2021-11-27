package castlewar.scene;

import castlewar.CastleWar;
import castlewar.network.PacketReader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class PlayScene extends CastleWarScene{
    private ImageView background;

    public PlayScene(CastleWar castleWar, int width, int height) {
        super(castleWar, width, height);

        background = new ImageView();
        background.setImage(new Image(new File("img/PlayScene.png").toURI().toString()));
        root.getChildren().add(background);
    }

    @Override
    public void update(double delta) {

    }

    @Override
    public void render() {

    }

    @Override
    public void receive(PacketReader reader) {

    }
}

package castlewar.scene;

import castlewar.CastleWar;
import castlewar.network.PacketReader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public abstract class CastleWarScene extends Scene {
    protected CastleWar castleWar;
    protected AnchorPane root;

    public CastleWarScene(CastleWar castleWar, double width, double height) {
        super(new AnchorPane(), width, height);
        this.castleWar = castleWar;
        root = (AnchorPane) getRoot();
        root.setPrefSize(width, height);
    }

    public abstract void update(double delta);

    public abstract void render();

    public abstract void receive(PacketReader reader);
}

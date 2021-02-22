import java.awt.Graphics2D;

public abstract class Scene {
    protected final Game game;

    public Scene(Game game) {
        this.game = game;
    }

    public abstract void update(long var1);

    public abstract void draw(Graphics2D var1);
}

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;

public class GameOverScene extends Scene {
    public GameOverScene(Game game) {
        super(game);
    }

    public void update(long nanosPassed) {
        Iterator var3 = this.game.getInput().getKeyPressedEvents().iterator();

        while(var3.hasNext()) {
            KeyEvent event = (KeyEvent)var3.next();
            if (event.getKeyCode() == 10) {
                this.game.setScene(new MainScene(this.game));
            }
        }

    }

    public void draw(Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, this.game.getScreenSize().width, this.game.getScreenSize().height);
        g.setFont(new Font("Default", 1, 16));
        g.setColor(Color.white);
        String message = "Press <Enter> to start new game";
        Rectangle2D messageBounds = g.getFontMetrics().getStringBounds(message, g);
        int messageWidth = (int)messageBounds.getWidth();
        int messageHeight = (int)messageBounds.getHeight();
        g.drawString(message, this.game.getScreenSize().width / 2 - messageWidth / 2, this.game.getScreenSize().height / 2 - messageHeight / 2);
    }
}


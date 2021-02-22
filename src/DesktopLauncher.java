import java.awt.Dimension;

public class DesktopLauncher {
    public DesktopLauncher() {
    }

    public static void main(String[] args) {
        int screenWidth = 384;
        int screenHeight = 384;
        Dimension screenSize = new Dimension(screenWidth, screenHeight);
        Game game = DesktopGameBuilder.build(screenSize);
        game.setScene(new MainScene(game));
        game.start();
    }
}

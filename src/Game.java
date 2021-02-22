import java.awt.Dimension;

public interface Game {
    void start();

    void pause();

    Dimension getScreenSize();

    Input getInput();

    void setScene(Scene var1);
}

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class DesktopGameBuilder {
    public DesktopGameBuilder() {
    }

    public static Game build(Dimension screenSize) {
        final CanvasGame game = new CanvasGame(screenSize);
        JFrame frame = new JFrame();
        frame.setFocusable(false);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.add(game);
        frame.pack();
        frame.setVisible(true);
        game.createBufferStrategy(2);
        frame.setDefaultCloseOperation(3);
        frame.setLocationRelativeTo((Component)null);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                game.pause();
            }
        });
        frame.requestFocus();
        game.requestFocus();
        return game;
    }
}

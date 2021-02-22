import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.concurrent.atomic.AtomicBoolean;

public class CanvasGame extends Canvas implements Game, Runnable {
    private Thread gameThread;
    private AtomicBoolean running = new AtomicBoolean(false);
    private Input input;
    private Scene scene;

    public CanvasGame(Dimension screenSize) {
        this.setSize(screenSize);
        this.initInput();
        this.initFocusListener();
    }

    private void initInput() {
        this.input = new Input();
        this.addKeyListener(this.input);
    }

    private void initFocusListener() {
        this.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent event) {
                CanvasGame.this.start();
            }

            public void focusLost(FocusEvent event) {
                CanvasGame.this.pause();
            }
        });
    }

    public void start() {
        if (this.running.compareAndSet(false, true)) {
            this.gameThread = new Thread(this);
            this.gameThread.start();
        }

    }

    public void pause() {
        if (this.running.compareAndSet(true, false)) {
            try {
                this.gameThread.join();
            } catch (InterruptedException var2) {
                throw new RuntimeException(var2);
            }
        }

    }

    public Dimension getScreenSize() {
        return this.getSize();
    }

    public Input getInput() {
        return this.input;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void run() {
        long previousIterationTime = System.nanoTime();

        while(this.running.get()) {
            if (this.scene != null) {
                long now = System.nanoTime();
                long nanosPassed = now - previousIterationTime;
                previousIterationTime = now;
                Graphics2D g = (Graphics2D)this.getBufferStrategy().getDrawGraphics();
                this.scene.update(nanosPassed);
                this.scene.draw(g);
                this.getBufferStrategy().show();
            }
        }

    }
}

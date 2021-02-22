import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collection;

public class Input implements KeyListener {
    private final Collection<KeyEvent> keyPressedEvents = new ArrayList();
    private final Collection<KeyEvent> keyReleasedEvents = new ArrayList();

    public Input() {
    }

    public void keyTyped(KeyEvent event) {
    }

    public synchronized void keyPressed(KeyEvent event) {
        this.keyPressedEvents.add(event);
    }

    public synchronized Collection<KeyEvent> getKeyPressedEvents() {
        Collection<KeyEvent> events = new ArrayList(this.keyPressedEvents);
        this.keyPressedEvents.clear();
        return events;
    }

    public synchronized void keyReleased(KeyEvent event) {
        this.keyReleasedEvents.add(event);
    }

    public synchronized Collection<KeyEvent> getKeyReleasedEvents() {
        Collection<KeyEvent> events = new ArrayList(this.keyReleasedEvents);
        this.keyReleasedEvents.clear();
        return events;
    }
}

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<BodyPart> body;
    private Direction direction;

    public Snake(int x, int y, Direction direction) {
        this.direction = direction;
        this.body = new ArrayList();
        this.body.add(new BodyPart(x, y));
        this.body.add(new BodyPart(x - direction.deltaX(), y - direction.deltaY()));
        this.body.add(new BodyPart(x - direction.deltaX() * 2, y - direction.deltaY() * 2));
    }

    public void move() {
        this.moveBody();
        this.moveHead();
    }

    private void moveBody() {
        for(int i = this.body.size() - 1; i > 0; --i) {
            BodyPart current = (BodyPart)this.body.get(i);
            BodyPart previous = (BodyPart)this.body.get(i - 1);
            current.setX(previous.getX());
            current.setY(previous.getY());
        }

    }

    private void moveHead() {
        this.head().setX(this.head().getX() + this.direction.deltaX());
        this.head().setY(this.head().getY() + this.direction.deltaY());
    }

    public BodyPart head() {
        return (BodyPart)this.body.get(0);
    }

    public List<BodyPart> getBody() {
        return this.body;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}


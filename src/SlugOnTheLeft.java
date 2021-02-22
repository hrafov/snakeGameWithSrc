import java.util.ArrayList;
import java.util.List;

public class SlugOnTheLeft {
    private List<BodyPart> body;
    private Direction direction;

    public SlugOnTheLeft() {
        Direction var10001 = this.direction;
        this.direction = Direction.LEFT;
        int x = 10;
        int y = 10;
        this.body = new ArrayList();
        this.body.add(new BodyPart(x, y));
        this.body.add(new BodyPart(x - this.direction.deltaX(), y - this.direction.deltaY()));
    }

    public SlugOnTheLeft(int x, int y, Direction direction) {
        this.direction = direction;
        this.body = new ArrayList();
        this.body.add(new BodyPart(x, y));
        this.body.add(new BodyPart(x - direction.deltaX(), y - direction.deltaY()));
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
        Direction var10001;
        if (this.head().getX() < 1) {
            this.head().setX(1);
            var10001 = this.direction;
            this.setDirection(Direction.DOWN);
        }

        if (this.head().getX() > 32) {
            this.head().setX(32);
            var10001 = this.direction;
            this.setDirection(Direction.UP);
        }

        this.head().setY(this.head().getY() + this.direction.deltaY());
        if (this.head().getY() < 1) {
            this.head().setY(1);
            var10001 = this.direction;
            this.setDirection(Direction.RIGHT);
        }

        if (this.head().getY() > 32) {
            this.head().setY(32);
            var10001 = this.direction;
            this.setDirection(Direction.LEFT);
        }

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


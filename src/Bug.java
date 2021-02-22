public class Bug {
    private int x;
    private int y;
    private Direction direction;

    public Bug() {
        this.x = 20;
        this.y = 20;
    }

    public Bug(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void move() {
        double p = Math.random();
        if (p <= 0.25D) {
            this.direction = Direction.RIGHT;
        } else if (p <= 0.5D) {
            this.direction = Direction.LEFT;
        } else if (p <= 0.75D) {
            this.direction = Direction.UP;
        } else {
            this.direction = Direction.DOWN;
        }

        this.setX(this.getX() + this.direction.deltaX());
        this.setY(this.getY() + this.direction.deltaY());
        if (this.getX() < 1) {
            this.setX(32);
        }

        if (this.getX() > 32) {
            this.setX(1);
        }

        if (this.getY() < 1) {
            this.setY(32);
        }

        if (this.getY() > 32) {
            this.setY(1);
        }

    }
}


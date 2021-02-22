import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainScene extends Scene {
    private Delay snakeMoveDelay;
    private Snake snake;
    private Snake snake1;
    private Apple apple;
    private Bug bug;
    private Plant plant;
    private CrossPlant crossplant;
    private SlugOnTheRight slug;
    private SlugOnTheLeft slug1;
    private List<Bug> listofbugs;

    public MainScene(Game game) {
        super(game);
        this.snake = new Snake(16, 16, Direction.RIGHT);
        this.snake1 = new Snake(8, 8, Direction.LEFT);
        this.plant = new Plant(6, 2);
        this.crossplant = new CrossPlant(25, 10);
        this.placeApple();
        this.placeBug();
        this.listofbugs = new ArrayList();

        for(int i = 0; i <= 15; ++i) {
            this.listofbugs.add(new Bug(20 - i, 20 - i));
        }

        this.slug = new SlugOnTheRight();
        this.slug1 = new SlugOnTheLeft(2, 2, Direction.LEFT);
        this.snakeMoveDelay = new Delay(300L);
    }

    private boolean isGameOver() {
        return false;
    }

    private void processInput() {
        Iterator var1 = this.game.getInput().getKeyPressedEvents().iterator();

        while(var1.hasNext()) {
            KeyEvent event = (KeyEvent)var1.next();
            switch(event.getKeyCode()) {
                case 37:
                    this.snake.setDirection(Direction.LEFT);
                    break;
                case 38:
                    this.snake.setDirection(Direction.UP);
                    break;
                case 39:
                    this.snake.setDirection(Direction.RIGHT);
                    break;
                case 40:
                    this.snake.setDirection(Direction.DOWN);
            }
        }

    }

    public void update(long nanosPassed) {
        if (this.isGameOver()) {
            this.game.setScene(new GameOverScene(this.game));
        } else {
            this.processInput();
            if (this.snakeMoveDelay.updateAndCheck(nanosPassed)) {
                this.snake.move();
                this.snake1.move();
                this.bug.move();

                for(int i = 0; i <= this.listofbugs.size() - 1; ++i) {
                    ((Bug)this.listofbugs.get(i)).move();
                }

                this.plant.grow();
                this.crossplant.grow();
                this.slug.move();
                this.slug1.move();
                BodyPart head = this.snake.head();
                if (head.getX() < 1) {
                    head.setX(32);
                }

                if (head.getX() > 32) {
                    head.setX(1);
                }

                if (head.getY() < 1) {
                    head.setY(32);
                }

                if (head.getY() > 32) {
                    head.setY(1);
                }

                if (this.apple.getX() - head.getX() > 0) {
                    this.snake.setDirection(Direction.RIGHT);
                }

                if (this.apple.getX() - head.getX() < 0) {
                    this.snake.setDirection(Direction.LEFT);
                }

                if (this.apple.getX() - head.getX() == 0) {
                    if (this.apple.getY() - head.getY() > 0) {
                        this.snake.setDirection(Direction.UP);
                    } else if (this.apple.getY() - head.getY() < 0) {
                        this.snake.setDirection(Direction.DOWN);
                    }
                }

                BodyPart head1 = this.snake1.head();
                if (head1.getX() < 1) {
                    head1.setX(32);
                }

                if (head1.getX() > 32) {
                    head1.setX(1);
                }

                if (head1.getY() < 1) {
                    head1.setY(32);
                }

                if (head1.getY() > 32) {
                    head1.setY(1);
                }

                if (Math.pow((double)(this.apple.getX() - head.getX()), 2.0D) + Math.pow((double)(this.apple.getY() - head.getY()), 2.0D) - (Math.pow((double)(this.apple.getX() - head1.getX()), 2.0D) + Math.pow((double)(this.apple.getY() - head1.getY()), 2.0D)) < 0.0D) {
                    if (16 - head1.getY() > 0) {
                        this.snake1.setDirection(Direction.UP);
                    }

                    if (16 - head1.getY() < 0) {
                        this.snake1.setDirection(Direction.DOWN);
                    }

                    if (16 - head1.getY() == 0) {
                        if (16 - head1.getX() > 0) {
                            this.snake1.setDirection(Direction.RIGHT);
                        } else if (16 - head1.getX() < 0) {
                            this.snake1.setDirection(Direction.LEFT);
                        }
                    }
                } else {
                    if (this.apple.getY() - head1.getY() > 0) {
                        this.snake1.setDirection(Direction.UP);
                    }

                    if (this.apple.getY() - head1.getY() < 0) {
                        this.snake1.setDirection(Direction.DOWN);
                    }

                    if (this.apple.getY() - head1.getY() == 0) {
                        if (this.apple.getX() - head1.getX() > 0) {
                            this.snake1.setDirection(Direction.RIGHT);
                        } else if (this.apple.getX() - head1.getX() < 0) {
                            this.snake1.setDirection(Direction.LEFT);
                        }
                    }
                }

                List body;
                BodyPart bodyPart;
                if (head.getX() == this.apple.getX() && head.getY() == this.apple.getY() && (head1.getX() != this.apple.getX() || head1.getY() != this.apple.getY() || Math.random() > 0.5D)) {
                    body = this.snake.getBody();
                    bodyPart = (BodyPart)body.get(body.size() - 1);
                    body.add(new BodyPart(bodyPart.getX(), bodyPart.getY()));
                    System.out.println("      Magenta snake length = " + body.size());
                    if (this.isGameOver()) {
                        this.game.setScene(new GameOverScene(this.game));
                    } else {
                        this.placeApple();
                    }
                }

                if (head1.getX() == this.apple.getX() && head1.getY() == this.apple.getY()) {
                    body = this.snake1.getBody();
                    bodyPart = (BodyPart)body.get(body.size() - 1);
                    body.add(new BodyPart(bodyPart.getX(), bodyPart.getY()));
                    System.out.println(" Gray snake length = " + body.size());
                    if (this.isGameOver()) {
                        this.game.setScene(new GameOverScene(this.game));
                    } else {
                        this.placeApple();
                    }
                }

                Iterator var8 = this.plant.getBody().iterator();

                while(var8.hasNext()) {
                    bodyPart = (BodyPart)var8.next();
                    if (bodyPart.getX() == head.getX() && bodyPart.getY() == head.getY() && this.snake.getBody().size() > 1) {
                        this.snake.getBody().remove(1);
                        break;
                    }

                    if (bodyPart.getX() == head1.getX() && bodyPart.getY() == head1.getY() && this.snake1.getBody().size() > 1) {
                        this.snake1.getBody().remove(1);
                        break;
                    }
                }
            }

        }
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, this.game.getScreenSize().width, this.game.getScreenSize().height);
        this.drawSnakes(g);
        this.drawApple(g);
        this.drawBug(g);
        this.drawCloudOfBugs(g);
        this.drawPlant(g);
        this.drawCrossPlant(g);
        this.drawSlugs(g);
    }

    private void drawSnakes(Graphics2D g) {
        g.setColor(Color.MAGENTA);
        Iterator var2 = this.snake.getBody().iterator();

        BodyPart bodyPart;
        while(var2.hasNext()) {
            bodyPart = (BodyPart)var2.next();
            g.fillRect(bodyPart.getX() * 12 - 12, this.game.getScreenSize().height - bodyPart.getY() * 12, 12, 12);
        }

        g.setColor(Color.black);
        g.fillOval(this.snake.head().getX() * 12 - 12, this.game.getScreenSize().height - this.snake.head().getY() * 12, 12, 12);
        g.setColor(Color.LIGHT_GRAY);
        var2 = this.snake1.getBody().iterator();

        while(var2.hasNext()) {
            bodyPart = (BodyPart)var2.next();
            g.fillRect(bodyPart.getX() * 12 - 12, this.game.getScreenSize().height - bodyPart.getY() * 12, 12, 12);
        }

        g.setColor(Color.black);
        g.fillOval(this.snake1.head().getX() * 12 - 12, this.game.getScreenSize().height - this.snake1.head().getY() * 12, 12, 12);
    }

    private void drawApple(Graphics2D g) {
        g.setColor(Color.red);
        g.fillOval(this.apple.getX() * 12 - 12, this.game.getScreenSize().height - this.apple.getY() * 12, 12, 12);
    }

    private void drawBug(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.fillOval(this.bug.getX() * 12 - 12, this.game.getScreenSize().height - this.bug.getY() * 12, 12, 12);
        g.setColor(Color.BLACK);
        g.drawString("@", this.bug.getX() * 12 - 12, this.game.getScreenSize().height - this.bug.getY() * 12 + 12);
    }

    private void drawCloudOfBugs(Graphics2D g) {
        g.setColor(Color.BLUE);

        for(int i = 0; i <= 15; ++i) {
            g.fillOval(((Bug)this.listofbugs.get(i)).getX() * 12 - 12, this.game.getScreenSize().height - ((Bug)this.listofbugs.get(i)).getY() * 12, 12, 12);
            g.setColor(Color.BLACK);
            g.drawString("@", ((Bug)this.listofbugs.get(i)).getX() * 12 - 12, this.game.getScreenSize().height - ((Bug)this.listofbugs.get(i)).getY() * 12 + 12);
        }

    }

    private void drawPlant(Graphics2D g) {
        Color greenish = new Color(124, 252, 0);
        g.setColor(greenish);
        Iterator var3 = this.plant.getBody().iterator();

        while(var3.hasNext()) {
            BodyPart bodyPart = (BodyPart)var3.next();
            g.fillRect(bodyPart.getX() * 12 - 12, this.game.getScreenSize().height - bodyPart.getY() * 12, 12, 12);
        }

    }

    private void drawCrossPlant(Graphics2D g) {
        Color greenish = new Color(34, 139, 34);
        g.setColor(greenish);
        Iterator var3 = this.crossplant.getBody().iterator();

        while(var3.hasNext()) {
            BodyPart bodyPart = (BodyPart)var3.next();
            g.fillRect(bodyPart.getX() * 12 - 12, this.game.getScreenSize().height - bodyPart.getY() * 12, 12, 12);
        }

    }

    private void drawSlugs(Graphics2D g) {
        g.setColor(Color.yellow);
        Iterator var2 = this.slug.getBody().iterator();

        BodyPart bodyPart;
        while(var2.hasNext()) {
            bodyPart = (BodyPart)var2.next();
            g.fillRect(bodyPart.getX() * 12 - 12, this.game.getScreenSize().height - bodyPart.getY() * 12, 12, 12);
        }

        g.setColor(Color.black);
        g.fillOval(this.slug.head().getX() * 12 - 12, this.game.getScreenSize().height - this.slug.head().getY() * 12, 12, 12);
        g.setColor(Color.WHITE);
        var2 = this.slug1.getBody().iterator();

        while(var2.hasNext()) {
            bodyPart = (BodyPart)var2.next();
            g.fillRect(bodyPart.getX() * 12 - 12, this.game.getScreenSize().height - bodyPart.getY() * 12, 12, 12);
        }

        g.setColor(Color.black);
        g.fillOval(this.slug1.head().getX() * 12 - 12, this.game.getScreenSize().height - this.slug1.head().getY() * 12, 12, 12);
    }

    private void placeApple() {
        int x = 1 + (int)(Math.random() * 32.0D);
        int y = 1 + (int)(Math.random() * 32.0D);

        while(!this.isCellEmpty(x, y)) {
            if (x < 32) {
                ++x;
            } else if (y < 32) {
                x = 1;
                ++y;
            } else {
                x = 1;
                y = 1;
            }
        }

        this.apple = new Apple(x, y);
    }

    private void placeBug() {
        int x = 1 + (int)(Math.random() * 32.0D);
        int y = 1 + (int)(Math.random() * 32.0D);

        while(!this.isCellEmpty(x, y) && (x != this.apple.getX() || y != this.apple.getY())) {
            if (x < 32) {
                ++x;
            } else if (y < 32) {
                x = 1;
                ++y;
            } else {
                x = 1;
                y = 1;
            }
        }

        this.bug = new Bug(x, y);
    }

    private boolean isCellEmpty(int x, int y) {
        Iterator var3 = this.snake.getBody().iterator();

        BodyPart bodyPart;
        do {
            if (!var3.hasNext()) {
                var3 = this.snake1.getBody().iterator();

                do {
                    if (!var3.hasNext()) {
                        var3 = this.plant.getBody().iterator();

                        do {
                            if (!var3.hasNext()) {
                                var3 = this.crossplant.getBody().iterator();

                                do {
                                    if (!var3.hasNext()) {
                                        return true;
                                    }

                                    bodyPart = (BodyPart)var3.next();
                                } while(bodyPart.getX() != x || bodyPart.getY() != y);

                                return false;
                            }

                            bodyPart = (BodyPart)var3.next();
                        } while(bodyPart.getX() != x || bodyPart.getY() != y);

                        return false;
                    }

                    bodyPart = (BodyPart)var3.next();
                } while(bodyPart.getX() != x || bodyPart.getY() != y);

                return false;
            }

            bodyPart = (BodyPart)var3.next();
        } while(bodyPart.getX() != x || bodyPart.getY() != y);

        return false;
    }
}

import java.util.ArrayList;
import java.util.List;

public class Plant {
    private List<BodyPart> bodyofplant;
    private int x;
    private int y;
    private int growingstep = 0;

    public Plant() {
        this.x = 10;
        this.y = 10;
        this.bodyofplant = new ArrayList();
        this.bodyofplant.add(new BodyPart(this.x, this.y));
        this.bodyofplant.add(new BodyPart(this.x, this.y + 1));
        this.bodyofplant.add(new BodyPart(this.x, this.y + 2));
        this.bodyofplant.add(new BodyPart(this.x, this.y + 3));
        this.bodyofplant.add(new BodyPart(this.x + 1, this.y + 1));
        this.bodyofplant.add(new BodyPart(this.x + 2, this.y + 1));
        this.bodyofplant.add(new BodyPart(this.x + 2, this.y + 2));
        this.bodyofplant.add(new BodyPart(this.x - 1, this.y + 1));
        this.bodyofplant.add(new BodyPart(this.x - 2, this.y + 1));
        this.bodyofplant.add(new BodyPart(this.x - 2, this.y + 2));
    }

    public Plant(int x, int y) {
        this.x = x;
        this.y = y;
        this.bodyofplant = new ArrayList();
        this.bodyofplant.add(new BodyPart(x, y));
        this.bodyofplant.add(new BodyPart(x, y + 1));
        this.bodyofplant.add(new BodyPart(x, y + 2));
        this.bodyofplant.add(new BodyPart(x, y + 3));
        this.bodyofplant.add(new BodyPart(x + 1, y + 1));
        this.bodyofplant.add(new BodyPart(x + 2, y + 1));
        this.bodyofplant.add(new BodyPart(x + 2, y + 2));
        this.bodyofplant.add(new BodyPart(x - 1, y + 1));
        this.bodyofplant.add(new BodyPart(x - 2, y + 1));
        this.bodyofplant.add(new BodyPart(x - 2, y + 2));
    }

    public List<BodyPart> getBody() {
        return this.bodyofplant;
    }

    public void grow() {
        ++this.growingstep;
        this.bodyofplant.add(new BodyPart(this.x, this.y + 3 + this.growingstep / 50));
        this.bodyofplant.add(new BodyPart(this.x - 2, this.y + 2 + this.growingstep / 60));
        this.bodyofplant.add(new BodyPart(this.x + 2, this.y + 2 + this.growingstep / 70));
    }
}


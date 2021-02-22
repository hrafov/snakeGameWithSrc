import java.util.ArrayList;
import java.util.List;

public class CrossPlant {
    private List<BodyPart> bodyofplant;
    private int x;
    private int y;
    private int growingstep = 0;

    public CrossPlant() {
        this.x = 10;
        this.y = 10;
        this.bodyofplant = new ArrayList();
        this.bodyofplant.add(new BodyPart(this.x, this.y));
        this.bodyofplant.add(new BodyPart(this.x, this.y + 1));
        this.bodyofplant.add(new BodyPart(this.x, this.y - 1));
        this.bodyofplant.add(new BodyPart(this.x + 1, this.y));
        this.bodyofplant.add(new BodyPart(this.x - 1, this.y));
    }

    public CrossPlant(int x, int y) {
        this.x = x;
        this.y = y;
        this.bodyofplant = new ArrayList();
        this.bodyofplant.add(new BodyPart(x, y));
        this.bodyofplant.add(new BodyPart(x, y + 1));
        this.bodyofplant.add(new BodyPart(x, y - 1));
        this.bodyofplant.add(new BodyPart(x + 1, y));
        this.bodyofplant.add(new BodyPart(x - 1, y));
    }

    public List<BodyPart> getBody() {
        return this.bodyofplant;
    }

    public void grow() {
        ++this.growingstep;
        this.bodyofplant.add(new BodyPart(this.x, this.y + 1 + this.growingstep / 150));
        this.bodyofplant.add(new BodyPart(this.x, this.y - 1 - this.growingstep / 250));
        this.bodyofplant.add(new BodyPart(this.x + 1 + this.growingstep / 500, this.y));
        this.bodyofplant.add(new BodyPart(this.x - 1 - this.growingstep / 300, this.y));
    }
}


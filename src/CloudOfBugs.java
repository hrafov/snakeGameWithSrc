import java.util.ArrayList;
import java.util.List;

public class CloudOfBugs {
    private int x;
    private int y;
    private Direction direction;
    private List<Bug> listofbugs;
    private int numberofbugs;

    public CloudOfBugs() {
        this.numberofbugs = 5;
        this.listofbugs = new ArrayList();

        for(int i = 1; i <= this.numberofbugs; ++i) {
            this.listofbugs.add(new Bug(20, 20));
        }

    }

    public CloudOfBugs(int x, int y, int numberofbugs) {
        this.listofbugs = new ArrayList();

        for(int i = 1; i <= numberofbugs; ++i) {
            this.listofbugs.add(new Bug(x, y));
        }

    }
}

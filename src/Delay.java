public class Delay {
    private long delayNanos;
    private long passedNanos;

    public Delay(long delayMillis) {
        this.delayNanos = TimeConverter.millisToNanos(delayMillis);
    }

    public boolean updateAndCheck(long deltaNanos) {
        this.passedNanos += deltaNanos;
        if (this.passedNanos > this.delayNanos) {
            this.passedNanos = 0L;
            return true;
        } else {
            return false;
        }
    }
}

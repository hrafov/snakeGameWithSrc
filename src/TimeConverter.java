public class TimeConverter {
    private static final int NANOSECONDS_IN_MILLISECOND = 1000000;

    public TimeConverter() {
    }

    public static long nanosToMillis(long nanos) {
        return nanos / 1000000L;
    }

    public static long millisToNanos(long millis) {
        return millis * 1000000L;
    }
}

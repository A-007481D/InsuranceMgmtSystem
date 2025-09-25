package util;
import java.util.concurrent.atomic.AtomicLong;

public final class IDGenerator {
    private static final AtomicLong COUNTER = new AtomicLong(1);
    private IDGenerator(){}
    public static long next(){ return COUNTER.getAndIncrement();}
}

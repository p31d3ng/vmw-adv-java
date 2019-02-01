package collecting;

import java.util.OptionalDouble;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;

class Average {
    private double sum = 0;
    private long count = 0;

    public Average() {
    }

    public void include(double d) {
        sum += d;
        count++;
    }

    public void merge(Average other) {
        this.count += other.count;
        this.sum += other.sum;
    }

    public OptionalDouble get() {
        if (count > 0) {
            return OptionalDouble.of(sum / count);
        } else {
            return OptionalDouble.empty();
        }
    }
}

public class Averages {
    public static void main(String[] args) {
        long start = System.nanoTime();
        DoubleStream.generate(() -> ThreadLocalRandom.current().nextDouble(-Math.PI, Math.PI))
//        DoubleStream.iterate(0, x -> ThreadLocalRandom.current().nextDouble(-Math.PI, Math.PI))
                .limit(80_000_000L)
                .parallel()
                .map(x -> Math.sin(x))
//                .forEachOrdered(System.out::println);
                .collect(() -> new Average(), (a, v) -> a.include(v), (af, ai) -> af.merge(ai))
                .get().ifPresent(System.out::println);
//                .forEach(System.out::println);
        long time = System.nanoTime() - start;

        System.out.println("time to process was " + (time/1_000_000_000));
    }
}

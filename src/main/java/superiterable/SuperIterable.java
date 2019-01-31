package superiterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class SuperIterable<E> implements Iterable<E> {
    private Iterable<E> self;

    private SuperIterable() {
    }

    public static <E> SuperIterable<E> of(Iterable<E> target) {
        SuperIterable<E> newObject = new SuperIterable<>();
        newObject.self = target;
        return newObject;
    }

    public SuperIterable<E> filter(Predicate<E> crit) {
        List<E> rv = new ArrayList<>();
        for (E e : self) {
            if (crit.test(e)) rv.add(e);
        }
        return SuperIterable.of(rv);
    }

    public <F> SuperIterable<F> map(Function<E, F> op) {
        List<F> rv = new ArrayList<>();
        for (E e : self) {
            rv.add(op.apply(e));
        }
        return SuperIterable.of(rv);
    }

    public <F> SuperIterable<F> flatMap(Function<E, SuperIterable<F>> op) {
        List<F> rv = new ArrayList<>();

        self.forEach(e -> op.apply(e).forEach(f -> rv.add(f)));
        return SuperIterable.of(rv);
    }
//
//    public <F> SuperIterable<F> flatMap(Function<E, SuperIterable<F>> op) {
//        List<F> rv = new ArrayList<>();
//        for (E e : self) {
//            for (F f : op.apply(e)) {
//                rv.add(f);
//            }
//        }
//        return SuperIterable.of(rv);
//    }

//    public void forEvery(Consumer<E> op) {
//        for (E e : self) op.accept(e);
//    }

    @Override
    public Iterator<E> iterator() {
        return self.iterator();
    }

    @Override
    public String toString() {
        return "SuperIterable{" + self + '}';
    }
}

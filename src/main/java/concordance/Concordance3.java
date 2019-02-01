package concordance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static concordance.Concordance2.getLines;

interface ExFunction<E, F> {
    F apply(E e) throws Throwable;
}

public class Concordance3 {

    public static <E, F> Function<E, Optional<F>> wrap(ExFunction<E, F> op) {
        return e -> {
            try {
                return Optional.of(op.apply(e));
            } catch (Throwable throwable) {
                return Optional.empty();
            }
        };
    }

    public static void main(String[] args) {
        Comparator<Map.Entry<String, Long>> rev = Map.Entry.<String, Long>comparingByValue().reversed();
        Pattern WORD_BOUNDARY = Pattern.compile("\\W+");

        Stream.of("PrideAndPrejudice.txt", "bad.txt", "Emma.txt")
                .map(wrap(n -> Files.lines(Paths.get(n))))
                .peek(o -> {if (!o.isPresent()) System.err.println("That didn't work");})
                .filter(Optional::isPresent)
                .flatMap(Optional::get)
                .flatMap(WORD_BOUNDARY::splitAsStream)
                .filter(s -> s.length() > 0)
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))

                .entrySet().stream()
                .sorted(rev)
                .limit(200)
                .map(e -> String.format("%20s : %5d", e.getKey(), e.getValue()))
                .forEach(System.out::println);

    }
}

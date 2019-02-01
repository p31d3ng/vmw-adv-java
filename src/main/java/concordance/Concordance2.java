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

public class Concordance2 {
    public static Optional<Stream<String>> getLines(String path) {
        try {
//            return Files.lines(Paths.get(path));
            return Optional.of(Files.lines(Paths.get(path)));
        } catch (IOException ioe) {
            return Optional.empty();
//            throw new RuntimeException(ioe);
        }
    }

    public static void main(String[] args) {
        Comparator<Map.Entry<String, Long>> rev = Map.Entry.<String, Long>comparingByValue().reversed();
        Pattern WORD_BOUNDARY = Pattern.compile("\\W+");

        Stream.of("PrideAndPrejudice.txt", "bad.txt", "Emma.txt")
//                .flatMap(n -> Files.lines(Paths.get(n)))
//                .flatMap(n -> getLines(n))
                .map(n -> getLines(n))
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

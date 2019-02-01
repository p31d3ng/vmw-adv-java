package concordance;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Concordance {
    public static void main(String[] args) {
//        Stream<String> ss = null;
//        try {
//            ss = Files.lines(Paths.get("PrideAndPrejudice.txt"));
//        } catch (IOException ioe) {
//            System.err.println("oops that broke");
//        } finally {
//            if (ss != null) {
//                try {
//                    ss.close();
//                } catch (IOException ioe) {
//
//                }
//            }

//        Comparator<Map.Entry<String, Long>> comp = Map.Entry.comparingByValue();
//        Comparator<Map.Entry<String, Long>> rev = comp.reversed();

        Comparator<Map.Entry<String, Long>> rev = Map.Entry.<String, Long>comparingByValue().reversed();
        
        Pattern WORD_BOUNDARY = Pattern.compile("\\W+");
        try (Stream<String> ss = Files.lines(Paths.get("PrideAndPrejudice.txt"));) {
            Map<String, Long> m1 = ss
//                    .flatMap(s -> WORD_BOUNDARY.splitAsStream(s))
                    .flatMap(WORD_BOUNDARY::splitAsStream)
                    .filter(s -> s.length() > 0)
                    .map(String::toLowerCase)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    ;
            m1.entrySet()
                    .stream()
                    .sorted(rev)
//                    .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                    .limit(200)
                    .map(e -> String.format("%20s : %5d", e.getKey(), e.getValue()))
                    .forEach(System.out::println);
        } catch (IOException fnfe) {
            System.err.println("It broke: " + fnfe.getMessage());
        }

    }
}

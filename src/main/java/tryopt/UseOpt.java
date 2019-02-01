package tryopt;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UseOpt {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("Fred", "Jones");

        String n = "Freddy";
        try {
            String lastname = map.get(n);
            String lastNameShout = lastname.toUpperCase();
            String greeting = "Mx. " + lastNameShout;
            System.out.println(greeting);
        } catch (Throwable t) {
            System.out.println("oops!!!");
        }
        System.out.println("------------------");
        Optional.of(map)
                .map(m -> m.get(n))
                .map(String::toUpperCase)
                .map(s -> "Mx. " + s)
                .ifPresent(System.out::println);


    }
}

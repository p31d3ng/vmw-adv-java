package generic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UseList {
    public static void breakList(List l) {
        l.add(0, LocalDate.now());
    }
    public static void main(String[] args) {
        List<String> ls = new ArrayList<>(Arrays.asList("Fred"/*, LocalDate.now()*/));
        ls.add("Fred");
        ls.add("Jim");
//        ls.add(LocalDate.now());

//        breakList(ls);
        System.out.println("List contains: " + ls);

        String n1 = ls.get(0);
    }
}

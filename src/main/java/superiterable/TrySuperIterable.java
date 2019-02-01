package superiterable;

import students.Student;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TrySuperIterable {
    public static String getLetterGrade(Student s) {
        double gpa = s.getGpa();
        if (gpa > 3.7) return "A";
        if (gpa > 3.3) return "B";
        if (gpa > 2.9) return "C";
        if (gpa > 2.5) return "D";
        return "F";
    }
    public static void main(String[] args) {
        SuperIterable<String> sis = SuperIterable.of(Arrays.asList("Fred", "Jim", "Sheila"));

        System.out.println(sis);
        System.out.println("---------------------------");
//        for (String s : sis) {
//            System.out.println("> " + s);
//        }
//        sis.forEvery(s -> System.out.println(s));
        sis.forEach(System.out::println);
        System.out.println("---------------------------");
        sis
                .filter(s -> s.length() > 3)
//                .map(s -> s.toUpperCase())
                .map(String::toUpperCase)
                .forEach(System.out::println);
        System.out.println("---------------------------");
        sis
                .filter(s -> s.length() > 3)
                .map(String::length)
                .forEach(System.out::println);

        List<Student> studentList = Arrays.asList(
                Student.ofNameGpaCourses("Fred", 3.2, "Math", "Physics"),
                Student.ofNameGpaCourses("Jim", 2.2, "Art"),
                Student.ofNameGpaCourses("Jim1", 2.2, "Art", "History", "Politics", "Religion"),
                Student.ofNameGpaCourses("Jim2", 1 ),
                Student.ofNameGpaCourses("Jim3", 3.9, "Art"),
                Student.ofNameGpaCourses("Sheila", 3.9, "Math", "Physics", "Quantum Mechanics", "Astrophysics")
        );
        SuperIterable<Student> roster = SuperIterable.of(studentList);
        System.out.println("---------------------------");
        roster
                .filter(s -> s.getGpa() > 3)
                .map(s -> s.getName() + " scored " + s.getGpa())
//                .forEvery(System.out::println);
                .forEach(System.out::println);
        System.out.println("---------------------------");
        roster
                .filter(s -> s.getGpa() > 3)
                .flatMap(s -> SuperIterable.of(s.getCourses()))
                .forEach(System.out::println);

        System.out.println("---------------------------");
        studentList.stream()
                .filter(s -> s.getGpa() > 3)
                .flatMap(s -> s.getCourses().stream())
                .forEach(System.out::println);

        System.out.println("---------------------------");
        studentList.stream()
                .forEach(System.out::println);

        System.out.println("---------------------------");
        studentList.stream()
                .filter(s -> s.getGpa() < 3)
                .forEach(System.out::println);

        System.out.println("---------------------------");
        studentList.stream()
                .filter(s -> s.getGpa() < 3)
                .map(s -> s.getName() + " only scored " + s.getGpa())
                .forEach(System.out::println);

        System.out.println("---------------------------");
        studentList.stream()
                .filter(s -> s.getGpa() > 3)
                .flatMap(s -> s.getCourses().stream())
                .forEach(System.out::println);

        System.out.println("---------------------------");
        studentList.stream()
                .filter(s -> s.getGpa() > 3)
                .flatMap(s -> s.getCourses().stream())
                .sorted()
                .forEach(System.out::println);

        System.out.println("---------------------------");
        studentList.stream()
                .filter(s -> s.getGpa() > 3)
                .flatMap(s -> s.getCourses().stream())
                .sorted()
                .distinct()
                .forEach(System.out::println);


        System.out.println("---------------------------");
        boolean allNamesAreLong = studentList.stream()
                .peek(s-> System.out.println("> " + s))
//                .forEach(System.out::println)
                .allMatch(s -> s.getName().length() > 2)
        ;
        System.out.println("All names are long? " + allNamesAreLong);

        System.out.println("---------------------------");
        IntStream.iterate(1, x -> x + 2)
                .limit(100)
                .forEach(System.out::println);

        System.out.println("---------------------------");
        studentList.stream()
                .filter(s -> s.getGpa() > 3)
                .flatMap(s -> s.getCourses().stream().map(c -> s.getName() + " takes " + c))
                .forEach(System.out::println);

        Map<String, Long> theMap = studentList.stream()
                .collect(Collectors.groupingBy(TrySuperIterable::getLetterGrade, Collectors.counting()));
        System.out.println("Results:\n" + theMap);
    }


}

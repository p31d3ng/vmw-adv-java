package students;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class School {

    public static Predicate<Student> and(Predicate<Student> ... crits) {
        return s -> {
            for (Predicate<Student> ps : crits) {
                if (!ps.test(s)) return false;
            }
            return true;
        };
    }

    public static Predicate<Student> and(Predicate<Student> c1, Predicate<Student> c2) {
        return s -> c1.test(s) && c2.test(s);
    }

    public static Predicate<Student> or(Predicate<Student> c1, Predicate<Student> c2) {
        return s -> c1.test(s) || c2.test(s);
    }

    public static Predicate<Student> negate(Predicate<Student> crit) {
        return s -> !crit.test(s);
    }

    public static <E> void showStudents(Iterable<E> ls) {
        for (E s : ls) {
            System.out.println("> " + s);
        }
        System.out.println("----------------------------------");
    }

//    public static List<Student> getStudentByCriterion(Iterable<Student> ls, Predicate<Student> crit) {
//        List<Student> results = new ArrayList<>();
//        for (Student s : ls) {
//            if (crit.test(s)) {
//                results.add(s);
//            }
//        }
//        return results;
//    }
    public static <E> List<E> getStudentByCriterion(Iterable<E> ls, Predicate<E> crit) {
        List<E> results = new ArrayList<>();
        for (E s : ls) {
            if (crit.test(s)) {
                results.add(s);
            }
        }
        return results;
    }

    public static void main(String[] args) {
        List<Student> roster = Arrays.asList(
                Student.ofNameGpaCourses("Fred", 3.2, "Math", "Physics"),
                Student.ofNameGpaCourses("Jim", 2.2, "Art"),
                Student.ofNameGpaCourses("Jim1", 2.2, "Art", "History", "Politics", "Religion"),
                Student.ofNameGpaCourses("Jim2", 1 ),
                Student.ofNameGpaCourses("Jim3", 3.9, "Art"),
                Student.ofNameGpaCourses("Sheila", 3.9, "Math", "Physics", "Quantum Mechanics", "Astrophysics")
        );

        System.out.println("Smart:");
        showStudents(getStudentByCriterion(roster, Student.getSmartCriterion(3)));

        System.out.println("negate-Smart:");
        showStudents(getStudentByCriterion(roster, negate(Student.getSmartCriterion(3))));

        System.out.println("Very Smart:");
        showStudents(getStudentByCriterion(roster, Student.getSmartCriterion(3.5)));

        System.out.println("negate-Very Smart:");
        showStudents(getStudentByCriterion(roster, negate(Student.getSmartCriterion(3.5))));

        System.out.println("Enthusiastic:");
        showStudents(getStudentByCriterion(roster, Student.getEnthusiasticCriterion()));
        System.out.println("Not Smart:");
        showStudents(getStudentByCriterion(roster, x -> x.getGpa() < 3.5));
        System.out.println("Smart but not enthusiastic:");
        showStudents(getStudentByCriterion(roster, x -> x.getGpa() > 3 && x.getCourses().size() < 4));

        Predicate<Student> nameBeginsWithJim = s -> s.getName().startsWith("Jim");
        System.out.println("name begins with Jim");
        showStudents(getStudentByCriterion(roster, nameBeginsWithJim));

        Predicate<Student> isEnthusiastic = s -> s.getCourses().size() > 3;
        System.out.println("Very enthusiastic");
        showStudents(getStudentByCriterion(roster, isEnthusiastic));

        System.out.println("not very enthusiastic and is called Jim");
//        showStudents(getStudentByCriterion(roster, and(negate(isEnthusiastic),nameBeginsWithJim)));
        showStudents(getStudentByCriterion(roster, isEnthusiastic.negate().and(nameBeginsWithJim)));

        List<String> names = Arrays.asList("Fred", "Jim", "Sheila");
        showStudents(getStudentByCriterion(names, s -> s.length() > 3));


    }
}

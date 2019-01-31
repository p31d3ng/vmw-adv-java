package students;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class School {

    public static Predicate<Student> negate(Predicate<Student> crit) {
        return s -> !crit.test(s);
    }

    public static void showStudents(Iterable<Student> ls) {
        for (Student s : ls) {
            System.out.println("> " + s);
        }
        System.out.println("----------------------------------");
    }

    public static List<Student> getStudentByCriterion(Iterable<Student> ls, Predicate<Student> crit) {
        List<Student> results = new ArrayList<>();
        for (Student s : ls) {
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
    }
}

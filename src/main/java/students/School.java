package students;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@FunctionalInterface
interface StudentCriterion {
//    void bad();
    boolean test(Student s);
}

interface Silly {
    boolean oddity(Student s);
}
//
//class SmartCriterion implements StudentCriterion {
//    public boolean test(Student s) {
//        return s.getGpa() > 3.0;
//    }
//}
//
//class EnthusiasticCriterion implements StudentCriterion {
//    public boolean test(Student s) {
//        return s.getCourses().size() > 3;
//    }
//}

public class School {
    public static void showStudents(Iterable<Student> ls) {
        for (Student s : ls) {
            System.out.println("> " + s);
        }
        System.out.println("----------------------------------");
    }

//    public static List<Student> getSmartStudent(Iterable<Student> ls, double threshold) {
//        List<Student> results = new ArrayList<>();
//        for (Student s : ls) {
//            if (s.getGpa() > threshold) {
//                results.add(s);
//            }
//        }
//        return results;
//    }
//
//    public static List<Student> getEnthusiasticStudent(Iterable<Student> ls, int threshold) {
//        List<Student> results = new ArrayList<>();
//        for (Student s : ls) {
//            if (s.getCourses().size() > threshold) {
//                results.add(s);
//            }
//        }
//        return results;
//    }
//
    public static List<Student> getStudentByCriterion(Iterable<Student> ls, StudentCriterion crit) {
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
//        showStudents(getSmartStudent(roster, 3));
//        showStudents(getEnthusiasticStudent(roster, 3));
        System.out.println("Smart:");
        showStudents(getStudentByCriterion(roster, Student.getSmartCriterion()));
        System.out.println("Enthusiastic:");
        showStudents(getStudentByCriterion(roster, Student.getEnthusiasticCriterion()));

        StudentCriterion obj;
        obj = s -> s.getCourses().size() > 3;

        boolean isSmart = ((Silly)(s -> s.getCourses().size() > 3)).oddity(Student.ofNameGpaCourses("Alan", 2.2));
    }
}

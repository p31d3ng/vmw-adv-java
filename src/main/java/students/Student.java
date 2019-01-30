package students;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Student {
    private String name;
    private double gpa;
    private List<String> courses;

    private Student(){}


    /*
        Student.ofNameGpaCourses("Fred", 3.2, "Math", "Physics");
        // or
        String [] courses = new String[] { "Math", "Physics"} ;
        Student.ofNameGpaCourses("Fred", 3.2, courses);
     */
    public static Student ofNameGpaCourses(String name, double gpa, String ... courses) {
        if (gpa < 0 || gpa > 5 || name == null) throw new IllegalArgumentException("Bad stuff for making students!");
        Student self = new Student();
        self.name = name;
        self.gpa = gpa;
        self.courses = Arrays.asList(courses);
        return self;
    }

    public String getName() {
        return name;
    }

    public double getGpa() {
        return gpa;
    }

    public List<String> getCourses() {
//        return Collections.unmodifiableList(courses);
        return courses;
    }

    public Student addCourse(String course) {
        List<String> c2 = new ArrayList(courses);
        c2.add(course);
        return Student.ofNameGpaCourses(this.name, this.gpa, c2.toArray(new String[]{}));
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", gpa=" + gpa +
                ", courses=" + courses +
                '}';
    }

    public static StudentCriterion getSmartCriterion() {
        return smartCriterion;
    }

    private static StudentCriterion smartCriterion = new SmartCriterion();

    private static class SmartCriterion implements StudentCriterion {
        public boolean test(Student s) {
            return s.getGpa() > 3.0;
        }
    }

    public static StudentCriterion getEnthusiasticCriterion() {
        return enthusiasticCriterion;
    }

//    private static StudentCriterion enthusiasticCriterion = new EnthusiasticCriterion();
//
//    private static class EnthusiasticCriterion implements StudentCriterion {
//        public boolean test(Student s) {
//            return s.getCourses().size() > 3;
//        }
//    }

//    private static StudentCriterion enthusiasticCriterion = new /*EnthusiasticCriterion();
//
//    private static class EnthusiasticCriterion implements */ StudentCriterion() {
//        public boolean test(Student s) {
//            return s.getCourses().size() > 3;
//        }
//    };

//    private static StudentCriterion enthusiasticCriterion = new StudentCriterion() {
//        public boolean test(Student s) {
//            return s.getCourses().size() > 3;
//        }
//    };

//    private static StudentCriterion enthusiasticCriterion = /*new StudentCriterion() {*/
//        /*public boolean test*/(Student s) -> {
//            return s.getCourses().size() > 3;
//        }
//    /*}*/;

    private static StudentCriterion enthusiasticCriterion = (Student s) -> {
            return s.getCourses().size() > 3;
        };

}

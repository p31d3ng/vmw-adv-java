package students;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class Student {
    private String name;
    private double gpa;
    private List<String> courses;

    private Student(){}

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

    public static Predicate<Student> getSmartCriterion(double threshold)
    {
        final double t1 = threshold+0.5;
        return s -> s.getGpa() > t1;
    }

//    public static Predicate<Student> getSmartCriterion() {
//        return smartCriterion;
//    }
//
//    private static Predicate<Student> smartCriterion = s -> s.getGpa() > 3;

    public static Predicate<Student> getEnthusiasticCriterion() {
        return enthusiasticCriterion;
    }

    private static Predicate<Student> enthusiasticCriterion = s -> s.getCourses().size() > 3 ;

}

package annots;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class TestRunner {
    public static void main(String[] args) throws Throwable {
//        System.setSecurityManager(new SecurityManager());
        Properties toLoad = new Properties();
        toLoad.load(Files.newBufferedReader(Paths.get("toload.properties")));
        String classToLoad = toLoad.getProperty("1");
        System.out.println("Loading class " + classToLoad);

        Class<?> loadedClass = Class.forName(classToLoad);

        Object o = loadedClass.getDeclaredConstructor().newInstance();
//        Object o = new TestThis();

        Class clazz = o.getClass();
        Method[] methods = clazz.getDeclaredMethods();
//        Method[] methods = clazz.getMethods();
        for (Method m : methods) {
            System.out.println("Found method: " + m);
            RunMe annot = m.getAnnotation(RunMe.class);
            if (annot != null) {
                System.out.println("***** RunMe!!!!, text is " + annot.value());
                m.setAccessible(true);
                m.invoke(o);
            }
        }
    }
}

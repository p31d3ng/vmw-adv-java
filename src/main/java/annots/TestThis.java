package annots;

//@RunMe
public class TestThis {
//    @RunMe
    private String name;

    @RunMe("First")
    public void doStuff() {
        System.out.println("My name is " + name);
    }
    public void doOtherStuff() {
        System.out.println("Other: My name is " + name);
    }
    @RunMe(value="Second", count=3)
    private void doSillyStuff() {
        System.out.println("Silly: My name is " + name);
    }
}

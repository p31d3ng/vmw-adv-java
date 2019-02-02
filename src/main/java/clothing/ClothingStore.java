package clothing;

interface Sized {
    int getSize();
}

class Pair<E> {
    E left;
    E right;

    public Pair(E left, E right) {
        this.left = left;
        this.right = right;
    }

    public E getLeft() {
        return left;
    }

    public E getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}

class ClothingPair<E extends Sized & Colored/*, Banana*/> extends Pair<E> {
//    Banana s = "Hello";
//    java.lang.String s1 = "Goodbye";

    public ClothingPair(E left, E right) {
        super(left, right);
    }

    public boolean matches() {
        return left.getSize() == right.getSize()
                && left.getColor().equals(right.getColor());
    }

}

public class ClothingStore {
    public static void main(String[] args) {
        Pair<String> ps = new Pair<>("Albert", "Jones");
        ClothingPair<Glove> p = new ClothingPair<>(new Glove(8, "Red"), new Glove(7, "Red"));
        System.out.println("Gloves match: " + p.matches());

        ClothingPair<Glove> p1 = new ClothingPair<>(new Glove(7, "Reddish-brown"), new Glove(7, "Red"));
        System.out.println("Gloves match: " + p1.matches());
    }
}

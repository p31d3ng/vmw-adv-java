package clothing;

public class Glove implements Sized, Colored {
    private int size;
    private String color;

    public int getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public Glove(int size, String color) {

        this.size = size;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Glove{" +
                "size=" + size +
                ", color='" + color + '\'' +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        Point p1 = new Point(3, 4);
        System.out.println(p1.numPoints());
        Point p2 = p1.shift(4, 5);
        System.out.println(p2.numPoints());
    }
}

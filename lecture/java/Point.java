public class Point {
    /* x and y are private fields                                       *
     * these private fields cannot be accessed from outside the class   *
     * in C, C++, or Python; there is always a way to get at that stuff */
    private int x;
    private int y;

    /* Here we have a class field that applies to EVERY instance of this class *
     * it's a fancy way to say global variable                                 */
    private static int num;

    /* this is a constructor */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.num++;
    }


    /* Class Methods (with a static annotation)
    static Point shift(Point p, int dx, int dy) {
        return new Point(p.x + dx, p.y + dy);
    }

    static int length(Point p) {
        return (int) Math.sqrt(p.x * p.x + p.y * p.y);
    } */

    /* Instance Methods */
    public Point shift(int dx, int dy) {
        return new Point(this.x + dx, this.y + dy);
    }

    public int length() {
        /* but we can drop this. if the variable name is not being shadowed */
        return (int) Math.sqrt(x * x + y * y);
    }

    public static int numPoints() { return num; }
}

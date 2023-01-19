package final_prep.s21_p2;

public class GraphBuilderHelper extends ListGraph implements Graph {

    int state = 0; // 0 = needs .from(), 1 = needs .to(), 2 = needs .edge()
    String s1 = null;
    String s2 = null;

    public GraphBuilderEdge edge() {
        if (state != 2) { throw new UnsupportedOperationException(); }

        state = 0;
        s1 = null;
        s2 = null;
    }

    public GraphBuilderHelper from(String s) {
        if (state != 0) { throw new UnsupportedOperationException(); }

        s1 = s;
        state = 1;
    }

    public GraphBuilderHelper to(String s) {
        if (state != 1) { throw new UnsupportedOperationException(); }

        s2 = s;
        state = 2;

        this.addNode(s1);
        this.addNode(s2);
        this.addEdge(s1, s2);
    }

}

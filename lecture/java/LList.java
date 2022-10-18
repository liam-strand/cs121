public class LList {

    /* this is an inner class, and the private members of this private inner *
     * class are visible to the outer class.                                 */
    private class Cell {
        private int elt;
        private Cell next;
        private Cell(int elt, Cell next) {
            this.elt = elt;
            this.next = next;
        }
    }

    private Cell head; /* the default initialization is null */

    public void insert(int x) {
        Cell c = new Cell(x, head);
        head = c;
    }
    public void printList() {
        for (Cell c = head; c != null; c = c.next) {
            System.out.println(c.elt);
        }
    }

    public int get(int pos) {
        Cell c = head;
        for (int i = 0; i < pos; i++) {
            if (c == null) { throw new IndexOutOfBoundsException(pos); }
            c = c.next;
        }
        return c.elt;
    }
}

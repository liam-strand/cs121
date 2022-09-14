public class Main {
    public static void main(String[] args) {
        LList ll = new LList();

        ll.insert(5);
        ll.insert(4);
        ll.insert(3);
        ll.insert(2);
        ll.insert(1);

        ll.printList();

        System.out.println(ll.get(2));
    }
}

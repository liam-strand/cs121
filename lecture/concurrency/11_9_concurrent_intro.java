
class Tick extends Thread {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("tick " + i);
            try { 
                sleep(1000);
            } catch (InterruptedException e) {}
        } 
    }
}

class Tock extends Thread {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("tock " + i);
            try { 
                sleep(1000);
            } catch (InterruptedException e) {}
        } 
    }
}

class Main {
    public static void main(String[] args) {
        Thread t1 = new Tick(); // Doesn't start the thread's execution
        Thread t2 = new Tock();
        t1.start();             // Now we actually run the thread (t's .run() method)
        t2.start();
        try {
            t1.join();          // Wait until t is done (t's .run() returns)
            t2.join();         // do this instead of busy-waiting, busy-waiting
                               // is evil.
                               // .join() has basically no overhead, which is awesome
        } catch (InterruptedException e) {}
        System.out.println("Done!");
    }
}

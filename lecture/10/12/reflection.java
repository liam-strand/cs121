import java.io.*;
import java.lang.reflect.*;

class Main {
    public static void main(String[] args) throws Exception {
        /* we want something like System.out.println("Hello, world!"); */
        
        Field f = System.class.getField("out");
        PrintStream out = (PrintStream) f.get(null);
        Method m = PrintStream.class.getMethod("println", String.class);
        m.invoke(out, "Hello, world!");
    }
}

---
title: Java Endgame
date: September 26, 2022
---

# Java

## Exceptions Continued

Consider the example:

```Java
class Foo {
    static void g() {
        throw new ArrayIndexOutOfBoundsException();
    }
    static void f() {
        try {
            g();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Got an exception!");
        }
    }
    public static void main(String[] args) {
        f();
    }
}
```

We use `try` and `catch` to handle errors. The errors are automatically propigated down the stack to the appropriate error handling code. We do not need to include custom control flow constructs to handle this.

Remember, we specify what kinds of exceptions we `catch`. The type is important!

We can also write our own exceptions:

```Java
class MyException extends RuntimeException {
    String msg;
    MyException(String msg) {
        this.msg = msg;
    }
    public String toString() {
        return "MyException " + msg;
    }
}
```

We can `catch` more than one type of exception, and we use `finally` when we absolutely must execute some code to restore invariants or close files or whatever.

```Java
class Bar {
    static void h() {
        throw new MyException();
    }
    static void i() {
        try {
            h();
        } catch (MyException e) {
            System.out.println("Got a custom exception");
        } catch (RuntimeException e) {
            System.out.println("Got another exception");
        } finally {
            System.out.println("This will always print");
        }
    }
}
```

There are two types of exceptions:

- Checked exceptions, that extend `Exception`
- Unchecked exceptions, that extend `RuntimeException`

Checked exceptions **_must_** be caught. A function throwing a checked exception is a part of its signature and will break the compiler.

```Java
class OtherException extends Exception { ... }

class Baz {
    static void j() throws OtherException {
        throw new OtherException();
    }
}
```

It is usually a good idea to define custom exceptions to go with an interface, since the client of the interface shouldn't need to worry about exceptions that are thrown by the guts of the implementation.

Also, exceptions are `Object`s, so we can store them however we want and use them however we want to help us debug.

## Overloading

"Ad hoc polymorphism"

```
3 + 4
3.14 + 6.28
```

We use the `+` symbol in different contexts, but the compiler understands that we use different instructions for floating point vs integer addition.

We can do this too!

```Java
class Parent {
    void m(int x) { System.out.println("int x"); }
    void m(Object y) { System.out.println("Object y"); }
}

class Fooa {
    public static void main(String[] args) {
        Parent p = new Parent();
        p.m(42);
        p.m(new Object());
        p.m("forty two");
    }
}
```

`java Fooa` prints:

```
int x
Object y
Object y
```

The overloading system calls the appropriate method for the appropriate argument type. So we can actually pass any one thing into `.m()` because one of its overloads takes an `Object` as its argument.

This gets complicated with inheritance:

```Java
class Child extends Parent {
    void m(String s) { System.out.println("String s"); }
}
```

Now if we say:

```Java
class Bara {
    public static void main(String[] args) {
        Child c = new Child();
        c.m(42);
        c.m(new Object());
        c.m("forty two");
        c.m(Integer.valueOf(42));
        c.m((Object) "forty two"); /* upcast to superclass */
    }
}
```

`java Bara` prints:

```
int x
Object y
String s
Object y
Object y
```

The overloading system looks at the **_compile-time_** type of the arguments. The dynamic dispatch system knows to look in the superclass if there's no method that takes the appropriate type.

It's a really weird combination of compile-time and run-time analysis.

If there's ambiguity, the Java compiler will get angry. For example:

```Java
class A {
    void m(Object o, String s) { System.out.println("1"); }
    void m(String s, Object o) { System.out.println("2"); }
}
class Baza {
    public static void main(String[] args) {
        (new A).m("42", "43");
    }
}
```

Which of the methods of `A` should we call? It's ambiguous, so this will not compile.

# Design Patterns

iterators. yay. i already know about these.

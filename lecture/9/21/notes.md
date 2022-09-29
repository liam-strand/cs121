---
title: Even More Java
date: September 21, 2022
---

# Parametric Types

We can make containers and data structures generic! (the syntax is very similar to Rust)

Example:

```Java
public class LL<F> implements L<F> {
    private class Cell<E> {
        private E elt;
        private Cell<E> next;
        private Cell(E elt, Cell<E> next) {
            this.elt = elt;
            this.next = next;
        }
    }
}
```

Yay it's easy and straightforward.

# Inheritance 

Almost never a good idea, but if you're planning stuff out and are really thoughtful, then it can be quite helpful.

```Java
class A {
    public void m() { System.out.println("A.m"); }
}

class B extends A {
    /* B is a subclass of A,
     * A is a superclass of B,
     * B inherits from A.
     */
    public void n() { System.out.println("B.n"); }
}

class Main {
    public static void main(String[] args) {
        A a  = new A();
        a.m();
        B b = new B();
        b.m();
        b.n();
        A c = new B(); /* this is a downcast and is valid */
        c.m();
        /* c.n(); this is a bug */
    }
}
```

It is terrible. Let's never do this again.

## Overriding

If we override `.equals()` we should probably also override `.hashCode()` so that everything we define to be equivalent hashes the same way. This is very important if we plan to put these objects in a `HashTable`.

# Delegation

Instead of inheriting code from a superclass, just seperate the damn things out and include an instance of the superclass in the class.

It's better.

# Exceptions

We use exceptions when things go wrong.

We try to write resilient code, but we usually need to verify some preconditions before we can perform some computation. There isn't always a nice, reasonable return value for every input.

We can set aside some special invalid value that we return when something goes wrong (a sentinal valie), but there are problems:
* again, there isn't always a nice thing to returnand 
* we might need more than one sential
* the sentinal might actually be a valid value
* the error-handling code gets splattered across the codebase because you always need to check return values

Exceptions are cleaner. They just break normal control flow and go into an **exception**al control flow system.

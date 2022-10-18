---
title: Design Patterns
date: September 28, 2022
geometry: margin=1in
output: pdf_document
---

## Iterator

### External Iterator

A prerequsite is that we need some sort of collection to iterate over.

The iterator starts out by pointing to the "beginning" of the collection. Then, whenever we call `next()`, we need to increment that "pointer" to point to the next thing. `hasNext()` will check to see if we have a next thing to iterate to.

This iterator is an object and can be passed around and works the way you would expect.

With these two methods, we can get the nice `for` loop that Java provides (C++ does the same trick I think)

For example, if we are in a linked list class `LL`:

```Java
class LLIterator<H> implements java.util.Iterator<H> {
    Cell<H> cur;
    LLIterator(Cell<H> cur) { this.cur = cur; }
    public boolean hasNext() {
        return cur != null;
    }
    public H next() {
        H tmp = cur.elt; cur = cur.next; return tmp;
    }
}
public LLIterator<F> iterator() {
    return new LLIterator<F>(head);
}
```

### Internal Iterator

This iterator is not passed around, but instead we embed a method in the object that can take some sort of operation as an argument, then applies that operation to every element in the collection.

For example, if we are in a linked list class `LL`:

```Java
public void iterate(Processor<F> p) {
    for (Cell<F> cur = head; cur != null; cur = cur.next) {
        p.process(cur.elt);
    }
}
```

Since Java isn't a functional language, we need to pass the operation inside of some class. We defined a utility interface called `Processor` to handle this.

We can write a class that implements the `Processer` interface to use the internal iterator, but then we need to put that one function in that class in another file and it's super ugly. Instead, we can use a cool inline `interface` instantiation syntax (we call this an anonymous class):

```Java
LL<String> a = new LL<String>();

a.iterate(new Processor<String>() {
    public void process(String s) {
        System.out.println(s);
    }
})
```

The benefit of this type of iterator is that we don't need to allocate extra space on the heap for the iterator object, and that we don't need to do as many method calls.

# Creational Patterns

## Singleton

A singleton class is one that should only ever have one instance. For example...

- Logger
- Database
- ThreadPool
- Config

Unfortunately in Java we can just call `new` to create an instance. The singleton pattern tries to prevent this from happening.

```Java
class Logger {
    private static Logger theLogger;
    private Logger() { ... }

    public static logger getLogger() {
        if (theLogger == null) {
            theLogger = new Logger();
        }
        return theLogger;
    }
}
```

Here we actually enforce that there is only ever one logger, because the true constructor is private.

We can actually do a bit better:

```Java
class Logger {
    private final static Logger theLogger = new Logger();
    private Logger() { ... }

    public static logger getLogger() {
        return theLogger;
    }
}
```

The `final` keyword means that this member can be initialized but never changed.

This type of design pattern is helpful, but can be dangerous because what if we want to add another instance later on? We can't :(

## Enumeration (Enum)

When we want to select one thing from a group of things as a sort of identifier.

We don't want users to create new elements of the enumeration, and there should be no new allocations of these kinds of things.

```Java
public class Suit {
    private final String name;
    private Suit(String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }

    public static final Suit CLUBS    = new Suit("clubs");
    public static final Suit DIAMONDS = new Suit("diamonds");
    public static final Suit HEARTS   = new Suit("hearts");
    public static final Suit SPADES   = new Suit("spades");
}
```

That is nice, but it is pretty verbose. Luckily, this is a pretty popular pattern, so it is built-in to Java!

```Java
public enum Suit { CLUBS, DIAMONDS, HEARTS, SPADES }
```

I love you Java

## Factory

A factory method is a method that you call to create an object when the constructor is private. A factory object is an object with a factory method.

For example, the enum and the singleton are both examples of a factory!

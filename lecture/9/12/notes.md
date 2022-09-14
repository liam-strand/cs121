---
title: September 12th Lecture Notes
date: September 12, 2020
---

# September 12: Java

## What it do be
* General-purpose, object-oriented programming language
* Released in 1996
* Key goal: Write once, run anywhere (portability!)
* Running Java in the browser could also be cool
  * It was a good idea, but it was terrible
  * It got replaced by JavaScript (not Java)
* Java is not C or C++
  * It looks like it, but it is very different
  * No `*` dereferencing of pointers
  * The memory model is very different (no "giant array of bytes")
  * Memory can be allocated but the garbage collector takes care of deallocations

## Writing Java
### Barebones Java Class
```Java
public class A {
    public static void main(String[] args) {
        System.out.println("Hello, world.")
    }
}
```

### Namespacing is Different

#### In C:
```C
int add1(int x) {
    int y = x;
    return y + 1;
}

int first(char *y) {
    char x = y[0];
    return x;
}
```

Both `add1` and `first` are in the "extern" scope. This is bad because when you start `#include`ing stuff things get really crowded.

#### In Java we use classes as namespaces
```Java
class Arith {
    public static int add1(int x) { return x + 1; }
    public static int add2(int x) { return x + 2; }
}
```

Methods (functions) must be packaged into classes. Only classes are allowed in the global namespace. Classes are the module system.

### Methods:

In Java, it is acceptable to use methods before they are defined

```Java
class Arith {
    public static int add2(int x) { return add1(add1(x)); }
    public static int add1(int x) { return x + 1; }
}
```

### Primative types:

Type      | Size
---       | ---
`byte`    | 8 bits
`short`   | 16 bits
`int`     | 32 bits
`long`    | 64 bits
`float`   | 32 bit IEEE floating point
`double`  | 64 bit as above
`char`    | 8 bits
`boolean` | 1 bit 

## ADTs in Java:
* See [point.c](./point.c)
  * As long as people are given a consistent interface, they can deal with it regardless of the implementation.
  * We can change the implementation without breaking users' code.
  * The client doesn't need to understand the implementation
* See [Point.java](./Point.java) and its caller [Main.java](./Main.java)
  * We can make constructors and stuff for the types
  * We can write class (static) methods that are a part of the type, or instance methods that are a part of an instance of a type
  * Java uses **dynamic dispatch** for instance methods. This means that:
    1. Java looks up the objects *run-time* type
    2. Find the method in that class
    3. Call that method

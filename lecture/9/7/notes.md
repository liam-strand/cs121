---
title: September 7th Lecture Notes
date: September 7, 2022
---

# September 7: Intro

## Thinking about stuff

### Why take this class?
* Employment :)
* Learning Java
* Strategies for working with large codebases
* Best practices for documentation

### What is this class?
* Learn to program in Java
* Use Java to explore software engineering techniques

### What makes code good?
* Correctness
  * All code has bugs
  * It is impossible to eliminate all bugs
  * Fixing bugs might not be worth the cost
  * Fixing bugs can introduce more bugs
* Robust
* Understandable
* Reusable
* Efficient
* Testable
* Scalable
* Secure

### Topics
* Java Programming
* Abstract data types, modularity, information hiding
* Design patterns
* Software architecture
* Testing
* Refactoring
* Concurrency
* Debugging

### Projects
1. Java
2. Design Patterns
3. Testing
4. Java on Rails
5. Concurrency

## Java

### An extremely brief example

```java
public class A {
    public static void main(String[] args) {
        System.out.println("Hello, world.");
    }
}
```

If we see "hot" code, the JVM will compile some bytecode into machine code. So the program gets faster as it runs...wild.

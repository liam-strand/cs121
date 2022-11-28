# We need to avoid data races

If we want to write to shared memory, but avoid data races, we can use locks to synchronize them.

```java
class C {
    int count;
    void inc() { synchronized(this) { count++; } }
    // or more concisely
    synchronized void dec() { count--; }
}
```

This does the following:
1. Aquires lock on `this`
2. Executes the method body
3. Releases the lock no matter what

Thank god for Java.


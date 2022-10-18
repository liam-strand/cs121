#include <stdio.h>
#include <stdlib.h>
#include <string.h>

enum clazz {A, B};
typedef struct PrintI {
  enum clazz id;
  void (*print)(void);
} * PrintI;

void printA(void) {
  printf("I'm an A!\n");
}
PrintI newA(void) {
  PrintI o = malloc(sizeof(*o));
  o->id = A;
  o->print = printA;
  return o;
}

void printB(void) {
  printf("I'm an B!\n");
}
PrintI newB(void) {
  PrintI o = malloc(sizeof(*o));
  o->id = B;
  o->print = printB;
  return o;
}

int main(int argc, char **argv) {
  PrintI o;
  if (strcmp(argv[1], "foo") == 0) {
    o = newA();
  } else {
    o = newB();
  }
  o->print();
}


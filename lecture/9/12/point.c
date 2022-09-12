#include <math.h>
#include <stdlib.h>

typedef struct point { int x; int y; } *point;

point mkPoint(int a, int b) {
    point p = malloc(sizeof(*p));
    p->x = a;
    p->y = b;
    return p;
}

point shift(point p, int dx, int dy) {
    return mkPoint(p->x + dx, p->y + dy);
}

int length(point p) {
    return sqrt((p->x * p->x) + (p->y * p->y));
}

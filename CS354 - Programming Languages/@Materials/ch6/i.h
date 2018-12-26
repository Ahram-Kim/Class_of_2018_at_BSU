#include <stdio.h>

int *f() {
  i=(i+1)%2;			/* side effect */
  return &i;
}

#define i (*f())

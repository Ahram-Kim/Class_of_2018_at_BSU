// gcc -Wall -o goto goto.c && ./goto

#include <stdio.h>

int main() {
  int i=5;
 loop:
  printf("%d\n",i);
  if (--i>0)
    goto loop;
  return 0;
}

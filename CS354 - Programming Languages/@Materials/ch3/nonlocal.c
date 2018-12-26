// gcc -o nonlocal nonlocal.c && ./nonlocal

#include <stdio.h>

void a() {
  void c() {
    void e() { printf("You are here!\n"); }
    void d() { e(); }
    d();
  }
  void b() { c(); }
  b();
}

int main() {
  a();
  return 0;
}

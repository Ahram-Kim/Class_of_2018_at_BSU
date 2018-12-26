// gcc -E -o macro.i macro.c

#define ALLOC(t,v)	  \
  t v=malloc(sizeof(*v)); \
  if (!v) ERR("malloc() failed")

typedef struct {
  int i;
  double d;
} *Foo;

typedef struct {
  char c;
  float f;
} *Bar;

int main() {
  ALLOC(Foo,foo);
  ALLOC(Bar,bar);
}

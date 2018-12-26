
CC=gcc
CFLAGS=-I. -Wall -g -O3 
LIBFLAGS=-I. -shared -fPIC
LIBS=-L. -lbuddy
LIBOBJS=buddy.o

all: libbuddy.so libbuddy.a malloc-test 

buddy.o: buddy.c
	$(CC) $(CFLAGS) -shared -fPIC -c -o $@ $?

libbuddy.so: $(LIBOBJS)
	$(LD) $(LIBFLAGS) -o $@ $?

libbuddy.a: $(LIBOBJS)
	$(AR)  rcv $@ $(LIBOBJS)
	ranlib $@

malloc-test: malloc-test.o 
	$(CC) $(CFLAGS) -o $@ $?

clean:	
	/bin/rm -f *.o a.out malloc-test libbuddy.* 

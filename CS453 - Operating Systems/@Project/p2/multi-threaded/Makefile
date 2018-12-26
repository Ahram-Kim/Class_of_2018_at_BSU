
CC=gcc
CFLAGS=-O -g -Wall -I.
# specify libraries to compile below, e.g. -lm (for the math library)
LIBS=-lpthread

mergesort: mergesortTest.o mergesort.o timing.o 
	$(CC) $(CPPFLAGS)  mergesortTest.o mergesort.o timing.o $(LIBS) -o mergesort

clean:
	/bin/rm -f *.o mergesort

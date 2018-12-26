

#Reference cs.boisestate.edu/~cs253/handouts/make-handout.pdf
#For more information regarding make

CC = gcc
CFLAGS = -Wall -g -MMD

-include *.d

all: myprog

myprog: main.o f1.o f2.o f3.o 
	$(CC) $(CFLAGS) $^ -o $@

clean:
	rm -f *.o myprog


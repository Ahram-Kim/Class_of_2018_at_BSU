
CC = gcc
CFLAGS = -Wall -g

all: smash

smash: smash.o history.o
	$(CC) $(CFLAGS) $^ -o $@

clean:
	rm -f *.o smash

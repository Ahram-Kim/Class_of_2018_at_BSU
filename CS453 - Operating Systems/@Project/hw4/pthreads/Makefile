DEBUG=
CC=gcc
CFLAGS=-g  -Wall  $(DEBUG)
LFLAGS=-lpthread -pthread

OBJS=FileAccess.o TestFileAccess.o

all: TestFileAccess 

TestFileAccess: FileAccess.h $(OBJS)
	$(CC) $(CFLAGS) -o $@ $(OBJS) $(LFLAGS)
	

clean:
	/bin/rm -f *.o core a.out $(OBJS) TestFileAccess

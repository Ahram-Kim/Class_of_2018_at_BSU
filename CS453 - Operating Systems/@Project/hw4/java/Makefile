

# Recognize files with .class and .java extensions
.SUFFIXES: .class .java
.java.class:
	javac $(JFLAGS) $<

JFLAGS=

CC=gcc
#CFLAGS=-I. -g   -Wall
CFLAGS=-I. -O  -Wall
LDFLAGS=-lpthread
OBJS=semaphore.o

all: FileAccess.class TestFileAccess.class FileAccessThread.class

clean:
	/bin/rm -f *.o *.class log*

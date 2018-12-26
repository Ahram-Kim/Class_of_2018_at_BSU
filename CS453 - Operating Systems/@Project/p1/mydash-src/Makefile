CC=gcc
CFLAGS=-g  -Wall -Wextra -Wpointer-arith -Wstrict-prototypes -std=gnu89 -O2 -I. $(INCLUDES) -MMD
LIBS=-lreadline -lncurses
#Change above line to the following to make it link with readline
#LIBS=-lreadline -lncurses
MV=/bin/mv

PROGS=mydash

all: $(PROGS)

mydash: mydash.o error.o git_version.o parsing.o ##mydash_command.o
	$(CC) $(CFLAGS) -o $@  $^ $(LIBS)
	$(MV) $@ ../

##
## on every build, record the working copy revision string
##
git_version.c: FORCE
		echo -n 'const char* git_version(void) { const char* GIT_Version = "' > git_version.c
		git rev-parse HEAD | cut -c1-6 | tr -d '\n'  >> git_version.c
		echo '"; return GIT_Version; }' >> git_version.c


-include *.d

FORCE:
##
## Then any executable that links in git_version.o will be able
## to call the function git_version() to get a string that
## describes exactly what revision was built.

clean:
	/bin/rm -f *.o $(PROGS) a.out core  *.d git_version.c

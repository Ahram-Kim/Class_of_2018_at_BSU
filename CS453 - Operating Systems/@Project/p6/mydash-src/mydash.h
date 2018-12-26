
#ifndef	__MYDASH_H
#define	__MYDASH_H


#include    <sys/wait.h>
#include    <sys/types.h>   /* required for some of our prototypes */
#include    <stdio.h>       /* for convenience */
#include    <stdlib.h>      
#include    <string.h>
#include    <unistd.h>  

#include    <sysexits.h>
#include    <signal.h>
#include    <errno.h>
#include    <dirent.h>
#include    <pwd.h>
#include    <setjmp.h>
#include    <mydash.h>

#include    <readline/readline.h>
#include    <readline/history.h>

#include	"../../list/include/List.h"
#include	"../../list/include/Node.h"

#include    "jobs.h" 
#include    "commands.h"


#define MAXLINE 4096            /* max line length */

#define FILE_MODE   (S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH)
                    /* default file access permissions for new files */
#define DIR_MODE    (FILE_MODE | S_IXUSR | S_IXGRP | S_IXOTH)
                    /* default permissions for new directories */

 
/* prototypes for our own functions */

void    err_dump(const char *, ...);    /* {App misc_source} */
void    err_msg(const char *, ...);
void    err_quit(const char *, ...);
void    err_ret(const char *, ...);
void    err_sys(const char *, ...);

/* parsing constants and prototypes */
#define MAX_LENGTH 1024
#define MAX_TOKENS 2048

jmp_buf point;

#define TRUE 1
#define FROM_ONINTR 333333

// from version.c
const char* git_version(void);

// from parsing.c
char **parseInput(char *s, char *delimiter, int *numTokens);
void freeToken(char **tokens, int numOfTokens);

// from mydash.c
void signal_init(void);
void signal_ignore(void);
void signal_handling(int signo);

#endif	/* __MYDASH_H */


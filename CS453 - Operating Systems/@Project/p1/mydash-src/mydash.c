/*
  This program needs the file error.c and mydash.h to compile.
 */
#include    <string.h>
#include    <unistd.h>
#include    <stdio.h>
#include    <stdlib.h>
#include    <readline/readline.h>
#include    <readline/history.h>
#include    <sys/types.h>
#include    <sys/wait.h>
#include    <mydash.h>
#include    "mydash.h"
#include    "errno.h"
#include    "pwd.h"

/* 'exit' command execution function */
void mydash_exit(char** token, int *count)
{
    int i = 0;
    for(i = 0; i < *count; i++)
    {
        char *current = token[i];
        free(current);
    }
    free(token);
    //exit(0);
}

/* 'cd' command execution function */
void mydash_cd(char *token)
{
    int changeDirectory = chdir(token);
    struct passwd *userInfo;
    
    if(token != NULL)
    {
        if(changeDirectory != 0)
        {
            fprintf(stderr, "error: %s does not exist\n", token);
        }
        else 
        {
            char* path = getcwd(NULL, 0);
            if(path == NULL){
				fprintf(stderr, "error: %s does not exist\n", token);
			}
            fprintf(stdout, "%s\n", path);
			free(path);
        }
    }
    else 
    {
        errno = 0;
        userInfo = getpwuid(getuid());

        if(errno == EINTR || errno == EIO || errno == EMFILE || errno == ENFILE || errno == ENOMEM || errno == ERANGE)
        {
            chdir("/");
        }
        else
        {
            chdir(userInfo -> pw_dir);
        }
    }
}

/* print git version */
void git_ver(int argc, char **argv)
{
    if(argc == 2 && strstr(argv[1], "-v"))
    {
        const char *v = git_version();
        fprintf(stdout, "Version is %s\n", v);

        exit(EXIT_SUCCESS);
    }
}

/* handle empty command */
int mydash_empty(const char *str)
{
    while(*str != '\0')
    {
        if(!isspace(*str))
        {
            return 0;
        }
        str++;
    }
    return 1;
}

// MAIN
int main(int argc, char **argv)
{
    char buf[MAXLINE];
    int count = 0;
    char *line;
    char *prompt;
    int status;
    pid_t pid;

    git_ver(argc, argv);

    // Set the environment variable to prompt
    if(!getenv("DASH_PROMPT"))
    {
        prompt = "mydashhhh > ";
    }
    else {
        prompt = getenv("DASH_PROMPT");
    }

    using_history(); /* enable readline history mechanism */

    while((line = readline(prompt)))
    {
        buf[strlen(buf) - 1] = '\0';    /* replace newline with null */

        // Empty command execution
        if(mydash_empty(line))
        {
            continue;
        }        
        
        add_history(line);
        char **token = parseInput(line, " ", &count);

        if(token[0] != 0)
        {
            //'exit' command execution
            if(strcmp(token[0], "exit") == 0)
            {
                mydash_exit(token, &count);
                break; 
            }
            // 'cd' command execution
            else if(strcmp(token[0], "cd") == 0)
            {
                mydash_cd(token[1]);
            }
            // 'history' command execution
            else if(strcmp(token[0], "history") == 0)
            {
                
            }
            // normal command execution
            else
            {
                if ((pid = fork()) < 0)
                {
                    err_sys("fork error");
                }
                /* child */
                else if (pid == 0)      
                {     
                    execvp(token[0], token); 
                    err_ret("couldn't execute: %s", line);
                    free(token);
                    exit(EXIT_SUCCESS);
                }

                /* parent */
                if ((pid = waitpid(pid, &status, 0)) < 0)      
                {
                    err_sys("waitpid error");
                }            
            }
        }
        free(line);
    }
    clear_history();
    exit(1);
    return 0;
}

/* vim: set ts=4: */

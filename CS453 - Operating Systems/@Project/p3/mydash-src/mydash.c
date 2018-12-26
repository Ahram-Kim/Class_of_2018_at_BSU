/*
This program needs the file error.c and mydash.h to compile.
*/
#include "mydash.h"

//export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:../list/lib



int main(int argc, char **argv) 
{

    char *unmodLine = "";
    char *line;

    struct list *list;
    int numTokens = 0;
    int backgroundCheck = 0;
    int tokenNum;
    char **token;
    char *delimiter = " ";

    using_history();

    signal_init();

    list = createList(&job_equal, &job_toString, &job_free);

    sigsetjmp(point, TRUE);

    if(argc == 1)
    {
        while ((line = readline(getDashPrompt()))) 
        {
            job_printDoneJobs(list);

            if (*line) 
            {
                add_history(line);
                unmodLine = malloc(sizeof(char) * (strlen(line) + 1));
                strcpy(unmodLine, line);
                int i = 1;

                mydash_empty(line);

                if (line[strlen(line) - i] == '&') 
                {
                    backgroundCheck = 1; 
                    line[strlen(line) - i] = '\0';
                }

                token = parseInput(line, delimiter, &numTokens);
                tokenNum = mydash_commands(token, list);

                if (tokenNum == 1) 
                {
                    freeToken(token, tokenNum);
                    free(line);
                    free(unmodLine);
                    continue;
                }

                else if (tokenNum == 2) 
                {
                    freeToken(token, tokenNum);
                    free(line);
                    freeList(list);
                    free(unmodLine);
                    exit(EXIT_SUCCESS);
                }

                mydash_exec(token, backgroundCheck, unmodLine, list);
                free(line);
                freeToken(token, tokenNum);
                free(unmodLine);
                backgroundCheck = 0;
            }

        }
    }
    else if(argc == 2)
    {
        get_gitVersion(argv);
    }
    else
    {
        /* Handling Arguments */
        fprintf(stderr, "Usage: %s or %s -v or %s -V\n", argv[0], argv[0], argv[0]);
        exit(0);
    }
    exit(EXIT_SUCCESS); 

}


void signal_init() 
{
    if (signal(SIGINT, signal_handling) == SIG_ERR)
    {
        printf("Error in setting up interrupt handler\n");
    }
    if (signal(SIGTSTP, signal_handling) == SIG_ERR)
    {
        printf("Error in setting up interrupt handler\n");
    }
}


void signal_ignore() 
{
    signal(SIGINT, SIG_IGN);
    signal(SIGTSTP, SIG_IGN);
}


void signal_handling(int signo) 
{
	signal_init();

	switch (signo) 
    {
        case SIGINT:
            printf("\n Caught Ctrl-C \n");
            siglongjmp(point, FROM_ONINTR);
            break;
        case SIGTSTP:
            printf("\n Caught Ctrl-Z \n");
            siglongjmp(point, FROM_ONINTR);
            break;
        default: break;
	}
}
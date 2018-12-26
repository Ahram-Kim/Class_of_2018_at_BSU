
#include    "mydash.h"
 

/* Printing git version */
void get_gitVersion(char **argv)
{
    if(strcmp(argv[1], "-v") || strcmp(argv[1], "-V"))
    {
        const char *v = git_version();
        fprintf(stdout, "Project Version is %s\n", v);

        exit(EXIT_SUCCESS);
    }
	else
	{
		/* Handling Arguments */
        fprintf(stderr, "Usage: %s or %s -v or %s -V\n", argv[0], argv[0], argv[0]);
        exit(0);
	}
}


/* Setting the environment variable to prompt */
char *getDashPrompt(void)
{
    char *prompt;

    if(!getenv("DASH_PROMPT"))
    {
        prompt = "mydashhhhh -> ";
    }
    else
    {
        prompt = getenv("DASH_PROMPT");
    }

    return prompt;
}


/* 'empty' command execution function */
void mydash_empty(char *line)
{
	int i = 1;
	while (isspace(line[strlen(line) - i])) 
    {
        i++;
    }
}


/* other command execution function */
void mydash_exec(char **token, int background, char *line, struct list *list) {

	pid_t pid;
	int status;
	char* cmd = line;

	if ( (pid = fork()) < 0)
	{
		err_sys("fork error");
	}
	else if (pid == 0) 
	{
		if (background) 
		{
			signal_ignore();
		}
		if (execvp(*token, token) < 0) 
		{
			err_ret("couldn't execute: %s", token);
			exit(EXIT_FAILURE);
		}
	}

	else 
	{
		if (!background) 
		{
			signal_ignore();
			if ( (pid = waitpid(pid, &status, WUNTRACED)) < 0)
			{
				err_sys("waitpid error");
			}
			else if (WIFSTOPPED(status)) 
			{
				job_adding(pid, cmd, list, 0);
			}
		} 
		else 
		{
			job_adding(pid, line, list, 1);	
		}
	}
	signal_init();
}


/* 'history' command execution function */
void mydash_history()
{
	HIST_ENTRY** history = history_list();

	if(!history)
	{
		return;
	}
	int i = 0;
	for(; history[i]; i++)
	{
		printf("%d %s\n", i + history_base, history[i] -> line);
	}
}


/* 'exit' command execution function */
void mydash_exit(char *token)
{
	printf("exit successfully \n");
	free(token);
	exit(EXIT_SUCCESS);
}


// NOT WORKING: ACTUALLY IT WORKED ON P1.
/* 'jobs' command execution function */
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


/* Here are mydash commands */
int mydash_commands(char **token, struct list *list) {

	if (token[0] == NULL) {
		return 1;
	}

	/* 'jobs' command execution function */
	if (strcmp(token[0], "jobs") == 0) {
		mydash_jobs(list);
		return 1;
	}

	/* 'jobs' command execution function */
	if (strcmp(token[0], "exit") == 0) {
		mydash_exit(*token);
		return 2;
	}

	/* 'jobs' command execution function */
	if (strcmp(token[0], "cd") == 0) {
		mydash_cd(*token);
		return 1;
	}

	/* 'jobs' command execution function */
	if(strcmp(token[1], "mydash") == 1)
	{
		get_gitVersion(token);
		return 2;
	}

	/* 'jobs' command execution function */
	if (strcmp(token[0], "history") == 0) {
		mydash_history();
		return 1;
	}
	return 0;
}
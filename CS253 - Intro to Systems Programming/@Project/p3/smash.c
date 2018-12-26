
  #include <string.h>
  #include <stdio.h>
  #include <stdlib.h>
  #include <unistd.h>
  #include <sys/wait.h>
  #include <errno.h>
  #include <time.h>
  #include "history.h"

  #define MAXLINE 1024
  #define MAXARGS 1024

  /**
   * Wrap strncpy to make sure we always have properly terminated strings.
   * If dst is less that src strncpy will not terminate the string (LOL) 
   * so we have to make sure that our string is properly terminated.
   */
  char* strncpy_safe(char *dst, const char *src, size_t len)
  {
        char *rval = NULL;
	rval = strncpy(dst, src, len);
  	rval[len - 1] = '\0';
  	return rval;
  }
	
  /**
   * Takes a line and splits on spaces. Returns an array os each token taken
   * from the line. The returned array will be formatted in the currect format for
   * the exec family of functions (ie terminated by a NULL ptr)
   * NOTE : Caller is responsible for freeing memory
   */
  char** chop_line_exec(const char* line)
  {
  	char *token = NULL;
  	int i = 0;
  	char linecpy[MAXLINE];
	
	/**
	 * Allocate an array big enough to handle the max number of args. If we have 
	 * a line with more than MAXARGS we will ignore everything after MAXARGS length.
	 */
  	char **rval = malloc(MAXARGS);
      
  	memset(rval, 0, MAXARGS);
  	strncpy_safe(linecpy, line, MAXLINE);
      
	// Now lets grab all the tokens.
  	token = strtok(linecpy, " ");
  	for(i = 0; token && i < MAXARGS; i++)
  	{
		fprintf(stderr, "got token %s\n", token);
		rval[i] = malloc(MAXLINE);
		strncpy_safe(rval[i], token, MAXLINE);
		token = strtok(NULL, " ");
     	}
      	return rval;
  }

  // exit Command execution
  void smash_exit()
  {
     	 exit(0);
  }
  
  // cd Command execution
  void smash_cd(char *token)
  {
      	int changeDirectory;
      	char *path;
	
      	changeDirectory = chdir(token);
      	path = getcwd(NULL, 0);
	
	if(token != NULL)
      	{
		if(changeDirectory != 0 || path == NULL)
		{
	      		printf("error: %s does not exist.\n", token);
	  	}
	 	else 
	 	{
	      		printf("%s\n", path);
	  	}
      	}
  }

  // Normal command execution
  void smash_exec(char** args)
  {  
	pid_t pid;
	int status;
	const char* s = getenv("CHAOS_MONKEY_DISABLE");
	int r = rand() % 100;
	
	if(s || r <= 75)
	{
		if ((pid = fork()) < 0) 
		{
			perror("fork");
			exit(errno);
		} 
		else if (pid == 0) 
		{ 
			execvp(args[0], args);
			exit(0);
		}
			    
		if(waitpid(pid, &status, 0) != pid)
		{
			perror("waitpid");
			exit(errno);
		}
	}
	printf("PATH : %s\n", (s != NULL)? s : "getenv returned NULL");
  }
  
  // MAIN
  int main(int argc, char **argv)
  {
  	struct history* history;
  	char buffer[MAXLINE];
	
	srand(time(NULL));
	
      	fprintf(stderr, "$ ");
     
      	history = init_history(100);
      
      	while ((fgets(buffer, MAXLINE, stdin)) != NULL)
      	{
		buffer[strlen(buffer) - 1] = '\0';	// replace new line with null
	  
		add_history(history, buffer);
		
	  	char** args = chop_line_exec(buffer);
	  
	  	if(args[0] != NULL)
	  	{
	      		if(strcmp(args[0], "exit") == 0)
	      		{
		  		smash_exit();
		  		break;
	      		}
	      		else if(strcmp(args[0], "cd") == 0)
	      		{	
		  		smash_cd(args[1]);
	      		}
	      		else if(strcmp(args[0], "history") == 0)
	      		{
		  		print_history(history);
	      		}
	      		else
	      		{
 				smash_exec(args);		   		
	      		}
	  	}
		fprintf(stderr, "$ ");
      }
      clear_history(history);
      return EXIT_SUCCESS;
  }
  

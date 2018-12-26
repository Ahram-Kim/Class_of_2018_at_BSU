
  #include "history.h"
  #include <stdio.h>
  #include <stdlib.h>
  #include <string.h>

  // Initialize all buffer (constructor in Java)
  struct history* init_history(int capacity)
  {   
  	struct history* cmd = (struct history*)malloc(sizeof(struct history));
    
  	cmd -> size = capacity;
  	cmd -> index = 0;
  	cmd -> arrayList = (struct cmd**) malloc(sizeof(struct cmd*) * cmd -> size);
    
  	int i;
  	for(i = 0; i < cmd -> size; i++)
  	{
		cmd -> arrayList[i] = NULL;
    	}

    	return cmd;
  }

  // Add command into a 'cmd struct'
  void add_history(struct history* history, char* token)
  {
  	struct cmd* newCmd = (struct cmd*)malloc(sizeof(struct cmd));
   
  	if(history -> index == history -> size)
    	{
		int i;
		for(i = 0; i < history -> size; i++)
		{
	    		free(history -> arrayList[i] -> cmd);
	    		free(history -> arrayList[i]);
	    		history -> arrayList[i] -> cmd = NULL;
	    		history -> arrayList[i] = NULL;
	    		history -> index = 0;
		}
	  
    	}

  	newCmd -> cmd = (char*)malloc(strlen(token));
    	strcpy(newCmd -> cmd, token);
    	history -> arrayList[history -> index] = newCmd;
    	history -> index++;
  }  

  // Free all memory and clear the history
  void clear_history(struct history* history)
  {
    	int i;
    	for(i = 0; i < history -> index; i++)
    	{
		free(history -> arrayList[i] -> cmd);
		free(history -> arrayList[i]);
		history -> arrayList[i] -> cmd = NULL;
		history -> arrayList[i] = NULL;
    	}
	
    	free(history -> arrayList);
    	free(history);
  }

  // Print the history to the command line
  void print_history(struct history* history)
  {
  	int i; 	
  	for(i = 0; i < history -> index; i++)
  	{
		printf("%d %s\n", i, history -> arrayList[i] -> cmd);
  	}
  }

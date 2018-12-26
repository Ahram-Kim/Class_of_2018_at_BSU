  #include <stdio.h>
  #include <stdlib.h>
  #include <unistd.h>
  #include <string.h>

  #define MAX_LENGTH 2048
  #define MAX_TOKENS 100

  int main(int argc, char **argv) 
  {   
	if(argc != 2)
	{
        	fprintf(stderr, "Usage: %s <sleep interval(seconds)>\n\n", argv[0]);
    	}
    	else
	{
		if(atoi(argv[1]) < 1)
		{
        		fprintf(stderr,"%s: Specified too small an interval. Must be at least 1 second. \n\n", argv[0]);
    		}
    		else
		{
    			fprintf(stderr, "%s: Using a default sleep time of %s seconds.\n", argv[0], argv[1]);

    			while (1) 
			{
        			int numOfMultithreaded = 0;
       				int totalNumProcess = 0;
        			double percentage = 0;
				char stored[10000];
        			FILE *pointer;

				system("ps augx > ps.log");
        
       				if ((pointer = fopen("ps.log", "r")) == NULL)
				{                
            				printf("File not found");
            				exit(1);
        			}
	
        			// special symbol for numOfMultithreaded is "l"
        			while (fscanf(pointer, " %100s", stored) == 1) 
				{ 
                			if(stored[0] == 'S' && stored[strlen(stored) - 1 ] == 'l')
					{
                    				numOfMultithreaded++;
                			}
        			}
        
        			rewind(pointer);
         
        			while(fgets(stored, 40965, pointer) != NULL)
				{
            			 totalNumProcess++;
        			}
        
        			percentage = 100.0 * numOfMultithreaded/ totalNumProcess;

        			fprintf(stdout, "%s: %d/%d processes are numOfMultithreaded[%.2lf%c]\n", argv[0], numOfMultithreaded, totalNumProcess, percentage, '%');        
        			sleep(atoi(argv[1]));
    			}
    		exit(0);
   		}
	}
	return 0;
  }




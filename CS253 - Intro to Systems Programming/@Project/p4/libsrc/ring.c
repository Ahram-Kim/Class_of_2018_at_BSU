
 #include <string.h>
 #include <stdio.h>
 #include <time.h>
 #include <ring.h>
 #include <signal.h>
 #include <unistd.h>

 static struct
 {
 	int curr;
 	char log[MAX_LOG_ENTRY][MAX_STRING_LENGTH];
 } buff;

 static void dump_buffer();

 static void onalarm(int signo)
 {
  	// This is probably bad because in industry you would NEVER want 
    	// to open and write a file in a signal handler. That is really really bad.
    	// But for this class, it is OK because we are still learning :)
   	printf("I'm awake! I'm going to take another nap....\n");
    	fflush(stdout);
    	signal(SIGALRM, onalarm);      // reset signal handler
    	alarm(alarm_interval); /* reset timer so it will go off again */
   	dump_buffer();
	
}

 void init_buffer()
 {
    	printf("Initialize the ring buffer\n");
    	int i;
    	for(i = 0; i < MAX_LOG_ENTRY; i++) 
	{
        	buff.log[i][0]='\0';
    	}
    	buff.curr = 0;
    	signal(SIGALRM, onalarm);      // Timer/alarm handler
    	alarm(alarm_interval);                      // Set the timer for n secs
 }

 /**
  * Return the current timestamp (localtime) from the system.
  */
 static char *getTimeString()
 {
    	time_t myTime = time(NULL); //this is a system call
    	char *timeString = ctime(&myTime);
    	timeString[strlen(timeString)-1] = '\0'; //erase the newline at the end
    	return timeString;
 }

 void log_msg(char *entry)
 {
    	if (entry == NULL) {
        	printf("Skipping null log entry!\n");
        	return;
    	}

    	printf("Adding log entry into buffer\n");

    	char *timeString = getTimeString();
    	int idx = buff.curr % MAX_LOG_ENTRY;

    	snprintf(buff.log[idx], MAX_STRING_LENGTH, "%s -- %s", timeString, entry);

    	buff.curr++;
 }

 /*
  * Right now this is just printing to the console. We want to change this to
  * write to a file (log_name) and we want to use signals to trigger the logging
  * event. This also needs to be fixed so that it prints the log messages in the
  * right order (from the oldest to the newest).
  *
  * This method should write all the current entries to disk in the right order
  * (from the oldest to the newest). We will use the constant log_name as the
  * name of the file.
  */
 static void dump_buffer()
 {
    	int i;
    	FILE* file;
    	file = fopen(log_name, "w");

    	for(i = MAX_LOG_ENTRY-1; i >= 0; i--) 
	{
        	fprintf(file, "log %d: %s\n",i, buff.log[i]);
    	}
 }

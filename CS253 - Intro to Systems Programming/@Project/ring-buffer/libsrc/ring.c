
 #include <string.h>
 #include <stdio.h>
 #include <stdlib.h>
 #include <time.h>
 #include <ring.h>
 #include <signal.h>
 #include <unistd.h>
 #include <pthread.h>


 static struct {
 	int curr;
 	char log[MAX_LOG_ENTRY][MAX_STRING_LENGTH];
	int threadID;
	pthread_mutex_t mutex;
 } buff;

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
	
	pthread_mutex_lock(&buff.mutex);

    	char *timeString = getTimeString();
    	int idx = buff.curr % MAX_LOG_ENTRY;

    	snprintf(buff.log[idx], MAX_STRING_LENGTH, "%s -- %s", timeString, entry);

    	buff.curr++;
	pthread_mutex_unlock(&buff.mutex);
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
	pthread_mutex_lock(&buff.mutex);
	
    FILE* file;
    file = fopen(log_name, "w");
	
	if(!file)
	{
		perror("Filed to open log file");
		return;
	}
	
	int i, j;
	int idx = buff.curr % MAX_LOG_ENTRY;
   	for(i = idx; i < MAX_LOG_ENTRY; i++) 
	{
        	fprintf(file, "log %d: %s\n ",j, buff.log[i]);
		j++;
    	}
    	
    	for(i = 0; i < idx; i++)
	{
		fprintf(file, "log %d: %s\n", j, buff.log[i]);
		j++;
	}
	fclose(file);
	pthread_mutex_unlock(&buff.mutex);
 }

 

// static void onalarm(int signo)
// {
  	// This is probably bad because in industry you would NEVER want 
    	// to open and write a file in a signal handler. That is really really bad.
    	// But for this class, it is OK because we are still learning :)
//   	printf("I'm awake! I'm going to take another nap....\n");
//    	fflush(stdout);
//    	signal(SIGALRM, onalarm);      // reset signal handler
//    	alarm(alarm_interval); /* reset timer so it will go off again */
//   	dump_buffer();	
// }



 
 void *thread_backup(void *data)
 {	
	printf("RING BUFFER: Successfully started the back up thread %d\n", (int)pthread_self());

	while(1)
	{
		sleep(alarm_interval);
		dump_buffer();
		printf("RING BUFFER: [Thread %d] dumping ring buffer to log file ring.log \n" , (int)pthread_self());
	}
	pthread_mutex_destroy(&buff.mutex);
	pthread_exit(NULL);
 }
 
 void init_buffer()
 {
	pthread_t threads;

    	printf("RING BUFFER: Initialize the ring buffer\n");
	
	pthread_mutex_init(&buff.mutex, NULL);
	int i;
    	for(i = 0; i < MAX_LOG_ENTRY; i++) 
	{
        	buff.log[i][0]='\0';
    	}
    	
	int j;	
    j = pthread_create(&threads, NULL, thread_backup, (void*)NULL);
	
	if(pthread_create(&threads, NULL, thread_backup, (void*)NULL))
	{
		printf("Error: return code from pthread_create() is %d\n", j);
		exit(-1);
	}
    	buff.curr = 0;
    	//signal(SIGALRM, onalarm);      // Timer/alarm handler
    	//alarm(alarm_interval);                      // Set the timer for n secs
 }



 #include <stdio.h>
 #include <stdlib.h>
 #include <unistd.h>
 #include <pthread.h>
 #include <ring.h>

 struct threads_data
 {
	 int threadID;
	 int numOfMsg;
	 pthread_t t;
 };
 
 void *thread_function(void *data)
 {
	char msg[MAX_STRING_LENGTH];
	struct threads_data* threadData;
	threadData = (struct threads_data*) data;
		
	printf("test-threads: thread %d starting to log %d messages.... \n", threadData -> threadID, threadData -> numOfMsg);
		
	int i;
	for(i = 0; i < threadData -> numOfMsg; i++)
	{
		sprintf(msg, "[Thread %d] log message %d \n", threadData -> threadID, i);
		log_msg(msg);
	}
	printf("test-threads: thread %d finishing.... \n", threadData -> threadID);
	pthread_exit(NULL);
 }


 int main(int args, char **argv)
 {
	int numOfThreads = atoi(argv[1]);
	int numOfMessages = atoi(argv[2]);
	
	struct threads_data threads[numOfThreads];

	if(args != 3)
	{
		fprintf(stderr, "Check you Input!\n");
	}
	init_buffer();
	
	int i;
	printf("test-threads: main thread %d \n", (int)pthread_self());
	for(i = 0; i < numOfThreads; i++)
	{	
		threads[i].threadID = i;
		threads[i].numOfMsg = numOfMessages;
		pthread_create(&threads[i].t, NULL, thread_function, (void*)&threads[i]);
	}
	
	for(i = 0; i < numOfThreads; i++)
	{
		pthread_join(threads[i].t, NULL);
	}
 	return 0;
 }

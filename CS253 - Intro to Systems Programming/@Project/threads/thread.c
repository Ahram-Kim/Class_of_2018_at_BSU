
 #include <stdio.h>
 #include <pthread.h>

 int c = 0;
 pthread_mutex_t mutex;

 // Thread start function
 void *thread_start(void* data)
 {
	while(1)
	{
		int i;
		for(i = 0; i < 22; i++)
		{
			pthread_mutex_lock(&mutex);
			c = c + 1;
			printf("c is now %d\n", c);
			pthread_mutex_unlock(&mutex);
		}
		sleep(1);
	}
	printf("c = %d\n", c);
	
	return NULL;
 }


 int main(void)
 {
	pthread_t pid, pid1, pid2;
	pthread_mutex_init(&mutex, NULL);
	pthread_create(&pid, NULL, thread_start, NULL);
	pthread_create(&pid1, NULL, thread_start, NULL);
	pthread_create(&pid2, NULL, thread_start, NULL);

	pthread_join(pid, NULL);
	pthread_join(pid1, NULL);
	pthread_join(pid2, NULL);

	pthread_mutex_destroy(&mutex);
 	return 0;
 }

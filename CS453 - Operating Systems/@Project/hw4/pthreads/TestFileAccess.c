
#include    <stdio.h>
#include    <stdlib.h>
#include    <unistd.h>
#include    <string.h>
#include    <signal.h>
#include    <time.h>
#include    "FileAccess.h"

void *thread_main(void *);

pthread_t *threads;
pthread_mutex_t mutex;

FileAccessPtr fileAccess;

int numOfThreads;
int maxSum;
int iterations;

void signalHandler(int);

int main(int argc, char **argv)
{
    if(argc < 3)
    {
        fprintf(stderr, "Usage: %s <number of threads> <max sum> <iterations>\n", argv[0]);
        exit(1);
    }

    numOfThreads = atoi(argv[1]);      
    maxSum = atoi(argv[2]);     
    iterations = atoi(argv[3]);

    if(numOfThreads > 32)
    {
        fprintf(stderr, "Usage: %s Too many threads specified. Defaulting to 32\n", argv[0]);
        numOfThreads = 32;
    }

    fileAccess = fileaccess_init(maxSum);    // max Sum
    signal(SIGALRM, signalHandler);
    alarm(30);

    pthread_mutex_init(&mutex, NULL);
    threads = (pthread_t *) malloc(sizeof(pthread_t)*numOfThreads);

    int i;

    for(i = 0; i < numOfThreads; i++)
    {
        pthread_create(&threads[i], NULL, thread_main, (void *) NULL);
    }

    for(i = 0; i < numOfThreads; i++)
    {
        pthread_join(threads[i], NULL);
    }

    pthread_mutex_destroy(&mutex);
    // free(threads);
    // free(fileAccess);
    exit(0);
}

static int get_my_thread_id() 
{
    pthread_mutex_lock(&mutex);

    int i;
    for(i = 0; i < numOfThreads; i++)
    {
        if(threads[i] == pthread_self())
        {
            pthread_mutex_unlock(&mutex);
            return i;
        }
    }
    pthread_mutex_unlock(&mutex);
    return -1;
}

void *thread_main(void *arg)
{
    int i;
    int sleepTime;
    time_t current;
    int id = get_my_thread_id() + 1;

    printf("Thread %1d starting up \n", id);

    for(i = 0; i < iterations; i++)
    {
        time(&current);
        (*fileAccess -> startAccess)(fileAccess, id);
        printf("Thread %d starting file access. \n", id);
        sleepTime = (random() % 5 + 1) * 100;
        usleep(sleepTime);
        (*fileAccess -> endAccess)(fileAccess, id);
        printf("Thread %d ending file access. \n", id);
        sleepTime = (random() % 5 + 1) * 100;
        usleep(sleepTime);
    }
    pthread_exit(NULL);
}

void signalHandler(int sig)
{
    printf("Aieeeeeee...received an alarm. Timeout! \n");
    exit(0);         
}

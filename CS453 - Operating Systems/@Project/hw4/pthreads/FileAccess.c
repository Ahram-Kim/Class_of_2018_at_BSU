
#include <stdlib.h>
#include "FileAccess.h"

static void startAccess(FileAccessPtr, long int);
static void endAccess(FileAccessPtr, long int);

FileAccessPtr fileaccess_init(long int max)
{
	FileAccessPtr fileAccess = (FileAccessPtr) malloc(sizeof(FileAccess));
	fileAccess -> sum = 0;
	fileAccess -> max = max;
	fileAccess -> startAccess = startAccess;
	fileAccess -> endAccess = endAccess;
	pthread_mutex_init(&(fileAccess -> mutex), NULL);
	pthread_cond_init(&(fileAccess -> waitForAccess), NULL);
	return fileAccess;
}

void startAccess(FileAccessPtr fileAccess, long int id)
{
	pthread_mutex_lock(&(fileAccess -> mutex));

	while (fileAccess -> max <= id + fileAccess -> sum) 
    {
		pthread_cond_wait(&(fileAccess -> waitForAccess), &(fileAccess -> mutex));
	}
	fileAccess -> sum = fileAccess -> sum + id;

	pthread_mutex_unlock(&(fileAccess -> mutex));
}


void endAccess(FileAccessPtr fileAccess, long int id)
{
	pthread_mutex_lock(&(fileAccess -> mutex));

	fileAccess -> sum = fileAccess -> sum - id;
	pthread_cond_broadcast(&(fileAccess -> waitForAccess));

	pthread_mutex_unlock(&(fileAccess -> mutex));
}

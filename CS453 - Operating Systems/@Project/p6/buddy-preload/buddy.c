
/**
 * Implementation of a memory allocator based on the Buddy System.
 * See Knuth Art of Computer Programming, vol. 1, page 442. 
 * Each available block starts with a header that consists
 * of a tag (free/reserved), kval (size of block 2^kval), next
 * and previous pointers. Each reserved block has the tag and kval 
 * field only. All allocations are done in powers of two. All requests
 * are rounded up to the next power of two.
 * 
 * @author  TBD
 * 
 */
 
#include <pthread.h>
#include "buddy.h"
#include "../buddy-non-preload/buddy.h"

int initialized = FALSE;

/* the header for an available block */
struct block_header {
	short tag;
	short kval;
	struct block_header *next;
	struct block_header *prev;
};
const int RESERVED = 0;
const int FREE = 1;
const int UNUSED = -1;


/* supports memory upto 2^(MAX_KVAL-1) (or 32 GB) in size */
#define  MAX_KVAL  35

/* default memory allocation is 512MB */
const size_t DEFAULT_MAX_MEM_SIZE = 512*1024*1024;

static pthread_mutex_t buddy_mutex = PTHREAD_MUTEX_INITIALIZER;

/* A static structure stores the table of pointers to the lists in the buddy system.  */
struct pool {
	void *start; // pointer to the start of the memory pool
	int lgsize;  // log2 of size
	size_t size; // size of the pool, same as 2 ^ lgsize
	/* the table of pointers to the buddy system lists */
	struct block_header avail[MAX_KVAL];
} pool;



int buddy_init(size_t size) 
{ 
    return buddy_init(0);
}


void *malloc(size_t size)
{
	if(!initialized)
	{
		printf("calling malloc! \n");
		buddy_init(0);
	}
	
	void *temp;
	pthread_mutex_lock(&buddy_mutex);
	temp = buddy_malloc(size);
	pthread_mutex_unlock(&buddy_mutex);
	return temp;
}


void *calloc(size_t nmemb, size_t size) 
{
	if(!initialized)
	{
		printf("calling calloc! \n");
		buddy_init(0);
	}
	
	void *temp;
	pthread_mutex_lock(&buddy_mutex);
	temp = buddy_calloc(size);
	pthread_mutex_unlock(&buddy_mutex);
	return temp;
}

void *realloc(void *ptr, size_t size) 
{
	if(!initialized)
	{
		printf("calling realloc! \n");
		buddy_init(0);
	}
	
	void *temp;
	pthread_mutex_lock(&buddy_mutex);
	temp = buddy_realloc(ptr, size);
	pthread_mutex_unlock(&buddy_mutex);
	return temp;
}


void free(void *ptr) 
{
	if(!initialized)
	{
		printf("calling free! \n");
		buddy_init(0);
	}

	pthread_mutex_lock(&buddy_mutex);
	buddy_free(ptr);
	pthread_mutex_unlock(&buddy_mutex);
}


void printBuddyLists()
{
	printBuddyLists();
}


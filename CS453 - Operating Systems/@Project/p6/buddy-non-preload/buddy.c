/**
 * Implementation of a memory allocator based on the Buddy System.
 * See Knuth Art of Computer Programming, vol. 1, page 442. 
 * Each available firstBlock starts with a h_block that consists
 * of a tag (free/reserved), kval (size of firstBlock 2^kval), next
 * and previous pointers. Each reserved firstBlock has the tag and kval 
 * field only. All allocations are done in powers of two. All requests
 * are rounded up to the next power of two.
 * 
 * @author  TBD
 * 
 */

#include "buddy.h"
int initialized = FALSE;

/* the h_block for an available firstBlock */
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
#define  MAX_KVAL  37

/* default memory allocation is 512MB */
const size_t DEFAULT_MAX_MEM_SIZE = 512*1024*1024;

/* A static structure stores the table of pointers to the lists in the secondBlock system.  */
struct pool {
        void *start; // pointer to the start of the memory pool
        int lgsize;  // log2 of size
        size_t size; // size of the pool, same as 2 ^ lgsize
        /* the table of pointers to the secondBlock system lists */
        struct block_header avail[MAX_KVAL];
} pool;



static unsigned int gettingKval(const size_t size)
{
    size_t newSize = size;
    unsigned int kval = 0;

    if(newSize == 0)
    {
        newSize = DEFAULT_MAX_MEM_SIZE;
    }

    newSize = newSize - 1;

    while(newSize > 0)
    {
        newSize >>= 1;
        kval++;
    }

    return kval;
}


void * gettingBlock(int index, struct block_header *firstBlock,int targetk)
{
    unsigned long offset;
    unsigned long  normalized;
    struct block_header *h_block;        
    void *newAddress;

    if(index == targetk)
    {
        firstBlock -> tag = RESERVED;
        return (void *) (firstBlock + 1);
    }

    index--;
    firstBlock -> kval = index;

    normalized = (void *) firstBlock - pool.start; 
    offset = normalized ^ (1L << (firstBlock -> kval));
    newAddress = offset + pool.start;
    
    h_block = (struct block_header *) newAddress;
    h_block -> kval = index;
    h_block -> prev = NULL;
    h_block -> next = NULL;

    if(pool.avail[index].next)
    {
        pool.avail[index].next -> prev = h_block;
        h_block -> next = pool.avail[index].next;
    }
    pool.avail[index].next = h_block;
    h_block -> tag = FREE;

    return gettingBlock(index, firstBlock, targetk);
}


int buddy_init(size_t size)
{ 
    if(initialized) 
    { 
        return 0; 
    }

    initialized = TRUE;

    unsigned int kval = gettingKval(size);
    unsigned long kSize = (1L << kval);
    void *start = sbrk(kSize);

    pool.start = start;
    pool.size = kSize;

    pool.avail[kval].next = start;
    pool.avail[kval].prev = start;

    pool.avail[kval].next -> kval = kval;
    pool.avail[kval].next -> tag = FREE;
    pool.avail[kval].next -> next = NULL;
    pool.avail[kval].next -> prev = NULL;

    return TRUE;
}


void *buddy_malloc(size_t size)
{
    unsigned int kval = gettingKval(size);
    struct block_header *block;
    int i = kval;

    if(!initialized)
    {
        buddy_init(0);
    }  

    while(i <= MAX_KVAL)
    {
        if(pool.avail[i].next)
        {
            block = pool.avail[i].next;

            if(block -> next)
            {
                pool.avail[i].next  = block -> next;
                block -> next -> prev = NULL;
            }
            else
            {
                pool.avail[i].next = NULL;
                pool.avail[i].prev = NULL;
            }
            return  gettingBlock(i, block, kval);
        }   
        i++;
    }    
    return NULL;
}


void *buddy_calloc(size_t nmemb, size_t size) 
{
    void *block;

    if(!initialized)
    {
        buddy_init(0);
    }
    int bytes = nmemb * size;
    block = buddy_malloc(bytes);
    memset(block, 0, bytes);

    return block;
}


void *buddy_realloc(void *ptr, size_t size) 
{
    void *block = NULL;

    block = buddy_malloc(size);
    memcpy(block, ptr, size);
    free(ptr);

    return block;
}


void buddy_free(void *ptr) 
{   
    struct block_header *firstBlock = ptr;
    struct block_header *secondBlock;
       
    unsigned long offset;
    unsigned long normalized = 0;
    unsigned long blocksize;
    void *newAddress;
    
    firstBlock = firstBlock - 1;
    offset = normalized ^ (1L << (firstBlock -> kval));
    normalized = (void *)firstBlock - pool.start; 
    newAddress = offset + pool.start;
    secondBlock = (struct block_header *) newAddress;
    blocksize = (1L << (firstBlock -> kval));
    firstBlock -> tag = FREE;

    if(secondBlock -> tag == FREE)
    {
        if(secondBlock -> next)
        {
            pool.avail[secondBlock -> kval].next  = secondBlock -> next;
            secondBlock -> next -> prev = NULL;
        }
        else
        {
            pool.avail[secondBlock -> kval].next=NULL;
            pool.avail[secondBlock -> kval].prev=NULL;
        }

        if(((void*)(secondBlock) - (void*)(firstBlock)) == blocksize)
        {
            secondBlock=firstBlock;
            secondBlock -> kval++;

            if(pool.avail[secondBlock -> kval].next)
            {
                pool.avail[secondBlock -> kval].next -> prev = secondBlock;
                secondBlock->next = pool.avail[secondBlock -> kval].next;
            }

            pool.avail[secondBlock -> kval].next = secondBlock;
        }
        else
        {
            firstBlock = secondBlock;
            firstBlock -> kval++;

            if(pool.avail[firstBlock -> kval].next)
            {
                pool.avail[firstBlock -> kval].next -> prev = firstBlock;
                firstBlock -> next = pool.avail[firstBlock -> kval].next;
            }

            pool.avail[firstBlock -> kval].next = firstBlock;
        }
    }
    else
    {
        firstBlock -> kval++;

        if(pool.avail[firstBlock -> kval].next)
        {
            pool.avail[firstBlock -> kval].next -> prev = firstBlock;
            firstBlock -> next = pool.avail[firstBlock -> kval].next;
        }

        pool.avail[firstBlock -> kval].next = firstBlock;
    }
}


void printBuddyLists()
{
    int i;
    struct block_header *block;

    for (i = 0; i < MAX_KVAL; i++)
    {
        printf("List %d: head = %p", i, &pool.avail[i]);

        if(pool.avail[i].next)
        {
            printf(" --> [tag=%d, kval=%d, addr=%p]\n", pool.avail[i].next->tag, pool.avail[i].next->kval, &pool.avail[i].next);
            block = block -> next;
        } 
        else 
        {
            printf(" --> <null>\n");
        }
    }
}
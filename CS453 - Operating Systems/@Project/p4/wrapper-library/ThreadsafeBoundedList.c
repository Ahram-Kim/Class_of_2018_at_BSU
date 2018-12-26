
#include    <pthread.h>
#include    <stdlib.h>
#include    "ThreadsafeBoundedList.h"

/**
 *  Set up for Libraries
 *  export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:../list/lib:./lib 
 **/

struct tsb_list 
{
    struct list *list;
    int capacity;
    Boolean stop_requested;
    pthread_mutex_t mutex;
    pthread_cond_t listNotFull;
    pthread_cond_t listNotEmpty;
};

struct tsb_list * tsb_createList(int (*equals)(const void *, const void *),
                                    char * (*toString)(const void *),
                                    void (*freeObject)(void *),
                                    int capacity)
{
    struct tsb_list *tsbList = (struct tsb_list*) malloc(sizeof(struct tsb_list*) + capacity * sizeof(struct node));

    tsbList -> list = createList(equals, toString, freeObject);
    tsbList -> capacity = capacity;
    tsbList -> stop_requested = 0;

    pthread_mutex_init(&(tsbList -> mutex), NULL);
    pthread_cond_init(&(tsbList -> listNotFull), NULL);
    pthread_cond_init(&(tsbList -> listNotEmpty), NULL);

    return tsbList;
}

void tsb_freeList(struct tsb_list * list)
{
    pthread_mutex_lock(&(list -> mutex));
    freeList(list -> list);
    pthread_mutex_unlock(&(list -> mutex));
}

int tsb_getSize(struct tsb_list * list)
{
    int size = getSize(list -> list);
    return size;
}

int tsb_getCapacity(struct tsb_list * list)
{
    return list -> capacity;
}

void tsb_setCapacity(struct tsb_list * list, int capacity)
{
    pthread_mutex_lock(&(list -> mutex));
    list -> capacity = capacity;
    pthread_mutex_unlock(&(list -> mutex));
}

Boolean tsb_isEmpty(struct tsb_list * list)
{
    int size = tsb_getSize(list);
    return size == 0;
}

Boolean tsb_isFull(struct tsb_list * list)
{
    int size = tsb_getSize(list);
    return size >= tsb_getCapacity(list);
}

void tsb_addAtFront(struct tsb_list * list, NodePtr node)
{
    pthread_mutex_lock(&(list -> mutex));

    while(tsb_isFull(list) && list -> stop_requested == 0)
    {
        pthread_cond_wait(&(list -> listNotFull), &(list -> mutex));
    }

    addAtFront(list -> list, node);
    pthread_mutex_unlock(&(list -> mutex));
    pthread_cond_signal(&(list -> listNotEmpty));
}

void tsb_addAtRear(struct tsb_list * list, NodePtr node)
{
    pthread_mutex_lock(&(list -> mutex));

    while(tsb_isFull(list) && list -> stop_requested == 0)
    {
        pthread_cond_wait(&(list -> listNotFull), &(list -> mutex));
    }

    addAtRear(list -> list, node);
    pthread_mutex_unlock(&(list -> mutex));
    pthread_cond_signal(&(list -> listNotEmpty));
}

NodePtr tsb_removeFront(struct tsb_list * list)
{
    pthread_mutex_lock(&(list -> mutex));

    while(tsb_isEmpty(list) && list -> stop_requested == 0)
    {
        pthread_cond_wait(&(list -> listNotEmpty), &(list -> mutex));
    }

    NodePtr node = NULL;

    if(list -> stop_requested == 0)
    {
        node = removeFront(list -> list);
    }

    pthread_mutex_unlock(&(list -> mutex));
    pthread_cond_signal(&(list -> listNotFull));

    return node;
}

NodePtr tsb_removeRear(struct tsb_list * list)
{
    pthread_mutex_lock(&(list -> mutex));

    while(tsb_isEmpty(list) && list -> stop_requested == 0)
    {
        pthread_cond_wait(&(list -> listNotEmpty), &(list -> mutex));
    }

    NodePtr node = NULL;

    if(list -> stop_requested == 0)
    {
        node = removeRear(list -> list);
    }

    pthread_mutex_unlock(&(list -> mutex));
    pthread_cond_signal(&(list -> listNotFull));

    return node;
}

NodePtr tsb_removeNode(struct tsb_list * list, NodePtr node)
{
    pthread_mutex_lock(&(list -> mutex));

    while(tsb_isEmpty(list) && list -> stop_requested == 0)
    {
        pthread_cond_wait(&(list -> listNotEmpty), &(list -> mutex));
    }

    NodePtr rmNode = NULL;

    if(list -> stop_requested == 0)
    {
        rmNode = removeNode(list -> list, node);
    }

    pthread_mutex_unlock(&(list -> mutex));

    return rmNode;
}

NodePtr tsb_search(struct tsb_list * list, const void *obj)
{
    pthread_mutex_lock(&(list -> mutex));

    NodePtr node = search(list -> list, obj);

    pthread_mutex_unlock(&(list -> mutex));

    return node;
}

void tsb_reverseList(struct tsb_list *  list)
{
    pthread_mutex_lock(&(list -> mutex));
    reverseList(list -> list);
    pthread_mutex_unlock(&(list -> mutex));
}

void tsb_printList(struct tsb_list * list)
{
    pthread_mutex_lock(&(list -> mutex));
    printList(list -> list);
    pthread_mutex_unlock(&(list -> mutex));
}

void tsb_finishUp(struct tsb_list * list)
{
    while(tsb_isEmpty(list) == 0) {}
    list -> stop_requested = 1;
    pthread_cond_broadcast(&(list -> listNotEmpty));
    pthread_cond_broadcast(&(list -> listNotFull));
}

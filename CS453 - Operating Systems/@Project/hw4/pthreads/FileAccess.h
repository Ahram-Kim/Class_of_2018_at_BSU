#ifndef __FILEACCESS_H
#define  __FILEACCESS_H

#include <stdlib.h>
#include <pthread.h>

typedef struct fileaccess FileAccess;
typedef struct fileaccess * FileAccessPtr;

struct fileaccess {
    long int sum;
    long int max;
    pthread_mutex_t mutex;
    pthread_cond_t waitForAccess;
    void (*startAccess) (FileAccessPtr, long int);
    void (*endAccess) (FileAccessPtr, long int);
};

// struct fileaccess * fileaccess_init(long int max);
FileAccessPtr fileaccess_init(long int max);

#endif /*  __FILEACCESS_H */

#ifndef JOBS_H
#define JOBS_H

#include	<string.h>
#define MAX_PID_DIGITS 5

struct job
{
	int numOfJob; 
	pid_t pid;
	char* command;
	int status;
};

typedef struct job *jobPtr;
typedef struct job job;


void mydash_jobs(struct list *list);
void job_printStatus(const void *object) ;
int job_done(const void *object);
void job_printDoneJobs(struct list *list) ;
void job_adding(pid_t pid, char *line, struct list *list, int status);
struct job* job_creating(int number, pid_t id, char* cmd, int status);
char *job_toString(const void *object);
void job_printSpecificJob(const void *object);
int job_equal(const void *job_1, const void *job_2) ;
void job_free(void *object);


#endif /* __OBJECT_H */
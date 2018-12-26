
#include	"mydash.h"


// execute 'jobs' command 
void mydash_jobs(struct list *list) 
{
	if (list -> size == 0)
	{
		return;
	}

	NodePtr temp = list->head;

	while (temp != NULL) 
	{
		job_printStatus(temp->obj);
		temp = temp->next;
	}
}


/* Printing the status of jobs */
void job_printStatus(const void *object) 
{
	jobPtr jobObject = (jobPtr) object;

	if (job_done(object)) 
	{
		printf("[%d] Done %s \n", jobObject -> numOfJob, jobObject -> command);
	}
	if (jobObject -> status == 1) 
	{
		printf("[%d] %d Running %s \n", jobObject -> numOfJob, (int) jobObject -> pid, jobObject -> command);
	}
	else if (jobObject -> status == 0) 
	{
		printf("[%d] %d Stopped %s \n", jobObject -> numOfJob, (int) jobObject -> pid, jobObject -> command);
	}

}


/* Checking if job is done or not */
int job_done(const void *object) 
{
	jobPtr jobObject = (jobPtr) object;
	int status;
	return waitpid(jobObject -> pid, &status, WNOHANG);
}


/* Printing the done job */
void job_printDoneJobs(struct list *list) 
{
	if (list -> size == 0) 
	{
		return;
	}

	NodePtr node = list -> head;

	while (node != NULL)
	{
		if (job_done(node -> obj)) 
		{
			NodePtr nextN = node -> next;
			job_printStatus(node -> obj);
			removeNode(list, node);
			freeNode(node, list -> freeObject);
			node = nextN;
		}
		else 
		{
			node = node -> next;
		}
	}
}


/* Adding a job */
void job_adding(pid_t pid, char *line, struct list *list, int status) 
{

	int num = (list -> size == 0) ? 1 : ((jobPtr) (list -> tail -> obj)) -> numOfJob + 1;

	jobPtr temp = job_creating(num, pid, line, status);

	NodePtr node = createNode(temp);

	addAtRear(list, node);
	char *tempString = job_toString(temp);
	job_printSpecificJob(temp);
	free(tempString);
}


/* Creating a job */
struct job* job_creating(int number, pid_t id, char* cmd, int status) 
{
	struct job* temp = (struct job*) malloc (sizeof(struct job));
	temp -> numOfJob = number;
	temp -> pid = id;
	temp -> command = (char *) malloc(sizeof(char) * (strlen(cmd) + 1));
	temp -> status = status;
	strcpy(temp -> command, cmd);
	return temp;
}


/* toString of job */
char *job_toString(const void *object) 
{
	struct job* jobs = (struct job*) object;
	char *temp;
	int maxCmd = strlen(jobs -> command) + 1;

	maxCmd += MAX_PID_DIGITS;
	temp = (char *)malloc(maxCmd);

	snprintf(temp, maxCmd, "[%d] %s", jobs -> pid, jobs -> command);
	return temp;
}


/* Printing a job */
void job_printSpecificJob(const void *object) 
{

	jobPtr jobObject = (jobPtr) object;
	if(jobObject -> status == 0)
	{
		printf("[%d] Stopped   %s \n", jobObject -> numOfJob, jobObject -> command);
	}
	else
	{
		printf("[%d] %d %s \n", jobObject -> numOfJob, (int) jobObject -> pid, jobObject -> command);
	}
}


/* Checking if the job is equal or not */
int job_equal(const void *job_1, const void *job_2) 
{
	struct job* j1 = (struct job*) job_1;
	struct job* j2 = (struct job*) job_2;
	return j1 -> pid == j2 -> pid;
}


/* freeing a job */
void job_free(void *object) 
{
	struct job* jobObject = (struct job*) object;
	if (jobObject == NULL) 
	{
		return;
	}
	free(jobObject -> command);
	free(jobObject);
}

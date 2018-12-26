

#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include <pthread.h>


#define TRUE 1
#define FALSE 0

/*
 * Struct Args
 */ 
typedef struct args {
	void *A;	
	int p;
	int r;
	int numOfThread;
} Args;

// function prototypes
void serial_mergesort(int A[], int p, int r); 
void merge(int A[], int p, int q, int r);
void insertion_sort(int A[], int p, int r);

// new function prototype
void parallel_mergesort(int A[], int p, int r, int numOfThread);
void *passingArgs(void *args);

const int INSERTION_SORT_THRESHOLD = 100; //based on trial and error

/*
 * insertion_sort(int A[], int p, int r):
 *
 * description: Sort the section of the A A[p..r].
 */
void insertion_sort(int A[], int p, int r) 
{
	int j;

	for (j=p+1; j<=r; j++) {
		int key = A[j];
		int i = j-1;
		while ((i > p-1) && (A[i] > key)) {	
			A[i+1] = A[i];
			i--;
		}
		A[i+1] = key;
	}
}

/*
 * passingArgs(void structure)
 */
void *passingArgs(void * structure){
	Args *arguments = structure;
	parallel_mergesort(arguments -> A, arguments -> p, arguments -> r, arguments -> numOfThread - 1);
	return structure;
}

/*
 * parallel_mersort(int A[], int p, int r)
 * 
 * description: Sort the section of the A A[p..r].
 */
void parallel_mergesort(int A[], int p, int r, int numOfThread)
{
	if(numOfThread <= 1)
	{
		serial_mergesort(A, p, r);
	}
	else
	{
		Args *args = (Args *) malloc(sizeof(Args));
		pthread_t thread;
		int q = (p + r) / 2;

		args -> A = A;
		args -> p = p;
		args -> r = q;
		args -> numOfThread = numOfThread;

		pthread_create(&thread, NULL, passingArgs, args);
		parallel_mergesort(A, q + 1, r, numOfThread - 1);
		pthread_join(thread, NULL);
		merge(A, p, q, r);
	}
}
		

/*
 * serial_mergesort(int A[], int p, int r):
 *
 * description: Sort the section of the A A[p..r].
 */
void serial_mergesort(int A[], int p, int r) 	// alt_mergesort
{
	if (r-p+1 <= INSERTION_SORT_THRESHOLD)  {
			insertion_sort(A,p,r);
	} else {
		int q = (p+r)/2;
		serial_mergesort(A, p, q);
		serial_mergesort(A, q+1, r);
		merge(A, p, q, r);
	}
}



/*
 * merge(int A[], int p, int q, int r):
 *
 * description: Merge two sorted sequences A[p..q] and A[q+1..r] 
 *              and place merged output back in A A. Uses extra
 *              space proportional to A[p..r].
 */     
void merge(int A[], int p, int q, int r) 
{
	int *B = (int *) malloc(sizeof(int) * (r-p+1));
		
	int i = p;
	int j = q+1;
	int k = 0;
	int l;
		
	// as long as both lists have unexamined elements
	// this loop keeps executing.
	while ((i <= q) && (j <= r)) {
		if (A[i] < A[j]) {
			B[k] = A[i];
			i++;
		} else {
			B[k] = A[j];
			j++;
		}
		k++;
	}
		
	// now only at most one list has unprocessed elements.
		
	if (i <= q) { 
		// copy remaining elements from the p list
		for (l=i; l<=q; l++) {
			B[k] = A[l];
			k++;
		}
	} else {
		// copy remaining elements from the second list
		for (l=j; l<=r; l++) {
			B[k] = A[l];
			k++;
		}
	}
		
	// copy merged output from A B back to A A
	k=0;
	for (l=p; l<=r; l++) {
		A[l] = B[k];
		k++;
	}


	free(B);
}


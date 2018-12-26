
#Homework 4: Monitors
  
* Author: Ahram Kim
* Class: CS453 [Operating Systems] Section 01

# Pseudo-code for File Monitor
monitor FileAccess {
	int sum;
	int max;
	condition okToAccess;

	FileAccess(int max) {
		this.max = max;
		this.sum = sum;
	}

	startAccess(int id) {
		while(sum+id >= max) {
			okToAccess.wait();
		}
		sum += id;
	}

	endAccess(int id) {
		sum -= id;
		okToAccess.signal();
	}
}


# Observations on the PThreads and Java version
Pthreads is very similar with sample code in github. I had some confusing when I was working on Java version. However, I figured out in a few minutes. Java version directly implements the monitors with synchronized keyword while pthreads does not. 


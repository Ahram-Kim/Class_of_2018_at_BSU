
###Homework 2 : Building the Linux Kernel###
Author: Ahram Kim
Class: CS453 Section 1

###Overview
This homework is to give me the experience of working with a large code base and an understanding 
of the structure of the code for a real operating system that is used on a billion pls devices 
around the world. 

###Compiling and Using
Compiling the kernel took lots of time to build the kernel and got lots of errors.
I made a new hard disk and growed them and compiled 'make'. Now it worked!

###Testing
The value in /proc/sys/kernel/threads-max was 63297.
This value's 95% is 60132.
When I process with the command 'ulimit -Ha', max user processes is 60138. They are similar. 

###Sources used
https://docs.google.com/document/d/1gY8Vbrp9qqd20gcnJFSb1yFaw1AIZ4P1-xi6V8jWo3g/edit#heading=h.g2quw7eyia52
http://cs.boisestate.edu/~amit/teaching/handouts/cs-linux.pdf

###Known issues
When I grow my current disk, I should have expanded the file system from inside Linux.
I got lots of errors for this step about like "no space". That was a terrible thing. 
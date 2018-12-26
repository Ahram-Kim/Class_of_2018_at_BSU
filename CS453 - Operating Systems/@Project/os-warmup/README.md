
###OS Warmup Project###
Author: Ahram Kim
Class: CS453 Section 1

##Overview
This project is to creae a logging program named ps-logger that logs the percentage of multithreaded programs on the underlying Linux operating system.

###Compiling and Using
README.md: this file
ps-sample.log: A sample output for "ps augx" command
ps-logger.c: skeleton code to get you started
Makefile: build file
readfile.c: shows how to read a text file in C
strings-ex4.c: shows how to parse a text file line by line

make clean and make should be worked before I compiling.
It should be typed 'ps-logger' with an argument <sleep interval(seconds)>. Example, ps-logger 2, ps-logger 5.

###Testing
At first I worked on an argument part. I checked that the argument works or not. After it worked properly, I worked on parsing the file part, and then I worked all other stuffs. Everything worked properly so that I checked valgrind and had no error on it.

###Sources used
I used CS253 sources and projects what I did before. Especially, the last two project for CS253 was really helpful for this project.


###Known issues
Somehow, I had all files including .o class. I ask professor Amit and he made .gitignore file not to show those files. 

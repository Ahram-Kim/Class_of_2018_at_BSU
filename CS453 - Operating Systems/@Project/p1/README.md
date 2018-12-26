# Project 1/Shell Part 1

* Author: Ahram Kim
* Class: CS453 [Operating Systems] 01 #114055134


## Overview

This project is to buld the simple shell called mydash(for My Dead Again Shell).
I needed to make them to accept arguments to command and add built-in commands.

## Manifest

Makefile - build file for the project
mydash-src/Makefile - build file for the project
mydash-src/mydash.c - main function of the projet
mydash-src/error.c - handle all error used in the project
mydash-src/mydash.h - Header file for mydash.c
mydash-src/parcing.c - parsing a string into tokens
mydash-src/git-version.c - show the git version
README.md - thie file

## Building the project

There was a sample which shows the usage of the readline library in p1 write up. 
I implemented the sample in other-progs/test-readline.c to my main. 

## Features and usage

I made three more functions except main function: mydash_cd, mydash_exit, and mydash_exec. And I implemented them to main function but it doesn't work somehow. So, I combine main functionand mydash_exec function. And I also make separate function for empty command and printing git version. I want my mydash.c look easy to read to make some functions, but stil looks messy. 

## Testing

When I worked all and try, it was all messy. I couldn't check my every error and warning. so I make them from the small part. I did make the parsing first successfully. If it seemed work, I did make 'exit', 'cd' command and then normal command. But testfiles work all not including history.

### Valgrind

valgrind --leak-check=full ./mydash
I have some of errors from some contexts. Sorry for not making to fix the error. It is almost going to due time.

### Known Bugs

During this project, I had lots of experience to have 'segmentation fault(core dumped)'.
I continued to read my code to fine the problem for this segmentation error such as where the overlaped function. 

## Reflection and Self Assessment

During every project, I feel to start the project as earlier as I can. I should not have delayed to do the project to tomorrow. No more tomorrow. 

I think my hardest part for this project is to read line and parse the line. I was struggle on storing copy line and parse them. So doing that work took almost 1 day. The fact that there are several .h .c files, implicit warning has encounter a lot than the project 0. 


## Sources used

I used my 253 project 3 a lot for this project. Even the method to read and parse is
different, it helped a lot to make it. normal command execution is totally different way, but cd and exit make it similar. I searched how to use getuid and getpwuid, and errno.h to find out the home directory of the user on Google stactoverflow.com. 

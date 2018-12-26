
# Project 5 / Device Driver

* Author: Ahram Kim
* Class: CS453 [Operating Systems] 01 #114055134


## Overview

This project is to write a simple character driver called booga for exploring how drivers interact 
with the operating system.

## Manifest

Makefile - build file for the project
booga.c - build file for the project
booga.h - Header file for booga.h
test-booga.c - main function of the projet
booga-test1.sh - given script for testing the driver
booga-test2.sh - given script for testing the driver
booga_load - executable to loads the driver
booga_unload - executable to unloads the driver
README.md - thie file

## Building the project

In order to make the driver I  worked, I need to run the command with my own administration
$ sudo ./booga_load

I run the command with 
$./test-booga <minor number> <buffer size> <read or write>

After making the driver, and I need to run the command with
$sudo ./booga_unload

## Testing

I tested this project with given test-booga.c. 
When I tried ' sudo ./booga_load', I got the result 'insmod: ERROR: could not insert module booga.ko: File exists'.
I tried to solve this problem with the command ' sudo rmmod booga' and 'sudo insmod booga.ko'.
I got the result ' rmmod: ERROR: Module booga is in use'.
I tried for this problem with the command 'modprobe -r booga' but still had the same problem.
I turned off the VM as Professor Amit recommended, and it worked!

### Valgrind

valgrind --leak-check=full ./test-booga 0 100 read
I got 20 bytess in 1 blocks in use at exit. Total heap usage is 2 allocs, 1free, and 121 bytes allocated.

## Reflection and Self Assessment

It was hard to work on the project in the kernel linux. That required me to get bunch of knowledge.
I needed to search for that every timee I made a progress.

## Sources used

The example code in the github.com/BoiseState/CS453-resources repo was helpful for this project. 




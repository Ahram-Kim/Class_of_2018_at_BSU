# Project / Thread-safe Library

* Author: Ahram Kim
* Class: CS453 [Operating Systems] 01 #114055134


## Overview

This project is to create a thread-safe version of an existing list library and then test
it using a provided solution to the producers-customers problem.

## Manifest

Makefile - build file for the project
wrapper-library/ThreadsafeBoundedList.c - Each function should be protected by a mutex.
wrapper-library/ThreadsafeBoundedList.h - header file for ThreadsafeBoundedList.c
pc.c - single queue monitor
pc-mq.c - multiple queue monitor

## Building the project

The professor provide wrapped versions of al the underlying functions from the list library as well as some additional functions.
I see the deader file for details on the functions that I need to be wrapping. I created and implemented these functions in the ThreadsafeBoundedList.c.

## Features and usage

I worked on ThreadsafeBoundedList.c with relevant man pages which are pthread_mutex_init, pthread_mutex_lock, pthread_mutex_unlock, 
pthread_cond_init, pthread_cond_wait, pthead_cond_signal, and pthread_cond_broadcast. 

## Testing

Testing was all good. When I was working on pc-mq.c, I didn't have segmentation fault I had before. I just had some of warning about the pointer.

## Reflection and Self Assessment

During this project, I missed the basic pointer concept. That makes me work on this project longer.

## Sources used

I used my boiseState/CS253-resources and boiseState/CS453-resources.

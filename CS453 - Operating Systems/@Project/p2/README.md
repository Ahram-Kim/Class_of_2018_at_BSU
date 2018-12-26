# Project 2/Multithreaded Mergesort

* Author: Ahram Kim
* Class: CS453 [Operating Systems] 01 #114055134


## Overview

This project is to convert the mergesort code to use multiple threads.

## Manifest

Makefile - build file for the project
multi-threaded/mergesort.c - build file for the project
multi-threaded/mergesortTest.c - main function of the projet
README.md - thie file

## Building the project

They gave the hint that 'Do not modif the give serial_mergesort function. Instead create a new 
parallel_mergesort function that will call serial_mergesort as a base case.' That really helped me
to understand where to start and how to get it. 

## Testing

Testing was all fine. I got them easier than the previous project. If I should say, dealing with
struct was confused to me still. 

### Valgrind

valgrind --leak-check=full ./mergesort 100000000 1
I have 0 errors from 0 contexts.

## Table

NumofThreads	runtime       speedup
-------------------------------------
1	        	12.01	      1
2       		6.39	      1.88
3       		3.83	      3.14
4       		3.82	      3.14
5       		3,82	      3.14
6	         	3.83	      3.14
7       		3.96	      3.03
8       		4.44	      2.70

## Sources used
The stackflow.com helped me a lot to understand the concept.

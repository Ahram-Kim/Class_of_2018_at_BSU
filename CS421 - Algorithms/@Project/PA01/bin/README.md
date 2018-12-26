# Programming Assignment #1

* Author: Ahram Kim
* Class: CS421
* Semester: Summer 2018

## Overview

This Java application is to solve the Knight's Tour proble using the exhaustive 
search with backtracking technique. There are many well known algorithms using
this technique such as the DFS search on a graph and the 8-queen problem.

## Compiling and Using

To compile, execute the following command in the main project directory:

$ javac KnightTour.java


Run the compiled class with the command with parameters:

$ java KnightTour <0/1/2 (no/heuristicI/heuristicII search)> <n> <x> <y>

## Discussion

// n = 7, x = 1, y = 1

$ javac KnightTour.java

$ java KnightTour 0 7 1 1
The total number of Moves is 254727173
21 46 41 2 23 26 9 
40 1 22 27 10 3 24 
47 20 45 42 25 8 11 
44 39 34 19 28 15 4 
33 48 43 36 7 12 29 
38 35 18 31 14 5 16 
49 32 37 6 17 30 13 

$ java KnightTour 1 7 1 1
The total number of Moves is 7329
21 40 11 2 23 30 13 
10 1 22 39 12 3 24 
41 20 9 36 31 14 29 
8 35 42 47 38 25 4 
19 46 37 32 43 28 15 
34 7 44 17 48 5 26 
45 18 33 6 27 16 49 

$ java KnightTour 2 7 1 1
The total number of Moves is 171
49 44 11 2 29 42 13 
10 1 46 43 12 3 28 
45 48 9 30 41 14 35 
8 25 40 47 36 27 4 
39 22 31 26 17 34 15 
24 7 20 37 32 5 18 
21 38 23 6 19 16 33 

# Project 06 / Memory Manager

* Author: Ahram Kim
* Class: CS453 [Operating Systems] 01 #114055134


## Overview

This project is to implement out own memory management.
This will replace malloc/free with my own memory management scheme based on Buddy system.

## Manifest

[user@localhost p6(master)]$ ls -R
.:
backpack.sh  buddy-non-preload  buddy-preload  buddy-system-movie.mpg  Buddy-System-notes.pdf  Makefile

./buddy-non-preload:
buddy.c  buddy.h  buddy-test.c  buddy-unit-test.c  Makefile  malloc-test.c

./buddy-preload:
buddy.c  buddy.h  Makefile  malloc-test.c


## Building the project

the following command to interpose for malloc using libbuddy.so:

    LD_LIBRARY_PATH=.:../list/lib LD_PRELOAD=./libbuddy.so ./mydash
    LD_LIBRARY_PATH=/home/faculty/amit/cs453/buddy/:../list/lib \ 
    D_PRELOAD=libbuddy.so ./mydash
    LD_LIBRARY_PATH=$(pwd):../list/lib LD_PRELOAD=./libbuddy.so ./mydash

the following command to time the interposed version

    time LD_LIBRARY_PATH=$(pwd) LD_PRELOAD=./libbuddy.so ./malloc-test <appropriate arguments>

## Testing

./buddy-test <num of tests> [random seed] [silent|terse|verbose|interactive]
./buddy-unit-test {silent|terse|verbose|interactive}

## Reflection and Self Assessment

During this project, I realize again c is the hardest language for me.
I do not like pointer arithmatic. I needed to know many things and recognized.
Even if I couldnt get done this project, this project helped me a lot to understand
memory and how it works. I read the provided reading paper. 


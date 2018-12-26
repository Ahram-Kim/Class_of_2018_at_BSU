****************
* P2 - Spec
* CS253
* September 22, 2017
* Ahram Kim
**************** 

OVERVIEW:

 This is a project for word count with simple matching

INCLUDED FILES:

 * Makefile
 * main.c - the file which has main code
 * data data1 data2 - the files I can test it with main.c

PROGRAM DESIGN AND IMPORTANT CONCEPTS:

 It makes me more familiarize with C. I used commnad-line development tools.
 I needed the skills about the basic string processing.
 I got some information about the command line arguments from the website
 https://www.gnu.org/software/libc/manual/html_node/Using-Getopt.html#Using-Getopt.
 I also write a C program that reads text from standard input(stdin) and counts the 
 number of words, lines, characters, and each digit fount in the input.
 Lastly, I implement simple matching on the streat of characters.

TESTING:
 $ ./wc-match -h
    : help message
 $ ./wc-match < data
    : test counting
 $ ./wc-match -m <data1 2> /dev/null 
    : test maching
    
 Console output will give the results after the program finishes.
 Before I run it the programming, I should have done 'make clean' and 'make' to update.

DISCUSSION:
 
 There are differences with java. I was familiar with java more than C.
 It makes me confused to make a code like 'getchar, EOF', make a function, 
 and especially treat the error.
 I need more practice to be familiar with C programming. 

----------------------------------------------------------------------------


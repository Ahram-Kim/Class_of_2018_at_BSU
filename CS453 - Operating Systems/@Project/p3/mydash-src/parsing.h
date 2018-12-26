#ifndef PARSER_H
#define PARSER_H

#include    <stdlib.h>
#include    <stdio.h>

char **parseInput(char *s, char *delimiter, int *numTokens);

void freeParsedList(char **token);

#endif /* PARSER_H */
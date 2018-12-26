

#include <mydash.h>

/**
 * Parse a given string into tokens using provide delimiters.
 * @param s the string to parse
 * @param delimiter a string of delimiters to use for tokenizing
 * @param numTokens  updates to number of toeksn found in the input string
 * @return a pointer to an array of tokens that must be freed by the caller
 */
char **parseInput(char *s, char *delimiter, int *numTokens) 
{
    char *nextToken;
    char **token = (char **) malloc (sizeof(char *) * MAX_TOKENS);

    /* tokenize the string s */
    nextToken = strtok(s, delimiter);
    *numTokens = 0;
    
    while (nextToken != NULL) {
        token[*numTokens] = (char *) malloc(sizeof(char) * (strlen(nextToken)+1));
        strcpy(token[*numTokens], nextToken);
        (*numTokens)++;
        nextToken = strtok(NULL, delimiter);
    }
    /*Now the tokens are copied into token[0..numTokens-1];*/
    return token;
}

#ifndef COMMANDS_H
#define COMMANDS_H

#include    <stdlib.h>
#include    <stdio.h>
#include	<string.h>
#include    "../../list/include/List.h"
#include    "../../list/include/Node.h"


void get_gitVersion(char **argv);
char *getDashPrompt(void);
void mydash_empty(char *line);
void mydash_cd(char *token);
void mydash_exit(char *token);
void mydash_history(void);
void mydash_exec(char **token, int background, char *unmodLine, struct list *list);
int mydash_commands(char **token, struct list *list);


#endif /* COMMANDS_H */
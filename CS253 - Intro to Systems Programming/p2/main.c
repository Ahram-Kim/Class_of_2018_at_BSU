#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#define IN 1 /* inside a word */
#define OUT 0 /* outside a word */

int wordCounting(char* matchValue);

/*
 * Accep two optional command line arguments.
 * The first (-m) is a flag that accepts a value <value> 
 * and the second(-h) is a help flag,
 * And print the counting and matching result.
 */
int main(int argc, char **argv)
{
  int c;
  int hFlag = 0;
  char *mValue = NULL;
  
  // 1. Command line arguments.
  while ((c = getopt (argc, argv, "m:h")) != -1)
  {
    switch (c)
      {
      case 'm':
        mValue = optarg;
        break;
      case 'h':
        hFlag = 1;
        break;
      case '?':
        if (optopt == 'm')
	{
          fprintf (stderr, "Option -%c requires an argument.\n", optopt);
	}
	else if (isprint (optopt))
	{
	  fprintf (stderr, "Unknown option `-%c'.\n", optopt);
	}
        else
	{
	  fprintf (stderr, "Unknown option character `\\x%x'.\n", optopt);
	}
	return 1;
      default:
        abort ();
      } 
  }

  if(hFlag)
  {
   printf("HELP!\n");
   return 0;
  }

  wordCounting(mValue);
  return 0;
}

// 2. Word counting
int wordCounting(char* matchValue)
{
  int array[10] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
  int words = 0;
  int chars = 0;
  int lines = 0;

  int index = 0;
  int ch;
  int state = OUT;
  int match = 0;

  for (index = 0; index < 10; ++index)
  {
      array[index] = 0;
  }

  //increment chars
  while((ch = getchar()) != EOF) 
  {
    // 3. Matching the value
    if(matchValue == NULL)
    {
      match = 0;
    }
    else if(ch == matchValue[index])
    {
      ++index;
      
      if(matchValue[index] == '\0')
      {
	++match;
      }
    }    
    else
    {
      index=0;
    }

    chars++;
    
    // increment lines
    if(ch == '\n')
    {
      lines++;
    }
    
    // increment words
    if((ch == ' ') || (ch == '\n') || (ch == '\t') )
    {
      state = OUT;
    }
    else if(state == OUT)
    {
      state = IN;
      words++;
    }
    
    // digit 0-9
    if(ch >= '0' && ch <= '9')
    {
      ++array[ch - '0'];
    }
  }
  
  printf ("words: %d\n", words);
  printf ("chars: %d\n", chars);
  printf ("lines: %d\n", lines);
  
  for(index = 0; index < 10; ++index)
  {
    printf ("digit %d: %d\n", index, array[index]); 
  } 
  printf ("matched Test: %d\n", match);
 
  return 0;
}


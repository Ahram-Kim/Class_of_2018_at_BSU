#!/bin/bash
if [ "$1" = "" ];then
  echo "usage: $0 <output file>"
  echo "   output file - the file to save output in"
  echo "   if output file exists, this script will append to it"
  exit 0;
fi
dest="$1"
if [ -f $dest ];
then
	echo
	echo "backpack.sh: output file $dest exists, appending to it"
	echo
fi

#Generate the students assignment
#Make should return no errors
echo >> $dest
echo "backpack.sh -- running smoke tests" >> $dest
echo >> $dest

make
if [ ! $? -eq 0 ];then
    echo "P1-ck: FAIL - make returned non-zero"  >> $dest
    exit 1
fi

#Make sure that there is an executable named mergesort at top level
if [ ! -x "multi-threaded/mergesort" ];then
    echo "P1-ck: FAIL - no exe named mergesort in multi-threaded subfolder" >> $dest
    exit 1
fi


echo >> $dest
echo "PASSED: Basic layout tests passed! " >> $dest
echo >> $dest

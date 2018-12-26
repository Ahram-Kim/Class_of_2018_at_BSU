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
make
if [ ! $? -eq 0 ];then
    echo "P1-ck: FAIL - make returned non-zero" 
    exit 1
fi

#Make sure that there is an executable named mydash at top level
if [ ! -x "ps-logger" ];then
    echo "P1-ck: FAIL - no exe named ps-logger at top level"
    exit 1
fi


#Make sure that they have a README.md file
if [ ! -e "README.md" ];then
    echo "P1-ck: FAIL - README.md missing"
    exit 1
fi


echo "PASSED: All smoke tests passed!" >> $dest

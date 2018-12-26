#!/bin/bash
if [ "$1" = "" ];then
  echo "usage: $0 <output file>"
  echo "   output file - the file to save the grades in"
  exit 0;
fi

dest="$1"

#clean up any old junk
rm -f expected actual

#Generate the students assignment
make

#Make sure the program built
if [ ! -e "wc-match" ];then
  echo "HW1: FAIL reason: Did not build" >> $dest
  exit 1 # fail the build
fi

#Students program executable name
EXE=wc-match

#Run the students file with sample input data
./$EXE < data1 > actual;

#Run the grader with the sample data
./grader < data1 > expected 2> /dev/null

diff -q  expected actual

if [ $? -eq 0 ];then
    echo "HW1: Basic functionally PASS" >> $dest
else
    echo "HW1: Output from your code does not match the grader" >> $dest
    exit 1
fi

make clean

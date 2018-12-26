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

#Make sure that there is an executable named mydash at top level
if [ ! -x "mydash" ];then
    echo "P1-ck: FAIL - no exe named mydash at top level" >> $dest
    exit 1
fi

#Make sure that they have the library
#if [ ! -e "../p0/lib/libmylib.so" ];then
#    echo "P1-ck: FAIL - libmylib.so missing (your linked list)" >> $dest
#    exit 1
#fi

#Make sure that they have  a screenshot of project management tool (like Trello)
if [ ! -e "pm.png" ];then
    echo "P1-ck: FAIL - pm.png missing (screenshot of your project management tool)" >> $dest
    exit 1
fi


TESTFILES=smoketest-files


echo "START: Testing normal command execution" >> $dest
cat $TESTFILES/test-normal-command | ./mydash | grep git_version.c > /dev/null
if test "$?" = "1"
then
    echo "P1-ck: FAIL - commands with arguments do not work" >> $dest
	exit 1
fi
echo "  END: Testing normal command execution" >> $dest


echo "START: Testing use of readline for filename completion and history" >> $dest
grep -r "readline.h"  mydash-src/*.c mydash-src/*.h > /dev/null
if test "$?" = "1"
then
    echo "P1-ck: FAIL - did not find readline.h being used" >> $dest
	exit 1
fi
grep -r "readline"  mydash-src/*.c  > /dev/null
if test "$?" = "1"
then
    echo "P1-ck: FAIL - did not find readline being used" >> $dest
	exit 1
fi
echo "  END: Testing use of readline" >> $dest


echo "START: Testing the empty command" >> $dest
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:../p0/lib
cat $TESTFILES/test-empty | ./mydash
if [ ! $? -eq 0 ];then
        echo "P1-ck: FAIL - empty command" >> $dest
	exit 1
fi
echo "  END: Testing the empty command" >> $dest


echo "START: Testing EOF" >> $dest
cat $TESTFILES/test-eof | ./mydash
if [ ! $? -eq 0 ];then
        echo "P1-ck: FAIL - EOF" >> $dest
	exit 1
fi
echo "  END: Testing EOF" >> $dest


echo "START: Testing exit" >> $dest
cat $TESTFILES/test-exit | ./mydash
if [ ! $? -eq 0 ];then
        echo "P1-ck: FAIL - exit command" >> $dest
	exit 1
fi
echo "  END: Testing exit" >> $dest


echo "START: Testing cd" >> $dest
cat $TESTFILES/test-cd | ./mydash
if [ ! $? -eq 0 ];then
        echo "P1-ck: FAIL - exit return code from mydash" >> $dest
	exit 1
fi
if [ ! -e ../"___CD-TEST___" ];then
    echo "P1-ck: FAIL - ACK!!! Tried to create a file named ___CD-TEST___ and we don't know where it went!" >> $dest
    exit 1
else
    rm ../"___CD-TEST___"
fi
echo "  END: Testing cd " >> $dest


echo "START: Testing version" >> $dest
./mydash -v > $$.log &
ps | grep mydash
if [ $? -eq 0 ];then
    echo "P1-ck: FAIL - mydash did not quit after processing -v command line argument " >> $dest
	/bin/rm -f $$.log
	exit 1
fi
/bin/rm -f $$.log
echo "  END: Testing version" >> $dest


#echo "START: Testing use of list library" >> $dest
#grep -r "createList"  mydash-src/*.c mydash-src/*.h > /dev/null
#if test "$?" = "1"
#then
#    echo "P1-ck: FAIL - did not find list library being used" >> $dest
#	exit 1
#fi
#echo "  END: Testing use of list library" >> $dest


echo >> $dest
echo "Valgrind was NOT run, make sure and test with valgrind as you progress further" >> $dest
echo "PASSED: Basic smoke tests passed! Please test for more functionality as you continue." >> $dest
echo >> $dest

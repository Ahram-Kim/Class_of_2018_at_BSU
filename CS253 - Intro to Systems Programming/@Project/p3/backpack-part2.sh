#!/bin/bash
if [ $# != 1 ];then
	echo "usage: $0 <output file>"
	echo "   output file - the file to save the grades in"
	exit 0;
fi

dest="$1"

# Compares program output to expected output for given input files.
# Params
#	$1 : The name of the executable.
#	$2 : Test input/output directory.
function test_prog()
{
	exe=$1
	testdir=$2

	# Make sure the executable exists before testing.
	if [ ! -e $exe ];then
		echo "P2: FAIL reason: Did not build the right executable ($exe)" >> $dest
		exit 1
	fi

	# Feed every test input file into program and compare results
	for f in $testdir/*.in
	do
		basename=${f%.in}
		actual=$basename.smash
		expected=$basename.out

		# Run executable and store results into .smash output file
		./$exe < $f > $actual 2> $actual.err

		# Make sure program exited with success
		status=$?
		if [ "$status" != 0 ];then
			echo "P2: FAIL $basename. Unsuccessful return value ($status)." >> $dest
			exit 1
		fi

		# Compare expected output to actual output
		diff $expected $actual > $basename.diff
		if [ "$?" != 0 ];then
			echo "P2: FAIL $basename. See $basename.diff for differences." >> $dest
			exit 1
		else
			rm -f $basename.diff $actual $actual.err
		fi
	done
}

function test_build()
{
	make
	if [ "$?" != 0 ];then
		echo "P2: FAIL make" >> $dest
		exit 1
	fi
}

function test_clean()
{
	make clean
	if [ "$?" != 0 ];then
		echo "P2: FAIL make clean" >> $dest
		exit 1
	fi
}

# Clean before we get going.
test_clean

# Test build on fresh directory.
test_build

# Test program.
test_prog "smash" "test"

# Clean when we are done.
test_clean

echo "PASS" >> $dest

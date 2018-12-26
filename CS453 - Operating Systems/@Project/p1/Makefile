all: mydash-project

mydash-project:
	cd mydash-src; make

dox:
	echo "Generating documentation using doxygen..."
	doxygen doxygen-config > doxygen.log 
	echo "Use konqueror docs/html/index.html to see docs (or any other browser)"

clean:
	/bin/rm -f *.o $(PROGS) a.out core *.d mydash
	cd other-progs; make clean
	cd mydash-src; make clean



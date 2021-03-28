
# run: 'make javadoc' to build javadoc for all java files

javadoc:
	mkdir -p doc
	find . -type f | grep .java | xargs javadoc -d doc

clean:
	rm -r doc/

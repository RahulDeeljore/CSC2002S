# Assignment3 program makefile
# Rahul Deeljore
# 17/09/2018

JAVAC=/usr/bin/javac
.SUFFIXES: .java .class

SRCDIR=src
BINDIR=bin
JAVADOC=javadoc
DOCDIR=docs

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<

CLASSES=sequential.class parallel.class

CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

doc:
	$(JAVADOC) -sourcepath $(SRCDIR)/sequential.java $(SRCDIR)/parallel.java $(SRCDIR) -d $(DOCDIR)

clean:
	rm $(BINDIR)/*.class
run:
	java -cp bin sequential
run1: 
	java -cp bin parallel


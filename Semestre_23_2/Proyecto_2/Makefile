
# Proyecto 2
# Mexicano Ixtepan Alejandro
# 4CM13
# Makefile

JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
		  App.java \
		  PoligonoReg.java \
		  
default: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
	
run:
	java App

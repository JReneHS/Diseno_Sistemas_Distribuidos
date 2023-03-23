
# Proyecto 2
# Hernandez Sanchez Juan Rene
# 4CM13
# Makefile

JFLAGS = -g
JC = javac
PK = Proyecto

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
		Proyecto/SimpleGui2.java \
		Proyecto/PoligonoReg.java \
		Proyecto/Coordenada.java \
		  
default: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
	
run:
	java Proyecto.SimpleGui2

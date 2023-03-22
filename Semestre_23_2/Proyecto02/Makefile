
# Proyecto 2
# Hernandez Sanchez Juan Rene
# 4CM13
# Makefile

JFLAGS = -g
JC = javac
PK = Proyecto

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $(PK)/$*.java

CLASSES = \
		SimpleGui2.java \
		App.java \
		PoligonoReg.java \
		Coordenada.java \
		  
default: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
	
run:
	java $(PK).SimpleGui2

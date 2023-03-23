JFLAGS = -g

JC = javac

 

.SUFFIXES: .java .class

 

.java.class:

	$(JC) $(JFLAGS) $*.java



CLASSES = \
		PruebaPoligIrreg.java \
		PoligonoIrreg.java \
		Coordenada.java \
		SortByMagnitud.java \


default: classes



classes: $(CLASSES:.java=.class)



clean:

	$(RM) *.class

run:

	java PruebaPoligIrreg
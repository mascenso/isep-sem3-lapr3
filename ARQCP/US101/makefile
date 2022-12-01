# A Makefile with variables and suffix rules
# variaveis
INCLUDES = demo_pcg.h
SOURCES = demo_pcg.s main.c
OBJFILES = demo_pcg.o main.o
EXEC = demo_pcg
# Suffix rules
.SUFFIXES : .c .o
# How to build an object .o from a code file .c ; $< -- file name
.c.o:
	gcc -Wall -g -c $<
${EXEC}: ${OBJFILES}
	gcc -Wall -g -o ${EXEC} ${OBJFILES}
${OBJFILES}: ${SOURCES} ${INCLUDES}
run: ${EXEC}
	./${EXEC}
clean:
	rm -f ${OBJFILES} ${EXEC}


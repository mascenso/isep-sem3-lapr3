#*--*--* ARQCP *--*--* Function Demos *--*--*
#Creates a Matrix of n x n elements

#--#--#--#--#--#--#--#--#--#
INCLUDES = ../matrix/new_matrix.h write_csv.h
SOURCES = ../matrix/new_matrix.c write_csv.c main.c
OBJFILES = ../matrix/new_matrix.o write_csv.o main.o

OBJFILES2 = new_matrix.o write_csv.o main.o
EXEC = ./matrix

#--#--#--#--#--#--#--#--#--#

#Suffix rules
.SUFFIXES : .c .o

.c.o:
	gcc -Wall -g -c $<

.SUFFIXES : .s .o

.s.o:
	gcc -Wall -g -c $<

${EXEC}: ${OBJFILES}
	gcc -Wall -g -o ${EXEC} ${OBJFILES2}

${OBJFILES}: ${SOURCES} ${INCLUDES}

run: ${EXEC}
	./${EXEC}

clean:
	rm -f ${OBJFILES2} ${EXEC}
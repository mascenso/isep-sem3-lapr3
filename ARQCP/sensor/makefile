#*--*--* ARQCP *--*--* Function Demos *--*--*
#Create a sensor struct function: US110
#--#--#--#--#--#--#--#--#--#
INCLUDES = sensor.h sensores.h
SOURCES = main.c sensor.c sensores.s
OBJFILES = main.o sensor.o sensores.o

OBJFILES2 = main.o sensor.o sensores.o
EXEC = ./sensor

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

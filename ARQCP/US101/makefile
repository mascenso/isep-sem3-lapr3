#ARQCP/Makefile
# Todo: Improve file organization
#--#--#--#--#--#--#--#--#--#
INCLUDES = main.h random/pcg32_random_r.h random/random.h sensor/sensor.h
SOURCES = main.c random/random.c random/pcg32_random_r.s sensor/sensor.c
OBJFILES = main.o random/random.o random/pcg32_random_r.o sensor/sensor.o

OBJFILES2 = main.o random.o pcg32_random_r.o sensor.o
EXEC = ./sensores

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

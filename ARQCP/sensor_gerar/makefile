#*--*--* ARQCP *--*--* Function Demos *--*--*
#Gerar valores para os dados dos sensores: US103
#--#--#--#--#--#--#--#--#--#
INCLUDES = ../random/random.h ../random/pcg32_random_r.h sensores.h
SOURCES = ../random/random.c ../random/pcg32_random_r.s sensores.s main.c
OBJFILES = ../random/random.o ../random/pcg32_random_r.o sensores.o main.o

OBJFILES2 =  random.o pcg32_random_r.o sensores.o main.o
EXEC = ./sensor_gerar

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

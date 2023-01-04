# exemplo de makefile com variaveis
# variaveis
INCLUDES = read_config.h
SOURCES =  read_config.c main.c
OBJFILES = read_config.o main.o
EXEC = read_cfg

# regras de sufixo
.SUFFIXES : .c .o
.SUFFIXES : .s .o
# como transformar um .c num .o ; $< -- nome do ficheiro
.c.o:
	gcc -Wall -g -c $<
# como transformar um .s num .o ; $< -- nome do ficheiro
.s.o:
	gcc -Wall -g -c $<	
	
${EXEC}: ${OBJFILES}
	gcc -Wall -g -o ${EXEC} ${OBJFILES}
	
${OBJFILES}: ${SOURCES} ${INCLUDES}

run: ${EXEC}
	./${EXEC}
	
clean:
	rm -f ${OBJFILES} ${EXEC}
	
#debug	
debug: ${EXEC}
	ddd ${EXEC} &	
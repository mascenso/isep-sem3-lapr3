#ARQCP/Makefile
# File organization:

#-Configuration: - folder config -
CONFIG_INCLUDES = config/read_config.h
CONFIG_SOURCES = config/read_config.c
CONFIG_OBJECTS = config/read_config.o

#-Random number generator: - folder random -
RANDOM_INCLUDES = random/random.h random/pcg32_random_r.h
RANDOM_SOURCES = random/random.c random/pcg32_random_r.s
RANDOM_OBJECTS = random/random.o random/pcg32_random_r.o

#-gerar dados para o sensor - folder sensor -
SENSOR_GERAR_INCLUDES = sensor_gerar/sensores.h sensor_gerar/inserir_values.h
SENSOR_GERAR_SOURCES = sensor_gerar/sensores.s sensor_gerar/inserir_values.c
SENSOR_GERAR_OBJECTS = sensor_gerar/sensores.o sensor_gerar/inserir_values.o

#-criar um sensor - folder sensor -
SENSOR_INCLUDES = sensor/sensor.h
SENSOR_SOURCES = sensor/sensor.c
SENSOR_OBJECTS = sensor/sensor.o

#-array de sensores - folder sensores -
SENSORES_INCLUDES = sensores/all_sensors.h
SENSORES_SOURCES = sensores/all_sensors.c
SENSORES_OBJECTS = sensores/all_sensors.o

#--#--#--#--#--#--#--#--#--#--#--#--#--#--#--#--#--#--#--#--#--#
INCLUDES =  $(CONFIG_INCLUDES) $(RANDOM_INCLUDES) $(SENSOR_GERAR_INCLUDES) $(SENSOR_INCLUDES) $(SENSORES_INCLUDES)
SOURCES =  main.c $(CONFIG_INCLUDES) $(RANDOM_SOURCES)  $(SENSOR_GERAR_SOURCES) $(SENSOR_SOURCES) $(SENSORES_SOURCES)
OBJFILES = main.o $(CONFIG_OBJECTS) $(RANDOM_OBJECTS)  $(SENSOR_GERAR_OBJECTS) $(SENSOR_OBJECTS) $(SENSORES_OBJECTS)

OBJFILES2 = main.o read_config.o random.o pcg32_random_r.o sensor.o sensores.o inserir_values.o all_sensors.o
EXEC = ./mainprogram
#--#--#--#--#--#--#--#--#--#--#--#--#--#--#--#--#--#--#--#--#--#

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

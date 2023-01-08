#ifndef SENSOR_H
#define SENSOR_H

#include "../config/read_config.h"

typedef struct
{
    unsigned short id;
    unsigned char sensor_type;
    // limites do sensor
    unsigned short max_limit;
    unsigned short min_limit;
    // frequency de leituras (em segundos)
    unsigned long frequency;
    // tamanho do array de leituras
    unsigned long readings_size;
    // array de leituras diárias 
    unsigned short *readings;
    //...
    // adicionar o que acharem conveniente
} Sensor;

//[id][id][id][id] [id][id][id][id]  // short: 2 bytes
//[st][st][][] [][] [][] [][] [][]  // ptr: 2 bytes
//[max][max][max][max] [max][max][max][max]  // short: 2 bytes
//[min][min][min][min] [min][min][min][min]  // short: 2 bytes
//[freq][freq][freq][freq] [freq][freq][freq][freq] [freq][freq][freq][freq] [freq][freq][freq][freq] // long: 4 bytes
//[rs][rs][rs][rs] [rs][rs][rs][rs] [rs][rs][rs][rs] [rs][rs][rs][rs] // long: 4 bytes
//[r][r][r][r] [r][r][r][r]  // ptr: 2 bytes

/**
    * @brief Define *readings size.
    * The size is defined by the daily frequency.
    * The frequency is in seconds, and its readed by the function.
    * The number of seconds in a day is 86400.
    * The size is defined by the formula: 86400 / frequency.
    * @param sensor Sensor to be defined.
    * @return 0 if the size was defined, -1 if the size was not defined.
    */
int define_readings_size(Sensor *sensor);

//Function to create a sensor
Sensor *create_sensor(unsigned short id, unsigned char sensor_type);

//Function to destroy (free) a sensor
void destroy_sensor(Sensor *sensor);

// unsigned long read_frequency_from_config_properties(unsigned char *sensor_type);

void print_sensor(Sensor *sensor);

#endif
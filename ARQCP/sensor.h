#ifndef SENSOR_H
#define SENSOR_H

typedef struct
{
    unsigned short id;
    unsigned char sensor_type;
    unsigned short max_limit;
    // limites do sensor
    unsigned short min_limit;
    unsigned long frequency;
    // frequency de leituras (em segundos)
    unsigned long readings_size; // tamanho do array de leituras
    unsigned short *readings;
    // array de leituras diárias
    //...
    // adicionar o que acharem conveniente
} Sensor;


//Function to create a sensor
Sensor *create_sensor(unsigned short id); // By now

//Function to destroy (free) a sensor
void destroy_sensor(Sensor *sensor);




#endif
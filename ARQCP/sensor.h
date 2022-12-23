#ifndef SENSOR_H
#define SENSOR_H

typedef struct
{
    unsigned short id;
    unsigned char *sensor_type; //[2]
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


//Function to create a sensor
Sensor *create_sensor(unsigned short id, unsigned char *sensor_type); // By now

//Function to destroy (free) a sensor
void destroy_sensor(Sensor *sensor);




#endif
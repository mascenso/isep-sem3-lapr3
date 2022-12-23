#include "sensor.h"
#include <stdlib.h>
#include <stdio.h>

unsigned long read_frequency_from_config_properties() {
    // get the frequency from the config.properties file

    unsigned long frequency;

    char line[100];  // buffer to hold a line of text from the file
    FILE* fp;

    fp = fopen("../config.properties", "r");
    
    if (fp == NULL) {
        printf("Error opening file");
        return 1;
    }

    while (fgets(line, sizeof(line), fp) != NULL) {
        if (sscanf(line, "frequency_sensor = %lu", &frequency) == 1) {
            // frequency variable was found and read successfully
            break;
        }
    }

    fclose(fp);

    return frequency;  // return the value of the frequency variable
}


//Function to create a sensor
Sensor *create_sensor(unsigned short id, unsigned char *sensor_type){
    Sensor *sensor = (Sensor*)malloc(sizeof(Sensor));
    sensor->id = id;
    sensor->sensor_type = sensor_type;
    sensor->max_limit = 0;
    sensor->min_limit = 0;
    sensor->frequency = 0;
    sensor->readings_size = 0;
    sensor->readings = 0;
    return sensor;
} // By now

//Function to destroy (free) a sensor
void destroy_sensor(Sensor *sensor){
    free(sensor);
};

void print_sensor(Sensor *sensor){
    printf("Sensor ID: %d | Sensor Type: %s", sensor->id, sensor->sensor_type);
}
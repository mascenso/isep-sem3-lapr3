#include "sensor.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>


Sensor *create_sensor(unsigned short id, unsigned char sensor_type[2]){
    Sensor *sensor = (Sensor*)malloc(sizeof(Sensor));
    sensor->id = id;
    sensor->sensor_type[0] = sensor_type[0];
    sensor->sensor_type[1] = sensor_type[1];
    sensor->max_limit = 0;
    sensor->min_limit = 0;
    sensor->frequency = 0;
    sensor->readings_size = 0;
    sensor->readings = 0;
    return sensor;
}; // By now


void destroy_sensor(Sensor *sensor){
    free(sensor);
};

void print_sensor(Sensor *sensor){
    printf("Sensor ID: %d | Sensor Type: %s\n", sensor->id, sensor->sensor_type);
};

// unsigned long read_frequency_from_config_properties(unsigned char *sensor_type) {
//     // get the frequency from the config.properties file
//
//     unsigned long frequency;
//
//     char line[100];  // buffer to hold a line of text from the file
//     FILE* fp;
//
//     fp = fopen("../config.properties", "r");
//
//     if (fp == NULL) {
//         printf("Error opening file");
//         return 1;
//     }
//
//     while (fgets(line, sizeof(line), fp) != NULL) {
//         if (sscanf(line, "frequency_sensor_%c%c = %lu", &sensor_type[0], &sensor_type[1], &frequency) == 1) {
//             // frequency variable was found and read successfully
//             break;
//         }
//     }
//
//     fclose(fp);
//
//     return frequency;  // return the value of the frequency variable
// };
//
// int define_readings_size(Sensor *sensor) {
//
//     unsigned long frequency = read_frequency_from_config_properties(sensor->sensor_type);
//
//     if (frequency == 0) {
//         printf("Error reading frequency from config.properties file");
//         return 1;
//     }
//
//     sensor->readings_size = 86400 / frequency;  // 86400 seconds in a day
//
//     // Reallocate memory for the unsigned short *readings array;
//     sensor->readings = (unsigned short *) realloc(sensor->readings, sensor->readings_size * sizeof(unsigned short));
//
//     return 0;
// };
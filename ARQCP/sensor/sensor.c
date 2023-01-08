#include "sensor.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

        Sensor *create_sensor(unsigned short id, unsigned char sensor_type){
            Sensor *sensor = (Sensor*)malloc(sizeof(Sensor));
            sensor->id = id;
            sensor->sensor_type = sensor_type;
            sensor->max_limit = 0;
            sensor->min_limit = 0;
            sensor->frequency = 0;
            sensor->readings_size = 1;
            sensor->readings = (unsigned short *) malloc(sizeof(unsigned short));
            return sensor;
        };

        void destroy_sensor(Sensor *sensor){
            free(sensor);
        };

        void print_sensor(Sensor *sensor){
            printf("Sensor ID: %d | Sensor Type: %d\n", sensor->id, sensor->sensor_type);
            printf("Freq: %ld", sensor->frequency);
        };

        //getters
        unsigned short get_id(Sensor *sensor){
            return sensor->id;
        };

        unsigned char get_sensor_type(Sensor *sensor){
            return sensor->sensor_type;
        };

        unsigned short get_max_limit(Sensor *sensor){
            return sensor->max_limit;
        };

        unsigned short get_min_limit(Sensor *sensor){
            return sensor->min_limit;
        };

        unsigned short get_frequency(Sensor *sensor){
            return sensor->frequency;
        };

        unsigned short get_readings_size(Sensor *sensor){
            return sensor->readings_size;
        };

        unsigned short *get_readings(Sensor *sensor){
            return sensor->readings;
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
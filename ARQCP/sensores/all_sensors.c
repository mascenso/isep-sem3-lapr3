#include "all_sensors.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

    //stores the number of sensors of each type
    int idCount[6];
    //stores the last created sensor id of each type
    unsigned char lastId[6];

    Sensor **sensor_array;

    Sensor **create_sensor_array(int types){
        sensor_array = (Sensor **)malloc(types * sizeof(Sensor *));
        for(int i = 0; i < types; i++){
            sensor_array[i] = NULL;
        }
        return sensor_array;
    }

    //Add a sensor to the matrix
    void add_sensor(Sensor **sensor_array, Sensor *sensor, int type){
       // Realloc at index type to get one more sensor
        sensor_array[type] = realloc(sensor_array[type], (idCount[type] + 1) * sizeof(Sensor));
        // Add the sensor to the array
        sensor_array[type][idCount[type]] = *sensor;
        // Increment the number of sensors of that type
        idCount[type]++;
        // Increment the last id of that type
        lastId[type] = lastId[type] + 1;
    }

    //Remove a sensor from the matrix
    void remove_sensor(Sensor **sensor_array, unsigned short id){
        // Get the type of the sensor
        int type = id >> 8;

        // If the sensor is the last one, just free the memory
        if(idCount[type] == 1){
            free(sensor_array[type]);
            sensor_array[type] = NULL;
            idCount[type] = 0;
            lastId[type] = 0;
        }
        //find the sensor to remove
        for(int i = 0; i < idCount[type]; i++){
            if(sensor_array[type][i].id == id){
                // If the sensor is not the last one, move the last sensor to the place of the removed one
                sensor_array[type][i] = sensor_array[type][idCount[type] - 1];
                // Realloc the array to remove the last sensor
                sensor_array[type] = realloc(sensor_array[type], (idCount[type] - 1) * sizeof(Sensor));
                // Decrement the number of sensors of that type
                idCount[type]--;
                // Decrement the last id of that type
                lastId[type] = lastId[type] - 1;
                break;
            }
        }
    }

    //Get a sensor from the matrix
    Sensor *get_sensor(Sensor **sensor_array, unsigned short id){
        // Get the type of the sensor
        int type = id >> 8;
        //find the sensor
        for(int i = 0; i < idCount[type]; i++){
            if(sensor_array[type][i].id == id){
                return &sensor_array[type][i];
            }
        }
        return NULL;
    }

    //Change frequency of a sensor
    void change_frequency(Sensor **sensor_array, unsigned short id, unsigned short frequency){
        // Get the type of the sensor
        int type = id >> 8;
        //find the sensor
        for(int i = 0; i < idCount[type]; i++){
            if(sensor_array[type][i].id == id){
                sensor_array[type][i].frequency = frequency;
                break;
            }
        }
    }


    void print_sensor_array(Sensor **sensor_array){
        //printf array idCount
        for(int i = 0; i < 6; i++){
            printf("Type %d: %d sensors\n", i, idCount[i]);
        }
        //printf array sensor_array
        //if sensor_array[i] != NULL
        for(int i = 0; i < 6; i++){
            if(sensor_array[i] != NULL){
                for(int j = 0; j < idCount[i]; j++){
                    print_id (sensor_array[i][j].id);
                    printf("freq=%ds\n", sensor_array[i][j].frequency);
                }
            }
        }

    }

    Sensor *create_sensor_and_create_id(unsigned char sensor_type){
        unsigned char id_number = lastId[sensor_type] + 1;
        unsigned short newId = createId(id_number, sensor_type);

        Sensor *sensor = create_sensor(newId, sensor_type);

        printf("Sensor created with: ");  print_id(sensor->id);
        add_sensor(sensor_array, sensor, sensor_type);
        return sensor;
    }

    unsigned short createId(unsigned char id_number, unsigned char sensor_type){
        unsigned short id = sensor_type;
        id = id << 8;
        id = id | id_number;
        return id;
    }

    void print_id (unsigned short id){
        unsigned char idNumber = id & 0x00FF;
        unsigned char idType = id >> 8;
        printf("Sensor type: %d, Sensor id_num: %d (ID%d)\n", idType, idNumber, id);
    }
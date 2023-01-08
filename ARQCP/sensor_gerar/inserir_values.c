#include <stdio.h>
#include <stdlib.h>
#include "inserir_values.h"
#include "sensores.h"

//Inserts a new value in the array
    void inserir_value (unsigned short *readings, unsigned long readings_size, unsigned short value){

        //Reallocation of the array
        readings = (unsigned short *) realloc(readings, (readings_size + 1) * sizeof(unsigned short));

        //Insertion of the value
        readings[readings_size] = value;

        //Increment of the readings size
        readings_size++;
    }

//Allocates the array
    unsigned short *inicializar_array(unsigned short first_value){

        //Allocation of the array
        unsigned short *readings = (unsigned short *) malloc(sizeof(unsigned short));

        //Insertion of the first value
        readings[0] = first_value;

        //Return of the array
        return readings;
    }


    //Function that returns the average of the values in the array
    float media(unsigned short *readings, unsigned long readings_size){

        //Variable that will store the sum of the values
        unsigned long sum = 0;

        //Variable that will store the average
        float average;

        //For that will sum all the values in the array
        for(int i = 0; i < readings_size; i++){
            sum += readings[i];
        }

        //Calculation of the average
        average = (float) sum / readings_size;

        //Return of the average
        return average;
    }


    //Function that returns the maximum value in the array
    unsigned short maximo(unsigned short *readings, unsigned long readings_size){

        //Variable that will store the maximum value
        unsigned short max = 0;

        //For that will search for the maximum value
        for(int i = 0; i < readings_size; i++){
            if(readings[i] > max){
                max = readings[i];
            }
        }

        //Return of the maximum value
        return max;
    }


    //Function that returns the minimum value in the array
    unsigned short minimo(unsigned short *readings, unsigned long readings_size){

        //Variable that will store the minimum value
        unsigned short min = 65535;

        //For that will search for the minimum value
        for(int i = 0; i < readings_size; i++){
            if(readings[i] < min){
                min = readings[i];
            }
        }

        //Return of the minimum value
        return min;
    }


    //Prints the values in the array
    //Receives the array, the size of the array and the name of the sensor
    void print_array(unsigned short *readings, unsigned long readings_size, char *sensor_name){

        //Prints the name of the sensor
        printf("%s: ", sensor_name);
        //For that will print all the values in the array
        for(int i = 0; i < readings_size; i++){
            printf("[%d]", readings[i]);
        }
        printf("\n");
    }

    //Free the array
    void free_array(unsigned short *readings){
        free(readings);
    }
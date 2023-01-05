#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include "all_sensors.h"

int main()
{
            //create a sensor_array to hold 6 types of sensors
            Sensor **sensors = create_sensor_array(6);

            // Sensor *create_sensor_and_create_id(unsigned char sensor_type);
            create_sensor_and_create_id(0);
            create_sensor_and_create_id(0);
            create_sensor_and_create_id(2);
            create_sensor_and_create_id(5);

            print_sensor_array(sensors);

            printf("******\n");

            // void remove_sensor(Sensor **sensor_array, unsigned short id);
            remove_sensor(sensors, 513);

            print_sensor_array(sensors);

}



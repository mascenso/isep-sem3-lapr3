#include "sensor.h"

int main()
{

unsigned short id = 0;
//Code temperature sensor: "TA"
unsigned char type = 0;

// Sensor *create_sensor(unsigned short id, unsigned char sensor_type[2]){
Sensor *sensor1 = create_sensor(id, type);

//print sensor
print_sensor(sensor1);

//free sensor
destroy_sensor(sensor1);

}



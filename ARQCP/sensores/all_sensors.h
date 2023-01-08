#ifndef ALL_SENSORS_H
#define ALL_SENSORS_H
#include "../sensor/sensor.h"

// Nr of sensors types
#define S_NUMBER 6
//static const char *SENSOR_NAMES[S_NUMBER] = {"TA", "VV"  , "DV", "HA", "HS", "Pl"};
//static const char *SENSOR_UNITS[S_NUMBER] =  {"ºc", "km/h", "º" , "%" , "%" , "mm"};

Sensor **create_sensor_array(int types);
Sensor **resize_sensor_array(Sensor **sensor_array, int types);

unsigned short createId(unsigned char id, unsigned char sensor_type);
void add_sensor(Sensor **sensor_array, Sensor *sensor, int type);
void remove_sensor(Sensor **sensor_array, unsigned short id);

void free_sensor_array(Sensor **sensor_array, int types, int sensors);
void print_sensor_array(Sensor **sensor_array);
void print_id (unsigned short id);
unsigned short createId(unsigned char id_number, unsigned char sensor_type);

void change_frequency(Sensor **sensor_array, unsigned short id, unsigned short frequency);

Sensor *create_sensor_and_create_id(unsigned char sensor_type);
void free_all_sensors(Sensor **sensor_array);

#endif
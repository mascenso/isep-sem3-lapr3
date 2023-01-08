#ifndef MAIN_H
#define MAIN_H

#define SECONDS_DAY 86400;

// Nr of sensors types
#define S_NUMBER 6
const char *SENSOR_NAMES[S_NUMBER] = {"TA", "VV"  , "DV", "HA", "HS", "Pl"};
const char *SENSOR_UNITS[S_NUMBER] =  {"ºc", "km/h", "º" , "%" , "%" , "mm"};

// define types of sensors -  temperatura, velocidade do vento, direcção do vento, humidade atmosférica, humidade do solo, pluviosidade.

// TA – Sensor de temperatura atmosférica
//  graus centígrados para a temperatura atmosferica
#define SENSOR_ATMOSPHERIC_TEMPERATURE "TA"
#define S_TEMP_INDEX 0

// VV – Sensor de velocidade do vento
//  km/h para a velocidade do vento
#define SENSOR_WIND_SPEED "VV"
#define S_WIND_INDEX 1

// DV – Sensor de direcção do vento
//  graus para a direcção do vento
#define SENSOR_WIND_DIRECTION "DV"
#define S_WIND_DIR_INDEX 2

// HA – Sensor de humidade do ar
//  percentagem para a humidade atmosférica
#define SENSOR_ATMOSPHERIC_HUMIDITY "HA"
#define S_AIR_HUM_INDEX 3

// HS – Sensor de humidade do solo
//  percentagem para a humidade do solo
#define SENSOR_SOIL_HUMIDITY "HS"
#define S_SOIL_HUM_INDEX 4

// Pl – Sensor de pluviosidade
//  mm para a pluviosidade
#define SENSOR_PLUVIOSITY "Pl"
#define S_PLUVIO_INDEX 5

// Falam deles em Bases de Dados mas vamos ignorar *--*--*--*--*--*--*--*--*
// TS – Sensor de temperatura do solo
//  graus centígrados para a temperatura solo
#define SENSOR_SOIL_TEMPERATURE "TS"
#define SENSOR_SOIL_TEMPERATURE_INDEX 6

// PA – Sensor de pressão atmosférica
//  graus relativamente ao Norte para a direção do vento
#define SENSOR_ATMOSPHERIC_PRESSURE "PA"
#define SENSOR_ATMOSPHERIC_PRESSURE_INDEX 7
// Falam deles em Bases de Dados mas vamos ignorar *--*--*--*--*--*--*--*--*

// Nr of measurements (min, max, avg)


void manage_data();
int check_reading_limits(int min, int max, char maxerrors, unsigned char first_read, unsigned char sensor_type);


#define S_NR_MEASURES 3
#define S_TEMP_QTY 7
#define NR_READS 20
void get_s_measures(char *ptrInput, float *ptrOutput);

#endif

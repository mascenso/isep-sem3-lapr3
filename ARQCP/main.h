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

// Nr of sensors by type
#define S_TEMP_QTY 7
#define S_WIND_QTY 5
#define S_WIND_DIR_QTY 2
#define S_AIR_HUM_QTY 2
#define S_SOIL_HUM_QTY 2
#define S_PLUVIO_QTY 3

// Time frequency for each sensor type
#define S_TEMP_FT 2
#define S_WIND_FT 5
#define S_WIND_DIR_FT 5
#define S_AIR_HUM_FT 5
#define S_SOIL_HUM_FT 3
#define S_PLUVIO_FT 10

// Define limits by sensor
#define S_TEMP_MIN -20
#define S_TEMP_MAX 60
#define S_TEMP_MAX_ERRORS 2

#define S_WIND_MIN 0
#define S_WIND_MAX 255
#define S_WIND_MAX_ERRORS 2

#define S_WIND_DIR_MIN 0
#define S_WIND_DIR_MAX 359
#define S_WIND_DIR_MAX_ERRORS 2

#define S_AIR_HUM_MIN 0
#define S_AIR_HUM_MAX 100
#define S_AIR_HUM_MAX_ERRORS 2

#define S_SOIL_HUM_MIN 0
#define S_SOIL_HUM_MAX 100
#define S_SOIL_HUM_MAX_ERRORS 2

#define S_PLUVIO_MIN 0
#define S_PLUVIO_MAX 255
#define S_PLUVIO_MAX_ERRORS 2


// Nr of reads
// Todo: This doesn't exist. Nr reads are defined by frequency
#define NR_READS 20

// Nr of sensors types
#define S_NUMBER 6

// Nr of measurements (min, max, avg)
#define S_NR_MEASURES 3

void manage_data();
char read_s_temp(unsigned char first_read);
int check_reading_limits(int min, int max, char maxerrors, unsigned char first_read, unsigned char sensor_type);
int read_from_sensor(unsigned char first_read, unsigned char sensor_type);
void init_rnd();
int export_sensor_data();
void write_matrix_to_csv(char *matrix, int nrows, int ncols);
#endif

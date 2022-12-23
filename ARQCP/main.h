#ifndef MAIN_H
#define MAIN_H

#define SECONDS_DAY 86400;

// Sensors indexes
#define S_TEMP_INDEX 0
#define S_WIND_INDEX 1
#define S_WIND_DIR_INDEX 2
#define S_AIR_HUM_INDEX 3
#define S_SOIL_HUM_INDEX 4
#define S_PLUVIO_INDEX 5

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

// Defining limits by sensor
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
#define NR_READS 20

// Nr of sensors types
#define S_NUMBER 6

// Nr of measurements (min, max, avg)
#define S_NR_MEASURES 3

void manage_data();
void read_s_temp(unsigned char first_read);
void read_s_wind(unsigned char first_read);
void read_s_wind_dir(unsigned char first_read);
void read_s_air_hum(unsigned char first_read);
void read_s_soil_hum(unsigned char first_read);
void read_s_pluvio(unsigned char first_read);
void get_s_measures(char *ptrInput, float *ptrOutput);
int check_reading_limits(int min, int max, char maxerrors, unsigned char first_read, unsigned char sensor_type);
int read_from_sensor(unsigned char first_read, unsigned char sensor_type);
void init_rnd();
#endif

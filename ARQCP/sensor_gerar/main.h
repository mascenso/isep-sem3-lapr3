#ifndef MAIN_H
#define MAIN_H
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

void init_rnd();

#endif

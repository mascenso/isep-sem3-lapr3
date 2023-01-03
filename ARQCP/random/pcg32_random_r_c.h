#ifndef PCG32_RANDOM_R_C_H
#define PCG32_RANDOM_R_C_H
#include <stdint.h>


uint32_t pcg32_random_r_c();
int32_t pcg32_random_r_c_init();
int32_t pcg32_random_r_c_min_max( int min, int max);

#endif
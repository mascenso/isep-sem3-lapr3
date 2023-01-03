#ifndef PCG32_RANDOM_R_H
#define PCG32_RANDOM_R_H

#include <stdint.h>

uint32_t pcg32_random_r();
int32_t pcg32_random_r_init();
int32_t pcg32_random_r_min_max(int min, int max);

#endif
#include <stdio.h>
#include <stdint.h>
#include <unistd.h>
#include "pcg32_random_r.h"
#include "pcg32_random_r_c.h"
#include "random.h"

extern uint64_t state;
extern uint64_t inc;

/**
 Demo US101: 32-bit PCG Random Number Generation
 **/
int main()
{
    //Bounds for the random number: It will be generated a number whose |value| is between min and max
    int min = 0;
    int max = 5000;

    printf("Random value_C: %d\n", pcg32_random_r_c_init());
    printf("Random value_C with bounds min=%d and max=%d: %d\n", min, max, pcg32_random_r_c_min_max(min, max));
    printf("\n");

    printf("Random value_ASM: %d\n", pcg32_random_r_init());
    int number = pcg32_random_r_min_max(min, max);
    printf("Random value_ASM with bounds min=%d and max=%d: %d\n", min, max, number);
    return 0;
}
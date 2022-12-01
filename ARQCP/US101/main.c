#include <stdio.h>
#include "demo_pcg.h"
#include "read_rnd.h"

int state = 0 ;
int inc = 0;

int main()
    {
    state = read_rnd();

    printf("%8lld\n",pcg32_random_r());
        return 0;
    } 

	

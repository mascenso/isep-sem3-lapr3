#include <stdio.h> 
#include "demo_pcg.h"

int state = 0 ;
int inc = 0;

int main()
    { 
        for(int i=0;i<32;i++){

            printf("%8x\n",pcg32_random_r());
            
        }
		    
        return 0;
    } 

	

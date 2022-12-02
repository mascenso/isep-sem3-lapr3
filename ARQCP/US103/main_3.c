#include <stdio.h>

int nr_reads = 10;
int nr_sensors = 6; //temp, vel, dir, plu, hum atmo, hum soil

int main() {
   
    int max, min, sum;
    //int inputarray[nr_sensors][nr_reads];

    max = 0;
    sum = 0;

    int inputarray[6][10]={
        {6209,1182,9346,7354,3867,7789,9965,7643,2162,6618},
        {5264,6663,1291,9778,5487,7364,6116,9544,4895,2667},
        {2202,9264,9300,5617,448,8297,8489,983,8866,218},
        {1416,7880,9047,8641,7243,5133,8476,7663,362,4851},
        {523,6128,6530,2943,8986,3343,5574,5107,3094,8179},
        {9636,1306,6683,1084,3858,7640,3929,8627,4725,7696}
    };
    
    int outputarray[6][3];

    int *ptr1, *ptr2, *ptr3, *ptr4;

    ptr1 = (int *)inputarray;
    ptr2 = (int *)inputarray;
    ptr3 = (int *)inputarray;
    ptr4 = (int *)outputarray;

    // *************************
    // print the array values
    // *************************

    /*for(int x = 0; x < nr_sensors; x++){
        for(int y = 0; y < nr_reads; y++){
            printf("Valor INPUT ARRAY[%d][%d]=%d\n", x, y, inputarray[x][y]);
        }
    }*/

    // *************************
    // finding the maximum value
    // saving it to the output
    // array using pointers
    // *************************

    for(int x = 0; x < nr_sensors; x++){
        for(int y = 0; y < nr_reads; y++){
            if(y == 0){
                max = *ptr1;
            }
            if(max <= *ptr1) {
                max = *ptr1;
            }
            ptr1++;
        }
        *ptr4 = max;
        ptr4 += 3;
        printf("MAX=%d\n",max);
        max=0;
    }

    ptr4 = (int *)outputarray;

    // *************************
    // finding the minimum value
    // saving it to the output
    // array using pointers
    // *************************

    ptr4 = (int *)outputarray;
    ptr4++;

    for(int x = 0; x < nr_sensors; x++){
        for(int y = 0; y < nr_reads; y++){
            if(y == 0) min = *ptr2;

            if(min >= *ptr2) {
                min = *ptr2;
            }
            ptr2++;
        }
        *ptr4 = min;
        if(x < nr_sensors-1) ptr4 += 3;
        printf("MIN=%d\n",min);
        min=0;
    }

    // *************************
    // calculating the average
    // saving it to the output
    // array using pointers
    // *************************

    ptr4 = (int *)outputarray;
    ptr4 += 2;
    
    for(int x = 0; x < nr_sensors; x++){
        for(int y = 0; y < nr_reads; y++){
            sum = sum + *ptr3;
            ptr3++;
        }
        float avg = (float)sum / nr_reads;
        *ptr4 = (int)avg;
        if(x < nr_sensors-1) ptr4 += 3;
        
        printf("AVG=%lf\n",avg);
        avg = 0;
        sum = 0;
    }

    ptr4 = (int *)outputarray;

    for(int x = 0; x < nr_sensors; x++){
        for(int y = 0; y < 3; y++){
            printf("VALORES DE OUTPUT=%d\n",*ptr4);
            ptr4++;
        }
    }

   return 0;
}

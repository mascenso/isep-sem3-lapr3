#include <stdio.h>

int main() {
   
   int tempmax, tempmin, max, min, sum;
   int array[10];
   int *ptr1, *ptr2, *ptr3;

   max = 0;
   tempmax = 0;
   tempmin = 0;
   sum = 0;

   //min = array[0] = 0;

   printf("Introduza 10 nrs inteiros: \n");

   for(int r = 0; r < 10; r++) {
      scanf("%d", &array[r]);
   }

   ptr1 = array;
   ptr2 = array;
   ptr3 = array;
   
   // print the array values

   for(int x = 0; x < 10; x++){
      printf("Valor ARRAY[%d]=%d\n", x, array[x]);
   }

   // finding the maximum value

   for(int i = 0; i < 10; i++) {

      printf("Valor MAX = '%d'\n", *ptr1);

      if(max <= *ptr1) {
         max = *ptr1;
      }
      ptr1++;
   }
   printf("MAX=%d\n",max);

   // finding the minimum value

   for(int j = 0; j < 10; j++) {
      printf("Valor MIN = '%d'\n", *ptr2);

      if(min >= *ptr2) {
         min = *ptr2;
      }
      ptr2++;
   }

   printf("MIN=%d\n",min);

   // calculating the average

   for (int k = 0; k < 10; k++) {
      sum = sum + *(ptr3 + k);
   }
   float avg = (float)sum / 10;

   printf("AVG=%lf\n",avg);

   return 0;
}

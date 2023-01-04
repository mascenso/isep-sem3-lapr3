#include <stdio.h>
#include <stdint.h>
#include "sensores.h"
#include "../random/random.h"
#include "../random/pcg32_random_r.h"

//char sens_temp(char ult_temp, char comp_rand);
//unsigned short sens_dir_vento(unsigned short ult_dir_vento, short comp_rand);
//unsigned char sens_pluvio(unsigned char ult_pluvio, char ult_temp, char comp_rand);
uint64_t state=0;
uint64_t inc=0;

int main()
{
/**US102: Gerar valores para os dados dos sensores **/

      //readings_temp[0] = 20;
      char comp_rand = random();
      state = random();
      inc = random();
      char temp;

      printf("Valor do comp_rand: %d\n", comp_rand);

        char ult_temp = 20;

        printf("Valores do sensor de temperatura:\n");
        int i = 0;
        while(i<10){
            temp = sens_temp(ult_temp, comp_rand);
            ult_temp = temp;

            printf("[%d]", temp);
            i++;
        }
      return 0;
}
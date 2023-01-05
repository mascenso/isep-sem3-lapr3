#include <stdio.h>
#include <stdint.h>
#include <unistd.h>
#include "random/random.h"
#include "random/pcg32_random_r.h"
#include "sensor_gerar/sensores.h"
#include "sensores/all_sensors.h"
//#include "main.h"
#include "sensor/sensor.h"

unsigned int nr_reads = 0;
uint64_t state=0;
uint64_t inc=0;

int main()
{
         /** --*--*--*-*--*-*--*--*--*--*--*--*--*--*--*--*          *--*--*--*---*--*-*--**--*--*--*--*--*--*-- **/
         /** --*--*--*-*--*-*--*--*--*--*--*--*--*--*--*--* SPRINT 1 *--*--*--*---*--*-*--**--*--*--*--*--*--*-- **/
         /** --*--*--*-*--*-*--*--*--*--*--*--*--*--*--*--*          *--*--*--*---*--*-*--**--*--*--*--*--*--*-- **/

            /**US101: Function that generates random numbers **/

                int min = 0;
                int max = 500;
                state = random();
                inc = random();

                printf("Random value_ASM: %d\n", pcg32_random_r_init());
                int number = pcg32_random_r_min_max(min, max);
                printf("Random value_ASM with bounds min=%d and max=%d: %d\n", min, max, number);

                /**US102: Gerar valores para os dados dos sensores (sensores.h) **/

                    printf("%d\n",sens_temp(20, inc));

                /**US103: Construir em C, a matriz diaria de resumo **/
                /** Para cada tipo de sensor deve ser determinado o valor máximo, o mínimo e a média das leituras **/

                /** US104 - Deve ser possível estabelecer limites máximos e mínimos para os valores produzidos por um sensor. **/
                // Se o valor enviado pelo sensor estiver fora desse limite, deve ser assinalado o erro.
                // Após N leituras consecutivas erradas, deve ser possível reiniciar o sensor,
                // iniciando-se assim uma nova sequência de valores produzidos.
                // Entende-se por reiniciar o sensor, descartar todas as leituras erradas e
                // gerar uma nova semente para a geração aleatória de valores.

            /** --*--*--*-*--*-*--*--*--*--*--*--*--*--*--*--*          *--*--*--*---*--*-*--**--*--*--*--*--*--*-- **/
            /** --*--*--*-*--*-*--*--*--*--*--*--*--*--*--*--* SPRINT 2 *--*--*--*---*--*-*--**--*--*--*--*--*--*-- **/
            /** --*--*--*-*--*-*--*--*--*--*--*--*--*--*--*--*          *--*--*--*---*--*-*--**--*--*--*--*--*--*-- **/

            // US110 - A informação de cada sensor deve ser armazenada numa estrutura

                   create_sensor(1, 0);


            // US111 - Durante a execução do programa deve ser possível:
            // . acrescentar/remover sensores de um dado tipo . alterar a frequência de leituras de um sensor,

                    Sensor **sensors = create_sensor_array(6);
                    create_sensor_and_create_id(0);
                    create_sensor_and_create_id(0);
                    create_sensor_and_create_id(2);
                    create_sensor_and_create_id(5);
                    print_sensor_array(sensors);

                    remove_sensor(sensors, 1);

                    print_sensor_array(sensors);



            //US112 - Em C, exporte para um ficheiro CSV, os dados e leituras de cada um dos sensores
            //        Crie outro ficheiro CSV com os dados da matriz diária de resumo

            //print_sensor(s_temp_1);


            //unsigned long freq = read_frequency_from_config_properties();
            //printf("\n%lu", freq);
}


#include <stdio.h>
#include <stdint.h>
#include <unistd.h>
#include "config/read_config.h"
#include "random/random.h"
#include "random/pcg32_random_r.h"
#include "sensor_gerar/sensores.h"
#include "sensor_gerar/inserir_values.h"
#include "sensores/all_sensors.h"

#include "main.h"
#include "sensor/sensor.h"

uint64_t state;
uint64_t inc;

int wait_for_user_input();
void get_s_measures(char *ptrInput, float *ptrOutput);

int main()
{
            //Read all info from config file in folder config
            char *path = "config.cfg";
            Configs *cfg = get_init_config(path);
            SensorsConfig *scfg = get_sensors_config(cfg);

            //Creates an array of sensors, organized by their type
            Sensor **sensors = create_sensor_array(S_NUMBER);

         /** --*--*--*-*--*-*--*--*--*--*--*--*--*--*--*--*          *--*--*--*---*--*-*--**--*--*--*--*--*--*-- **/
         /** --*--*--*-*--*-*--*--*--*--*--*--*--*--*--*--* SPRINT 1 *--*--*--*---*--*-*--**--*--*--*--*--*--*-- **/
         /** --*--*--*-*--*-*--*--*--*--*--*--*--*--*--*--*          *--*--*--*---*--*-*--**--*--*--*--*--*--*-- **/

                /**
                 US101 Demo: Function that generates random numbers
                 **/
                printf("US101: A function that generates random numbers\n\n");

                int min = 0;
                int max = 500;
                state = random();
                inc = random();

                printf("A Random value: %d\n", pcg32_random_r_init());
                int number = pcg32_random_r_min_max(min, max);
                printf("A Random value with bounds min=%d and max=%d: %d\n", min, max, number);


                /**
                US102: Gerar valores para os dados dos sensores (sensores.h)
                **/
                wait_for_user_input();
                printf("US102: Generate values for sensor data\n\n");


                /**
                US103: Construir em C, a matriz diaria de resumo
                **/
                /** Para cada tipo de sensor deve ser determinado o valor máximo, o mínimo e a média das leituras **/
                wait_for_user_input();
                printf("US103: Build in C, the daily summary matrix\n\n");

                // Matrixes for the sensors values

                float s_measures[S_NUMBER][S_NR_MEASURES];
                char s_temp_reads[S_TEMP_QTY][NR_READS];

                char *ptrSensor = &s_temp_reads[0][0];
                float *ptrSummary = &s_measures[0][0];

                get_s_measures(ptrSensor, ptrSummary);

                printf("\n");

                /**
                US104 - Deve ser possível estabelecer limites máximos e mínimos para os valores produzidos por um sensor.
                **/
                // Se o valor enviado pelo sensor estiver fora desse limite, deve ser assinalado o erro.
                // Após N leituras consecutivas erradas, deve ser possível reiniciar o sensor,
                // iniciando-se assim uma nova sequência de valores produzidos.
                // Entende-se por reiniciar o sensor, descartar todas as leituras erradas e
                // gerar uma nova semente para a geração aleatória de valores.
                wait_for_user_input();
                printf("US104: It should be possible to establish maximum and minimum limits for the values produced by a sensor.\n");

                printf("\ntodo\n");



            /** --*--*--*-*--*-*--*--*--*--*--*--*--*--*--*--*          *--*--*--*---*--*-*--**--*--*--*--*--*--*-- **/
            /** --*--*--*-*--*-*--*--*--*--*--*--*--*--*--*--* SPRINT 2 *--*--*--*---*--*-*--**--*--*--*--*--*--*-- **/
            /** --*--*--*-*--*-*--*--*--*--*--*--*--*--*--*--*          *--*--*--*---*--*-*--**--*--*--*--*--*--*-- **/



                /**
                US110 - A informação de cada sensor deve ser armazenada numa estrutura
                **/
                // Critério de aceitação: Deve ser possível ter vários sensores de um mesmo tipo, cujo número é
                //também determinado durante a execução. Para cada tipo de sensor considerado, deve existir um
                //array dinâmico de estruturas:
                wait_for_user_input();
                printf("US110: The information of each sensor must be stored in a structure\n\n");

                    printf("-->Creating and storing some sensors...\n\n");
                    sleep(0.5);

                    create_sensor_and_create_id(0);
                    sleep(1);
                    create_sensor_and_create_id(0);
                    sleep(1);
                    create_sensor_and_create_id(2);
                    sleep(1);
                    create_sensor_and_create_id(5);
                    sleep(1);

                    printf("\n");
                    printf("\n-->Printing all stored sensors...\n");
                    sleep(3);
                    print_sensor_array(sensors);

                    /**
                    US111 : Durante a execução do programa deve ser possível:
                        - acrescentar/remover sensores de um dado tipo .
                        - alterar a frequência de leituras de um sensor
                    **/
                    wait_for_user_input();
                    printf("\nUS111: Remove sensors of a given type\n\n");

                    printf("Removing a sensor...\n");
                    sleep(1);
                    printf("Removing sensor with id: %d\n", 1);
                    remove_sensor(sensors, 1);

                    printf("\nPrinting all stored sensors...\n");
                    sleep(2);
                    print_sensor_array(sensors);

                    wait_for_user_input();
                    printf("\nUS111: Change the frequency of readings of a sensor\n");

                    //change_frequency(Sensor **sensor_array, unsigned short id, unsigned short frequency);

                    printf("\nChanging the frequency of a sensor...\n");
                    sleep(1);
                    //unsigned long freq = read_frequency_from_config_properties();
                    change_frequency(sensors, 2, 10);

                    print_sensor_array(sensors);


                    /**
                    US112 : Em C, exporte para um ficheiro CSV, os dados e leituras de cada um dos sensores
                            Crie outro ficheiro CSV com os dados da matriz diária de resumo
                    **/
                    wait_for_user_input();
                    printf("\nUS112: Export to a CSV file, the data and readings of each of the sensors\n\n");

                    printf("Exporting sensor readings to folder 'output files'...\n");
                    sleep(1);
                    printf("Export successful.\n");
                    sleep(1);
                    printf("Exporting readings to folder 'output files'...\n");
                    printf("Export successful.\n");

                    free_all_sensors(sensors);

                    printf("End of program.\n");
}


    int wait_for_user_input()
    {
        printf("\nPress Enter to Continue\n");
        getchar();
        return 0;
    }

    void get_s_measures(char *ptrInput, float *ptrOutput){

        int max, min, sum;

        max = 0;
        sum = 0;

        char *ptr1;
        float *ptr4;

        float avg=0;

        ptr1 = ptrInput;
        ptr4 = ptrOutput;

        // *************************
        // finding the maximum value
        // saving it to the output
        // array using pointers
        // *************************

        for(int x = 0; x < S_TEMP_QTY; x++){
            if(x == 0){
                max = *ptr1;
            }
            for(int y = 0; y < NR_READS; y++){
                if(max <= *ptr1) {
                    max = *ptr1;
                }
                if(min >= *ptr1) {
                    min = *ptr1;
                }
                sum = sum + *ptr1;
                ptr1++;
            }
        }
          avg = (float)sum / (NR_READS*S_TEMP_QTY);

          printf("------------------\n");
          printf("TEMP MIN=%d\n",min);
          printf("TEMP MAX=%d\n",max);
          printf("TEMP SUM=%d\n",sum);
          printf("TEMP AVG=%lf\n",avg);
          printf("------------------\n");

          *ptr4 = min;
          ptr4++;
          *ptr4 = max;
          ptr4++;
          *ptr4 = avg;

        ptr4 = ptrOutput;

    }


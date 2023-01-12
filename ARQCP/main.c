#include <stdio.h>
#include <stdint.h>
#include <unistd.h>
#include "config/read_config.h"
#include "random/random.h"
#include "random/pcg32_random_r.h"
#include "sensor_gerar/sensores.h"
#include "sensor_gerar/inserir_values.h"
#include "sensor/sensor.h"
#include "sensores/all_sensors.h"

#include "main.h"
#include "sensor/sensor.h"

uint64_t state;
uint64_t inc;

int wait_for_user_input();
 int export();
void get_s_measures(unsigned short *sensor_readings, float *ptrOutput, unsigned long readings_size);

int main()
{
            //Read all info from config file in folder config
            char *path = "config/config.cfg";
            Configs *cfg = get_init_config(path);
            SensorsConfig *scfg = get_sensors_config(cfg);


            //Creates an array of sensors, organized by their type
            Sensor **sensors = create_sensor_array(S_NUMBER);

            //Fill w/ some sensors of type 0
            create_sensor_and_create_id(0);
            create_sensor_and_create_id(0);
            create_sensor_and_create_id(1);
            create_sensor_and_create_id(2);
            create_sensor_and_create_id(5);

            unsigned char frequency = getFrequencyOfAType(0, scfg);
            change_frequency(sensors, 1, frequency);
            frequency = getFrequencyOfAType(1, scfg);
            change_frequency(sensors, 257, frequency);
            frequency = getFrequencyOfAType(2, scfg);
            change_frequency(sensors, 513, frequency);
            frequency = getFrequencyOfAType(5, scfg);
            change_frequency(sensors, 1281, frequency);

         /** --*--*--*-*--*-*--*--*--*--*--*--*--*--*--*--*          *--*--*--*---*--*-*--**--*--*--*--*--*--*-- **/
         /** --*--*--*-*--*-*--*--*--*--*--*--*--*--*--*--* SPRINT 1 *--*--*--*---*--*-*--**--*--*--*--*--*--*-- **/
         /** --*--*--*-*--*-*--*--*--*--*--*--*--*--*--*--*          *--*--*--*---*--*-*--**--*--*--*--*--*--*-- **/

                /**
                 US101 Demo: Function that generates random numbers
                 **/
                wait_for_user_input();
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

                /**US102: Gerar valores para os dados dos sensores **/

                      char comp_rand = random();
                      state = random();
                      inc = random();

                      //printf("Valor do comp_rand: %d\n", comp_rand);

                      //Sensor *get_sensor(Sensor **sensor_array, unsigned short id);
                      //GET DE UM SENSOR DE TEMPERATURA
                      unsigned short id_t = 1;
                      Sensor *s_t = get_sensor(sensors, id_t);
                      unsigned long readings_size_s_t = get_readings_max_size(s_t);
                      unsigned short *readings_s_t = get_readings(s_t);

                      char string[] = "Sensor Temperatura";
                      char *sensor_name;
                      sensor_name = string;

                      // Sensor Temperatura:
                      //readings_p = inicializar_array(20);

                      unsigned short ult_val = get_last_value(s_t);
                      unsigned short value = sens_temp(ult_val, comp_rand);

                      for (int i = 0; i < readings_size_s_t; i++)
                      {
                            readings_s_t = get_readings(s_t);
                            insert_reading(sensors, 1, value);
                            value = sens_temp(ult_val, comp_rand);
                            ult_val = get_last_value(s_t);
                      }

                      //set_readings_size(s_t, readings_size_s_t);
                      print_array(readings_s_t, readings_size_s_t, sensor_name);

                      //free_array(readings);
                      //readings_size = 0;

                            unsigned long readings_size;
                            unsigned short *readings;
                            readings_size = 1;
                                  readings = inicializar_array(20);
                            // Sensor Direção do Vento:
                            sensor_name = "Direção do Vento";
                            value = 20;

                            for (int i = 0; i < 10; i++)
                                  {
                                        inserir_value (readings, readings_size, value);
                                        ult_val = value;
                                        value = sens_dir_vento(ult_val, comp_rand);
                                        readings_size++;
                                  }

                            print_array(readings, readings_size, sensor_name);
                            free_array(readings);
                            readings_size = 0;

                              // Sensor Velocidade do Vento:
                              //# unsigned char sens_velc_vento(unsigned char ult_velc_vento, char comp_rand);

                              sensor_name = "Velocidade do Vento";
                              value = 20;

                              for (int i = 0; i < 10; i++)
                                    {
                                          inserir_value (readings, readings_size, value);
                                          ult_val = value;
                                          value = sens_velc_vento(ult_val, comp_rand);
                                          readings_size++;
                                    }

                              print_array(readings, readings_size, sensor_name);
                              free_array(readings);
                              readings_size = 0;

                              // Sensor Pluviosidade:
                              // # unsigned char sens_pluvio(unsigned char ult_pluvio, char ult_temp, char comp_rand);

                              sensor_name = "Pluviosidade";
                              value = 20;
                              char ult_temp = 27;

                              for (int i = 0; i < 10; i++)
                                    {
                                          inserir_value (readings, readings_size, value);
                                          ult_val = value;
                                          value = sens_pluvio(ult_val, ult_temp, comp_rand);
                                          readings_size++;
                                    }

                              print_array(readings, readings_size, sensor_name);
                              free_array(readings);
                              readings_size = 1;


                /**
                US103: Construir em C, a matriz diaria de resumo
                **/
                /** Para cada tipo de sensor deve ser determinado o valor máximo, o mínimo e a média das leituras **/

                wait_for_user_input();
                printf("US103: Build in C, the daily summary matrix\n\n");
                printf("US104: It should be possible to establish maximum and minimum limits for the values produced by a sensor.\n");

                /**
                US104 - Deve ser possível estabelecer limites máximos e mínimos para os valores produzidos por um sensor.
                **/
                // Se o valor enviado pelo sensor estiver fora desse limite, deve ser assinalado o erro.
                // Após N leituras consecutivas erradas, deve ser possível reiniciar o sensor,
                // iniciando-se assim uma nova sequência de valores produzidos.
                // Entende-se por reiniciar o sensor, descartar todas as leituras erradas e
                // gerar uma nova semente para a geração aleatória de valores.

                // Matrixes for the sensors values

                float s_measures[S_NUMBER][S_NR_MEASURES]; //Summary matrix

                float *ptrSummary = &s_measures[0][0];

                unsigned short *ptrSensor = readings_s_t;

                get_s_measures(ptrSensor, ptrSummary, readings_size_s_t);

                printf("\n");

                wait_for_user_input();

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

                    frequency = getFrequencyOfAType(0, scfg);
                    change_frequency(sensors, 1, frequency);
                    frequency = getFrequencyOfAType(2, scfg);
                    change_frequency(sensors, 513, frequency);
                    frequency = getFrequencyOfAType(5, scfg);
                    change_frequency(sensors, 1281, frequency);

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

                    wait_for_user_input();
                    printf("\nChanging the frequency of a sensor...\n");
                    sleep(1);
                    //unsigned long freq = read_frequency_from_config_properties();
                    //change_frequency(sensors, 2, 10);
                    printf("\nYou can modify it on config file...\n");
                    wait_for_user_input();

                    Configs *cfg2 = get_init_config(path);
                    SensorsConfig *scfg2 = get_sensors_config(cfg2);
                    unsigned char anotherfreq = getFrequencyOfAType(0, scfg2);

                    change_frequency(sensors, 0, anotherfreq);
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

    void get_s_measures(unsigned short *sensor_readings, float *ptrOutput, unsigned long readings_size){

        int max, min, sum;

        max = 0;
        sum = 0;

        unsigned short *ptr1;
        float *ptr4;

        float avg=0;

        ptr1 = sensor_readings;
        ptr4 = ptrOutput;

        // *************************
        // finding the maximum value
        // saving it to the output
        // array using pointers
        // *************************

            for(int y = 0; y < readings_size; y++){
                if(max <= *ptr1) {
                    max = *ptr1;
                }
                if(min >= *ptr1) {
                    min = *ptr1;
                }
                sum = sum + *ptr1;
                ptr1++;
            }

          avg = (float)sum / (readings_size);

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


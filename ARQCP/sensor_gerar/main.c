#include <stdio.h>
#include <stdint.h>
#include "sensores.h"
#include "inserir_values.h"
#include "../random/random.h"
#include "../random/pcg32_random_r.h"

uint64_t state=0;
uint64_t inc=0;

int main()
{
/**US102: Gerar valores para os dados dos sensores **/

      char comp_rand = random();
      state = random();
      inc = random();

      printf("Valor do comp_rand: %d\n", comp_rand);

      unsigned long readings_size;
      unsigned short *readings;
      char string[] = "Sensor Temperatura";
      char *sensor_name;
      sensor_name = string;

      // Sensor Temperatura:
      // Define 1 readings to the pointer (This is defined by the freq numa user story mais à frente)
      readings_size = 1;
      readings = inicializar_array(20);

      unsigned char ult_val = 20;
      unsigned char value = sens_temp(ult_val, comp_rand);

      for (int i = 0; i < 10; i++)
      {
            inserir_value (readings, readings_size, value);
            ult_val = value;
            value = sens_temp(ult_val, comp_rand);
            readings_size++;
      }

      print_array(readings, readings_size, sensor_name);

      free_array(readings);
      readings_size = 0;


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

      return 0;
}
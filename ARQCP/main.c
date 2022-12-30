#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include "random/random.h"
#include "random/pcg32_random_r.h"
#include "main.h"
#include "sensor/sensor.h"
#include "sensor/sensores.h"

unsigned char SENSOR_TA[] = SENSOR_ATMOSPHERIC_TEMPERATURE;
unsigned long long state;
unsigned long long inc;

unsigned int nr_reads = 0;

// Matrixes for the sensors values
// Todo: Just a test
char s_temp_reads[3][4];
// unsigned char s_wind_reads[S_WIND_QTY][NR_READS];
// unsigned short s_wind_dir_reads[S_WIND_DIR_QTY][NR_READS];
// unsigned char s_air_hum_reads[S_AIR_HUM_QTY][NR_READS];
// unsigned char s_soil_hum_reads[S_SOIL_HUM_QTY][NR_READS];
// unsigned char s_pluvio_reads[S_PLUVIO_QTY][NR_READS];

int main()
{

        init_rnd(); // Initialize the random number generator, and stores the state in the global variables state and inc.
        //TODO change to arguments

        // Dinamically allocate memory for an array of Sensor pointers
        Sensor **sensors = (Sensor **) malloc(sizeof(Sensor *) * 3);

        // Create the sensors
        // Create the temperature sensors
        sensors[0] = create_sensor(0, SENSOR_TA);
        sensors[1] = create_sensor(1, SENSOR_TA);
        sensors[2] = create_sensor(2, SENSOR_TA);

        // Print the sensors
        for (int i = 0; i < 3; i++)
        {
            print_sensor(sensors[i]);
        }

       // US102 - Pretende-se que sejam gerados valores para os dados dos sensores. Cada sensor de um
       //  dado tipo deve produzir valores com uma determinada frequência (por exemplo, 20 segundos). Os
       //  protótipos das funções a desenvolver em Assembly estão indicados em anexo.
       //  Critério de aceitação: os valores gerados devem ter alguma coerência, quer entre valores
       //  consecutivos, quer, em alguns casos, entre os valores gerados por sensores de tipos diferentes.

        // Demo : Read frequency from the config file:
        unsigned long freq = read_frequency_from_config_properties( SENSOR_TA );
        printf("\nFrequency: %ld", freq);

        Sensor *s1 = create_sensor(1, SENSOR_TA);
        define_readings_size(s1);
        printf ("\nSensor %ld", s1->readings_size);
        printf("HELLO%d", s1->id);
        printf ("\nSensor %s", s1->sensor_type);

        printf ("\nSensor %s", sensors[0]->sensor_type);

        printf("%hn", &sensors[1]->id);

        export_sensor_data(sensors, 3);
        write_matrix_to_csv(s_temp_reads, 3, 3);

}

void init_rnd()
{
      unsigned int rnd_number = rnd();

      if (rnd_number == 1) exit(1);
      
      state = rnd_number;
      inc = rnd();
}

int export_sensor_data(Sensor **sensors, int nr_sensors)
{

          // Open the output file for writing
          FILE *f = fopen("output_files/sensors.csv", "w");
          if (f == NULL)
          {
              printf("Error creating file!\n");
              return 1;
          }


            // Write the header
            fprintf(f, "Sensor ID, Sensor Type, ....\n");

            // Write the data
            for (int i = 0; i < nr_sensors; i++)
            {
                // Write the sensor id
                fprintf(f, "%d,", sensors[i]->id);

                // Write the sensor type
                fprintf(f, "%s,", sensors[i]->sensor_type);

                //Paragraph
                fprintf(f, "\n");
            }

          // Close the output file
          fclose(f);
          return 0;
}

void write_matrix_to_csv(char *matrix, int nrows, int ncols)
{
    // Open the output file for writing
    FILE *f = fopen("output_files/matrix.csv", "w");
    if (f == NULL)
    {
        printf("Error opening file!\n");
        return;
    }

    // Write the matrix to the file
    for (int i = 0; i < nrows; i++)
    {
        for (int j = 0; j < ncols; j++)
        {
            fprintf(f, "%d", *(matrix + i * ncols + j));
            if (j < ncols - 1)
            {
                fprintf(f, ",");
            }
        }
        fprintf(f, "\n");
    }

    // Close the file
    fclose(f);
}


// Functions to review and write in assembly

//   void manage_data()
//   {
//         //int sum;
//
//         unsigned char first_read = 0;
//         unsigned char nr_reads = 0;
//
//         while (nr_reads < NR_READS)
//         {
//
//               printf("************************************************************\n");
//               printf("************************************************************\n");
//
//               read_s_temp(first_read);
//               read_s_wind(first_read);
//               read_s_wind_dir(first_read);
//               read_s_air_hum(first_read);
//               read_s_soil_hum(first_read);
//               read_s_pluvio(first_read);
//
//               // to simulate real time and get results faster
//               usleep(1);
//
//               first_read = 1;
//               nr_reads++;
//         }
//
//         float s_measures[S_NUMBER][S_NR_MEASURES];
//
//         char *ptrSensor = &s_temp_reads[0][0];
//         float *ptrSummary = &s_measures[0][0];
//
//         get_s_measures(ptrSensor, ptrSummary);
//   }
//
//
//   void get_s_measures(char *ptrInput, float *ptrOutput){
//
//       int max, min, sum;
//
//       max = 0;
//       sum = 0;
//
//       char *ptr1;
//       float *ptr4;
//
//       float avg=0;
//
//       ptr1 = ptrInput;
//       ptr4 = ptrOutput;
//
//       // *************************
//       // finding the maximum value
//       // saving it to the output
//       // array using pointers
//       // *************************
//
//       for(int x = 0; x < S_TEMP_QTY; x++){
//           if(x == 0){
//               max = *ptr1;
//           }
//           for(int y = 0; y < NR_READS; y++){
//               if(max <= *ptr1) {
//                   max = *ptr1;
//               }
//               if(min >= *ptr1) {
//                   min = *ptr1;
//               }
//               sum = sum + *ptr1;
//               ptr1++;
//           }
//       }
//         avg = (float)sum / (NR_READS*S_TEMP_QTY);
//
//         printf("------------------\n");
//         printf("TEMP MIN=%d\n",min);
//         printf("TEMP MAX=%d\n",max);
//         printf("TEMP SUM=%d\n",sum);
//         printf("TEMP AVG=%lf\n",avg);
//         printf("------------------\n");
//
//         *ptr4 = min;
//         ptr4++;
//         *ptr4 = max;
//         ptr4++;
//         *ptr4 = avg;
//
//       ptr4 = ptrOutput;
//
//
//       //for(int x = 0; x < S_NUMBER; x++){
//       //    for(int y = 0; y < 3; y++){
//       //        printf("VALORES DE OUTPUT=%lf\n",*ptr4);
//       //        ptr4++;
//       //    }
//       //}
//
//   }
//
//
//
//
//   void read_s_temp(unsigned char first_read)
//   {
//         const char MIN = -20, MAX = 60;
//         const unsigned char MAX_ERRORS = 2;
//         char s_temp_value;
//
//
//         for (int sensor = 0; sensor < S_TEMP_QTY; sensor++)
//         {
//               char last_value;
//               if(last_value == first_read){
//                     last_value = 0;
//               }
//               else {
//                     last_value = s_temp_reads[sensor][nr_reads];
//               }
//               if (nr_reads % S_TEMP_FT == 0)
//               {
//                     s_temp_value = check_reading_limits(MIN, MAX, MAX_ERRORS, first_read, S_TEMP_INDEX);
//                     printf("Read from temperature sensor #%d. Value read: %d\n", sensor, s_temp_value);
//               }
//               else
//               {
//                     s_temp_value = last_value;
//                     printf("Simulating value for temperature sensor #%d. Value simulated: %d\n", sensor, s_temp_value);
//               }
//               s_temp_reads[sensor][nr_reads] = s_temp_value;
//         }
//   }
//
//   void read_s_wind(unsigned char first_read)
//   {
//         const unsigned char MIN = 0, MAX = 255, MAX_ERRORS = 2;
//         unsigned char s_wind_value;
//
//         for (int sensor = 0; sensor < S_WIND_QTY; sensor++)
//         {
//               char last_value;
//               if(last_value == first_read){
//                     last_value = 0;
//               }
//               else {
//                     last_value = s_temp_reads[sensor][nr_reads];
//               }
//               if (nr_reads % S_WIND_FT == 0)
//               {
//                     s_wind_value = check_reading_limits(MIN, MAX, MAX_ERRORS, first_read, S_WIND_INDEX);
//                     printf("Read from wind sensor #%d. Value read: %d\n", sensor, s_wind_value);
//               }
//               else
//               {
//                     s_wind_value = last_value;
//                     printf("Simulating value for wind sensor #%d. Value simulated: %d\n", sensor, s_wind_value);
//               }
//               s_wind_reads[sensor][nr_reads] = s_wind_value;
//         }
//   }
//
//   void read_s_wind_dir(unsigned char first_read)
//   {
//         const unsigned short MIN = 0, MAX = 359;
//         const unsigned char MAX_ERRORS = 2;
//         unsigned short s_wind_dir_value;
//
//         for (int sensor = 0; sensor < S_WIND_DIR_QTY; sensor++)
//         {
//               char last_value;
//               if(last_value == first_read){
//                     last_value = 0;
//               }
//               else {
//                     last_value = s_temp_reads[sensor][nr_reads];
//               }
//               if (nr_reads % S_WIND_DIR_QTY == 0)
//               {
//                     s_wind_dir_value = check_reading_limits(MIN, MAX, MAX_ERRORS, first_read, S_WIND_DIR_INDEX);
//                     printf("Read from wind direction sensor #%d. Value read: %d\n", sensor, s_wind_dir_value);
//               }
//               else
//               {
//                     s_wind_dir_value = last_value;
//                     printf("Simulating value for wind direction sensor #%d. Value simulated: %d\n", sensor, s_wind_dir_value);
//               }
//               s_wind_dir_reads[sensor][nr_reads] = s_wind_dir_value;
//         }
//   }
//
//   void read_s_air_hum(unsigned char first_read)
//   {
//         const unsigned char MIN = 0, MAX = 100, MAX_ERRORS = 2;
//         unsigned char s_air_hum_value;
//
//         for (int sensor = 0; sensor < S_AIR_HUM_QTY; sensor++)
//         {
//               char last_value;
//               if(last_value == first_read){
//                     last_value = 0;
//               }
//               else {
//                     last_value = s_temp_reads[sensor][nr_reads];
//               }
//               if (nr_reads % S_AIR_HUM_FT == 0)
//               {
//                     s_air_hum_value = check_reading_limits(MIN, MAX, MAX_ERRORS, first_read, S_AIR_HUM_INDEX);
//                     printf("Read from air humidity sensor #%d. Value read: %d\n", sensor, s_air_hum_value);
//               }
//               else
//               {
//                     s_air_hum_value = last_value;
//                     printf("Simulating value for air humidity sensor #%d. Value simulated: %d\n", sensor, s_air_hum_value);
//               }
//
//               s_air_hum_reads[sensor][nr_reads] = s_air_hum_value;
//         }
//   }
//
//   void read_s_soil_hum(unsigned char first_read)
//   {
//         const unsigned char MIN = 0, MAX = 100, MAX_ERRORS = 2;
//         unsigned char s_soil_hum_value;
//
//         for (int sensor = 0; sensor < S_SOIL_HUM_QTY; sensor++)
//         {
//               char last_value;
//               if(last_value == first_read){
//                     last_value = 0;
//               }
//               else {
//                     last_value = s_temp_reads[sensor][nr_reads];
//               }
//               if (nr_reads % S_SOIL_HUM_FT == 0)
//               {
//                     s_soil_hum_value = check_reading_limits(MIN, MAX, MAX_ERRORS, first_read, S_SOIL_HUM_INDEX);
//                     printf("Read from soil humidity sensor #%d. Value read: %d\n", sensor, s_soil_hum_value);
//               }
//               else
//               {
//                     s_soil_hum_value = last_value;
//                     printf("Simulating value for soil humidity sensor #%d. Value simulated: %d\n", sensor, s_soil_hum_value);
//               }
//
//               s_soil_hum_reads[sensor][nr_reads] = s_soil_hum_value;
//         }
//   }
//
//   void read_s_pluvio(unsigned char first_read)
//   {
//         const unsigned char MIN = 0, MAX = 255, MAX_ERRORS = 2;
//         unsigned char s_pluvio_value;
//
//         for (int sensor = 0; sensor < S_PLUVIO_QTY; sensor++)
//         {
//               char last_value;
//               if(last_value == first_read){
//                     last_value = 0;
//               }
//               else {
//                     last_value = s_temp_reads[sensor][nr_reads];
//               }
//               if (nr_reads % S_PLUVIO_FT == 0)
//               {
//                     s_pluvio_value = check_reading_limits(MIN, MAX, MAX_ERRORS, first_read, S_PLUVIO_INDEX);
//                     printf("Read from pluviosity sensor #%d. Value read: %d\n", sensor, s_pluvio_value);
//               }
//               else
//               {
//                     s_pluvio_value = last_value;
//                     printf("Simulating value for pluviosity sensor #%d. Value simulated: %d\n", sensor, s_pluvio_value);
//               }
//               s_pluvio_reads[sensor][nr_reads] = s_pluvio_value;
//         }
//   }
//
//   int check_reading_limits(int min, int max, char maxerrors, unsigned char first_read, unsigned char sensor_type)
//   {
//         unsigned char nr_errors = 0, ok = 0;
//         int value = read_from_sensor(first_read, sensor_type);
//
//         do
//         {
//               if (value >= min && value <= max)
//                     ok = 1;
//               else
//               {
//                     value = read_from_sensor(first_read, sensor_type);
//
//                     if (nr_errors == maxerrors)
//                           init_rnd();
//                     else
//                           nr_errors++;
//               }
//         } while (ok != 1);
//
//         return value;
//   }
//
//   int read_from_sensor(unsigned char first_read, unsigned char sensor_type)
//   {
//         int value;
//
//         if (first_read)
//               value = pcg32_random_r();
//         else
//         {
//               switch (sensor_type)
//               {
//               case S_TEMP_INDEX:
//                     value = (char)pcg32_random_r();
//                     break;
//               case S_WIND_INDEX:
//                     value = (unsigned char)pcg32_random_r();
//                     break;
//               case S_WIND_DIR_INDEX:
//                     value = (unsigned short)pcg32_random_r();
//                     break;
//               case S_AIR_HUM_INDEX:
//                     value = (unsigned char)pcg32_random_r();
//                     break;
//               case S_SOIL_HUM_INDEX:
//                     value = (unsigned char)pcg32_random_r();
//                     break;
//               case S_PLUVIO_INDEX:
//                     value = (unsigned char)pcg32_random_r();
//               default:
//                     value = (char)pcg32_random_r();
//               }
//         }
//
//         return value;
//   }




void inserir_value (unsigned short *readings, unsigned long readings_size, unsigned short value);
unsigned short *inicializar_array(unsigned short first_value);
float media(unsigned short *readings, unsigned long readings_size);
unsigned short maximo(unsigned short *readings, unsigned long readings_size);
unsigned short minimo(unsigned short *readings, unsigned long readings_size);



void free_array(unsigned short *readings);
void print_array(unsigned short *readings, unsigned long readings_size, char *sensor_name);
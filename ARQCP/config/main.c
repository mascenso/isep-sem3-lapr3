#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "read_config.h"

int main(void) {

    char *path = "config.cfg";

    Configs *cfg = get_init_config(path);
    if(cfg == NULL) return 1;

    SensorsConfig *scfg = get_sensors_config(cfg);
    if(scfg == NULL) return 1;

    for (int i = 0; i < cfg->nrsensors; i++)
    {
        printf("Index do sensor:        %c\n", scfg[i].index);
        printf("Quantidade de sensores: %c\n", scfg[i].qty);
        printf("Frequencia de leitura:  %d\n", scfg[i].timefreq);
        printf("Valor minimo:           %d\n", scfg[i].min);
        printf("Valor maximo:           %hu\n", scfg[i].max);
        printf("Numero de erros:        %c\n", scfg[i].nrerrors);
    }
    
    free(scfg);
    free(cfg);

}




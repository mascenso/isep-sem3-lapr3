#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "read_config.h"

Configs *get_init_config(char *filename){

    FILE *fp = fopen(filename, "r");
    if (fp == NULL) {
        perror("Error opening file");
        return NULL;
    }

    // Read the number of lines and columns from the file
    int numLines = 0;
    int totalLines = 0;
    char line[1024];
    int value;

    // Will dinamically allocate space for the struct
    Configs *cfg = malloc(NR_INITIAL_CONFIGS * sizeof(Configs));

    // If unable to allocate, returns
    if (cfg == NULL) {
        perror("Error allocating memory for configs struct.\n");
        fclose(fp);
        return NULL;
    } else {
        printf("\nMemory allocated successfuly for configs struct.\n");
    }

    // Will read each file line
    while (fgets(line, sizeof(line), fp)) {

        if (line[0] == '#' || strlen(line) == 1){
            totalLines++;
            continue; // Skip the line if it starts with '#' or is LF
        }

        // otherwise will get the value in it
        sscanf(line, "%d", &value);
        printf("Valor lido: %d\n", value);
        numLines++;

        // the first valid lines defined in "NR_INITIAL_CONFIGS" are needed for the cfg struct
        if(numLines <= NR_INITIAL_CONFIGS){
            switch (numLines) {
                case 1:
                    cfg->nrsensors = value;
                    break;
                case 2:
                    cfg->nrreads = value;
                    break;
                case 3:
                    cfg->nrmeasures = value;
                    break;
                case 4:
                    cfg->nrindicators = value;
                    break;
            }
        }
        totalLines++;
    }

    // Prints relevant information read from configuration file
    printf("\nNr de sensores: %d\n", cfg->nrsensors);
    printf("\nNr de leituras: %d\n", cfg->nrreads);
    printf("\nNr de medicoes: %d\n", cfg->nrmeasures);
    printf("\nNr de indicadores: %d\n", cfg->nrindicators);
    printf("\n\nNr. de linhas de configuração: %d\n", numLines);
    printf("Nr. de linhas totais do ficheiro: %d\n", totalLines);

    // Closes the file
    fclose(fp);

    // Returns the struct
    return cfg;

}

SensorsConfig *get_sensors_config(Configs *cfg, char *filename) {
    
    // Open configuration file for read
    FILE *fp = fopen(filename, "r");
    if (fp == NULL) {
        perror("Error opening file");
        return NULL;
    }

    // Will dinamically allocate space for the sensors configuration struct
    SensorsConfig *scfg = malloc(cfg->nrsensors * sizeof(SensorsConfig));
    
    // If unable to allocate, closes the file and returns
    if (scfg == NULL) {
        perror("Error allocating memory for sensors configurations.\n");
        fclose(fp);
        return NULL;
    } else {
        printf("\nMemory allocated successfuly for sensors configurations.\n");
    }

    // Initializes the temporary variables
    int numLines = 0;
    char line[1024];
    int i = 0;
    numLines = 0;

    while (fgets(line, sizeof(line), fp)) {
        if (line[0] == '#' || strlen(line) == 1) {
            continue; // Skip the line if it starts with '#' or is LF
        } else {
            numLines++;
        }

        // If it is already in lines with sensors configuration, will add it to the sctruct
        if(numLines > NR_INITIAL_CONFIGS){
            sscanf(line, "%c %c %hhd %hhd %hu %c", &scfg[i].index, 
                                                 &scfg[i].qty, 
                                                 &scfg[i].timefreq,
                                                 &scfg[i].min,
                                                 &scfg[i].max,
                                                 &scfg[i].nrerrors);

            i++;
        }
    }
    
    // Close the file
    fclose(fp);

    // Returns the struct
    return scfg;  
}




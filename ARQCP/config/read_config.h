#ifndef READ_CONFIG_H
#define READ_CONFIG_H

    // Nr of initial configuration needed
    // In this case are:
    //      - total of sensor types
    //      - number of sensors reads
    //      - number of sensors measures
    //      - number of sensors indicators
    #define NR_INITIAL_CONFIGS 4

    typedef struct {
	    unsigned int nrsensors;     // total of sensor types
        unsigned int nrreads;       // number of sensors reads
        unsigned int nrmeasures;    // number of sensors measures
        unsigned int nrindicators;  // number of sensors indicators
    } Configs;

    typedef struct {
    	unsigned char index;    // sensor index
        unsigned char qty;      // quantity of this type of sensor
        unsigned char timefreq; // frequency time for the reads
        char min;               // minimum value acceptable to be read
        unsigned short max;     // maximum value acceptable to be read
        unsigned char nrerrors; // number of consecutive read errors
    } SensorsConfig;

    Configs *get_init_config(char *filename);
    SensorsConfig *get_sensors_config(Configs *cfg);

#endif

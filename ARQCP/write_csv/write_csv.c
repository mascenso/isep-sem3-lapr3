#include <stdio.h>
#include <stdlib.h>
#include <string.h>
//include new_matrix from matrix folder:
#include "../matrix/new_matrix.h"
#include "write_csv.h"

int write_csv(int **m, int y, int k, char filename[]){
    FILE *fptr; // file pointer
    
    fptr = fopen(filename, "w"); //create a file
    if (fptr == NULL)
    {
        printf("Error while opening the file.\n");
        return 0;
    }

    char *output = ""; 

    for(int i = 0; i < y; i++){
		  for(int j = 0; j < k; j++){
            // asprintf(&output,"%s%s",output,*(*(m+i)+j));
            if(j != (k-1)){
                //asprintf(&output,"%s,",output);
            }
        }
        fprintf(fptr, "%s",output);
        fprintf(fptr,"\n");
        output = "";
    }

    fclose(fptr);

    return 1;
}

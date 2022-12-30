#include <stdio.h>
#include <stdlib.h>
#include "../matrix/new_matrix.h"
#include "write_csv.h"

int main( void ){

	int y = 4;
	int k = 2;

	int **m = new_matrix(y, k);

	printf("Endereço da Matrix: '%p'\n", m);

	int result = write_csv(m, y, k, "lixo.csv");

	if(result == 1){
		printf("Escreveu!!");
	} else {
		printf("Era bom que tivesse escrito");
	}

	for (int i = 0; i < y ; i++){
		free (*(m+i) );
	}
	
	free(m);

	return 0;
}

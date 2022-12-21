#include <stdio.h>

unsigned int rnd()
{
	const int SIZE = 1;
	unsigned int buffer[SIZE];
	FILE *f;
	int result;

	f = fopen("/dev/urandom", "r");

	if (f == NULL)
	{
		printf("ERROR: open() failed to open /dev/urandom for reading\n");
		return 1;
	}

	result = fread(buffer, sizeof(unsigned int), SIZE, f);

	if (result < 1)
	{
		printf("ERROR: failed to read any words\n");
		return 1;
	}

	return buffer[0];
}

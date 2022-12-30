# Estrutura do programa:
As funções do programa para ARQCP estão divididas em diretórios:

* matrix: Funções para manipulação de matrizes (criar, destruir, preencher, imprimir, etc)
* output_files: Diretório para guardar os ficheiros de saída
* random: Funções para gerar números aleatórios
* read_cfg: Funções para ler o ficheiro de configuração
* sensor: Funções para manipulação dos sensores (criar, destruir, preencher, imprimir, etc)
* write_matrix_to_csv: Funções para escrever matrizes para ficheiros CSV
* write_sensor_to_csv: Funções para escrever sensores para ficheiros CSV
* main.c: Ficheiro principal do programa
* Makefile: Ficheiro para compilar o programa
* README.md: Ficheiro com a descrição do programa

Todos os diretorios contêm um makefile próprio e um "main.c" com um demo de cada função

# Compilação e execução:
Para compilar o programa, basta executar o comando "make" no diretório principal do programa. 
Para executar o programa, basta executar o comando "./main" no diretório principal do programa.


# Descrição das User Stories:

A estação meteorológica a considerar deve incluir os seguintes sensores: temperatura, velocidade do
vento, direcção do vento, humidade atmosférica, humidade do solo, pluviosidade.
Considera-se que os sensores produzem dados nas seguintes unidades:
 graus centígrados para a temperatura
 km/h para a velocidade do vento
 graus relativamente ao Norte para a direção do vento
 percentagem para a humidade atmosférica
 percentagem para a humidade do solo
 mm para a pluviosidade

### US101 - Pretende-se que seja implementada em Assembly uma função que gere números
pseudo-aleatórios, a ser usada na simulação dos sensores. É disponibilizada em C a função
pcg32_ramdom_r() e pretende-se que seja desenvolvida em Assembly uma função equivalente. É
também disponibilizado um exemplo de como ler de /dev/random para inicializar o gerador
indicado anteriormente.

### US102 - Pretende-se que sejam gerados valores para os dados dos sensores. Cada sensor de um
dado tipo deve produzir valores com uma determinada frequência (por exemplo, 20 segundos). Os
protótipos das funções a desenvolver em Assembly estão indicados em anexo.
Critério de aceitação: os valores gerados devem ter alguma coerência, quer entre valores
consecutivos, quer, em alguns casos, entre os valores gerados por sensores de tipos diferentes.

### US103 - Deve ser construída em C, uma matriz diária de resumo de todos os tipos de sensores. Para
cada tipo de sensor deve ser determinado o valor máximo, o mínimo e a média das leituras
produzidas por todos os sensores desse tipo. A alocação de memória para a matriz é estática. Deve
ser usada aritmética de apontadores para manipular a matriz.

### US104 - Deve ser possível estabelecer limites máximos e mínimos para os valores produzidos por
um sensor. Se o valor enviado pelo sensor estiver fora desse limite, deve ser assinalado o erro. Após
N leituras consecutivas erradas, deve ser possível reiniciar o sensor, iniciando-se assim uma nova
sequência de valores produzidos. Entende-se por reiniciar o sensor, descartar todas as leituras
erradas e gerar uma nova semente para a geração aleatória de valores.

### US110 - A informação de cada sensor deve ser armazenada numa estrutura do tipo:

typedef struct
{
unsigned short id;
unsigned char sensor_type;
unsigned short max_limit;
 // limites do sensor
unsigned short min_limit;
unsigned long frequency;
 // frequency de leituras (em segundos)
unsigned long readings_size; // tamanho do array de leituras
unsigned short *readings;
 // array de leituras diárias
...
 // adicionar o que acharem conveniente
} Sensor;

Cada tipo de sensor possui uma determinada frequência de leituras. Essa frequência deve ser obtida
durante a execução do programa, a partir de um ficheiro de configuração, ou perguntando ao
utilizador. Naturalmente, essa frequência determina o tamanho do array de leituras diárias.
Critério de aceitação: Deve ser possível ter vários sensores de um mesmo tipo, cujo número é
também determinado durante a execução. Para cada tipo de sensor considerado, deve existir um
array dinâmico de estruturas.

**bases de dados diz:
f o tipo de sensor é caracterizado da seguinte forma:
 HS – Sensor de humidade do solo
 Pl – Sensor de pluviosidade
 TS – Sensor de temperatura do solo
 VV – Sensor de velocidade do vento
 TA – Sensor de temperatura atmosférica
 HA – Sensor de humidade do ar
 PA – Sensor de pressão atmosférica

### US111 - Durante a execução do programa deve ser possível:
. acrescentar/remover sensores de um dado tipo
. alterar a frequência de leituras de um sensor, ajustando devidamente o vetor de leituras
O array de estruturas de cada tipo de sensor afetado pela alteração e/ou o array de leituras dos
sensores já existentes devem ser adaptados ao novo cenário.

### US112 - Em C, exporte para um ficheiro CSV, os dados e leituras de cada um dos sensores. Crie outro ficheiro CSV com os dados da matriz diária de resumo

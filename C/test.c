#include <stdio.h>
#define size 10

void printArray(int * x){
	int k = 0;
	int* i;
	printf("[");
	for(i = x; k < size - 1; k++, i++){
		printf("%d,", *i);
	}
	printf("%d]\n", *(i + 1));

	return;
}

int main(){
	int numArray[size];

	int* i;
	int k = 0;
	for(i = (int *)&numArray; k < size; i++, k++){
		*i = k;
	}

	printArray((void *)&numArray);

	return 0;
}
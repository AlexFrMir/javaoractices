#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <sys/socket.h>
#include <arpa/inet.h>
//#include <winsock2.h>

typedef struct RQ
{
	//Hay que hacerlo portable, no siempre short int es de 2 bytes
	short int opCode;
	char * filename;
	char * mode;
} request;

typedef struct DataPacket
{
	short int opCode;
	short int blockNumber;
	char * data;
}packet;

typedef struct ACKPacket
{
	short int opCode;
	short int blockNumber; 
} ack;

typedef struct ERRORPacket{
	short int opCode;
}error;
int main(int argc, char const *argv[]){
	/*printf("Tamanio de short int: %d bytes", sizeof(short int));
	printf("Tamanio de int: %d bytes", sizeof(int));*/
	char buffer [512];
	int16_t * opCode = buffer;
	
    char *filename = buffer + 2;
	int i=2;
	* opCode = htons(1);
	printf("%02X %02X\n", buffer[0], buffer[1]);
	while(buffer[i] != '\0')
		i++;
	//request * r;
	//sendto(fd, r, sizeof(*r), 0, (sockaddr_in *) socket, sizeof(socket));
	return 0;
}
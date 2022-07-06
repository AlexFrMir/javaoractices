#include <stdio.h>
#include <stdlib.h>
#include <termios.h>
#include <unistd.h>

#define columnas 6
#define renglones 10

int getch(void)
{
    struct termios oldattr, newattr;
    int ch;
    tcgetattr( STDIN_FILENO, &oldattr );
    newattr = oldattr;
    newattr.c_lflag &= ~( ICANON | ECHO );
    tcsetattr( STDIN_FILENO, TCSANOW, &newattr );
    ch = getchar();
    tcsetattr( STDIN_FILENO, TCSANOW, &oldattr );
    return ch;
}

void gotoxy(int x,int y)
{
    printf("%c[%d;%df",0x1B,y,x);
}

void tablero(char tabla[][21],int x, int y)
{
int iniciox=10; 
int inicioy=10;
for(int i=0;i<2*columnas+1;i++)
	{
	for(int j=0;j<2*renglones+1;j++)
		{
		if(i%2==0)
		{
			if(j%2==0)
				{
				gotoxy(iniciox+i*3,inicioy+j);
				printf("+");
                                }	
			else
				{
				if(tabla[i][j])
					{
					gotoxy(iniciox+i*3,inicioy+j);
					printf("|");
					}
				}			
		}
		else
			{
			if(j%2==0)
				{
				if(tabla[i][j]==1)
					{
					gotoxy(iniciox+i*3-1,inicioy+j);
					printf("---");
					}
				}
			else
				{
				if(tabla[i][j]!=0)
					{
					gotoxy(iniciox+i*3,inicioy+j);
					printf(" %c ",tabla[i][j]);
					}

                                }	
			}
		}
	}
gotoxy(iniciox,inicioy-2);
printf("x=%d , y=%d \n",x,y);

gotoxy(iniciox+x*3,inicioy+y);
}

int main(void)
{
char tabl[2*columnas+1][2*renglones+1];
int x=0,y=0;
char t;

    for(int i=0;i<2*columnas+1;i++)
	{	
		for(int j=0;j<2*renglones+1;j++)
			tabl[i][j]=0;
	}
	tablero(tabl,x,y);

	while((t=getch())!='q')
	{
         
         if(t==' ')
		{
		tabl[x][y]=1;
		if((x%2==0)&&(y%2==1))
			{
//				printf("A : %d %d",x,y);
			if(y>1)
				{
					if((tabl[x-2][y]==1)&&(tabl[x-1][y-1]==1)&&(tabl[x-1][y-1]==1))
						{
						tabl[x][y-1]=='A';
//				printf("Ba : %d %d",x,y);
						}
//				printf("B : %d %d",x,y);
				//printf("Contornos : %d %d %d %d %d/n",tabl[x-1][y-1],tabl[x-1][y-1],tabl[x][y-2],tabl[x+1][y-1],tabl[x][y]);
				}
			if(y<(2*renglones))
				{
					if((tabl[x+2][y]==1)&&(tabl[x+1][y-1]==1)&&(tabl[x+1][y+1]==1))						
						{
						tabl[x][y+1]=='A';
//				printf("Ca : %d %d",x,y);
						}
//				printf("C : %d %d",x,y);
				}
			}
		if((x%2==1)&&(y%2==0))
			{
			if(x>1)
				{
					if((tabl[x][y-2]==1)&&(tabl[x-1][y-1]==1)&&(tabl[x+1][y-1]==1))
						tabl[x][y-1]=='A';
//				printf("D : %d %d",x,y);

				}
			if(x<(2*columnas))
				{
					if((tabl[x][y+2]==1)&&(tabl[x-1][y+1]==1)&&(tabl[x+1][y+1]==1))
						tabl[x][y+1]=='A';
//				printf("E : %d %d",x,y);

				}
			}
		}
         if(t=='d' && x<(2*columnas))
			x+=2; 
         if(t=='a' && x>0)
			{
			x-=2;
			if(x<0)
				x=0;
			}
         if(t=='z' && y<(2*renglones))
			y+=1;
        if(t=='w' && y>0)
			y-=1;
	if((x%2==0)&&(y%2==0))
		x+=1; 
	if((x%2==1)&&(y%2==1))
		x-=1; 
	if(x>(2*columnas))
		x=2*columnas-1;
	
	if((x%2==0) && (y%2==0))
		if(x<2*columnas-1)
				x+=1; 
			else
				x-=1; 
        gotoxy(8,6);
        printf("tecla=%d",t);
        gotoxy(8,7);
	printf("Contornos : (%d,%d)=%d (%d,%d)=%d (%d,%d)=%d (%d,%d)=%d (%d,%d)=%d\n",x+1,y+1,tabl[x+1][y+1],x+2,y,tabl[x+2][y],x+3,y+1,tabl[x+3][y+1],x+2,y+2,tabl[x+2][y+2],x+2,y+1,tabl[x+2][y+1]);
        gotoxy(8,8);
	printf("Coordenada : (%d,%d)\n",x,y);
	tablero(tabl,x,y);

	}
    return 0;
} 

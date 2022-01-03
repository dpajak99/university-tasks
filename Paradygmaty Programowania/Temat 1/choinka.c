// SKOŃCZONE
// Dominik Pająk 25.02.2021 / 13:44

#include <stdio.h>

void main() {
    int i;
    int rowStartSum = 1;
    int starCounter = 0;
    int spaceCounter = 0;
    int rowCharacterSum = 1;
    int lastLoop = 0;
    
    drawTree:
        scanf("%i", &i);
        if( i == 7 ) lastLoop = 1;
        
        drawRow:
            printSpace:
                printf(" ");
                spaceCounter++;
                if( spaceCounter < i) goto printSpace;
            drawBranch:
                if( i%2 == 0) printf("*");
                if( i%2 == 1) printf("#");
                
                starCounter++;
                if(starCounter < rowStartSum) goto drawBranch;
        printf("\n");
        starCounter = 0;
        spaceCounter = 0;
        rowStartSum += 2;
        i--;
        if(i > 0) goto drawRow;
    rowStartSum = 1;
    if(lastLoop == 1 ) goto end;
    goto drawTree;
    
    end:
    return;
}
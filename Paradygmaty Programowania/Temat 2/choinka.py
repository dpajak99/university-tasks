# SKOŃCZONE
# Dominik Pająk | 04.03.2021 - 13:49

def drawTree( treeHeight ):
    currentLevel = 1
    starsOnLevel = 1
    while currentLevel <= treeHeight:
        drawRow( treeHeight, currentLevel, starsOnLevel )
        currentLevel += 1
        starsOnLevel += 2
    
def drawRow( treeHeight, currentLevel, starsOnLevel ):
    spacesCounter = treeHeight - currentLevel
    treeSymbol = treeHeight % 2 and '#' or '*'
    
    row = spacesCounter * ' ' + starsOnLevel * treeSymbol
    print(row)

def main():
    treeHeight = 0;
    
    while treeHeight != 7:
        treeHeight = int(input(''))
        drawTree( treeHeight )
        
        
main()
        
        



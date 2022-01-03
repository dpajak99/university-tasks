# SKOŃCZONE
# Dominik Pająk 25.05.2021 - 16:27

from functools import reduce

def calcAverage(array):
    return reduce(lambda a, b: a + b, array) / len(array)
    
def findMax(array):
    return reduce(lambda a, b: a if a > b else b, array)

def flatten(array):
    return reduce(lambda a, b: a + b, array)
    
def manhattan(x, y):
    return reduce(lambda sum, xy: sum + abs(xy[0]-xy[1]), zip(x,y), 0)

def main():
    sampleListAverage = [1,2,3,4,5,6,7]
    sampleListFindMax = [1,11,111,21,4,56,43]
    sampleListFlatten = [[1,2,3], [4,5,6]]
    
    sampleVectorX = [ 1,2,3,4,5,6,7,8,9,7,2,5 ]
    sampleVectorY = [ 9,8,7,6,5,4,3,2,1,5,1,9 ]
    
    print(calcAverage(sampleListAverage))
    print(findMax(sampleListFindMax))
    print(flatten(sampleListFlatten))
    print(manhattan(sampleVectorX, sampleVectorY))
    
main()
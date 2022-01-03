# SKOŃCZONE
# Dominik Pająk - 18.03.2021 - 13:57

def calcVariance(array):
    n = len(array)
    
    averageSequenceSum = 0
    varianceSequenceSum = 0

    for i in range(0,n):
        averageSequenceSum += array[i]

    average = 1/n*averageSequenceSum

    for i in range(0, n):
        varianceSequenceSum += (array[i] - average)**2
        
    variance = 1/(n-1)*varianceSequenceSum
    
    return {
        "average": average,
        "variance":variance
    }
    
def printVarianceData(data):
    print( "Średnia wynosi:" ,data['average'] )
    print( "Wariancja wynosi:" ,data['variance'] )
    

def main():
    Array1 = [3,3,3,3,3]
    Array2 = [5,6,7,8,9]
    userArray = []
    number = -1
    
    while number != 0:
        number = int(input())
        if(number != 0):
            userArray.append(number)
        
    printVarianceData(calcVariance(userArray))
    printVarianceData(calcVariance(Array1))
    printVarianceData(calcVariance(Array2))
    
    
    
main()
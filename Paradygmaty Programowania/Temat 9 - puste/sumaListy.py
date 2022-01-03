# SKOŃCZONE 
# Dominik Pająk - 25.05.2021 - 17:42

def sumaOgonowa(array, suma=0, index=0):
    if index >= len(array):
        return suma
    return sumaOgonowa(array=array, suma=(suma + array[index]), index=(index + 1))

def sumaRekurencyjnie(array):
    if len(array) == 0:
        return 0
    return array.pop(0) + sumaRekurencyjnie(array)


def main():
    sampleList1 = [1,2,3,4]
    sampleList2 = []
    
    print(sumaOgonowa(sampleList1))
    print(sumaOgonowa(sampleList2))
    
    print(sumaRekurencyjnie(sampleList1))
    print(sumaRekurencyjnie(sampleList2))
    
main()
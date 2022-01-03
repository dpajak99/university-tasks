# SKOŃCZONE
# Dominik Pajak - 22.04.2021 - 14:47

from operator import mul
from functools import partial

def multipleLambda( array ):
    return list(map(lambda x: x*len(array), array))
    
def multipleMul( array ):
    return list(map(partial(lambda x,y: x * y, len(array)), array))
    
def multiply(array):
    def by(value):
        return multipleLambda(array)
    return by   
    
def multipleClosure( array ):
    return multiply(array)(len(array))
    
    
def main():
    array = [1,2,7,4]
    print(multipleLambda(array))
    print(multipleMul(array))
    print(multipleClosure(array))

    
main()
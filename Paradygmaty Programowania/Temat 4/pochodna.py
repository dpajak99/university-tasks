# SKOŃCZONE
# Dominik Pajk - 18.03.2021 - 13:52

import math

def quadraticFunction(x):
    return x**2

def derivative( f, x, h = 0.0001 ):
    return (f(x+h)-f(x))/h

def main():
    print(derivative(math.sin, 1))
    print(derivative(math.sin, 0))
    print(derivative(quadraticFunction, 1, 0.00001))
    
main()
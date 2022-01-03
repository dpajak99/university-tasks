# SKOŃCZONE
# Dominik Pająk - 22.04.2021 - 14:42

from functools import reduce

def getPrimaryArray(num):
    return list(filter(lambda a: all(list(map(lambda x: a % x, list(range(2, a))))),list(range(2, num))))

def main():
    print(getPrimaryArray(100))
    
main()
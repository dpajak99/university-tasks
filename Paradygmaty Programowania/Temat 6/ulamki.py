# SKOŃCZONE
# Dominik Pająk - 8.04.2021 - 13:41

class Fraction:
    instances = []
    
    def __init__(self, *args):
        if (len(args) == 2 ):
            self.numerator = args[0]
            self.denominator = args[1]
        elif(len(args) == 1):
            strNumerator = str(args[0]-int(args[0]))[2:]
            self.denominator = 10**len(strNumerator)
            self.numerator = int(strNumerator)
            
        gcd = self.__greatestCommonDivisor(self.numerator, self.denominator);
        self.numerator /= gcd;
        self.denominator /= gcd;
        
        Fraction.instances.append(self)
        
    def __str__(self):
        if self.numerator == 0:
            return "0"
        elif self.numerator == self.denominator:
            return "1"
        else:
            return str(int(self.numerator)) + "/" + str(int(self.denominator))
        
    def __add__(self, o):
        newNumerator = self.numerator * o.denominator + self.denominator * o.numerator
        newDenominator = self.__makeEqualsDenominator(o)
        return Fraction(newNumerator, newDenominator)
        
    def __sub__(self, o):
        newNumerator = self.numerator * o.denominator - self.denominator * o.numerator
        newDenominator = self.__makeEqualsDenominator(o)
        return Fraction(newNumerator, newDenominator)
            
    def __mul__(self, o):
        newNumerator = self.numerator * o.numerator
        newDenominator = self.denominator * o.denominator
        return Fraction(newNumerator, newDenominator)
            
    def __truediv__(self, o):
        newNumerator = self.numerator * o.denominator
        newDenominator = self.denominator * o.numerator
        return Fraction(newNumerator, newDenominator)
            
    def __makeEqualsDenominator(self, o):
        return self.denominator * o.denominator
    
    def __greatestCommonDivisor(self, a, b):
        if b > 0:
            return self.__greatestCommonDivisor(b, a%b)
        else:
            return a
        
        
            
def main():
    f1 = Fraction(1,2)
    f2 = Fraction(4,8)
    f3 = Fraction(0.33)
    f4 = f1 + f2
    f5 = f1 * f1
    f6 = f1 - f1
    f7 = f1 / f1
    
    print(f1, "+", f2, "=", f4)
    print(f1, "*", f1, "=", f5)
    print(f1, "-", f1, "=", f6)
    print(f1, "/", f1, "=", f7)
    
    print("0.33 = ", f3 )
    print("Instances: ", len(Fraction.instances))
    
    
main()
        
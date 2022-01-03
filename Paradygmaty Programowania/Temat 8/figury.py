# SKOŃCZONE
# Dominik Pająk - 08.04.2021 - 14:03

class Figure:
    def __init__(self, name):
        self.__name = name
        
    def getName(self):
        return self.__name
        
class Circle(Figure):
    def __init__(self, name, radius):
        self.radius = radius
        super().__init__(name)
    
    def getPerimeter(self):
        return 2 * 3.14 * self.radius

class Rectangle(Figure):
    def __init__(self, name, sideA, sideB ):
        self.sideA = sideA
        self.sideB = sideB
        super().__init__(name)
    
    def getPerimeter(self):
        return 2 * self.sideA + 2 * self.sideB
    

class Square(Rectangle):
    def __init__(self, name, side):
        super().__init__(name, side, side)
        
        

def main():
    f1 = Figure("Figura")
    f2 = Circle("Koło", 10)
    f3 = Rectangle("Prostokąt", 2, 4)
    f4 = Square("Kwadrat", 4)

    print(f1.getName())
    print(f2.getName(), "-", f2.getPerimeter())
    print(f3.getName(), "-", f3.getPerimeter())
    print(f4.getName(), "-", f4.getPerimeter())
    
main()
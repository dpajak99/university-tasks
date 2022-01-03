# SKONCZONE
# Dominik Pająk - 25.03.2021 - 14:58

def my_range( *arg ):
    a = 0.0
    b = 0.0
    k = 1

    if len(arg) == 1:
        b = arg[0]
    elif len(arg) == 2:
        a = arg[0]
        b = arg[1]
    elif len(arg) == 3:
        a = arg[0]
        b = arg[1]
        k = arg[2]
    
    if k == 0 or len(arg) < 1 or len(arg) > 3:
        raise Exception('ValueError')
        return []
        
            
    result = []
    iterator = a;
            
    while( a < b and iterator < b ) or (a > b and iterator > b):
        result.append(float("{:.2f}".format(iterator)))
        iterator += k
            
    return result
    
    
def main():
    try:
        print(my_range())
    except Exception as e:
        print(e)
        
    try:
        print(my_range(2.2,1.1, -0.5))
    except Exception as e:
        print(e)
        
    try:
        print(my_range(1.1,2.2,0.5))
    except Exception as e:
        print(e)
        
    try:
        print(my_range(1.1,2.1,0))
    except Exception as e:
        print(e)
        
    try:
        print(my_range(1.1,2.2))
    except Exception as e:
        print(e)
        
    try:
        print(my_range(2.2))
    except Exception as e:
        print(e)
        
    try:
        print(my_range(2.2,1.1, -0.5,99999999999))
    except Exception as e:
        print(e)

main()
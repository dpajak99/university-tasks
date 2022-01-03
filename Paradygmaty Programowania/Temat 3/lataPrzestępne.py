# SKOŃCZONE
# Dominik Pająk 11.03.2021 - 13:45

def getLapYears( yearFrom, yearTo ):
    return [i for i in range(yearFrom-1, yearTo) if i % 4 == 0 and (i % 100 != 0 or i % 400 == 0) ]

def main():
    print(getLapYears(1900, 2000))
    
    
main()
# SKOŃCZONE
# Dominik Pająk | 04.03.2021 - 14:01

def main():
    charsToSearch = ['A', 'a']
    text = input()
    foundLettersCounter = 0;
    
    for c in text:
        if c in charsToSearch:
            foundLettersCounter += 1
            
    print("Sum of chars ", charsToSearch, " in '", text, "' = ", foundLettersCounter)

main()
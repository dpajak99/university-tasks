# SKOŃCZONE
# Dominik Pająk - 11.03.2021 - 13:30

def translateInt(x):
    return {
        '1': 'jeden',
        '2': 'dwa',
        '3': 'trzy',
        '4': 'cztery',
        '5': 'pięć',
        '6': 'sześć',
        '7': 'siedem',
        '8': 'osiem',
        '9': 'dziewięć',
    }.get(x, 'undefined')

def changeCharToIntName(text):
    result = ''
    for x in text:
        translatedX = translateInt(x)
        if translatedX != 'undefined':
            result += translatedX + ' '
    return result

def main():
    text = input()
    print( changeCharToIntName(text) )

main()
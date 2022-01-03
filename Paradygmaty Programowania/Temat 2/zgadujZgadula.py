# POPRAWIONE
# Dominik Pająk | 10.03.2021 - 20:57

# KOMENTARZ
# Według tego co mi wiadomo, else wykonuje się "w każdym innym przypadku" 
# niż jest napisane w warunku. W moim wykonaniu, za "break" robi mi odpowiednia 
# zmiana statusu tego warunku i gra wykonuje się do czasu, kiedy spełniane 
# są założone warunki (gracz ma jeszcze życia lub nie wygrał gry), co jak 
# najbardziej ma sens - w moim mniemaniu równoważny z użyciem  breaka. 
# Po jego dopisaniu, działanie programu jest takie samo

# SKOŃCZONE
# Dominik Pająk | 04.03.2021 - 14:24

import random

def main():
    MaxLifes = 3;
    GenerateFrom = 1
    GenerateTo = 10
    
    randomInt = random.randint(GenerateFrom, GenerateTo)
    gameWinStatus = False
    lifesLeft = MaxLifes
    
    while lifesLeft > 0 and not gameWinStatus:
        print("Zostało żyć", lifesLeft)
        gameAlert = "Zgadnij liczbę od " + str(GenerateFrom) + " do " + str(GenerateTo) + ":  "
        guessedNumber = int(input(gameAlert))
        if guessedNumber == randomInt:
            gameWinStatus = True
            break;
        else:
            lifesLeft -= 1
            hint = guessedNumber < randomInt and "większa" or "mniejsza"
            print("Liczba jest ", hint, " od ", guessedNumber)
    else:
        if gameWinStatus:
            print("Wygrana! Odgadłeś liczbę")
        else:
            print("Przegrałeś. Liczba do odgadnięcia to: ", randomInt)
            

main()
# SKOŃCZONE
# Dominik Pająk - 11.03.2021 - 14:02

def getPhoneNumberBySurrname( myDict, surnamePattern ):
    matchingPhoneNumbers = []
    for item in list(myDict.keys()):
        if item[1] == surnamePattern: 
            matchingPhoneNumbers.append(myDict[item])
    
    return matchingPhoneNumbers

def main():
    contacts = {
        ('Jan', 'Kowalski'):"123456789", 
        ('Adam', 'Nowak'):"987654321" , 
        ('Adam', 'Kowalski'): "600300900"
    }
    
    print(contacts[('Jan', 'Kowalski')]);
    print(getPhoneNumberBySurrname(contacts, 'Kowalski'))
    
main()
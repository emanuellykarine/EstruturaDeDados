# testes tdd
import unittest

# Versão 1 - Escrever o teste (Red) - Receber numero 3
# def test_divisivel3():
#     assert divisivel3(3) == "fizz"

# Versão 2 - Fazer o teste passar (Green)
# def test_divisivel3():
#     assert divisivel3(3) == "fizz"

# def divisivel3(numero):
#     if numero == 3:
#         return "fizz"

#Versão 3 - Refactor
# def test_divisivel3():
#     assert divisivel3(6) == "fizz"

# def divisivel3(numero):
#     if numero % 3 == 0:
#         return "fizz"

#-----------------------------------------------------

#Versão 4 - Escrever o teste (Red) - Receber numero 5
# def test_divisivel5():
#     assert divisivel5(5) == "buzz"

#Versão 5 - Fazer o teste passar (Green)
# def test_divisivel5():
#     assert divisivel5(5) == "buzz"

# def divisivel5(numero):
#     if numero == 5:
#         return "buzz"

#Versão 6 - Refactor
# def test_divisivel5():
#     assert divisivel5(25) == "buzz"

# def divisivel5(numero):
#     if numero % 5 == 0:
#         return "buzz"

#-----------------------------------------------------

#Versão 7 - Escrever o teste (Red) - Receber numero 15
# def test_divisivel3e5():
#     assert divisivel3e5(15) == "fizzbuzz"

#Versão 8 - Fazer o teste passar (Green)
# def test_divisivel3e5():
#     assert divisivel3e5(15) == "fizzbuzz"

# def divisivel3e5(numero):
#     if numero == 15:
#         return "fizzbuzz"

#Versão 9 - Refactor
# def test_divisivel3e5():
#     assert divisivel3e5(30) == "fizzbuzz"

# def divisivel3e5(numero):
#     if (numero % 3 == 0) and (numero % 5 == 0):
#         return "fizzbuzz"

#-------------------------------------------------------------------

#Versão 10 - Escrever o teste (Red) - Receber X número e retornar de acordo com ele, juntando todos os divisiveis
# def test_fizzbuzz():
#     assert fizzbuzz(25) == "buzz"

#Versão 11 - Fazer o teste passar (Green)
# def test_fizzbuzz():
#     assert fizzbuzz(25) == "buzz"

# def fizzbuzz(numero):
#     if (numero == 25):
#         return "buzz"

#Versão 12 - Refactor
def test_fizzbuzz():
    assert fizzbuzz(30) == "fizzbuzz" 

def test_fizz():
    assert fizzbuzz(9) == "fizz"

def test_buzz():
    assert fizzbuzz(25) == "buzz"
    
def fizzbuzz(numero):
    if (numero % 3 == 0) and (numero % 5 == 0):
        return "fizzbuzz"
    elif (numero % 5 == 0):
        return "buzz"
    elif (numero % 3 == 0):
        return "fizz"
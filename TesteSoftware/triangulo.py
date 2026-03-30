a, b, c = map(int, input().split(" "))
 
if (a + b <= c) or (a + c <= b) or (b + c <= a):
    print('Nao é um triangulo')
elif (a == b) and (a == c) :
    print('Equilatero')
elif (a==b) or (a==c) or (b==c):
    print('Isósceles')
else:
    print('Escaleno')

# @emanuellykarine ➜ /workspaces/EstruturaDeDados/TesteSoftware (main) $ python triangulo.py
# 1 2 3
# Escaleno
# @emanuellykarine ➜ /workspaces/EstruturaDeDados/TesteSoftware (main) $ python triangulo.py
# 5 6 4
# Escaleno
# @emanuellykarine ➜ /workspaces/EstruturaDeDados/TesteSoftware (main) $ python triangulo.py
# 4 5 4 
# Isósceles
# @emanuellykarine ➜ /workspaces/EstruturaDeDados/TesteSoftware (main) $ python triangulo.py
# 4 4 4
# Equilatero




## Exemplo
Faça um programa que receba três valores que correspondem aos lados de um triângulo e retorne se esse triângulo é escaleno, isóceles ou equilátero.

## Passo 1 

Identificação das variáveis de entrada e das condições que estas devem satisfazer.

| Variáveis de entrada | Condições |
| :--- | :--- |
| Lista de valores | Tam_lista == 3 |
|  | Regra de existência de triângulo |
|  | Valores positivos


## Passo 2

Determinando as classes de equivalência. 

| Variáveis de entrada | Condições | Classes válidas | Classes inválidas
| :--- | :--- | :--- | :--- |
| Lista de valores | Tam_lista == 3 | 1. Tam == 3 | 2. Tam != 3
|  | Regra de existência de triângulo | 3. x + y > z && x + z > y && z + y > x | 4. Qualquer caso que a soma não seja maior que o outro lado
|  | Valores positivos | 5. x > 0 && y > 0 && z > 0 | 6. x <= 0 or y <= 0 or z <= 0

## Passo 3 

Especifique os casos de teste:

- Para (1), (3) e (5) - Válidos: Tamanho da lista igual a 3, valores positivos e bate com a condição de existência do triângulo. 
    
    tam_lista = 3 
    
    lista_valores = 4, 3, 5

- Para (2) - Tamanho da lista diferente de 3

    tam_lista = 4

    lista_valores = 2, 5, 6, 5

- Para (4) - Soma de dois valores é menor que o terceiro

    tam_lista = 3

    lista_valores = 12, 4, 7

- Para (6) - Valor negativo 

    tam_lista = 3

    lista_valores = -1, 4, 3



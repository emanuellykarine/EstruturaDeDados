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


## Análise do valor limite — Triângulo

A análise do valor limite testa os valores exatamente nos limites de cada condição (fronteiras), além de valores imediatamente abaixo e acima desses limites.

### Fronteiras identificadas

| Condição | Limite inválido (fora) | Limite válido (dentro) |
|----------|------------------------|------------------------|
| Lado positivo | 0 (a+b ≤ c quando a=0) | 1 (mínimo positivo) |
| Regra de existência (a+b vs c) | a+b = c (≤, inválido) | a+b = c+1 (>, válido) |


### Casos de teste

**Variando a (b=5, c=5 fixados):**

| a  | b | c | Resultado Esperado | Fronteira |
|----|---|---|--------------------|-----------|
| 0  | 5 | 5 | Não é triângulo    | a=0: a+b=5 ≤ c=5 (mínimo inválido) |
| 1  | 5 | 5 | Isósceles          | a=1 (mínimo válido) |
| 9  | 5 | 5 | Isósceles          | a = b+c−1 (último valor válido) |
| 10 | 5 | 5 | Não é triângulo    | a = b+c (no limite, inválido) |

**Variando b (a=5, c=5 fixados):** 

| a | b  | c | Resultado Esperado | Fronteira |
|---|----|---|--------------------|-----------|
| 5 | 0  | 5 | Não é triângulo    | b=0 (mínimo inválido) |
| 5 | 1  | 5 | Isósceles          | b=1 (mínimo válido) |

**Variando c (a=5, b=5 fixados):**

 a | b | c  | Resultado Esperado | Fronteira |
---|---|----|--------------------|-----------|
 5 | 5 | 0  | Não é triângulo    | c=0 (mínimo inválido) |
 5 | 5 | 1  | Isósceles          | c=1 (mínimo válido) |


---

## Análise do valor limite — Desconto por Dependente

**Problema:** a entrada é a idade do dependente, restrita ao intervalo **[0..24]**.

### Faixas de desconto

| Faixa | Intervalo de idade | Desconto |
|-------|--------------------|----------|
| 1     | 0 – 12             | 15%      |
| 2     | 13 – 18            | 12%      |
| 3     | 19 – 21            | 5%       |
| 4     | 22 – 24            | 3%       |

### Fronteiras identificadas

| Fronteira | Valor abaixo | Valor no limite | Valor acima |
|-----------|-------------|-----------------|-------------|
| Limite mínimo global    | —   | 0 (15%)  | 1 (15%)  |
| Faixa 1 → Faixa 2       | 12 (15%) | — | 13 (12%) |
| Faixa 2 → Faixa 3       | 18 (12%) | — | 19 (5%)  |
| Faixa 3 → Faixa 4       | 21 (5%)  | — | 22 (3%)  |
| Limite máximo global    | 24 (3%)  | — | 25 (inválido) |

### Casos de teste

| # | Idade | Resultado Esperado | Fronteira / Justificativa |
|---|-------|--------------------|---------------------------|
| 1 | −1    | Inválido           | Abaixo do limite mínimo (min−1) |
| 2 | 0     | 15%                | Limite mínimo global (min) |
| 3 | 1     | 15%                | Logo acima do mínimo (min+1) |
| 4 | 6     | 15%                | Valor nominal da faixa 1 |
| 5 | 11    | 15%                | Abaixo da fronteira F1/F2 (max−1 da faixa 1) |
| 6 | 12    | 15%                | Limite superior da faixa 1 (max da faixa 1) |
| 7 | 13    | 12%                | Limite inferior da faixa 2 (min da faixa 2) |
| 8 | 14    | 12%                | Logo acima do mínimo da faixa 2 (min+1) |
| 9 | 15    | 12%                | Valor nominal da faixa 2 |
| 10| 17    | 12%                | Abaixo da fronteira F2/F3 (max−1 da faixa 2) |
| 11| 18    | 12%                | Limite superior da faixa 2 (max da faixa 2) |
| 12| 19    | 5%                 | Limite inferior da faixa 3 (min da faixa 3) |
| 13| 20    | 5%                 | Valor nominal da faixa 3 |
| 14| 21    | 5%                 | Limite superior da faixa 3 (max da faixa 3) |
| 15| 22    | 3%                 | Limite inferior da faixa 4 (min da faixa 4) |
| 16| 23    | 3%                 | Valor nominal da faixa 4 |
| 17| 24    | 3%                 | Limite máximo global (max) |
| 18| 25    | Inválido           | Acima do limite máximo (max+1) |

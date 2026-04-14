## Testes funcionais - CDU 014 - Emitir relatório

Este documento especifica os testes que devem ser realizados para o caso de uso 014 - Emitir relatório. Ele contém todas as informações necessárias para a construção dos scripts de teste, como preparação do ambiente, dados de entrada e resultados esperados.

### Especificação do CDU
- **Ator principal:** Assistente Social
- **Resumo:** As Assistentes Sociais podem emitir relatórios contendo um resumo de informações pré-selecionadas por elas.
- **Pré-condição:** Ter feito o login e Estar na aba de "Relatórios"
- **Pós-condição:**
O sistema deve emitir um relatório com as informações pré-selecionadas e o formato escolhido pelo usuário.


### Classes de equivalência

- Obrigatórias: `titulo`, `formato_relatorio`
- Opcionais de filtro: `ano`, `programa`, `situacao_sistemica`, `ingresso`, `curso`, `periodo_referencia`, `situacao_periodo`, `turno`

| Campo | Regra/Condição | Classe válida | Classe inválida | Resultado esperado |
| :--- | :--- | :--- | :--- | :--- |
| `titulo` | Texto obrigatório | Texto não vazio e no formato aceito pelo serializer | Ausente, vazio, tipo inválido | Inválido retorna HTTP 400 |
| `formato_relatorio` | Domínio fechado | `pdf`, `docx`, `excel` | Qualquer outro valor, vazio, ausente | Inválido retorna HTTP 400 |
| `ano` | Filtro opcional com faixa | Inteiro entre `2000` e ano atual | Não numérico, `<2000`, `>ano atual` | Válido filtra `data_inicio__year`; inválido retorna HTTP 400 |
| `programa` | Filtro opcional | Inteiro válido (após conversão) | Não numérico | Válido filtra por `tipo_auxilio`; inválido retorna HTTP 400 |
| `situacao_periodo` | Filtro opcional | Valor válido para status do aluno | Tipo/valor inválido | Válido filtra por `aluno__status`; inválido retorna HTTP 400 |
| `ingresso` | Filtro opcional | Valor válido para regra de ingresso | Tipo/valor inválido | Válido filtra por `aluno__ingresso`; inválido retorna HTTP 400 |
| `periodo_referencia` | Filtro opcional | Valor válido para período | Tipo/valor inválido | Válido filtra por `aluno__periodo`; inválido retorna HTTP 400 |
| `curso` | Filtro opcional por ID | Inteiro válido e existente em `Cursos` | Não numérico, ID inexistente | ID inexistente tende a erro de execução (`Cursos.DoesNotExist`) |
| `situacao_sistemica` | Campo opcional recebido | Presente ou ausente | Valor inválido (se serializer restringir) | No código atual não altera filtro |
| `turno` | Campo opcional recebido | Presente ou ausente | Valor inválido (se serializer restringir) | No código atual não altera filtro |
| Combinação de filtros | Interseção de critérios | Filtros consistentes | Combinação incompatível | Válido pode retornar 0, 1 ou N registros |
| Resultado de busca | Dados para gerar arquivo | Lista com registros | Lista vazia | Ambos são válidos; arquivo deve ser gerado mesmo vazio |

### Análise de valor limite 

Para este caso de uso, a maior parte dos campos é categórica (enum/texto), então análise de valor limite se aplica principalmente a campos numéricos/identificadores validados no serializer.

#### 1) `ano` (inteiro com faixa validada no serializer)

Faixa válida: `2000 <= ano <= ano_atual`.

| Caso | Entrada | Resultado esperado |
| :--- | :--- | :--- |
| Limite inferior inválido | `ano = 1999` | HTTP 400 (`Ano inválido.`) |
| Limite inferior válido | `ano = 2000` | Relatório gerado |
| Valor nominal válido | `ano = 2024` | Relatório gerado |
| Limite superior válido | `ano = ano_atual` | Relatório gerado |
| Limite superior inválido | `ano = ano_atual + 1` | HTTP 400 (`Ano inválido.`) |

#### 2) `curso` (conversão para inteiro)

| Caso | Entrada | Resultado esperado |
| :--- | :--- | :--- |
| Tipo inválido | `curso = "abc"` | HTTP 400 (`Curso deve ser um número inteiro válido.`) |
| Tipo válido (existente) | `curso = 1` (exemplo existente) | Relatório gerado com filtro de curso |
| Valor grande inexistente | `curso = 999999` | Erro por `Cursos.DoesNotExist` (comportamento atual) |

#### 3) `programa` (conversão para inteiro)

| Caso | Entrada | Resultado esperado |
| :--- | :--- | :--- |
| Tipo inválido | `programa = "xyz"` | HTTP 400 (`Programa deve ser um número inteiro válido.`) |
| Tipo válido | `programa = 2` | Relatório gerado com filtro de programa |

#### 4) `titulo` (texto obrigatório)

| Caso | Entrada | Resultado esperado |
| :--- | :--- | :--- |
| Ausente | sem `titulo` | HTTP 400 |
| Vazio | `titulo = ""` | HTTP 400 |
| Preenchido | `titulo = "Relatório mensal"` | Relatório gerado |

### Regras dos models que impactam o relatório

Com base em `Beneficio` e `Solicitacao`:

- `Beneficio.status` é booleano: no relatório deve sair como `Ativo` (True) ou `Inativo` (False).
- `Beneficio.data_inicio` e `Beneficio.data_fim` possuem regra de consistência (`data_inicio <= data_fim`).
- `tipo_auxilio` e `solicitacao.status` podem ser nulos em cenários de dados incompletos históricos; o serializer retorna `None` para campos derivados quando necessário.
- `data_inicio` e `data_fim` devem ser exibidas no formato `dd/mm/aaaa` nos arquivos gerados.

### Casos essenciais derivados das classes

| Entrada 1 | Entrada 2 | Entrada 3 | Entrada 4 | Entrada 5 | Resultado Esperado | Resultado Obtido | Situação |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| `titulo` válido | `formato_relatorio=pdf` | sem filtros opcionais | - | - | HTTP 200, `Content-Type=application/pdf` | - | Não executado |
| `titulo` válido | `formato_relatorio=docx` | sem filtros opcionais | - | - | HTTP 200, arquivo `.docx` | - | Não executado |
| `titulo` válido | `formato_relatorio=excel` | sem filtros opcionais | - | - | HTTP 200, arquivo `.xlsx` | - | Não executado |
| `titulo` válido | `formato_relatorio=csv` | sem filtros opcionais | - | - | HTTP 400 | - | Não executado |
| sem `titulo` | `formato_relatorio=pdf` | sem filtros opcionais | - | - | HTTP 400 | - | Não executado |
| `titulo` válido | `formato_relatorio=pdf` | `curso=1` (existente) | - | - | HTTP 200, dados filtrados | - | Não executado |
| `titulo` válido | `formato_relatorio=pdf` | `curso=999999` | - | - | Erro de execução no fluxo atual (`Cursos.DoesNotExist`) | - | Não executado |
| `titulo` válido | `formato_relatorio=pdf` | `ano=2000` | sem outros filtros | - | HTTP 200 | - | Não executado |
| `titulo` válido | `formato_relatorio=pdf` | `ano=1999` | sem outros filtros | - | HTTP 400 | - | Não executado |
| `titulo` válido | `formato_relatorio=pdf` | `programa="xyz"` | sem outros filtros | - | HTTP 400 | - | Não executado |
| `titulo` válido | `formato_relatorio=pdf` | massa com `Beneficio.status=True` | sem outros filtros | - | Campo Status exibido como `Ativo` | - | Não executado |
| `titulo` válido | `formato_relatorio=pdf` | massa com `Beneficio.status=False` | sem outros filtros | - | Campo Status exibido como `Inativo` | - | Não executado |
| `titulo` válido | `formato_relatorio=excel` | registro com `data_inicio=2026-04-14` | `data_fim=2026-12-31` | - | Datas exibidas como `14/04/2026` e `31/12/2026` | - | Não executado |
| `titulo` válido | `formato_relatorio=docx` | benefício com `tipo_auxilio` nulo | sem outros filtros | - | Geração sem quebra; célula de tipo vazia/`None` | - | Não executado |
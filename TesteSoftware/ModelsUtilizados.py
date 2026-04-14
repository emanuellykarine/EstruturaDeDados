class Solicitacao(models.Model):
    status = models.ForeignKey('StatusSolicitacao', on_delete=models.PROTECT, related_name = 'solicitacoes')
    descricao = models.CharField(max_length=200)
    data_criacao = models.DateField(auto_now_add=True)
    data_deferimento = models.DateField(null= True, blank = True)
    id_assistente_social = models.ForeignKey('AssistenteSocial', on_delete=models.CASCADE, related_name="id_AS", null= True, blank = True) # assistente social que deferiu ou nao deferiu o aluno
    id_aluno = models.ForeignKey('Aluno', on_delete=models.CASCADE, related_name="id_aluno")
    tipo_auxilio = models.ForeignKey('Auxilio', on_delete=models.CASCADE, related_name="auxilio")
    class Meta:
        db_table = "solicitacao"

    def clean(self):
        erros = {}

        # Regras de datas
        if self.data_criacao and self.data_deferimento and self.data_deferimento < self.data_criacao:
            erros['data_deferimento'] = "A data de deferimento não pode ser anterior à data de criação."

        if self.status.codigo == 'DEFERIDO' and not self.data_deferimento:
            erros['data_deferimento'] = "Deferimento exige data de deferimento"

        if self.status.codigo == 'EM_ANALISE' and self.data_deferimento:
            erros['status'] = "Status 'em análise' não deve ter data de deferimento"

        # Regras de benefício ativo
        aluno = self.id_aluno

        existe_deferimento_ativo = Beneficio.objects.filter(
            aluno=aluno,
            tipo_auxilio=self.tipo_auxilio,
            status=True   
        ).exclude(id=self.id if self.id else None)

        if existe_deferimento_ativo.exists():
            erros['status'] = "O aluno já possui um benefício ativo para este auxílio."

        if erros:
            raise ValidationError(erros)

    def __str__(self):
        return f"Solicitação {self.status}"

class Beneficio(models.Model):
    aluno = models.ForeignKey('Aluno', on_delete=models.CASCADE, related_name="beneficio")
    solicitacao = models.ForeignKey('Solicitacao', on_delete=models.CASCADE, related_name="beneficio")
    tipo_auxilio = models.ForeignKey('Auxilio', on_delete=models.CASCADE, related_name="beneficio")
    status = models.BooleanField(default=True)
    data_inicio = models.DateField(auto_now_add=True)
    data_fim = models.DateField()
    data_finalizacao = models.DateField(null=True, blank=True)
    justificativa_finalizacao = models.CharField(max_length=200, null=True, blank=True)

    def clean(self):
        erros = {}        
        if self.data_fim and self.data_inicio and self.data_inicio > self.data_fim:
            erros['data_fim'] = "A data de início não pode ser posterior à data de término."
        if erros:
            raise ValidationError(erros)

    def __str__(self):
        return self.tipo_auxilio.descricao
    
    class Meta:
        db_table = "beneficio"
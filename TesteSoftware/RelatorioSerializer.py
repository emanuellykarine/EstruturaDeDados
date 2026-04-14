class RelatorioCreateSerializer(serializers.Serializer):

    class Meta:
        model =  Solicitacao
        fields = [
            'status',
            'descricao',
            'data_deferimento',
            'id_aluno',
            'data_criacao',
            'id_assistente_social',
            'tipo_auxilio'
        ]
        
class RelatorioSerializer(serializers.Serializer):
    ano = serializers.CharField(required=False, allow_blank=True, allow_null=True)
    titulo = serializers.CharField(required=True)
    programa = serializers.CharField(required=False, allow_blank=True, allow_null=True)
    situacao_sistemica = serializers.CharField(required=False, allow_blank=True, allow_null=True)
    ingresso = serializers.CharField(required=False, allow_blank=True, allow_null=True)
    curso = serializers.CharField(required=False, allow_blank=True, allow_null=True)
    periodo_referencia = serializers.CharField(required=False, allow_blank=True, allow_null=True)
    situacao_periodo = serializers.CharField(required=False, allow_blank=True, allow_null=True)
    turno = serializers.CharField(required=False, allow_blank=True, allow_null=True)
    formato_relatorio = serializers.CharField(required=True)

    def validate_ano(self, value):
        if value in [None, '', 'null']:
            return None
        try:
            ano = int(value)
            if ano < 2000 or ano > datetime.now().year:
                raise serializers.ValidationError("Ano inválido.")
            return ano
        except (ValueError, TypeError):
            raise serializers.ValidationError("Ano deve ser um número inteiro válido.")

    def validate_programa(self, value):
        if value in [None, '', 'null']:
            return None
        try:
            return int(value)
        except (ValueError, TypeError):
            raise serializers.ValidationError("Programa deve ser um número inteiro válido.")

    def validate_curso(self, value):
        if value in [None, '', 'null']:
            return None
        try:
            return int(value)
        except (ValueError, TypeError):
            raise serializers.ValidationError("Curso deve ser um número inteiro válido.")

    def validate_formato_relatorio(self, value):
        formatos_validos = ["pdf", "docx", "excel"]
        if value not in formatos_validos:
            raise serializers.ValidationError("Formato inválido.")
        return value

class RelatorioGeradoSerializer(serializers.ModelSerializer):
    aluno = AlunoSerializer()
    curso = serializers.CharField(source='aluno.curso')
    tipo_auxilio = serializers.SerializerMethodField()
    motivo_entrada = serializers.SerializerMethodField()
    parecer = serializers.SerializerMethodField()

    def get_tipo_auxilio(self, obj):
        return obj.tipo_auxilio.nome if obj.tipo_auxilio else None

    def get_motivo_entrada(self, obj):
        return obj.solicitacao.descricao if obj.solicitacao else None

    def get_parecer(self, obj):
        return obj.solicitacao.status.descricao if obj.solicitacao and obj.solicitacao.status else None

    class Meta:
        model = Beneficio
        fields = [
            'aluno',
            'status',
            'tipo_auxilio',
            'data_inicio',
            'data_fim',
            'motivo_entrada',
            'parecer',
            'curso'
        ]
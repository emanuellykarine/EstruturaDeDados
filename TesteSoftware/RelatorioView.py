from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from api.models import Beneficio, Cursos
from api.serializers.auxilio_beneficio import RelatorioSerializer, RelatorioGeradoSerializer
from ..services.relatorioService import gerar_pdf, gerar_docx, gerar_xlsx
from drf_yasg.utils import swagger_auto_schema
from rest_framework.permissions import AllowAny

class RelatorioView(APIView):
    authentication_classes = [] # impede o jwt
    permission_classes = (AllowAny,)  # endpoin public pq é de login


    @swagger_auto_schema(request_body=RelatorioSerializer, security=[{'Bearer': []}])
    def post(self, request):
        serializer = RelatorioSerializer(data=request.data)

        if not serializer.is_valid():
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

        dados_validados = serializer.validated_data

        titulo = dados_validados['titulo']
        formato_relatorio = dados_validados['formato_relatorio']
        
        ano = dados_validados.get('ano')
        programa = dados_validados.get('programa')
        situacao_sistemica = dados_validados.get('situacao_sistemica')
        ingresso = dados_validados.get('ingresso')
        curso = dados_validados.get('curso')
        periodo_referencia = dados_validados.get('periodo_referencia')
        situacao_periodo = dados_validados.get('situacao_periodo')
        turno = dados_validados.get('turno')

        filtros = {}
        
        if situacao_periodo:
            filtros['aluno__status'] = situacao_periodo
        
        if curso is not None:
            curso_nome = Cursos.objects.get(id=curso).nome
            filtros['aluno__curso'] = curso_nome
        
        if periodo_referencia:
            filtros['aluno__periodo'] = periodo_referencia
        
        if ingresso:
            filtros['aluno__ingresso'] = ingresso
        
        if ano is not None:
            filtros['data_inicio__year'] = ano
        
        if programa is not None:
            filtros['tipo_auxilio'] = programa

        dados = Beneficio.objects.filter(**filtros)
        serializer = RelatorioGeradoSerializer(dados, many=True)

        if formato_relatorio == 'pdf':
            return gerar_pdf(titulo, serializer.data)
        elif formato_relatorio == 'docx':
            return gerar_docx(titulo,serializer.data)
        elif formato_relatorio == 'excel':       
            return gerar_xlsx(titulo, serializer.data)
        
        return Response({"ok": True})

# Projeto de Testes com Mockito
# C214 Projeto 1
## Descrição

Este projeto visa implementar e testar uma aplicação de gerenciamento de horários de atendimento de professores utilizando Mockito para mocks e JUnit para testes unitários. O sistema busca informações sobre horários de atendimento com base na identificação do professor e determina o prédio e sala de acordo com as informações fornecidas.

## Alunos

- Pedro Guilherme Fernandes Oliveira
- Lucas Mendes Ribeiro do Couto

## Estrutura do Projeto

### Classes

- **`BuscaHorario`**: Responsável por buscar o horário de atendimento do professor e determinar o prédio com base na sala.
- **`HorarioAtendimento`**: Representa as informações de horário de atendimento de um professor.
- **`HorarioServico`**: Interface para o serviço que fornece as informações de horário de atendimento.
- **`CampoObrigatorioAusenteException`**: Responsável pela exception personalizada se houver algum campo ausente ou inválido.

### Testes

Os testes são realizados utilizando JUnit e Mockito. Os principais testes incluem:

1. **Teste de sucesso**: Verifica se a busca pelo professor retorna as informações corretas, incluindo o nome do professor, horário de atendimento, período, sala e prédio.
2. **Teste de falha**: Verifica se a busca por um professor que não existe retorna nulo.
3. **Teste de predio**: Verifica se o prédio é corretamente determinado com base na sala fornecida. Inclui testes de sucesso e falha para diferentes intervalos de salas.

## Configuração

Para configurar o projeto, siga os passos abaixo:

1. Clone o repositório:
   ```bash
   git clone https://github.com/SeuUsuario/SeuRepositorio.git
2. Navegue até o diretório do projeto:
   ```bash
   cd seuRepositorio
3. Instale as Dependencias
   ```bash
   mvn install
4. execute os testes
   ```bash
   mvn test


      

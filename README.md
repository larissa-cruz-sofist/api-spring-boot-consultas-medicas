# API Consultas Médicas - Larissa - Patrick
Projeto voltado a aprendizagem, capaz de cadastrar Médicos, Pacientes e Consultas; bem como, realizar pesquisa por Médico, Paciente e Consultas; atualizar dados de Médico e Paciente; excluir (tornando inativo) o Médico, Paciente e Consultas, anteriormente cadastrados.

## Getting Started
1. Editar as variáveis de ambiente indicando o Java JDK 17
2. Clonar o projeto em sua máquina
3. Importar a pasta do projeto no Visual Studio Code
4. Ter as seguintes extensões instaladas no VS Code:
     - _Debugger for Java (Microsoft);_
     - _Extension Pack for Java (Microsoft);_
     - _Language Support for Java(TM) by Red Hat (Red Hat);_
     - _Lombok Annotations Support for VS Code (Microsoft);_
     - _Maven for Java (Microsoft);_
     - _Project Manager for Java (Microsoft);_
     - _Test Runner for Java (Microsoft);_
     - _Spring Boot Dashboard (Microsoft);_
     - _Spring Boot Extension Pack (VMware);_
     - _Spring Boot Tools (VMware);_
     - _Spring Initializr Java Support (Microsoft);_
5. Start na API pelo terminal: **mvn package spring-boot:repackage** (dentro da pasta do projeto), após, **java -jar target/api-consultas-0.0.1-SNAPSHOT.jar**

## Project Structure
- .github/workflows/ci.yml: Script do projeto para rodar na pipeline
- src/main: Arquivos de estrutura do projeto
- src/main/resources/db/migration: Arquivos de criação e alteração de tabelas e colunas do banco de dados
- src/main/resources/application.properties: Arquivo de configuração do banco de dados
- src/test: Mapeamento do cenário de teste

## Contributing
- Console do banco de dados H2: http://www.localhost:8080/h2-console
- Swagger: http://www.localhost:8080/swagger-ui/index.html
- Para gerar o relatório de cobertura de código e verificar o percentual de cobertura é necessário rodar o seguinte comando no terminal: **mvn clean verify**

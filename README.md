# API Consultas Médicas - Larissa - Patrick
Projeto trabalhado no curso "Spring Boot 3: Desenvolva uma API Rest em Java" da plataforma de aprendizagem Alura. Projeto voltado a aprendizagem, capaz de cadastrar Médicos, Pacientes e Consultas; bem como, realizar pesquisa por Médico, Paciente e Consultas; atualizar dados de Médico e Paciente; excluir (tornando inativo) o Médico, Paciente e Consultas, anteriormente cadastrados.

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
     - _Project Manager for Java_
     - _Test Runner for Java (Microsoft);_
     - _Spring Boot Dashboard (Microsoft);_
     - _Spring Boot Extension Pack (VMware);_
     - _Spring Boot Tools (VMware);_
     - _Spring Initializr Java Support (Microsoft);_
5. Start na API pelo terminal: **mvn install** (dentro da pasta do projeto), após, **java -jar target/api-consultas-0.0.1-SNAPSHOT.jar**

## Project Structure
- .github/workflows/ci.yml: Script do projeto para rodar na pipeline
- src/main: Arquivos de estrutura do projeto
- src/main/resources/db/migration: Arquivos de criação e alteração de tabelas e colunas do banco de dados
- src/main/resources/application.properties: Arquivo de configuração do banco de dados
- src/test: Mapeamento do cenário de teste

## Contributing
- Banco de dados H2: http://www.localhost:8080/h2-console
- Swagger: http://www.localhost:8080/swagger-ui/index.html
- Dentro do arquivo **application.properties** o caminho do banco de dados é definido como: **_spring.datasource.url=jdbc:h2:file:./data/demo_** para que rode na pipeline, contudo, para rodar localmente é necessário alterá-lo para: **_spring.datasource.url=jdbc:h2:file:/data/demo_**
- Para gerar o relatório de cobertura de código são necessários dois comandos: **mvn clean**, após, **jacoco:prepare-agent test install jacoco:report**

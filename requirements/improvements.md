## Boas práticas e convenções em Java

- Utilize nomes descritivos para classes, métodos e variáveis, seguindo o padrão camelCase.
- Escolha nomes que sejam significativos e representem claramente a função e o propósito do elemento.
- Evite abreviações e siglas obscuras, priorizando a clareza e a legibilidade do código.
- Siga as convenções de nomenclatura da linguagem Java, como usar nomes de classes iniciando com letra maiúscula e nomes de métodos e variáveis iniciando com letra minúscula.
- Organize seu código em pacotes bem estruturados, seguindo a convenção de nomenclatura reversa do domínio da empresa, como "br.com.sofist.projeto".
- Utilize nomes de pacotes, classes e interfaces que sejam claros e representem o propósito do código.
- Evite nomes genéricos para métodos, como `getData()` ou `processaDados()`, e opte por nomes mais específicos e descritivos.
- Prefira utilizar verbos para indicar a ação realizada pelos métodos, como `calcularTotal()` ou `salvarRegistro()`.
- Utilize comentários para explicar partes do código que não são óbvias ou que exigem esclarecimento adicional.
- Mantenha seu código limpo e legível, evitando linhas muito longas, indentação inconsistente e códigos complexos e confusos.
- Faça uso de constantes para valores fixos e evite "hardcoding" diretamente no código.
- Utilize convenções de formatação de código, como espaçamento adequado, quebras de linha e indentação consistente.
- Utilize boas práticas de programação, como evitar duplicação de código, manter métodos curtos e bem definidos, e seguir princípios de orientação a objetos.
- Realize testes unitários para garantir a qualidade e a robustez do código.
- Utilize ferramentas de análise estática de código, como o Sonar.



### Boas práticas no Spring Boot

- Utilize anotações do Spring Boot, como `@Controller`, `@Service` e `@Repository`, para marcar as classes corretamente e seguir a inversão de controle (IoC) e injeção de dependência.
- Utilize a anotação `@Autowired` para injetar dependências automaticamente, em vez de criar instâncias manualmente.
- Organize seu código em camadas (controllers, services, repositories) para uma melhor separação de responsabilidades.
- Utilize a anotação `@GetMapping`, `@PostMapping`, `@PutMapping` ou `@DeleteMapping` para mapear os endpoints corretamente e seguir as convenções REST.
- Faça uso dos recursos de tratamento de exceções do Spring, como a anotação `@ExceptionHandler`, para lidar com erros e retornar respostas adequadas.
- Utilize a configuração adequada para conexões com bancos de dados, como o `application.properties` ou o `application.yml`.
- Utilize o padrão DTO (Data Transfer Object) para transferir dados entre as camadas e evitar a exposição direta das entidades do banco de dados.
- Faça uso das anotações `@Transactional` e `@Rollback` para gerenciar transações de banco de dados.
- Utilize a anotação `@Cacheable` para aplicar caching em métodos que são acessados com frequência.
- Siga as melhores práticas de segurança, como a proteção de endpoints com autenticação e autorização adequadas.
- Monitore e registre logs adequadamente usando o framework de logging do Spring Boot. (pode dar uma olhada no sl4j)

### Estrutura de pacotes (pastas)

Em projetos de API Spring Boot, a organização dos pacotes geralmente segue uma estrutura comum, que ajuda a manter o código-fonte organizado e de fácil manutenção. Exemplo:

```
br
└── com
    └── sofist
        └── projeto
            ├── config
            ├── controller
            ├── dto
            ├── exception
            ├── model
            ├── repository
            ├── service
            └── util
```

Explicação dos pacotes:


- **config**: Contém classes de configuração, como configurações do Spring, configurações de segurança, configurações de banco de dados, etc.
- **controller**: Contém classes responsáveis por receber as requisições HTTP, mapear os endpoints da API e realizar a lógica de roteamento e controle.
- **dto**: Contém classes de objetos de transferência de dados (DTO) que representam as estruturas de dados utilizadas na comunicação entre o cliente e o servidor.
- **exception**: Contém classes que representam exceções personalizadas da aplicação.
- **model**: Contém classes que representam os modelos de dados da aplicação, como entidades JPA, objetos de domínio, etc.
- **repository**: Contém interfaces que definem as operações de acesso a dados, como consultas em banco de dados.
- **service**: Contém classes que implementam a lógica de negócio da aplicação.
- **util**: Contém classes utilitárias, como classes para manipulação de datas, conversão de tipos, etc.


## Exemplos de boas práticas no java


```
// Utilize nomes descritivos para classes, métodos e variáveis, seguindo o padrão camelCase.
public class ExemploClasse {
    private int exemploVariavel;

    public void exemploMetodo() {
        // ...
    }
}

// Siga as convenções de nomenclatura da linguagem Java.
public class ExemploDeClasse {
    private int exemploDeVariavel;

    public void exemploDeMetodo() {
        // ...
    }
}

// Organize seu código em pacotes bem estruturados.
package br.com.empresa.projeto;

public class ExemploClasse {
    // ...
}

// Utilize nomes de métodos específicos e descritivos.
public class Exemplo {
    public void calcularTotal() {
        // ...
    }
}

// Utilize comentários para explicar partes do código.
public class Exemplo {
    private int valor; // Valor da variável

    /**
     * Método para processar o valor e retornar o resultado.
     */
    public int processarValor() {
        // ...
    }
}

// Faça uso de constantes para valores fixos.
public class Exemplo {
    private static final int TAMANHO_MAXIMO = 100;

    public void processarDados() {
        if (dados.length > TAMANHO_MAXIMO) {
            // ...
        }
    }
}

// Realize testes unitários para garantir a qualidade do código.
public class ExemploTeste {
    @Test
    public void testarMetodoExemplo() {
        // ...
    }
}
```
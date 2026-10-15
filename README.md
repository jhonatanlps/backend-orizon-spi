# Orizon SPI — Backend

API backend do **Orizon SPI**, um sistema de monitoramento e gestão de segurança industrial desenvolvido para auxiliar no acompanhamento de máquinas, Equipamentos de Proteção Individual (EPIs), zonas de risco, ocorrências e alertas.

O backend centraliza as regras de negócio, disponibiliza endpoints REST para o frontend e utiliza WebSocket para transmitir atualizações em tempo real.

## Tecnologias utilizadas

- **Java** — linguagem de programação.
- **Spring Boot** — framework para desenvolvimento da aplicação backend.
- **Spring Web** — criação de endpoints REST.
- **Spring Data JPA** — persistência e acesso aos dados.
- **WebSocket e STOMP** — comunicação em tempo real entre backend e frontend.
- **Maven** — gerenciamento de dependências e build.
- **H2 Database** — banco de dados utilizado no ambiente de desenvolvimento, conforme a configuração da aplicação.

> O banco de dados e as dependências efetivamente utilizadas devem ser conferidos nos arquivos de configuração e no `pom.xml` do projeto.

## Funcionalidades

### Gerenciamento de máquinas

Permite manter informações sobre as máquinas monitoradas, incluindo nome, setor e descrição.

### Gerenciamento de EPIs

Organiza os Equipamentos de Proteção Individual cadastrados no sistema, possibilitando associá-los às zonas monitoradas.

### Gerenciamento de zonas de risco

Permite cadastrar zonas vinculadas às máquinas e configurar seus polígonos de delimitação, além de relacionar os EPIs aplicáveis a cada zona.

### Gerenciamento de ocorrências

Registra eventos relacionados à segurança industrial, incluindo data e hora, status, zona relacionada e funcionário associado, conforme os dados disponíveis.

### Sistema de alertas

Disponibiliza informações sobre alertas gerados a partir de ocorrências, incluindo mensagem, status, data e hora e referência à ocorrência relacionada.

### Comunicação em tempo real

Utiliza WebSocket com STOMP para publicar atualizações aos clientes conectados, permitindo que o frontend receba novas informações sem depender exclusivamente de consultas HTTP periódicas.

### Indicadores para o dashboard

Disponibiliza dados utilizados na apresentação de indicadores, como quantidades de ocorrências por status, informações sobre zonas e alertas registrados no sistema.

## Arquitetura do projeto

O backend segue uma organização em camadas, separando as responsabilidades da aplicação.

```text
backend-orizon-spi/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/fiap/ec/backend_orizon_spi/
│   │   │       ├── model/        # Entidades do sistema
│   │   │       ├── controller/   # Endpoints REST
│   │   │       ├── service/      # Regras de negócio
│   │   │       ├── repository/   # Acesso aos dados
│   │   │       └── ...           # Outras configurações
│   │   └── resources/
│   │       └── application.properties
│   └── test/                     # Testes automatizados
├── pom.xml
└── README.md
```

A estrutura é ilustrativa. Os pacotes e arquivos adicionais devem seguir a organização presente no repositório.

## Pré-requisitos

Antes de executar a aplicação, instale:

- [JDK](https://adoptium.net/) compatível com a versão do projeto.
- [Git](https://git-scm.com/).
- Maven, caso não utilize o Maven Wrapper incluído no repositório.
- Uma IDE Java, como IntelliJ IDEA, Eclipse ou VS Code, opcionalmente.

## Instalação

Clone o repositório:

```bash
git clone https://github.com/jhonatanlps/backend-orizon-spi.git

cd backend-orizon-spi
```

## Executando a aplicação

### Com Maven Wrapper

No Linux ou macOS:

```bash
./mvnw spring-boot:run
```

No Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

### Com Maven instalado

```bash
mvn spring-boot:run
```

Após iniciar, a aplicação ficará disponível na porta configurada no projeto. No ambiente de desenvolvimento utilizado pelo frontend, o endereço esperado é:

```text
http://localhost:8080
```

Caso a porta tenha sido alterada nas configurações, utilize o endereço correspondente.

## Gerando o build

Para compilar o projeto e executar os testes:

```bash
./mvnw clean verify
```

Para gerar o artefato JAR:

```bash
./mvnw clean package
```

O arquivo gerado ficará normalmente em `target/`.

Para executar o JAR:

```bash
java -jar target/nome-do-arquivo.jar
```

Substitua `nome-do-arquivo.jar` pelo nome efetivamente gerado pelo Maven.

## Entidades principais

| Entidade | Responsabilidade |
|---|---|
| `Maquina` | Representa uma máquina monitorada, com informações como nome e setor. |
| `Epi` | Representa um Equipamento de Proteção Individual. |
| `Zona` | Representa uma área monitorada, associada a uma máquina e aos EPIs aplicáveis. |
| `ZonaEpi` | Representa a associação entre uma zona e os EPIs relacionados. |
| `Ocorrencia` | Representa um evento de segurança registrado no sistema. |
| `Funcionario` | Armazena informações do funcionário relacionado a uma ocorrência. |
| `Alerta` | Representa uma notificação relacionada a uma ocorrência. |

Os relacionamentos e os campos obrigatórios são definidos nas respectivas entidades e configurações de persistência.

## API REST

A aplicação disponibiliza endpoints HTTP para consultar e gerenciar os dados do sistema.

Entre os recursos utilizados pela integração com o frontend estão:

| Recurso | Finalidade |
|---|---|
| `/api/alerta` | Acesso aos dados de alertas, conforme o mapeamento da API. |
| `/api/alerta/alertas-dia` | Consulta de informações de alertas do dia, conforme a implementação existente. |

Os caminhos acima representam rotas utilizadas no desenvolvimento da integração. Para obter a lista completa dos endpoints, consulte as anotações `@RequestMapping`, `@GetMapping`, `@PostMapping`, `@PutMapping` e `@DeleteMapping` nos controllers.

### Testando a API

Com o backend em execução, uma consulta pode ser realizada com `curl`:

```bash
curl -i http://localhost:8080/api/alerta
```

O resultado dependerá dos dados disponíveis no banco e do mapeamento efetivo da rota.

## WebSocket com STOMP

O backend mantém um canal de comunicação em tempo real para notificar o frontend sobre alterações nos dados monitorados.

Endpoint de conexão utilizado no ambiente local:

```text
ws://localhost:8080/ws
```

### Destinos de mensagens

| Tópico | Finalidade |
|---|---|
| `/topic/alerta` | Publicação de informações relacionadas a alertas. |
| `/topic/maquina` | Atualizações relacionadas às máquinas. |
| `/topic/epi` | Atualizações relacionadas aos EPIs. |
| `/topic/zona` | Atualizações relacionadas às zonas monitoradas. |

Os destinos devem corresponder exatamente aos tópicos configurados nos serviços de publicação e nas inscrições do frontend.

O fluxo geral de comunicação é:

1. Uma operação ou evento é processado pelo backend.
2. O serviço responsável prepara os dados da atualização.
3. O backend publica a mensagem no destino STOMP correspondente.
4. O frontend recebe a mensagem e atualiza os componentes inscritos.

## Integração com o frontend

O backend é integrado ao frontend Vue 3 disponível no repositório:

https://github.com/jhonatanlps/frontend-orizon-spi

Em ambiente de desenvolvimento, o frontend utiliza o proxy do Vite para encaminhar requisições HTTP ao backend, normalmente executado na porta `8080`.

A comunicação ocorre por meio de:

- **HTTP/REST:** consultas e operações sobre os recursos da API.
- **WebSocket/STOMP:** transmissão de eventos e atualização de informações em tempo real.

Para testar a integração completa:

1. Inicie o backend.
2. Inicie o frontend com `npm run dev`.
3. Acesse a interface pelo navegador.
4. Verifique se as consultas HTTP retornam os dados esperados.
5. Confirme a conexão WebSocket e as inscrições nos tópicos.
6. Registre ou atualize uma ocorrência de teste e confira se as informações são refletidas na interface.

## Configuração do banco de dados

As configurações de persistência ficam no arquivo `src/main/resources/application.properties` ou no arquivo equivalente utilizado pelo projeto.

Confira nesse arquivo:

- URL de conexão com o banco de dados.
- Driver JDBC.
- Usuário e senha, quando aplicável.
- Configuração do Hibernate e do esquema.
- Perfil de execução ativo.

Se a aplicação utilizar H2 em memória, os dados poderão ser perdidos quando o processo for encerrado. Verifique a URL JDBC e o modo de armazenamento configurado antes de assumir que os registros serão persistidos entre execuções.

## Solução de problemas

**A aplicação não inicia**

- Verifique a versão do Java.
- Confira as mensagens de erro no terminal.
- Execute `./mvnw clean verify` para identificar problemas de compilação ou testes.

**Erro 404 em uma requisição**

- Confira o caminho completo do endpoint.
- Verifique o prefixo definido em `@RequestMapping`.
- Confirme se a rota está registrada no controller.

**Erro de conexão com o banco**

- Confira a URL JDBC e as credenciais.
- Verifique se o banco está disponível.
- Analise a configuração do perfil ativo.

**O frontend não recebe atualizações**

- Verifique se o endpoint WebSocket está ativo.
- Confira se o frontend e o backend utilizam os mesmos destinos STOMP.
- Verifique se os serviços publicam as mensagens corretamente.
- Consulte os logs do backend e o console do navegador.

## Repositórios relacionados

- **Backend:** https://github.com/jhonatanlps/backend-orizon-spi
- **Frontend:** https://github.com/jhonatanlps/frontend-orizon-spi

## Objetivo do projeto

O Orizon SPI tem como objetivo centralizar informações relacionadas à segurança industrial, permitindo acompanhar máquinas, EPIs, zonas de risco, ocorrências e alertas em uma aplicação integrada.

A arquitetura combina uma API REST com comunicação em tempo real para facilitar a atualização dos indicadores e apoiar o monitoramento operacional.

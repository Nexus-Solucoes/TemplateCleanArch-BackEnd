# 🧼 TemplateCleanArch-BackEnd

Projeto backend com arquitetura limpa (Clean Architecture), desenvolvido em **Java 17** com **Spring Boot 3**, utilizando camadas bem definidas de domínio, aplicação, infraestrutura e configuração.

📺 Baseado no vídeo: [Clean Architecture com Spring Boot - DDD na prática](https://www.youtube.com/watch?v=hit0XHGt4WI)

---

## 📁 Estrutura de Diretórios

```
/
├─ pom.xml                                      # Configurações Maven e dependências
├─ src/
│  ├─ main/
│  │  ├─ java/com/salaoDeBeleza/
│  │  │  ├─ SalaoDeBelezaApplication.java       # Ponto de entrada Spring Boot
│  │  │
│  │  │  ├─ domain/                             # Camada de domínio (regras de negócio)
│  │  │  │  └─ entity/UserDomain.java           # Entidade do domínio
│  │  │
│  │  │  ├─ application/                        # Casos de uso
│  │  │  │  ├─ gateways/UserGateway.java        # Interface de abstração para acesso a dados
│  │  │  │  └─ usecases/CreateUserInteractor.java # Lógica para criação de usuário
│  │  │
│  │  │  ├─ infrastructure/                     # Camada de infraestrutura
│  │  │  │  ├─ controllers/User/...             # Controller, DTOs e Mapper
│  │  │  │  ├─ gateways/...                     # Implementação de gateways
│  │  │  │  └─ persistence/User/...             # Entidade JPA e Repository
│  │  │
│  │  │  └─ main/UserConfig.java                # Beans e injeções Spring
│  │
│  └─ resources/application.properties          # Configurações do Spring Boot
```

---

## 🧠 Clean Architecture Aplicada

A arquitetura segue os princípios de **separação de responsabilidades**, com **baixo acoplamento** e **alta coesão** entre camadas:

### 🧱 Camada de Domínio (`domain`)
Contém as entidades principais e regras puras de negócio.

- `UserDomain`: Define o modelo de domínio do usuário (username, password, email).

### 🚦 Camada de Casos de Uso (`application`)
Implementa a lógica de aplicação e orquestra o uso das entidades de domínio.

- `CreateUserInteractor`: Responsável por orquestrar a criação de um novo usuário.
- `UserGateway`: Interface que define como interagir com persistência, desacoplada da tecnologia.

### 🧰 Camada de Infraestrutura (`infrastructure`)
Responsável pela persistência, mapeamento e entrada de dados.

- **Controller**: `UserController` lida com requisições HTTP.
- **DTOs**: `CreateUserRequest` e `CreateUserResponse` são usados na entrada e saída da API.
- **Mapper**: `UserDTOMapper` converte entre DTOs e entidades do domínio.
- **Gateway**: `UserRepositoryGateway` implementa `UserGateway` usando Spring Data.
- **Persistence**: `UserEntity` (anotada com `@Entity`) e `UserRepository` (interface JPA).

### ⚙️ Camada de Configuração (`main`)
Registra os beans do projeto no contexto Spring.

- `UserConfig`: Configura beans como `UserDTOMapper`, `UserEntityMapper`, `CreateUserInteractor`, etc.

---

## 🌐 API REST

### Endpoint: Criar Usuário

- **URL:** `POST /users`
- **Request Body:**
```json
{
  "username": "joao",
  "password": "123456",
  "email": "joao@email.com"
}
```

- **Response:**
```json
{
  "username": "joao",
  "email": "joao@email.com"
}
```

---

## ⚙️ Dependências (pom.xml)

Inclui os principais starters do Spring Boot:

- `spring-boot-starter-web` → Web e REST API
- `spring-boot-starter-data-jpa` → ORM com JPA
- `postgresql` e `h2` → Suporte a banco de dados
- `lombok` → Redução de boilerplate (constructors, getters, etc.)
- `spring-boot-devtools` → Hot reload

---

## 🛠️ Como Rodar

### Pré-requisitos

- Java 17+
- Maven

### Passos

```bash
# 1. Clonar o repositório
git clone https://github.com/Nexus-Solucoes/TemplateCleanArch-BackEnd
cd TemplateCleanArch-BackEnd

# 2. Rodar o projeto
./mvnw spring-boot:run
```

A aplicação será iniciada em: [http://localhost:8080](http://localhost:8080)

---

## 🧪 Testes

```bash
./mvnw test
```

---

## 📦 Banco de Dados

### H2 (default para testes)
- URL: `jdbc:h2:~/data/db`

---

## 📋 Convenções de Código

- **Domínio:** `UserDomain.java`
- **Controller:** `UserController.java`
- **DTOs:** `CreateUserRequest`, `CreateUserResponse`
- **Mapper:** `UserDTOMapper`, `UserEntityMapper`
- **Repository:** Interface `UserRepository` + entidade JPA
- **UseCases:** `CreateUserInteractor`
- **Configuração:** Beans definidos em `UserConfig.java`

---

## 💡 Boas práticas utilizadas

- Inversão de dependência via interfaces (UserGateway)
- Camadas independentes com injeção via Spring
- DTOs para entrada/saída de API
- Mapeamento limpo entre entidades do domínio ↔ entidades de banco
- Arquitetura preparada para escalar e adicionar novos casos de uso

---

## 🧰 Tutorial: Criando uma nova funcionalidade (exemplo: Produto)

Este projeto usa o `User` como template. Siga os passos abaixo para criar qualquer nova entidade seguindo a Clean Architecture:

### 1. **Camada de Domínio (`domain`)**
Crie a entidade de domínio:

```java
// domain/entity/ProductDomain.java
public class ProductDomain {
    private String nome;
    private Double preco;
}
```

---

### 2. **Camada de Aplicação (`application`)**

#### a) Interface Gateway:
```java
// application/gateways/ProductGateway.java
public interface ProductGateway {
    ProductDomain create(ProductDomain product);
}
```

#### b) Caso de uso:
```java
// application/usecases/CreateProductInteractor.java
public class CreateProductInteractor {
    private final ProductGateway gateway;

    public CreateProductInteractor(ProductGateway gateway) {
        this.gateway = gateway;
    }

    public ProductDomain execute(ProductDomain product) {
        return gateway.create(product);
    }
}
```

---

### 3. **Camada de Infraestrutura (`infrastructure`)**

#### a) Entidade JPA:
```java
// infrastructure/persistence/Product/ProductEntity.java
@Entity
public class ProductEntity {
    @Id @GeneratedValue
    private Long id;
    private String nome;
    private Double preco;
}
```

#### b) Repository:
```java
// infrastructure/persistence/Product/ProductRepository.java
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {}
```

#### c) Mapper de entidade:
```java
// infrastructure/gateways/mappers/ProductEntityMapper.java
public class ProductEntityMapper {
    public ProductEntity toEntity(ProductDomain domain) {
        ...
    }

    public ProductDomain toDomain(ProductEntity entity) {
        ...
    }
}
```

#### d) Implementação do Gateway:
```java
// infrastructure/gateways/ProductRepositoryGateway.java
public class ProductRepositoryGateway implements ProductGateway {
    private final ProductRepository repository;
    private final ProductEntityMapper mapper;

    public ProductRepositoryGateway(...) { ... }

    public ProductDomain create(ProductDomain product) {
        return mapper.toDomain(repository.save(mapper.toEntity(product)));
    }
}
```

#### e) DTOs e Mapper:
```java
// controllers/Product/dto/CreateProductRequest.java
public record CreateProductRequest(String nome, Double preco) {}

// controllers/Product/dto/CreateProductResponse.java
public record CreateProductResponse(String nome, Double preco) {}

// controllers/Product/dto/mapper/ProductDTOMapper.java
public class ProductDTOMapper {
    public ProductDomain toDomain(CreateProductRequest req) { ... }
    public CreateProductResponse toResponse(ProductDomain domain) { ... }
}
```

#### f) Controller:
```java
// controllers/Product/ProductController.java
@RestController
@RequestMapping("/products")
public class ProductController {
    private final CreateProductInteractor interactor;
    private final ProductDTOMapper mapper;

    @PostMapping
    public ResponseEntity<CreateProductResponse> create(@RequestBody CreateProductRequest req) {
        var domain = interactor.execute(mapper.toDomain(req));
        return ResponseEntity.ok(mapper.toResponse(domain));
    }
}
```

---

### 4. **Configuração (Beans)**

```java
// main/ProductConfig.java
@Configuration
public class ProductConfig {

    @Bean
    public ProductDTOMapper productDTOMapper() {
        return new ProductDTOMapper();
    }

    @Bean
    public ProductEntityMapper productEntityMapper() {
        return new ProductEntityMapper();
    }

    @Bean
    public ProductGateway productGateway(ProductRepository repository, ProductEntityMapper mapper) {
        return new ProductRepositoryGateway(repository, mapper);
    }

    @Bean
    public CreateProductInteractor createProductInteractor(ProductGateway gateway) {
        return new CreateProductInteractor(gateway);
    }
}
```

---

✅ **Pronto!** Agora você tem uma nova funcionalidade (`Product`) 100% integrada e isolada seguindo a arquitetura limpa.

Para outras funcionalidades, basta repetir essa estrutura com os nomes da nova entidade.

---

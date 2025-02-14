
# Gerenciamento de Mercados

## Descrição

Esta aplicação é um sistema de gerenciamento de mercados, clientes, funcionários e compras. O back-end é desenvolvido em **Java com Spring Boot**, enquanto o front-end é feito com **React.js**. A aplicação permite a **criação**, **leitura**, **atualização** e **exclusão (CRUD)** de mercados, clientes, atendimentos e compras. Além disso, oferece uma interface responsiva para o gerenciamento desses dados.

## Tecnologias Utilizadas

### Back-End:
- **Java 21** (ou superior)
- **Spring Boot**
- **JPA (Java Persistence API)**
- **PostgreSQL** (Banco de dados)
- **Maven** (Gerenciador de dependências)
- **Spring Data JPA** (Para interação com o banco de dados)

### Front-End:
- **React.js** (Biblioteca JavaScript)
- **React Router DOM** (Para navegação entre as páginas)
- **CSS** (Para estilo e layout responsivo)
- **Axios** (Para chamadas à API)

## Funcionalidades

### Back-End:
- **CRUD** para **Mercados**, **Clientes**, **Atendimentos** e **Compras**.
- Implementação de endpoints **RESTful** para comunicação com o front-end.

### Front-End:
- Página de listagem de **Mercados**, com visualização de detalhes e exclusão de mercados.
- Funcionalidades de **criação**, **edição**, **visualização** e **exclusão** para mercados e clientes.

## Instalação

### Requisitos:
- **Java 21 ou superior**
- **PostgreSQL** (Instalado localmente ou em um servidor)
- **Node.js** (Para rodar o front-end)

### Passos para Rodar o Projeto:

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/victorrgoms/market-management.git
   ```

2. **Back-End:**
    - Navegue até a pasta do **back-end**:
      ```bash
      cd mercado-app
      ```
    - Para rodar o back-end, use o comando:
      ```bash
      mvnw.cmd spring-boot:run
      ```
    - Depois, instale as dependências:
      ```bash
      mvn clean install
      ```

3. **Front-End:**
    - Navegue até a pasta do **front-end**:
      ```bash
      cd mercado-frontend
      ```
    - Instale as dependências:
      ```bash
      npm install
      ```
    - Instale o **Axios** para fazer chamadas à API:
      ```bash
      npm install axios
      ```
    - Por fim, inicie o servidor de desenvolvimento:
      ```bash
      npm start
      ```

4. **Configuração do Banco de Dados:**
    - Em sua IDE, abra o arquivo `application.yml`, localizado em:
      `Mercado Gerenciamento\mercado-app\src\main
 esources`
    - Alterar o **username** e o **password** para os valores corretos do seu banco de dados PostgreSQL:
      ```yaml
      spring:
        datasource:
          url: jdbc:postgresql://localhost:5432/mercado_db
          username: <SEU_USUARIO>
          password: <SUA_SENHA>
      ```

5. **Banco de Dados:**
    - Certifique-se de que o PostgreSQL está instalado e configurado corretamente.
    - Crie a base de dados `mercado_db` no PostgreSQL com o comando:
      ```sql
      CREATE DATABASE mercado_db;
      ```


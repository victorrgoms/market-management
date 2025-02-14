# Gerenciamento de Mercados

## Descrição

Esta é uma aplicação de gerenciamento de mercados, clientes, funcionários e compras. A aplicação foi construída utilizando Java com Spring Boot para o back-end e React.js para o front-end. Ela permite a criação, leitura, atualização e exclusão de mercados, clientes, atendimentos e compras, além de oferecer uma interface interativa e responsiva para o gerenciamento dos dados.

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

- **Back-End**:
  - CRUD (Criar, Ler, Atualizar, Deletar) para **Mercados**, **Clientes**, **Atendimentos** e **Compras**.
  - Implementação de endpoints RESTful para comunicação com o front-end.

- **Front-End**:
  - Página de listagem de **Mercados** com visualização de detalhes e exclusão de mercados.
  - Funcionalidade de **criação**,**edição**,**vizualização** e **exclusão**.

## Instalação

1. Clone o repositório:
   ```bash
   git clone https://github.com/victorrgoms/market-management.git

2. No terminal navegue até a pasta mercado-app
  - Rode:
    mvnw.cmd spring-boot:run
  - Depois rode:
    mvn clean install

3. No terminal navegue até a pasta mercado-frontend
- Rode:
  npm install
- Depois rode:
  npm install axios
- Por fim, rode:
  npm start

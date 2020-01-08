# Custumer Service

### Requisitos

1. JDK 8
1. Maven 3

### Rodando

1. Clone o projeto: `https://github.com/leonardohenrique/tokio-test.git`
1. Entre na pasta `tokio-test` e execute: `mvn spring-boot:run`
1. Acesse: `http://localhost:8080/customers`


###
Para gerar o token, acessar a URI: http://localhost:8080/auth
passando os atributos "email" e "senha" no corpo da requisicao (JSON)
O email e a senha eh de um usuario que foi criado na classe "InitializeDatabaseWithRecords" a senha no formato descriptografado é "43924896"
Apos gerar o Token, estara disponivel as requisicoes do tipo POST, PUT e DELETE nas URIs informando o mesmo.

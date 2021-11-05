Para rodar, necessário Java e Maven

- mvn install
- mvn spring-boot:run



Usuário padrão para realizar login: 
        {
            "email": "admin@admin.com",
            "password": "admin"
        }

Rotas get { /address e /customer } estão liberadas. Para o restante, autenticar pelo login e passar o header para obter autorização.

Db script: '\teste\src\main\resources'

Testes incompletos.

Swagger: /swagger-ui.html

Rota /customer/admin para criar admin, /customer para criar usuário padrão

Prova 2 - Projeto de Software e Gestão Ágil

---

Sobre os requisitos não funcionais (ou atributos de qualidade), principalmete a segurança, verifique as afirmações a seguir e selecione apenas as alternativas verdadeiras


- A disponibilidade, que indica o tempo durante o qual o sistema deve estar operacional, é um requisito funcional.

- A técnica de "least privilege," que concede aos usuários apenas as permissões mínimas necessárias para realizar suas tarefas, é uma abordagem recomendada para fortalecer a segurança da arquitetura de software.

- A prática de manter sistemas e bibliotecas sempre atualizados com as últimas correções de segurança é relevante para a segurança de uma aplicação.

- A portabilidade, que se refere à facilidade de transferir um sistema para diferentes ambientes, é um exemplo de requisito não funcional.

- Ferramentas de automação de fluxos de CI/CD são importantes apenas para a confiabilidade da aplicação.

- Requisitos de segurança, que visam proteger o sistema contra ameaças, são geralmente considerados requisitos funcionais.

- O processo de autorização em uma aplicação é dependente do processo de autenticação.

- O controle de acesso é o único aspecto de seguran;ca que deve ser considerado na implementação de uma aplicação.

- Requisitos de usabilidade, como a facilidade de aprendizado e a satisfação do usuário, são considerados requisitos não funcionais.

- A confiabilidade de software não é uma preocupação significativa em sistemas que não lidam com informações críticas ou operações sensíveis.

---

Baixe o projeto disponível no arquivo prova-2.zip, nele existe um projeto que usa o spring-security já implementado, esse projeto possui duas rotas:


1) POST /user que é uma rota aberta (não tem autenticação) para salvar novos usuários e que recebe o email, password e uma lista de roles daquele usuário.

2) GET /user/teste que é uma rota apenas para teste da autenticação, para acessar essa rota é necessário enviar os headers com o usuário e senha do usuário.


A partir desse projeto faça as seguintes implementações:


1) Inclua no momento do login do usuário um log de auditória, esse log deve salvar toda tentativa de acesso do usuário, incluindo um id, o e-mail do usuário e o horário que o acesso foi feito. (2,0 pontos)

2) Crie uma nova rota que mostre todos os registros que existem neste log de auditória. Apenas usuários que possuem o papel de ADMIN devem ter acesso a essa. rota. (1,0 ponto)

3) Crie uma nova rota para salvar vendas em uma nova tabela do banco de dados. As vendas devem ter os seguintes dados: id, valor, dataVenda e cpf do cliente. Essa rota deve ser autenticada e apenas ADMINs podem acessar essa rota. (2,0 pontos)

4) Crie uma nova rota para listar todas as vendas que foram cadastradas no sistema. Essa rota deve ocultar o cpf do cliente que fez a compra e deve ter um filtro de valorMinimo (retornar apenas vendas com valor acima do parâmetro passado). Essa rota deve ser aberta (não precisa de autenticação). (1 ponto)

5) Crie uma nova rota para retornar os dados específicos de uma venda. Essa rota deve receber no path o id de uma venda. Essa rota só pode ser acessada por usuários que tenham a role CLIENT e apenas os clientes que fizeram a compra podem acessar a sua própria compra, validar isso pelo cpf e e-mail do usuário. (1 ponto)

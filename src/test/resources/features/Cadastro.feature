# language: pt
Funcionalidade: : Cadastro de nova conta

  Cenario: Cadastro de conta
    Dado usuario acessou cadastro para criar nova conta
    E usuario preencheu o formulario com informacoes validas
      | nome | sobrenome | pais | estado | email | senha |
      | Joao | das Neves | BR | Sao Paulo | joao@dasneves.com | senha123 |
    Quando usuario submeter os dados
    Então usuario o cadastro deve ter sido efetuado com sucesso
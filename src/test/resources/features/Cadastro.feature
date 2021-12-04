# language: pt
Funcionalidade: : Cadastro de nova conta

  Cenario: Cadastro de conta
    Dado usuario acessou cadastro para criar nova conta
    E usuario preencheu o formulario com informacoes validas
      | usuario | senha | endereco | numero | cep | cidade | estado | e-mail | cpf |
      | jose | senha123 | rua das gaivotas | 123 | 12946-742 | atibaia | SP | jose@jose.com.br | 555.555.555-55 |
    Quando usuario submeter os dados
    Então usuario o cadastro deve ter sido efetuado com sucesso
# language: pt
  Funcionalidade: Login na plataforma

    Esquema do Cenario: Login com dados validos
      Dado usuario que possui uma conta ativa
      Quando usuario insere nome e senha validos
      E usuario clica no botao Entrar
      Entao sistema deve redirecionar para a pagina inicial
      E suario deve estar autenticado

    Esquema do Cenario: Login com dados invalidos
      Dado usuario que nao possui conta cadastrada
      Quando usuario insere nome e senha invalidos
      E usuario clica no botao Entrar
      Entao sistema deve mostrar a mensagem de erro
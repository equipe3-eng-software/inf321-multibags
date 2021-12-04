# language: pt
  Funcionalidade: Login na plataforma

    Cenario: Login com dados validos
      Dado usuario pre cadastrado
        | email | senha |
        | joao@dasneves.com | senha123 |
      Quando clica no botao submit
      Entao sistema deve redirecionar para o dashboard
      E usuario deve estar autenticado

    Cenario: Login com dados invalidos
      Dado usuario sem cadastro previo
        | email | senha |
        | talita@tester.com | unicamp |
      Quando clica no botao submit
      Entao sistema deve mostrar a mensagem de erro
# language: pt
  Funcionalidade: Alteracao de senha

#    Se o teste falhar no login é pq a nova_senha e senha_antiga estão trocadas!

    Cenario: Alterar senha de usuario
      Dado que estou na tela de perfil do usuario
        | email | senha |
        | joao@dasneves.com | 123senha |
      Quando clico no botao alterar senha
      Entao abrira o formulario com os campos
      E ao inserir os dados corretamente o botao de submissao sera habilitado
        | nova_senha | senha_antiga |
        | senha123     | 123senha |
      E devo efetuar a acao de clique
      E devo receber a mensagem de sucesso
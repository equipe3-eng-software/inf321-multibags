# language: pt
  Funcionalidade: Alteracao de senha

    Cenario: Alterar senha de usuario
      Dado que estou na tela de perfil do usuario
      Quando clico no botao alterar senha
      Entao abrira o formulario com os campos
      E ao inserir os dados corretamente o botao de submissao sera habilitado
      E devo efetuar a acao de clique
      E devo ser redirecionado para meu perfil
      E dados devem estar com os novos valores
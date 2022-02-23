#language: pt
Funcionalidade: Realizar compras no site de e-commerce parte 2

  Cenario: voltar pagina inicial
    Dado que retorno para pagina inicial

  Esquema do Cenario: realizar compras de todos os produtos
    Dado que seleciono os produtos <opcao>
    Quando adicionar no carrinho de compras todos os produtos
    Entao realizo o checkout dos produtos

    Exemplos: 
      | opcao   |
      | "todos" |

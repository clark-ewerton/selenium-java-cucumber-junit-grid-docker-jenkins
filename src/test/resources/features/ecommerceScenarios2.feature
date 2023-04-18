#language: pt
Funcionalidade: Realizar compras no site de e-commerce com produtos sem desconto

  @ignore
  Cenario: voltar pagina inicial
    Dado que retorno para pagina inicial

  Cenario: voltar pagina todos os produtos
    Dado que retorno para pagina todos os produtos

  Esquema do Cenario: realizar compras de todos os produtos sem desconto
    Dado que seleciono os produtos <opcao>
    #Quando adicionar no carrinho de compras todos os produtos
    Entao realizo o checkout de um dos produtos

    Exemplos: 
      | opcao   |
      | "semdesconto" |

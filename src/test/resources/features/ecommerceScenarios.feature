#language: pt
Funcionalidade: Realizar compras no site de e-commerce com produtos com desconto

  Esquema do Cenario: realizar compras de produtos com desconto
    Dado que seleciono os produtos <opcao>
    #Quando adicionar no carrinho de compras os produtos com desconto
    Entao realizo o checkout de um dos produtos

    Exemplos: 
      | opcao      |
      | "desconto" |

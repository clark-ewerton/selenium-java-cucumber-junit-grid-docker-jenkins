#language: pt
Funcionalidade: Realizar compras no site de e-commerce

  Esquema do Cenario: realizar compras de produtos com desconto
    Dado que seleciono os produtos <opcao>
    Quando adicionar no carrinho de compras os produtos com desconto
    Entao realizo o checkout dos produtos

    Exemplos: 
      | opcao      |
      | "desconto" |

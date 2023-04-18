package com.clark.stepDefinitions;

import com.clark.page.ecommerce.CarrinhoComprasPage;
import com.clark.page.ecommerce.TodosProdutosPage;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

public class TodosProdutos_Steps {
    TodosProdutosPage todosProdutos = new TodosProdutosPage();

    @Quando("^que retorno para pagina todos os produtos$")
    public void que_retorno_para_pagina_todos_os_produtos() throws Throwable {
        todosProdutos.deveAcessarPaginaTodosOsProdutos();
    }
    @Entao("^realizo o checkout de um dos produtos$")
    public void realizo_o_checkout_de_um_dos_produtos() throws Throwable {
        todosProdutos.deveFinalizarCompra();
    }
}

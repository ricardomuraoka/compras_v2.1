package carrinho;


import estoque.Estoque;
import estoque.FiltrarProdutos;
import estoque.Produto;
import helpers.MenuCompras;
import historico.Historico;
import usuarios.Cliente;
import usuarios.Usuario;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class CarrinhoDeCompras {
    private Map<Produto, Integer> produtosNoCarrinho = new LinkedHashMap<>();
    private double totalCompras;
    private final Usuario clienteCarrinho;


    public CarrinhoDeCompras(Usuario atual) {
        clienteCarrinho = atual;
    }

    public CarrinhoDeCompras(CarrinhoDeCompras another) {
        this.produtosNoCarrinho = another.produtosNoCarrinho;
        this.totalCompras = another.totalCompras;
        this.clienteCarrinho = another.clienteCarrinho;
    }



    public void adicionaProduto(CarrinhoDeCompras car, Estoque est) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        System.out.println("Qual produto gostaria de adicionar? \n");
        String itemProduto = in.nextLine();
        System.out.println("Quantos itens gostaria de adicionar? \n");
        int qtdeProdutos = Integer.parseInt(in.nextLine());
        new FiltrarProdutos().filtrarProdutos(est, itemProduto);
        for (Map.Entry<Produto, Integer> entry : est.getItensEstoque().entrySet()) {
            if (entry.getKey().getNome().equals(itemProduto)) {
                    if (entry.getValue() < qtdeProdutos) {
                        car.produtosNoCarrinho.put(entry.getKey(), entry.getValue());
                        System.out.printf("Não temos essa quantidade em compras_v_2_1.estoque, ajustamos automaticamente para %d," +
                                "quantidade que temos no momento %n",entry.getValue() );
                        Thread.sleep(3000);

                    } else {
                        car.produtosNoCarrinho.put(entry.getKey(), qtdeProdutos);
                        setTotalCompras(totalCompras(car));
                    }
                }
        }
    }

    public  Double totalCompras(CarrinhoDeCompras car) {
        double total = 0;
        for (Map.Entry<Produto, Integer> entry : car.getProdutosNoCarrinho().entrySet()) {
            total += entry.getKey().getPreco() * entry.getValue();
        }
        return total;
    }




    public void fecharCompra(CarrinhoDeCompras car, Estoque est, Historico hist, List<Cliente> clientes, Usuario logado) throws InterruptedException {
        if (car.getProdutosNoCarrinho().size() == 0) {
            System.out.println("Carrinho vazio, nada para mostrar");
            Thread.sleep(3000);
            new MenuCompras().menuCompras(car.getClienteCarrinho(), new CarrinhoDeCompras(logado), est, hist, clientes);
        } else {
            for (Map.Entry<Produto, Integer> produtosCarrinho : car.getProdutosNoCarrinho().entrySet()) {
                int qtdeProdutoCarrinho = produtosCarrinho.getValue();
                for (Map.Entry<Produto, Integer> produtoEstoque : est.getItensEstoque().entrySet()) {
                     if (produtosCarrinho.getKey().equals(produtoEstoque.getKey()) &&
                                produtoEstoque.getValue() >= produtosCarrinho.getValue()) {
                            est.getItensEstoque().put(produtoEstoque.getKey(), produtoEstoque.getValue() - qtdeProdutoCarrinho);
                        }
                    }
                }
            CarrinhoDeCompras another = new CarrinhoDeCompras(car);
            hist.adicionarCompra(another);
            logado.adicionarCarrinho(another);
        }
        this.produtosNoCarrinho = new LinkedHashMap<>();
        this.totalCompras = 0;
    }

    public Map<Produto, Integer> getProdutosNoCarrinho() {
        return produtosNoCarrinho;
    }

    public void setTotalCompras(double totalCompras) {
        this.totalCompras = totalCompras;
    }

    public StringBuilder produtosNoCarrinho() {
        var str = new StringBuilder();
        for (var i : produtosNoCarrinho.entrySet()) {
            str.append(String.format("%nProduto: %-15s Preco: %-6.2f R$ Qtde: %-3d", i.getKey().getNome(), i.getKey().getPreco(), i.getValue()));
        }
        return str;
    }

    public Usuario getClienteCarrinho() {
        return clienteCarrinho;
    }


    @Override
    public String toString() {
        String carrinho;
        if (produtosNoCarrinho.size() == 0) {
            carrinho = String.format("%nCliente: %s Valor Total: R$%.2f%nCarrinho Vazio", clienteCarrinho, totalCompras);
        } else {
            carrinho = String.format("%nCliente: %sValor Total: R$%.2f%nCarrinho: ", clienteCarrinho, totalCompras) +
                    produtosNoCarrinho();
        }
        return carrinho;
    }
}



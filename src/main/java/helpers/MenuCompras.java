package helpers;

import carrinho.CarrinhoDeCompras;
import estoque.Estoque;
import estoque.FiltrarProdutos;
import historico.Historico;
import usuarios.Cliente;
import usuarios.Usuario;

import java.util.List;
import java.util.Scanner;

import static estoque.Produto.getPageProduct;
import static estoque.Produto.getProdutos;
import static usuarios.Cliente.criaClienteList;


public class MenuCompras {

    public void menuCompras(Usuario usuario, CarrinhoDeCompras carrinho, Estoque est, Historico hist, List<Cliente> clientes) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        System.out.println("ESCOLHA UMA OPÇÃO");
        System.out.println("1 - DIGITE 1 - BUSCAR PRODUTOS. ");
        System.out.println("2 - DIGITE 2 - LISTA PRODUTOS. ");
        System.out.println("3 - DIGITE 3 - ADICIONAR PRODUTO AO CARRINHO. ");
        System.out.println("4 - DIGITE 4 - EXIBE CARRINHO.  ");
        System.out.println("5 - DIGITE 5 - VOLTAR AO MENU. ");

        int option = Integer.parseInt(in.nextLine());


        switch (option) {
            case 1 -> {
                System.out.println("Qual produto deseja buscar?");
                String buscaProduto = in.nextLine();
                System.out.println(new FiltrarProdutos().filtrarProdutos(est, buscaProduto));
                new MenuCompras().menuCompras(usuario, carrinho, est, hist, clientes);
            }
            case 2 -> {
                final int pageSize = 5;
                final int pages = (criaClienteList().size() + pageSize - 1) / pageSize;
                for (int i = 1; i <= pages; i++) {
                    System.out.println(getPageProduct(getProdutos(), i, pageSize));
                    Thread.sleep(3000);
                }
                new MenuCompras().menuCompras(usuario, carrinho, est, hist, clientes);
            }
            case 3 -> {
                carrinho.adicionaProduto(carrinho, est);
                new MenuCompras().menuCompras(usuario, carrinho, est, hist, clientes);
            }
            case 4 -> {
                System.out.println(carrinho);
                System.out.println("1 - DIGITE 1 - VOLTAR AS COMPRAS");
                System.out.println("2 - DIGITE 2 - FECHAR COMPRAS");
                int opt = Integer.parseInt(in.nextLine());
                if (opt == 1) {
                    new MenuCompras().menuCompras(usuario, carrinho, est, hist, clientes);
                } else if (opt == 2) {
                    carrinho.fecharCompra(carrinho, est, hist, clientes, usuario);
                } else {
                    System.out.println("Escolha umas das opções");
                }
                new MenuInicial().menu(usuario, carrinho, est, hist, clientes);
            }
            case 5 -> new MenuInicial().menu(usuario, carrinho, est, hist, clientes);
            default -> {
                System.out.println("Escolha uma das opções: ");
                new MenuCompras().menuCompras(usuario, carrinho, est, hist, clientes);
            }
        }
    }
}

package helpers;

import carrinho.CarrinhoDeCompras;
import estoque.Estoque;
import historico.Historico;
import login.Login;
import usuarios.Admin;
import usuarios.Cliente;
import usuarios.Usuario;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static login.Login.validaUsuario;

/**
 * Classe utilitária menu
 */
public class MenuInicial {

    public MenuInicial() {
    }

    public void iniciaMenu(List<Cliente> clientes, Estoque estoque, Historico historico) throws InterruptedException {
        Usuario logado = new Login().login(clientes);
        if (validaUsuario(logado, clientes)) {
            var carrinho = new CarrinhoDeCompras(logado);
            new MenuInicial().menu(logado, carrinho, estoque, historico, clientes);
        } else {
            System.out.println("Usuário não cadastrado");
            new Cadastro().desejaCadastrar(estoque, historico, clientes);
        }
    }


    public void menu(Usuario usuario, CarrinhoDeCompras carrinho, Estoque estoque, Historico hist, List<Cliente> clientes) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        System.out.println("ESCOLHA UMA OPÇÃO");
        if (!Objects.equals(usuario.getClass(), Admin.class)) {
            System.out.println("1 - DIGITE 1 - FAZER COMPRAS. ");
        }
        System.out.println("2 - DIGITE 2 - TROCAR USUÁRIO. ");
        System.out.println("3 - DIGITE 3 - SOBRE. ");
        System.out.println("4 - DIGITE 4 - SAIR. ");
        if (Objects.equals(usuario.getClass(), Admin.class)) {
            System.out.println("5 - DIGITE 5 - RELATÓRIOS SOBRE CLIENTES. ");
        }
        int option = Integer.parseInt(in.nextLine());
        try {
            switch (option) {
                case 1 -> new MenuCompras().menuCompras(usuario, carrinho, estoque, hist, clientes);
                case 2 -> Login.trocaUsuario(estoque, hist, clientes);
                case 3 -> {
                    System.out.println(new Sobre());
                    Thread.sleep(3000);
                    new MenuInicial().menu(usuario, carrinho, estoque, hist, clientes);
                }
                case 4 -> System.exit(0);
                case 5 -> new MenuRelatorios().escolhaRelatorio(usuario, carrinho, estoque, hist, clientes);
                default -> System.out.println("Escolha uma das opções: ");
            }
        } catch (NumberFormatException e) {
            new MenuInicial().menu(usuario, carrinho, estoque, hist, clientes);
        }
    }
}


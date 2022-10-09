package helpers;

import carrinho.CarrinhoDeCompras;
import estoque.Estoque;
import historico.Historico;
import usuarios.Cliente;
import usuarios.FiltrarCliente;
import usuarios.Usuario;

import java.util.List;
import java.util.Scanner;

public class MenuRelatorios {

    public void escolhaRelatorio(Usuario usuario, CarrinhoDeCompras carrinho, Estoque est, Historico hist, List<Cliente> clientes) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        System.out.println("1 - DIGITE 1 - RELATORIO DE CLIENTES - NUMERO DE COMPRAS");
        System.out.println("2 - DIGITE 2 - RELATORIO DE CLIENTES - TOTAL COMPRADO");
        System.out.println("3 - DIGITE 3 - RELATORIO DE CLIENTES - VALOR MÉDIO DE COMPRAS");
        System.out.println("4 - DIGITE 4 - NUMERO DE COMPRAS");
        System.out.println("5 - DIGITE 5 - TOTAL COMPRADO");
        System.out.println("6 - DIGITE 6 - VALOR MÉDIO DE COMPRAS");
        System.out.println("7 - DIGITE 7 - LISTA DE CLIENTES");
        System.out.println("8 - DIGITE 8 - VOLTAR");
        int tipoRelatorio = Integer.parseInt(in.nextLine());

        if (tipoRelatorio == 1) {
            System.out.println("Digite o CPF para relatórios de cliente desejado");
            String busca = in.nextLine();
            System.out.printf("Total de compras: %d%n", new Relatorios().relatorioNumeroComprasCliente(clientes, busca));
            escolhaRelatorio(usuario, carrinho, est, hist, clientes);
        } else if (tipoRelatorio == 2) {
            System.out.println("Digite o CPF para relatórios de cliente desejado");
            String busca = in.nextLine();
            System.out.printf("Valor total em compras: %.2f%n", new Relatorios().relatorioTotalComprasCliente(clientes, busca));
            escolhaRelatorio(usuario, carrinho, est, hist, clientes);
        } else if (tipoRelatorio == 3) {
            System.out.println("Digite o CPF para relatórios de cliente desejado");
            String busca = in.nextLine();
            System.out.printf("Valor médio em compras: %.2f%n", new Relatorios().relatorioMediaCompradoCliente(clientes, busca));
            escolhaRelatorio(usuario, carrinho, est, hist, clientes);
        } else if (tipoRelatorio == 4) {
            System.out.printf("Total de compras: %d%n", new Relatorios().relatorioNumeroCompras(hist));
            escolhaRelatorio(usuario, carrinho, est, hist, clientes);
        } else if (tipoRelatorio == 5) {
            System.out.printf("Valor total em compras: %.2f%n", new Relatorios().relatorioTotalCompras(hist));
            escolhaRelatorio(usuario, carrinho, est, hist, clientes);
        } else if (tipoRelatorio == 6) {
            System.out.printf("Valor médio em compras: %.2f%n", new Relatorios().relatorioMediaComprado(hist));
            escolhaRelatorio(usuario, carrinho, est, hist, clientes);
        } else if (tipoRelatorio == 7) {
            new FiltrarCliente().listaClientes(clientes, 1);
            escolhaRelatorio(usuario, carrinho, est, hist, clientes);
        } else if (tipoRelatorio ==8){
            new MenuInicial().menu(usuario, carrinho, est, hist, clientes);
        } else {
            System.out.println("Escolha uma das opções");
            escolhaRelatorio(usuario, carrinho, est, hist, clientes);
        }
    }
}

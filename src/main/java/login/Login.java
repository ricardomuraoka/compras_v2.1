package login;

import carrinho.CarrinhoDeCompras;
import estoque.Estoque;
import helpers.Cadastro;
import helpers.MenuInicial;
import historico.Historico;
import usuarios.Admin;
import usuarios.Cliente;
import usuarios.Usuario;

import java.util.List;
import java.util.Scanner;

public class Login {

    public Login() {

    }



    public Usuario login(List<Cliente> clientes) {
        Admin admin = new Admin();
        Scanner in = new Scanner(System.in);
        System.out.println("Digite o compras_v_2_1.login (cpf): ");
        String userLogin = in.nextLine();
        System.out.println("Digite a senha: ");
        String psd = in.nextLine();
        Usuario usuarioLogin = null;
        if ((userLogin.equals(admin.getCpf())) && (psd.equals(admin.getSenha()))) {
            usuarioLogin = admin;
        } else {
            for (Usuario usuario : clientes) {
                if (userLogin.equals(usuario.getCpf()) && psd.equals(usuario.getSenha())) {
                    usuarioLogin = usuario;
                }
            }
        }
        return usuarioLogin;
    }

    public static boolean validaUsuario(Usuario usuario, List<Cliente> clientes) {
        boolean valida = false;
        Admin admin = new Admin();
        if (usuario == null) {
            valida = false;
        } else if ((usuario.getCpf().equals(admin.getCpf())) && (usuario.getSenha().equals(admin.getSenha()))) {
            valida = true;
        } else {
            for (Cliente cli : clientes)
                if (usuario.getCpf().equals(cli.getCpf()) && usuario.getSenha().equals(cli.getSenha())) {
                    valida = true;
                    break;
                } else {
                    valida = false;
                }

        }
        return valida;
    }

    public static void trocaUsuario(Estoque est, Historico hist, List<Cliente> clientes) throws InterruptedException {
        Login log = new Login();
        Usuario novoUsuario = log.login(clientes);
        if (validaUsuario(novoUsuario, clientes)) {
            new MenuInicial().menu(novoUsuario, new CarrinhoDeCompras(novoUsuario),est, hist, clientes);
        } else {
            System.out.println("Usuário não cadastrado");
            new Cadastro().desejaCadastrar(est, hist, clientes);
        }
    }
}

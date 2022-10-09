import helpers.MenuInicial;
import historico.Historico;
import usuarios.Cliente;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import static estoque.Estoque.criaEstoque;

public class Main {
    public static void main(String[] args) throws InterruptedException, NoSuchAlgorithmException {
        var estoque = criaEstoque();
        var clientes = new ArrayList<>(Cliente.criaClienteList());
        var historico = new Historico();
        new MenuInicial().iniciaMenu(clientes, estoque, historico);
    }
}



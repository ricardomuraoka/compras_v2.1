package usuarios;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class FiltrarCliente {

    /**
     *
     * @param clientes the list of clients for this store
     * @param cpf      the string that will be matched to find a specific client
     * @return client matching given CPF
     */
    public Cliente filtrarCpf(List<Cliente> clientes, String cpf) {
        return clientes.stream()
                .filter(cli -> cli.getCpf().equals(cpf))
                .toList()
                .get(0);
    }


    /**
     *
     * @param clientes      the list of clients for this store
     * @param paginacao     default should be 1, the Page Number of the given client list
     */
    public void listaClientes(List<Cliente> clientes, int paginacao) {
        Scanner in = new Scanner(System.in);
        final String SAIR = "3 - DIGITE 3 - SAIR";
        final int pageSize = 5;
        final int pages = (clientes.size() + pageSize - 1) / pageSize;
        int i = paginacao;
        int pagina;
        do {
            new FiltrarCliente().novaLista(clientes, i);
            System.out.println("2 - DIGITE 2 - AVANCAR");
            System.out.println(SAIR);
            pagina = Integer.parseInt(in.nextLine());
            if (pagina == 2) {
                i++;
                new FiltrarCliente().novaLista(clientes, i);
            } else {
                return;
            }
            System.out.println("1 - DIGITE 1 - VOLTAR");
            if (i < pages) {
                System.out.println("2 - DIGITE 2 - AVANCAR");
                System.out.println(SAIR);
                pagina = Integer.parseInt(in.nextLine());
                switch (pagina) {
                    case 1:
                        i--;
                        new FiltrarCliente().novaLista(clientes, i);
                        break;
                    case 2:
                        i++;
                        new FiltrarCliente().novaLista(clientes, i);
                        break;
                    default:
                        return;
                }
            } else {
                System.out.println(SAIR);
                pagina = Integer.parseInt(in.nextLine());
                if (pagina == 1) {
                    i--;
                } else {
                    return;
                }
            }
        } while (true);
    }

    public void novaLista(List<Cliente> clientes, int pagina) {
        for (Cliente cli : new FiltrarCliente().getPageClient(clientes, pagina, 5))
            System.out.println(cli);
    }


    public List<Cliente> getPageClient(List<Cliente> clientes, int page, int pageSize) {
        if(pageSize <= 0 || page <= 0) {
            throw new IllegalArgumentException("invalid page size: " + pageSize);
        }

        int fromIndex = (page - 1) * pageSize;
        if(clientes == null || clientes.size() <= fromIndex){
            return Collections.emptyList();
        }

        return clientes.subList(fromIndex, Math.min(fromIndex + pageSize, clientes.size()));
    }
}

package helpers;

import carrinho.CarrinhoDeCompras;
import estoque.Produto;
import historico.Historico;
import usuarios.Cliente;
import usuarios.FiltrarCliente;

import java.util.List;
import java.util.Map;

public class Relatorios {

    /**
     *
     * @param his the Historico that will be used to generate a report
     * @return    a report with the total value of purchases made
     */
    public double relatorioTotalCompras(Historico his){
        var hist = his.getHistoricoCompras();
        double total = 0;
        for (CarrinhoDeCompras entry: hist) {
            for (Map.Entry<Produto, Integer> entry2 : entry.getProdutosNoCarrinho().entrySet()) {
                total += entry2.getKey().getPreco() * entry2.getValue();
            }
        }
        return total;
    }


    /**
     *
     * @param his the Historico that will be used to generate a report
     * @return    a report with the total number of purchases
     */
    public int relatorioNumeroCompras(Historico his) {
        var hist = his.getHistoricoCompras();
        int i = 0;
        for (CarrinhoDeCompras entry: hist) {
            i++;
        }
        return i;
    }

    /**
     *
     * @param his the Historico that will be used to generate a report
     * @return    a report with the average value of purchases
     * @throws ArithmeticException if it there is 0 values in the division
     */
    public double relatorioMediaComprado(Historico his) throws ArithmeticException {
        try {
            return relatorioTotalCompras(his) / relatorioNumeroCompras(his);
        }
        catch(Exception e) {
            throw new ArithmeticException("Não pode ser divido");
        }
    }

    /**
     *
     * @param clientes a client list which will be used to generate a report
     * @param cpf      the String that will be used to filter a client
     * @return         a report with the total value of purchases made by a client
     * @see            FiltrarCliente
     */
    public double relatorioTotalComprasCliente(List<Cliente> clientes, String cpf){
        var cliente = new FiltrarCliente().filtrarCpf(clientes, cpf);
        var carrinhos = cliente.getCarrinhoCliente();

        double total = 0;
        for (CarrinhoDeCompras entry: carrinhos) {
            for (Map.Entry<Produto, Integer> entry2 : entry.getProdutosNoCarrinho().entrySet()) {
                total += entry2.getKey().getPreco() * entry2.getValue();
            }
        }
        return total;
    }

    /**
     *
     * @param clientes a client list which will be used to generate a report
     * @param cpf      the String that will be used to filter a client
     * @return         a report with the total number of purchases by a client
     * @see            FiltrarCliente
     */
    public int relatorioNumeroComprasCliente(List<Cliente> clientes, String cpf) {
        var cliente = new FiltrarCliente().filtrarCpf(clientes, cpf);
        var carrinhos = cliente.getCarrinhoCliente();

        int i = 0;
        for (CarrinhoDeCompras entry: carrinhos) {
            i++;
        }
        return i;
    }

    /**
     *
     * @param clientes a client list which will be used to generate a report
     * @param cpf      the String that will be used to filter a client
     * @return         a report with the average value of purchases bye a specific client
     * @see            FiltrarCliente
     */
    public double relatorioMediaCompradoCliente(List<Cliente> clientes, String cpf) throws ArithmeticException {
        double media;
        try {
            var cliente = new FiltrarCliente().filtrarCpf(clientes, cpf);
            var carrinhos = cliente.getCarrinhoCliente();

            double total = 0;
            for (CarrinhoDeCompras entry : carrinhos) {
                for (Map.Entry<Produto, Integer> entry2 : entry.getProdutosNoCarrinho().entrySet()) {
                    total += entry2.getKey().getPreco() * entry2.getValue();
                }
            }

            int i = 0;
            for (CarrinhoDeCompras entry : carrinhos) {
                i++;
            }
            if (i != 0) {
                media = total / i;
            } else {
                media = 0;
            }
        } catch (Exception e) {
            throw new ArithmeticException("Não pode ser divido");
        }
        return media;
    }
}



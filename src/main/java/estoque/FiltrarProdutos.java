package estoque;


import java.util.List;
import java.util.Map;

public class FiltrarProdutos {
    /**
     * @param est     the Estoque that will be filtered
     * @param produto the String that will be searched
     * @return a filtered List<Map.Entry<Produto, Integer> for
     */
    public List<Map.Entry<Produto, Integer>> filtrarProdutos(Estoque est, String produto) {
        var estoqueFiltrado = est.getItensEstoque();
        return estoqueFiltrado.entrySet().stream()
                .filter(prod -> prod.getKey().getNome().contains(produto))
                .toList();
    }

    public void listaProdutosFiltrados(Estoque est, String produto) {
        var estoqueFiltrado = est.getItensEstoque()
                .entrySet()
                .stream()
                .filter(prod -> prod.getKey().getNome().contains(produto))
                .toList();

    }
}


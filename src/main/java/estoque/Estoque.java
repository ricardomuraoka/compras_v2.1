package estoque;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class Estoque {

    private Map<Produto, Integer> itensEstoque = new LinkedHashMap<>();


    public void setQtdeItensEstoque(Map<Produto, Integer> itensEstoque) {
        this.itensEstoque = itensEstoque;
    }

    public Integer getQtdeItensEstoque(String produto) {
        int qtde = 0;
        for (Map.Entry<Produto, Integer> entry : itensEstoque.entrySet()) {
            if (produto.equals(entry.getKey().getNome())) {
                qtde = entry.getValue();
            }
        }
        return qtde;
    }




    public static Estoque criaEstoque() throws NoSuchAlgorithmException {
        Random rand = SecureRandom.getInstanceStrong();
        var produtos = Produto.getProdutos();
        Estoque estoque = new Estoque();
        for (Produto prod : produtos) {
            estoque.itensEstoque.put(prod, rand.nextInt(5) + 2);
        }
        return estoque;
    }



    public Map<Produto, Integer> getItensEstoque() {
        return itensEstoque;
    }


    @Override
    public String toString() {
        return itensEstoque.keySet().stream()
                .map(key -> key + "=" + itensEstoque.get(key))
                .collect(Collectors.joining(", ", "{", "}"));
    }
}

package estoque;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Produto {
    private int id;
    private String nome;
    private Double preco;


    public Produto(int id, String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
    }


    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public int getId() {
        return id;
    }


    @Override
    public String toString() {
        return String.format("%nProduto: %s, Preço: R$%.2f ", nome, preco);
    }



    public static List<Produto> getPageProduct(List<Produto> produtos, int page, int pageSize) {
        if(pageSize <= 0 || page <= 0) {
            throw new IllegalArgumentException("invalid page size: " + pageSize);
        }

        int fromIndex = (page - 1) * pageSize;
        if(produtos == null || produtos.size() <= fromIndex){
            return Collections.emptyList();
        }

        return produtos.subList(fromIndex, Math.min(fromIndex + pageSize, produtos.size()));
    }

    public static List<Produto> getProdutos() {
        return Arrays.asList(
                new Produto(1, "CANECA", 19.00),
                new Produto(2, "COPO", 12.00),
                new Produto(3, "CHAVEIRO COLECIONAVEL", 2.00),
                new Produto(4, "GROWLEY STANLEY", 450.00),
                new Produto(5, "GROWLEY TABAJARA", 30.00),
                new Produto(6, "TACA", 25.00),
                new Produto(7, "KIT DE CERVEJAS", 250.00)
        );

    }

}

package helpers;

public class Sobre {
        String nomeLoja = "Utilidades Bebidas";
        String versao = "2.0.1";
        String autor = "Ricardo Muraoka";


    @Override
    public String toString() {
        return String.format("%nNome da loja: %s%nVersão: %s %nCriado por: %s %n", nomeLoja
                ,versao, autor);
    }


}

package usuarios;

/**
 * Classe administrador
 */
public class Admin extends Usuario {
    private static final String ADM = "admin";
    private String cpf;
    private String senha;

    public Admin() {
        super(ADM, ADM);
        this.cpf = ADM;
        this.senha = ADM;
    }


    public String getCpf() {
        return cpf;
    }


    public String getSenha() {
        return cpf;
    }
}



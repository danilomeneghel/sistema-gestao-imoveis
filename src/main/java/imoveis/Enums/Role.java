package imoveis.Enums;

public enum Role {

    ADMIN("ROLE_ADMIN", "Administrador"),
    USER("ROLE_USER","Usuário");

    private final String tipo;
    private final String nome;

    private Role(String tipo, String nome) {
        this.tipo = tipo;
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
    }

}

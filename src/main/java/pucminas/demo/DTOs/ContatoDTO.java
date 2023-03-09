package pucminas.demo.DTOs;

public class ContatoDTO {

    private String Nome;
    private String Endereco;
    private String Telefone;

    public ContatoDTO(String nome, String endereco, String telefone) {
        Nome = nome;
        Endereco = endereco;
        Telefone = telefone;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String endereco) {
        Endereco = endereco;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }
}

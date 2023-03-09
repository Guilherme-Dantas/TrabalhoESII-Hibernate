package pucminas.demo.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="CONTATOS_755952_734661")
public class Contato implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="codigo")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name="nome")
    private String Nome;

    @Column(name="endereco")
    private String Endereco;

    @Column(name="telefone")
    private String Telefone;

    public Contato(String nome, String endereco, String telefone) {
        Nome = nome;
        Endereco = endereco;
        Telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Nome;
    }

    public void setNome(String nome) {
        this.Nome = nome;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String endereco) {
        this.Endereco = endereco;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        this.Telefone = telefone;
    }

    @Override
    public String toString() {
        return "Contato Details?= Id: " + this.id + ", Nome: " + this.Nome + ", Endereco: " + this.Endereco + ", Telefone: " + this.Telefone;
    }
}
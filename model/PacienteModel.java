package model;

public class PacienteModel {

    private int codigoPaciente;

    private String nome;

    private int idade;

    // BD - CHAVE ESTRANGEIRA (int) <=> APLICAÇÃO - OBJETO

    private PlanoSaudeModel plano;

    public PacienteModel() { plano = new PlanoSaudeModel(); }

    public int getCodigoPaciente() { return codigoPaciente; }

    public void setCodigoPaciente (int codigoPaciente) {

        this.codigoPaciente = codigoPaciente;
    }

    public String getNome() { return nome; }

    public void setNome (String nome) {

        this.nome = nome;
    }

    public int getIdade() { return idade; }

    public void setIdade (int idade) {

        this.idade = idade;
    }

    public PlanoSaudeModel getPlano() { return plano; }

    public void setPlano (PlanoSaudeModel plano) {

        this.plano = plano;
    }
}
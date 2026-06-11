package model;

public class MedicoModel {

    private int codigoMedico;

    private String nome;

    // BD - CHAVE ESTRANGEIRA (int) <=> APLICAÇÃO - OBJETO

    private PlanoSaudeModel plano;

    public MedicoModel() { plano = new PlanoSaudeModel(); }

    public int getCodigoMedico() { return codigoMedico; }

    public void setCodigoMedico (int codigoMedico) {

        this.codigoMedico = codigoMedico;
    }

    public String getNome() { return nome; }

    public void setNome (String nome) {

        this.nome = nome;
    }

    public PlanoSaudeModel getPlano() { return plano; }

    public void setPlano(PlanoSaudeModel plano) {

        this.plano = plano;
    }
}
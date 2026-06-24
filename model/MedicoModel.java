package model;

public class MedicoModel {

    private int codigoMedico;

    private String nome;

    private PlanoSaudeModel plano; // BD - CHAVE ESTRANGEIRA (int) <=> APLICAÇÃO - OBJETO

    public MedicoModel() { plano = new PlanoSaudeModel(); }

    public MedicoModel (int codigo) {

        codigoMedico = codigo;

        this();
    }

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

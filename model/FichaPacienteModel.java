package model;

public class FichaPacienteModel {

    private int codigoFicha;

    private String descricao;

    // BD - CHAVE ESTRANGEIRA (int) <=> APLICAÇÃO - OBJETO

    private PacienteModel paciente;

    public FichaPacienteModel() { paciente = new PacienteModel(); }

    public int getCodigoFicha() { return codigoFicha; }

    public void setCodigoFicha (int codigoFicha) {

        this.codigoFicha = codigoFicha;
    }

    public String getDescricao() { return descricao; }

    public void setDescricao (String descricao) {

        this.descricao = descricao;
    }

    public PacienteModel getPaciente() { return paciente; }

    public void setPaciente (PacienteModel paciente) {

        this.paciente = paciente;
    }
}
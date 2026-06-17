package model;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConsultaModel {

    private final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final int CODIGO_CONSULTA;

    private final LocalDate DATA;

    private final MedicoModel MEDICO;

    private final PacienteModel PACIENTE;

    private LocalDate conferirData (String dataEnviada) {

        LocalDate dataConferida;

        try { dataConferida = LocalDate.parse(dataEnviada, FORMATO_DATA); }

        catch (Exception e) {

            String mensagemErro = "A data \"" + dataEnviada + "\" está no formato errado.\n\nO formato certo seria \"dd/MM/yyyy\".";

            System.out.println(mensagemErro);

            JOptionPane.showMessageDialog(new JOptionPane(), mensagemErro);

            dataConferida = LocalDate.now();
        }

        return dataConferida;
    }

    public ConsultaModel (int codigoConsulta, String data, MedicoModel medico, PacienteModel paciente) {

        DATA = conferirData(data);

        CODIGO_CONSULTA = codigoConsulta;

        MEDICO = medico;
        
        PACIENTE = paciente;
    }

    public ConsultaModel (int codigoConsulta, String data, int codigoMedico, int codigoPaciente) {

        MedicoModel medico = new MedicoModel(codigoMedico);

        PacienteModel paciente = new PacienteModel(codigoPaciente);

        this(codigoConsulta, data, medico, paciente);
    }

    public String data () { return DATA.format(FORMATO_DATA); }

    public int codigoConsulta () { return CODIGO_CONSULTA; }

    public MedicoModel medico () { return MEDICO; }

    public PacienteModel paciente () { return PACIENTE; }
}
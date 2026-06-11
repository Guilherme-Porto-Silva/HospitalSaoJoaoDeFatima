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

    public ConsultaModel(int codigoConsulta, String data, MedicoModel medico, PacienteModel paciente) {

        LocalDate data1;

        try { data1 = LocalDate.parse(data, FORMATO_DATA); }

        catch (Exception e) {

            String mensagemErro = "A data \"" + data + "\" está no formato errado.\n\nO formato certo seria \"dd/MM/yyyy\".";

            System.out.println(mensagemErro);

            JOptionPane.showMessageDialog(new JOptionPane(), mensagemErro);

            data1 = LocalDate.now();
        }

        DATA = data1;

        CODIGO_CONSULTA = codigoConsulta;

        MEDICO = medico;
        
        PACIENTE = paciente;
    }

    public int codigoConsulta () { return CODIGO_CONSULTA; }

    public LocalDate data () { return DATA; }

    public MedicoModel medico () { return MEDICO; }

    public PacienteModel paciente () { return PACIENTE; }
}
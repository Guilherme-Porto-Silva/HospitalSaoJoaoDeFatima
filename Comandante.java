import view.MedicoView;
import view.PacienteView;
import view.PlanoSaudeView;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class Comandante extends JFrame {

    private JDesktopPane areaTrabalho;

    private JMenuBar barraMenu;

    private JMenu menuDoCadastro;

    private JMenu menuDoRelatorio;

    private JMenuItem submenuDaConsulta;

    private JMenuItem submenuDaFichaPaciente;

    private JMenuItem submenuDoMedico;

    private JMenuItem submenuDoPaciente;

    private JMenuItem submenuDoPlanoSaude;

    public Comandante () {

        initComponents();
    }
    private void initComponents () {

        areaTrabalho = new JDesktopPane();

        barraMenu = new JMenuBar();

        menuDoCadastro = new JMenu();

        submenuDaConsulta = new JMenuItem();

        submenuDaFichaPaciente = new JMenuItem();

        submenuDoMedico = new JMenuItem();

        submenuDoPaciente = new JMenuItem();

        submenuDoPlanoSaude = new JMenuItem();

        menuDoRelatorio = new JMenu();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setTitle("Hospital São João de Fátima");

        GroupLayout areaTrabalhoLayout = new GroupLayout(areaTrabalho);

        areaTrabalho.setLayout(areaTrabalhoLayout);

        areaTrabalhoLayout.setHorizontalGroup(areaTrabalhoLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 772, Short.MAX_VALUE));

        areaTrabalhoLayout.setVerticalGroup(areaTrabalhoLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 580, Short.MAX_VALUE));

        menuDoCadastro.setText("Cadastro");

        submenuDaConsulta.setText("Consulta");

        submenuDaConsulta.addActionListener(this::submenuDoPlanoSaudeActionPerformed);

        menuDoCadastro.add(submenuDaConsulta);

        submenuDoPaciente.setText("Paciente");

        submenuDaFichaPaciente.addActionListener(this::submenuDoPacienteActionPerformed);

        submenuDoMedico.setText("Médico");

        submenuDaFichaPaciente.addActionListener(this::submenuDoMedicoActionPerformed);

        menuDoCadastro.add(submenuDaFichaPaciente);

        submenuDoPlanoSaude.setText("Plano de saúde");

        submenuDoPlanoSaude.addActionListener(this::submenuDoPlanoSaudeActionPerformed);

        menuDoCadastro.add(submenuDoPlanoSaude);

        barraMenu.add(menuDoCadastro);

        menuDoRelatorio.setText("Relatório");

        barraMenu.add(menuDoRelatorio);

        setJMenuBar(barraMenu);

        javax.swing.GroupLayout layout = new GroupLayout(getContentPane());

        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(areaTrabalho, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addGap(0, 0, Short.MAX_VALUE)));

        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(areaTrabalho, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addGap(0, 0, Short.MAX_VALUE)));

        pack();
    }

    private void submenuDoPacienteActionPerformed (ActionEvent evt) {

        PacienteView paciente = new PacienteView();

        areaTrabalho.add(paciente);

        paciente.setVisible(true);
    }

    private void submenuDoMedicoActionPerformed (ActionEvent evt) {

        MedicoView medico = new MedicoView();

        areaTrabalho.add(medico);

        medico.setVisible(true);
    }

    private void submenuDoPlanoSaudeActionPerformed (ActionEvent evt) {

        PlanoSaudeView planoSaude = new PlanoSaudeView();

        areaTrabalho.add(planoSaude);

        planoSaude.setVisible(true);
    }



    static void main () {

        EventQueue.invokeLater(() -> new Comandante().setVisible(true));
    }
}
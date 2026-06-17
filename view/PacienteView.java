package view;

import controller.PacienteController;
import controller.PlanoSaudeController;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import model.PacienteModel;
import model.PlanoSaudeModel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PacienteView extends JInternalFrame {

    private JButton jbEditar;
    
    private JButton jbExcluir;
    
    private JButton jbFechar;
    
    private JButton jbNovo;
    
    private JButton jbPesquisar;
    
    private JButton jbSalvar;
    
    private JComboBox<String> jcbPlano;
    
    private JScrollPane jScrollPane1;
    
    private JLabel jlId;
    
    private JLabel jlIdade;
    
    private JLabel jlNome;
    
    private JLabel jlPlano;
    
    private JTable jtPaciente;
    
    private JTextField jtxId;
    
    private JTextField jtxIdade;
    
    private JTextField jtxNome;

    private PacienteController controle = new PacienteController();
    
    private PlanoSaudeController planoControle = new PlanoSaudeController();
    
    private List<PlanoSaudeModel> listaPlanos;



    private void avisarErro (Exception braba, String mensagem) {

            braba.printStackTrace();

            JOptionPane.showMessageDialog(this, mensagem);
        }

    private void avisarErro (Exception braba) {

            braba.printStackTrace();

            JOptionPane.showMessageDialog(this, braba.getMessage());
        }
    
    

    public PacienteView() {
        
        initComponents();
        
        abrirJanela();

        try { carregarPlanoSaude(); }

        catch (SQLException e) { avisarErro(e, "Não foi possível carregar os planos de saúde."); }

        try { carregarTabela(); }

        catch (SQLException e) { avisarErro(e, "Não foi possível carregar a tabela."); }
    }
    
    

    private void abrirJanela() {
        
        jtxNome.setEditable(false);
        
        jtxIdade.setEditable(false);
        
        jcbPlano.setEnabled (false);
        
        jbSalvar.setEnabled (false);
        
        jbEditar.setEnabled (false);
        
        jbExcluir.setEnabled (false);
        
        jbNovo.setEnabled (true);
        
        jbPesquisar.setEnabled (true);
        
        jtxId.setEditable(true);
    }
    
    

    private void limparCampos() {
        
        jtxId.setText("");
        
        jtxNome.setText("");
        
        jtxIdade.setText("");
        
        if (jcbPlano.getItemCount() > 0) jcbPlano.setSelectedIndex(0);
    }
    
    

    private void carregarPlanoSaude() throws SQLException {
        
        listaPlanos = planoControle.selecionarTodos();
        
        jcbPlano.removeAllItems();
        
        for (PlanoSaudeModel plano: listaPlanos) jcbPlano.addItem(plano.getNome());
    }
    
    

    private void carregarTabela() throws SQLException {
        
        List<PacienteModel> lista = controle.selecionarTodos();
        
        DefaultTableModel model = (DefaultTableModel) jtPaciente.getModel();
        
        model.setRowCount(0);
        
        var tamanhoLista = lista.size();
        
for (int i = 0; i < tamanhoLista; i++) model.addRow(new String[] {String.valueOf(lista.get(i).getCodigoPaciente()), lista.get(i).getNome(), String.valueOf(lista.get(i).getIdade()), String.valueOf(lista.get(i).getPlano().getCodigoPlano())});
    }
    
    

    private void initComponents() {
        
        jlId = new JLabel("Código Paciente:");
        
        jlNome = new JLabel("Nome:");
        
        jlIdade = new JLabel("Idade:");
        
        jlPlano = new JLabel("Plano:");
        
        jtxId = new JTextField ();
        
        jtxNome = new JTextField ();
        
        jtxIdade = new JTextField ();
        
        jcbPlano = new JComboBox<>();
        
        jbPesquisar = new JButton("Pesquisar");
        
        jbNovo = new JButton("Novo");
        
        jbSalvar = new JButton("Salvar");
        
        jbEditar = new JButton("Editar");
        
        jbExcluir = new JButton("Excluir");
        
        jbFechar = new JButton("Fechar");
        
        jScrollPane1 = new JScrollPane();
        
        jtPaciente = new JTable();

        setTitle("Cadastro Paciente");

        jbPesquisar.addActionListener(evt -> {
            
            try { jbPesquisarActionPerformed (evt); }
               
            catch (SQLException e) { avisarErro(e, "Um problema relacionado ao banco de dados impediu a pesquisa:\n\n"); }
        });
        
        jbNovo.addActionListener(evt -> jbNovoActionPerformed (evt));
        
        jbSalvar.addActionListener(evt -> {
            
            try { jbSalvarActionPerformed (evt); }
               
            catch (SQLException e) { avisarErro(e, "Um problema relacionado ao banco de dados impediu o arquivamento:\n\n"); }
        });
        
        jbEditar.addActionListener(evt -> {
            
            try { jbEditarActionPerformed (evt); }
               
            catch (SQLException e) { avisarErro(e, "Um problema relacionado ao banco de dados impediu a edição."); }
        });
        
        jbExcluir.addActionListener(evt -> {
            
            try { jbExcluirActionPerformed (evt); }
            
            catch (SQLException e) { avisarErro(e, "Um problema relacionado ao banco de dados impediu a exclusão:\n\n"); }
        });
        
        jbFechar.addActionListener(evt -> jbFecharActionPerformed (evt));
        
        jtPaciente.addMouseListener(new MouseAdapter() {
            
            public void mouseClicked (MouseEvent evt) { jtPacienteMouseClicked (evt); }
        });

        jtPaciente.setModel(new DefaultTableModel(new Object[][]{}, new String[] { "Código Paciente", "Nome", "Idade", "Plano" }));
        
        jScrollPane1.setViewportView(jtPaciente);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jlId)
                                                        .addComponent(jlNome)
                                                        .addComponent(jlIdade)
                                                        .addComponent(jlPlano))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jtxId, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jbPesquisar))
                                                        .addComponent(jtxNome, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jtxIdade, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jcbPlano, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jbNovo)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jbSalvar)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jbEditar)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jbExcluir)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jbFechar)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jlId)
                                        .addComponent(jtxId, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jbPesquisar))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jlNome)
                                        .addComponent(jtxNome, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jlIdade)
                                        .addComponent(jtxIdade, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jlPlano)
                                        .addComponent(jcbPlano, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jbNovo)
                                        .addComponent(jbSalvar)
                                        .addComponent(jbEditar)
                                        .addComponent(jbExcluir)
                                        .addComponent(jbFechar))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }
    
    

    private void jbNovoActionPerformed (ActionEvent evt) {
        
        limparCampos();
        
        jtxNome.setEditable(true);
        
        jtxIdade.setEditable(true);
        
        jcbPlano.setEnabled (true);
        
        jbSalvar.setEnabled (true);
        
        jbNovo.setEnabled (false);
        
        jbPesquisar.setEnabled (false);
        
        jtxId.setEditable(false);
    }
    
    

    private void jbSalvarActionPerformed (ActionEvent evt) throws SQLException {
        
        PacienteModel paciente = new PacienteModel();
        
        paciente.setNome(jtxNome.getText());
        
        paciente.setIdade(Integer.parseInt(jtxIdade.getText()));
        
        int idx = jcbPlano.getSelectedIndex();
        
        paciente.getPlano().setCodigoPlano(listaPlanos.get(idx).getCodigoPlano());
        
        if (controle.inserir(paciente)) {
            
            JOptionPane.showMessageDialog(this, "Salvo com sucesso!");
            
            carregarTabela();
            
            limparCampos();
            
            abrirJanela();
        }
        
        else JOptionPane.showMessageDialog(this, "Falha ao salvar...");
    }
    
    

    private void jbEditarActionPerformed (ActionEvent evt) throws SQLException {
        
        PacienteModel paciente = new PacienteModel();
        
        paciente.setCodigoPaciente(Integer.parseInt(jtxId.getText()));
        
        paciente.setNome(jtxNome.getText());
        
        paciente.setIdade(Integer.parseInt(jtxIdade.getText()));
        
        int idx = jcbPlano.getSelectedIndex();
        
        paciente.getPlano().setCodigoPlano(listaPlanos.get(idx).getCodigoPlano());
        
        if (controle.editar(paciente)) {
            
            JOptionPane.showMessageDialog(this, "Editado com sucesso!");
            
            carregarTabela();
            
            limparCampos();
            
            abrirJanela();
        }

        else JOptionPane.showMessageDialog(this, "Falha ao editar...");
    }
    
    

    private void jbExcluirActionPerformed (ActionEvent evt) throws SQLException {
        
        PacienteModel paciente = new PacienteModel();
        
        paciente.setCodigoPaciente(Integer.parseInt(jtxId.getText()));
        
        if (controle.excluir(paciente)) {
            
            JOptionPane.showMessageDialog(this, "Excluído com sucesso!");
            
            carregarTabela();
            
            limparCampos();
            
            abrirJanela();
        }

        else JOptionPane.showMessageDialog(this, "Falha ao excluir...");
    }
    
    

    private void jbPesquisarActionPerformed (ActionEvent evt) throws SQLException {
        
        if (!jtxId.getText().isEmpty()) {
            
            PacienteModel paciente = new PacienteModel();
            
            paciente.setCodigoPaciente(Integer.parseInt(jtxId.getText()));
            
            PacienteModel resultado = controle.pesquisar(paciente);
            
            if (resultado != null) {
                
                jtxNome.setText(resultado.getNome());
                
                jtxIdade.setText(String.valueOf(resultado.getIdade()));
                
                byte quantosPlanos = (byte) listaPlanos.size();
                
                for (byte i = 0; i < quantosPlanos; i++) {
                    
                    if (listaPlanos.get(i).getCodigoPlano() == resultado.getPlano().getCodigoPlano()) {
                        
                        jcbPlano.setSelectedIndex(i);
                        
                        break;
                    }
                }
                
                jbEditar.setEnabled (true);
                
                jbExcluir.setEnabled (true);
                
                jtxNome.setEditable(true);
                
                jtxIdade.setEditable(true);
                
                jcbPlano.setEnabled (true);
                
                jbPesquisar.setEnabled (false);
                
                jbSalvar.setEnabled (false);
                
                jtxId.setEditable(false);
            }
            
            else JOptionPane.showMessageDialog(this, "Paciente não encontrado.");
        }
    }
    
    

    private void jbFecharActionPerformed (ActionEvent evt) { this.dispose(); }
    
    

    private void jtPacienteMouseClicked (MouseEvent evt) {
        
        var linha = jtPaciente.getSelectedRow();
        
        if (linha != -1) {
            
            jtxId.setText(String.valueOf(jtPaciente.getValueAt(linha, 0)));
            
            jtxNome.setText(String.valueOf(jtPaciente.getValueAt(linha, 1)));
            
            jtxIdade.setText(String.valueOf(jtPaciente.getValueAt(linha, 2)));
            
            var idPlano = Integer.parseInt(String.valueOf(jtPaciente.getValueAt(linha, 3)));

            byte quantosPlanos = (byte) listaPlanos.size();
            
            for (byte i = 0; i < quantosPlanos; i++) {
                
                if (listaPlanos.get(i).getCodigoPlano() == idPlano) {
                    
                    jcbPlano.setSelectedIndex(i);
                    
                    break;
                }
            }
            
            jbEditar.setEnabled (true);
            
            jbExcluir.setEnabled (true);
            
            jtxNome.setEditable(true);
            
            jtxIdade.setEditable(true);
            
            jcbPlano.setEnabled (true);
            
            jbPesquisar.setEnabled (false);
            
            jbSalvar.setEnabled (false);
            
            jtxId.setEditable(false);
        }
    }
}

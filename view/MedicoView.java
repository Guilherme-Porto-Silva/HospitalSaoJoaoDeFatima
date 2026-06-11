package view;

import controller.MedicoController;
import controller.PlanoSaudeController;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import model.MedicoModel;
import model.PlanoSaudeModel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MedicoView extends JInternalFrame {

    private JButton jbEditar;
    
    private JButton jbExcluir;
    
    private JButton jbFechar;
    
    private JButton jbNovo;
    
    private JButton jbPesquisar;
    
    private JButton jbSalvar;
    
    private JComboBox<String> jcbPlano;
    
    private JScrollPane jScrollPane1;
    
    private JLabel jlId;
    
    private JLabel jlNome;

    private JLabel jlPlano;

    private JTable jtMedico;
    
    private JTextField jtxId;
    
    private JTextField jtxNome;

    private final MedicoController controle = new MedicoController();
    
    private final PlanoSaudeController planoControle = new PlanoSaudeController();
    
    private List<PlanoSaudeModel> listaPlanos;
    
    

    public MedicoView () {
        
        initComponents();
        
        abrirJanela();

        try { carregarPlanoSaude(); }

        catch (SQLException e) {

        JOptionPane.showMessageDialog(this,"Não foi possível carregar os planos de saúde.");
        }

        try { carregarTabela(); }

        catch (SQLException e) {

        JOptionPane.showMessageDialog(this,"Não foi possível carregar a tabela.");
        }
    }
    
    

    private void abrirJanela() {
        
        jtxNome.setEditable(false);
        
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
        
        if (jcbPlano.getItemCount() > 0) jcbPlano.setSelectedIndex(0);
    }

    
    
    private void carregarPlanoSaude() throws SQLException {
        
        listaPlanos = planoControle.selecionarTodos();
        
        jcbPlano.removeAllItems();

        for (PlanoSaudeModel plano: listaPlanos) jcbPlano.addItem(plano.getNome());
    }
    
    

    private void carregarTabela() throws SQLException {
        
        var lista = controle.selecionarTodos();
        
        DefaultTableModel model = (DefaultTableModel) jtMedico.getModel();
        
        model.setRowCount(0);
        
        byte tamanhoLista = (byte) lista.size();
        
        for (byte i = 0; i < tamanhoLista; i++) model.addRow(new String[] {

                String.valueOf(lista.get(i).getCodigoMedico()),

                lista.get(i).getNome(),

                String.valueOf(lista.get(i).getPlano().getCodigoPlano())
        });
    }
    
    

    private void initComponents() {
        
        jlId = new JLabel("Código Médico:");
        
        jlNome = new JLabel("Nome:");

        jlPlano = new JLabel("Plano de Saúde:");
        
        jtxId = new JTextField ();
        
        jtxNome = new JTextField ();
        
        jcbPlano = new JComboBox<>();
        
        jbPesquisar = new JButton("Pesquisar");
        
        jbNovo = new JButton("Novo");
        
        jbSalvar = new JButton("Salvar");
        
        jbEditar = new JButton("Editar");
        
        jbExcluir = new JButton("Excluir");
        
        jbFechar = new JButton("Fechar");
        
        jScrollPane1 = new JScrollPane();
        
        jtMedico = new JTable();

        setTitle("Cadastro Médico");

        jbPesquisar.addActionListener(evt -> {

            try { jbPesquisarActionPerformed (evt); }

            catch (SQLException e) {

            JOptionPane.showMessageDialog(this,"Não foi possível pesquisar, devido a um problema técnico.");
            }
        });
        
        jbNovo.addActionListener(evt -> jbNovoActionPerformed (evt));
        
        jbSalvar.addActionListener(evt -> {

            try { jbSalvarActionPerformed (evt); }

            catch (SQLException e) {

            JOptionPane.showMessageDialog(this,"Um problema técnico impediu o arquivamento de dados.");
            }
        });
        
        jbEditar.addActionListener(evt -> {

            try { jbEditarActionPerformed (evt); }

            catch (SQLException e) {

            JOptionPane.showMessageDialog(this,"Um problema técnico impediu a edição dos dados.");
            }
        });
        
        jbExcluir.addActionListener(evt -> {

            try { jbExcluirActionPerformed (evt); }

            catch (SQLException e) {

            JOptionPane.showMessageDialog(this, "Um problema técnico impediu a exclusão dos dados.");
            }
        });
        
        jbFechar.addActionListener(evt -> jbFecharActionPerformed (evt));
        
        jtMedico.addMouseListener(new java.awt.event.MouseAdapter() {
            
            public void mouseClicked (MouseEvent evt) {

                jtMedicoMouseClicked (evt);
            }
        });

        jtMedico.setModel(new DefaultTableModel(new Object[][]{}, new String[]{ "Código Médico", "Nome", "Plano" }));
        
        jScrollPane1.setViewportView(jtMedico);

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
                                                        .addComponent(jlPlano))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jtxId, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jbPesquisar))
                                                        .addComponent(jtxNome, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
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
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }
    
    

    private void jbNovoActionPerformed (ActionEvent evt) {
        
        limparCampos();
        
        jtxNome.setEditable(true);
        
        jcbPlano.setEnabled (true);
        
        jbSalvar.setEnabled (true);
        
        jbNovo.setEnabled (false);
        
        jbPesquisar.setEnabled (false);
        
        jtxId.setEditable(false);
    }
    
    

    private void jbSalvarActionPerformed (ActionEvent evt) throws SQLException {
        
        MedicoModel medico = new MedicoModel();
        
        medico.setNome(jtxNome.getText());
        
        int idx = jcbPlano.getSelectedIndex();
        
        medico.getPlano().setCodigoPlano(listaPlanos.get(idx).getCodigoPlano());
        
        if (controle.inserir(medico)) {
            
            JOptionPane.showMessageDialog(this, "Salvo com sucesso!");
            
            carregarTabela();
            
            limparCampos();
            
            abrirJanela();
        }
    }
    
    

    private void jbEditarActionPerformed (ActionEvent evt) throws SQLException {
        
        MedicoModel medico = new MedicoModel();
        
        medico.setCodigoMedico(Integer.parseInt(jtxId.getText()));
        
        medico.setNome(jtxNome.getText());
        
        int idx = jcbPlano.getSelectedIndex();
        
        medico.getPlano().setCodigoPlano(listaPlanos.get(idx).getCodigoPlano());
        
        if (controle.editar(medico)) {
            
            JOptionPane.showMessageDialog(this, "Editado com sucesso!");
            
            carregarTabela();
            
            limparCampos();
            
            abrirJanela();
        }
    }
    
    

    private void jbExcluirActionPerformed (ActionEvent evt) throws SQLException {
        
        MedicoModel medico = new MedicoModel();
        
        medico.setCodigoMedico(Integer.parseInt(jtxId.getText()));
        
        if (controle.excluir(medico)) {
            
            JOptionPane.showMessageDialog(this, "Excluído com sucesso!");
            
            carregarTabela();
            
            limparCampos();
            
            abrirJanela();
        }
    }
    
    

    private void jbPesquisarActionPerformed (ActionEvent evt) throws SQLException {
        
        if (!jtxId.getText().isEmpty()) {
            
            MedicoModel medico = new MedicoModel();
            
            medico.setCodigoMedico(Integer.parseInt(jtxId.getText()));
            
            MedicoModel resultado = controle.pesquisar(medico);
            
            if (resultado != null) {
                
                jtxNome.setText(resultado.getNome());
                
                for (int i = 0; i < listaPlanos.size(); i++) {
                    
                    if (listaPlanos.get(i).getCodigoPlano() == resultado.getPlano().getCodigoPlano()) {
                        
                        jcbPlano.setSelectedIndex(i);
                        
                        break;
                    }
                }
                
                jbEditar.setEnabled (true);
                
                jbExcluir.setEnabled (true);
                
                jtxNome.setEditable(true);
                
                jcbPlano.setEnabled (true);
                
                jbPesquisar.setEnabled (false);
                
                jbSalvar.setEnabled (false);
                
                jtxId.setEditable(false);
                
            } else {
                JOptionPane.showMessageDialog(this, "Médico não encontrado!");
            }
        }
    }

    private void jbFecharActionPerformed (ActionEvent evt) { this.dispose(); }

    private void jtMedicoMouseClicked (MouseEvent evt) {
        
        int linha = jtMedico.getSelectedRow();
        
        if (linha != -1) {
            
            jtxId.setText(String.valueOf(jtMedico.getValueAt(linha, 0)));
            
            jtxNome.setText(String.valueOf(jtMedico.getValueAt(linha, 1)));
            
            int idPlano = Integer.parseInt(String.valueOf(jtMedico.getValueAt(linha, 2)));
            
            for (int i = 0; i < listaPlanos.size(); i++) {
                
                if (listaPlanos.get(i).getCodigoPlano() == idPlano) {
                    
                    jcbPlano.setSelectedIndex(i);
                    
                    break;
                }
            }
            
            jbEditar.setEnabled (true);
            
            jbExcluir.setEnabled (true);
            
            jtxNome.setEditable(true);
            
            jcbPlano.setEnabled (true);
            
            jbPesquisar.setEnabled (false);
            
            jbSalvar.setEnabled (false);
            
            jtxId.setEditable(false);
        }
    }
}
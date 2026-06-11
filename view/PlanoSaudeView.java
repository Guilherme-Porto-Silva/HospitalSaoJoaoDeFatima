package view;

import controller.PlanoSaudeController;
import java.lang.RuntimeException;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.PlanoSaudeModel;

import java.awt.event.ActionEvent;

public class PlanoSaudeView extends JInternalFrame {

    private JButton jbEditar;

    private JButton jbExcluir;

    private JButton jbFechar;

    private JButton jbNovo;

    private JButton jbPesquisar;

    private JButton jbSalvar;

    private JPanel jPanel1;

    private JScrollPane jScrollPane1;

    private JLabel jlId;

    private JLabel jlNome;

    private JTable jtPlanoSaude;

    private JTextField jtxId;

    private JTextField jtxNome;

    private final PlanoSaudeController CONTROLE = new PlanoSaudeController();

    
    
    public PlanoSaudeView() {
        
        initComponents();
        
        abrirJanela();

        try { carregarTabela(); }

        catch (SQLException e) {

        JOptionPane.showMessageDialog(this,"Não foi possível carregar a tabela.");
        }
    }
    
    

    private void abrirJanela() {
        
        jtxNome.setEditable(false);
        
        jbSalvar.setEnabled(false);
        
        jbEditar.setEnabled(false);
        
        jbExcluir.setEnabled(false);
        
        jbNovo.setEnabled(true);
        
        jbPesquisar.setEnabled(true);
        
        jtxId.setEditable(true);
    }
    
    

    private void limparCampos() {
        
        jtxId.setText("");
        
        jtxNome.setText("");
    }
    
    

    private void carregarTabela() throws SQLException {
        
        List<PlanoSaudeModel> lista = CONTROLE.selecionarTodos();
        
        DefaultTableModel model = (DefaultTableModel) jtPlanoSaude.getModel();
        
        model.setRowCount(0);
        
        byte tamanhoLista = (byte) lista.size();
        
        for (byte i = 0; i < tamanhoLista; i++)
            
            model.addRow(new String[] {
                    
                    String.valueOf(lista.get(i).getCodigoPlano()),
                    
                    lista.get(i).getNome()
            });
    }
    
    

    private void initComponents() {
        
        jPanel1 = new JPanel();
        
        jlId = new JLabel("Código Plano:");
        
        jlNome = new JLabel("Nome:");
        
        jtxId = new JTextField();
        
        jtxNome = new JTextField();
        
        jbPesquisar = new JButton("Pesquisar");
        
        jbNovo = new JButton("Novo");
        
        jbSalvar = new JButton("Salvar");
        
        jbEditar = new JButton("Editar");
        
        jbExcluir = new JButton("Excluir");
        
        jbFechar = new JButton("Fechar");
        
        jScrollPane1 = new JScrollPane();
        
        jtPlanoSaude = new JTable();

        setTitle("Cadastro Plano de Saúde");

        jbPesquisar.addActionListener(evt -> {
            try {
                jbPesquisarActionPerformed(evt);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Um problema relacionado ao banco de dados impediu a pesquisa.");
            }
        });
        
        jbNovo.addActionListener(evt -> jbNovoActionPerformed(evt));
        
        jbSalvar.addActionListener(evt -> {
            try {
                jbSalvarActionPerformed(evt);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Um problema relacionado ao banco de dados impediu o arquivamento.");
            }
        });
        
        jbEditar.addActionListener(evt -> {
            try {
                jbEditarActionPerformed(evt);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Um problema relacionado ao banco de dados impediu a edição.");
            }
        });
        
        jbExcluir.addActionListener(evt -> {
            try {
                jbExcluirActionPerformed(evt);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Um problema relacionado ao banco de dados impediu a exclusão.");
            }
        });
        
        jbFechar.addActionListener(evt -> jbFecharActionPerformed(evt));
        
        
        
        jtPlanoSaude.addMouseListener (new java.awt.event.MouseAdapter() {
            
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                
                jtPlanoSaudeMouseClicked(evt);
            }
        });
        
        

        jtPlanoSaude.setModel(new DefaultTableModel(
                
                new Object [][] {},
                
                new String [] { "Código Plano", "Nome" }
        ) {
            boolean[] canEdit = new boolean [] { false, false };
            
            public boolean isCellEditable(int rowIndex, int columnIndex) { return canEdit [columnIndex]; }
        });
        
        
        
        jScrollPane1.setViewportView(jtPlanoSaude);

        // Código de Layout simplificado para compilação autônoma Swing
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jlId)
                                                        .addComponent(jlNome))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jtxId, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jbPesquisar))
                                                        .addComponent(jtxNome, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)))
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
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jbNovo)
                                        .addComponent(jbSalvar)
                                        .addComponent(jbEditar)
                                        .addComponent(jbExcluir)
                                        .addComponent(jbFechar))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }
    
    

    private void jbNovoActionPerformed (ActionEvent evt) {
        
        limparCampos();
        
        jtxNome.setEditable(true);
        
        jbSalvar.setEnabled(true);
        
        jbNovo.setEnabled(false);
        
        jbPesquisar.setEnabled(false);
        
        jtxId.setEditable(false);
    }
    
    

    private void jbSalvarActionPerformed (ActionEvent evt) throws SQLException {
        
        PlanoSaudeModel plano = new PlanoSaudeModel();
        
        plano.setNome(jtxNome.getText());
        
        if (CONTROLE.inserir(plano)) {
            
            JOptionPane.showMessageDialog(this, "Salvo com sucesso!");
            
            carregarTabela();
            
            limparCampos();
            
            abrirJanela();
        }
    }
    
    

    private void jbEditarActionPerformed (ActionEvent evt) throws SQLException {
        
        PlanoSaudeModel plano = new PlanoSaudeModel();
        
        plano.setCodigoPlano(Integer.parseInt(jtxId.getText()));
        
        plano.setNome(jtxNome.getText());
        
        if (CONTROLE.editar(plano)) {
            
            JOptionPane.showMessageDialog(this, "Editado com sucesso!");
            
            carregarTabela();
            
            limparCampos();
            
            abrirJanela();
        }
    }
    
    

    private void jbExcluirActionPerformed (ActionEvent evt) throws SQLException {
        
        PlanoSaudeModel plano = new PlanoSaudeModel();
        
        plano.setCodigoPlano(Integer.parseInt(jtxId.getText()));
        
        if (CONTROLE.excluir(plano)) {
            
            JOptionPane.showMessageDialog(this, "Excluído com sucesso!");
            
            carregarTabela();
            
            limparCampos();
            abrirJanela();
        }
    }
    
    

    private void jbPesquisarActionPerformed (ActionEvent evt) throws SQLException {
        
        if (!jtxId.getText().isEmpty()) {
            
            PlanoSaudeModel plano = new PlanoSaudeModel();
            
            plano.setCodigoPlano(Integer.parseInt(jtxId.getText()));
            
            PlanoSaudeModel resultado = CONTROLE.pesquisar(plano);
            
            if (resultado != null) {
                
                jtxNome.setText(resultado.getNome());
                
                jbEditar.setEnabled(true);
                
                jbExcluir.setEnabled(true);
                
                jtxNome.setEditable(true);
                
                jbPesquisar.setEnabled(false);
                
                jbSalvar.setEnabled(false);
                
                jtxId.setEditable(false);
                
            } else {
                JOptionPane.showMessageDialog(this, "Plano não encontrado!");
            }
        }
    }
    
    

    private void jbFecharActionPerformed (ActionEvent evt) {
        
        this.dispose();
    }
    
    

    private void jtPlanoSaudeMouseClicked (java.awt.event.MouseEvent evt) {
        
        int linha = jtPlanoSaude.getSelectedRow();
        
        if (linha != -1) {
            
            jtxId.setText(String.valueOf(jtPlanoSaude.getValueAt(linha, 0)));
            
            jtxNome.setText(String.valueOf(jtPlanoSaude.getValueAt(linha, 1)));
            
            jbEditar.setEnabled(true);
            
            jbExcluir.setEnabled(true);
            
            jtxNome.setEditable(true);
            
            jbPesquisar.setEnabled(false);
            
            jbSalvar.setEnabled(false);
            
            jtxId.setEditable(false);
        }
    }
}
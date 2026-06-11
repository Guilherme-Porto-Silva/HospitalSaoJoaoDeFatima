package util;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
    private final String URL;

    private final String USUARIO;

    private final String SENHA;

    public Connection conector;

    public Conexao (String url, String usuario, String senha) {
        
        URL = url;
        
        USUARIO = usuario;
        
        SENHA = senha;
    }

    private void avisarErro (String mensagemErro) {

        System.out.println(mensagemErro);

        JOptionPane.showMessageDialog(new JOptionPane(), mensagemErro);
    }

    private void avisarErro (Exception braba) {

        String mensagemErro = braba.getMessage();

        System.out.println(mensagemErro);

        JOptionPane.showMessageDialog(new JOptionPane(), mensagemErro);
    }

    public void conectar() {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            conector = DriverManager.getConnection(URL, USUARIO, SENHA);
        }

        catch (ClassNotFoundException e) { avisarErro("Driver de conexão não encontrado."); }

        catch (SQLException e) { avisarErro("Erro ao conectar com banco: " + e.getMessage()); }
    }

    public void desconectar() {

        try { conector.close(); }

        catch (SQLException e) { avisarErro(e); }
    }
}

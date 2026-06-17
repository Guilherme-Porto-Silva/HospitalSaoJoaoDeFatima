package controller;

import model.PacienteModel;
import java.util.ArrayList;
import util.Conexao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;

public class PacienteController {

    private final Conexao DB = new Conexao("jdbc:mysql://localhost:/HospitalSaoJoaoDeFatima", System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
    
    

    public boolean inserir (PacienteModel paciente) throws SQLException {
        
        String sql = "insert into pacientes(nome, plano, idade) values (?,?,?)";
        
        DB.conectar();

        PreparedStatement sentenca = DB.conector.prepareStatement(sql);
        
        sentenca.setString(1, paciente.getNome());
        
        sentenca.setInt(2, paciente.getPlano().getCodigoPlano());
        
        sentenca.setInt(3, paciente.getIdade());

        var quantasLinhasAlteradas = sentenca.executeUpdate();
        
        boolean deuCerto = (quantasLinhasAlteradas > 0);
        
        DB.desconectar();
        
        return deuCerto;
    }
    
    

    public boolean editar (PacienteModel paciente) throws SQLException {
        
        String sql = "update pacientes set nome = ?, plano = ?, idade = ? where codigo_paciente = ?";
        
        DB.conectar();
        
        PreparedStatement sentenca = DB.conector.prepareStatement(sql);
        
        sentenca.setString(1, paciente.getNome());
        
        sentenca.setInt(2, paciente.getPlano().getCodigoPlano());
        
        sentenca.setInt(3, paciente.getIdade());
        
        sentenca.setInt(4, paciente.getCodigoPaciente());
        
        boolean retorno = !sentenca.execute();
        
        DB.desconectar();
    
        return retorno;
    }
    
    

    public boolean excluir (PacienteModel paciente) throws SQLException {
        
        String sql = "delete from pacientes where codigo_paciente = ?";
        
        DB.conectar();

        PreparedStatement sentenca = DB.conector.prepareStatement(sql);
        
        sentenca.setInt(1, paciente.getCodigoPaciente());

        var quantasLinhasAlteradas = sentenca.executeUpdate();
        
        boolean deuCerto = (quantasLinhasAlteradas > 0);
        
        DB.desconectar();
        
        return deuCerto;
    }
    
    

    public PacienteModel pesquisar (PacienteModel paciente) throws SQLException {
        
        PacienteModel retorno = null;
        
        String sql = "select * from pacientes where codigo_paciente = ?";
        
        DB.conectar();

        PreparedStatement sentenca = DB.conector.prepareStatement(sql);
        
        sentenca.setInt(1, paciente.getCodigoPaciente());
        
        ResultSet encontrado = sentenca.executeQuery();
        
        if (encontrado.next()) {
            
            retorno = new PacienteModel();
            
            retorno.setCodigoPaciente(encontrado.getInt(1));
            
            retorno.setNome(encontrado.getString(2));
            
            retorno.getPlano().setCodigoPlano(encontrado.getInt(3));
            
            retorno.setIdade(encontrado.getInt(4));
        }
        
        DB.desconectar();
        
        return retorno;
    }
    
    

    public List<PacienteModel> selecionarTodos() throws SQLException {
        
        List<PacienteModel> retorno = new ArrayList<>();
        
        String sql = "select * from pacientes";
        
        DB.conectar();
        
        PreparedStatement sentenca = DB.conector.prepareStatement(sql);
        
        ResultSet encontrado = sentenca.executeQuery();
        
        while (encontrado.next()) {
            
            PacienteModel paciente = new PacienteModel();
            
            paciente.setCodigoPaciente(encontrado.getInt(1));
            
            paciente.setNome(encontrado.getString(2));
            
            paciente.getPlano().setCodigoPlano(encontrado.getInt(3));
            
            paciente.setIdade(encontrado.getInt(4));
            
            retorno.add(paciente);
        }
        
        DB.desconectar();
        
        return retorno;
    }
}
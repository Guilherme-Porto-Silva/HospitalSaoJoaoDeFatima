package controller;

import model.ConsultaModel;
import java.util.ArrayList;
import util.Conexao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;

public class ConsultaController {

    private final Conexao DB = new Conexao("jdbc:mysql://localhost:/HospitalSaoJoaoDeFatima", System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
    
    

    public boolean inserir (ConsultaModel consulta) throws SQLException {
        
        String sql = "insert into consulta(codigo_medico, codigo_paciente, data) values (?,?,?)";
        
        DB.conectar();

        PreparedStatement sentenca = DB.conector.prepareStatement(sql);
        
        sentenca.setInt(1, consulta.medico().getCodigoMedico());
        
        sentenca.setInt(2, consulta.paciente().getCodigoPaciente());
        
        sentenca.setString(3, consulta.data());

        var quantasLinhasAlteradas = sentenca.executeUpdate();
        
        boolean deuCerto = (quantasLinhasAlteradas > 0);
        
        DB.desconectar();
        
        return deuCerto;
    }
    
    

    public boolean editar (ConsultaModel consulta) throws SQLException {
        
        String sql = "update consulta set codigo_medico = ?, codigo_paciente = ?, data = ? where codigo_consulta = ?";
        
        DB.conectar();

        PreparedStatement sentenca = DB.conector.prepareStatement(sql);
        
        sentenca.setInt(1, consulta.medico().getCodigoMedico());
        
        sentenca.setInt(2, consulta.paciente().getCodigoPaciente());
        
        sentenca.setString(3, consulta.data());
        
        sentenca.setInt(4, consulta.codigoConsulta());
        
        var quantasLinhasAlteradas = sentenca.executeUpdate();
        
        boolean deuCerto = (quantasLinhasAlteradas > 0);
        
        DB.desconectar();
        
        return deuCerto;
    }
    
    

    public boolean excluir (ConsultaModel consulta) throws SQLException {
        
        String sql = "delete from consulta where codigo_consulta = ?";
        
        DB.conectar();

        PreparedStatement sentenca = DB.conector.prepareStatement(sql);
        
        sentenca.setInt(1, consulta.codigoConsulta());
        
        var quantasLinhasAlteradas = sentenca.executeUpdate();
        
        boolean deuCerto = (quantasLinhasAlteradas > 0);
        
        DB.desconectar();
        
        return deuCerto;
    }
    
    

    public ConsultaModel pesquisar (ConsultaModel consulta) throws SQLException {
        
        ConsultaModel retorno = null;
        
        String sql = "select * from consulta where codigo_consulta = ?";
        
        DB.conectar();

        PreparedStatement sentenca = DB.conector.prepareStatement(sql);
        
        sentenca.setInt(1, consulta.codigoConsulta());
        
        ResultSet encontrado = sentenca.executeQuery();
        
        if (encontrado.next()) retorno = new ConsultaModel(encontrado.getInt(1), encontrado.getString(4), encontrado.getInt(2), encontrado.getInt(3));
        
        DB.desconectar();
        
        return retorno;
    }



    public List<ConsultaModel> selecionarTodos() throws SQLException {

        List<ConsultaModel> retorno = new ArrayList<>();

        String sql = "select * from consulta";
        
        DB.conectar();

        PreparedStatement sentenca = DB.conector.prepareStatement(sql);

        ResultSet encontrado = sentenca.executeQuery();

        while (encontrado.next()) {

            ConsultaModel consulta = new ConsultaModel(encontrado.getInt(1), encontrado.getString(4), encontrado.getInt(2), encontrado.getInt(3));

            retorno.add(consulta);
        }

        DB.desconectar();

        return retorno;
    }
}
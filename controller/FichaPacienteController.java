package controller;

import model.FichaPacienteModel;
import java.util.ArrayList;
import util.Conexao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;

public class FichaPacienteController {

    private final Conexao DB = new Conexao("jdbc:mysql://localhost:/HospitalSaoJoaoDeFatima", System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));



    public boolean inserir (FichaPacienteModel ficha) throws SQLException {
        
        String sql = "insert into ficha_paciente(codigo_paciente, descricao) values (?,?)";
        
        DB.conectar();

        PreparedStatement sentenca = DB.conector.prepareStatement(sql);

        sentenca.setInt(1, ficha.getPaciente().getCodigoPaciente());

        sentenca.setString(2, ficha.getDescricao());

        var quantasLinhasAlteradas = sentenca.executeUpdate();
        
        boolean deuCerto = (quantasLinhasAlteradas > 0);
        
        DB.desconectar();
        
        return deuCerto;
    }



    public boolean editar (FichaPacienteModel ficha) throws SQLException {
        
        String sql = "update ficha_paciente set codigo_paciente = ?, descricao = ? where codigo_ficha = ?";
        
        DB.conectar();

        PreparedStatement sentenca = DB.conector.prepareStatement(sql);

        sentenca.setInt(1, ficha.getPaciente().getCodigoPaciente());

        sentenca.setString(2, ficha.getDescricao());

        sentenca.setInt(3, ficha.getCodigoFicha());

        var quantasLinhasAlteradas = sentenca.executeUpdate();
        
        boolean deuCerto = (quantasLinhasAlteradas > 0);
        
        DB.desconectar();
        
        return deuCerto;
    }



    public boolean excluir (FichaPacienteModel ficha) throws SQLException {
        
        String sql = "delete from ficha_paciente where codigo_ficha = ?";
        
        DB.conectar();

        PreparedStatement sentenca = DB.conector.prepareStatement(sql);

        sentenca.setInt(1, ficha.getCodigoFicha());

        var quantasLinhasAlteradas = sentenca.executeUpdate();
        
        boolean deuCerto = (quantasLinhasAlteradas > 0);
        
        DB.desconectar();
        
        return deuCerto;
    }



    public FichaPacienteModel pesquisar (FichaPacienteModel ficha) throws SQLException {

        FichaPacienteModel retorno = null;

        String sql = "select * from ficha_paciente where codigo_ficha = ?";
        
        DB.conectar();

        PreparedStatement sentenca = DB.conector.prepareStatement(sql);

        sentenca.setInt(1, ficha.getCodigoFicha());

        ResultSet encontrado = sentenca.executeQuery();

        if (encontrado.next()) {

            retorno = new FichaPacienteModel();

            retorno.setCodigoFicha(encontrado.getInt(1));

            retorno.getPaciente().setCodigoPaciente(encontrado.getInt(2));

            retorno.setDescricao(encontrado.getString(3));
        }

        DB.desconectar();

        return retorno;
    }



    public List<FichaPacienteModel> selecionarTodos() throws SQLException {

        List<FichaPacienteModel> retorno = new ArrayList<>();

        String sql = "select * from ficha_paciente";
        
        DB.conectar();

        PreparedStatement sentenca = DB.conector.prepareStatement(sql);

        ResultSet encontrado = sentenca.executeQuery();

        while(encontrado.next()) {

            FichaPacienteModel ficha = new FichaPacienteModel();

            ficha.setCodigoFicha(encontrado.getInt(1));

            ficha.getPaciente().setCodigoPaciente(encontrado.getInt(2));

            ficha.setDescricao(encontrado.getString(3));

            retorno.add(ficha);
        }

        DB.desconectar();

        return retorno;
    }
}
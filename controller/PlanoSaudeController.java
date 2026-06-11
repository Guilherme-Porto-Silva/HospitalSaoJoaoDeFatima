package controller;

import model.PlanoSaudeModel;
import java.util.ArrayList;
import util.Conexao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;

public class PlanoSaudeController {

    private final Conexao DB = new Conexao("jdbc:mysql://localhost:/HospitalSaoJoaoDeFatima", System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));



    public boolean inserir (PlanoSaudeModel plano) throws SQLException {

        String sql = "insert into planos_saude(nome) values (?)";

        DB.conectar();

        PreparedStatement sentenca = DB.conector.prepareStatement(sql);

        sentenca.setString(1, plano.getNome());

        boolean retorno = !sentenca.execute();

        DB.desconectar();

        return retorno;
    }



    public boolean editar (PlanoSaudeModel plano) throws SQLException {

        String sql = "update planos_saude set nome = ? where codigo_plano = ?";
        
        DB.conectar();

        PreparedStatement sentenca = DB.conector.prepareStatement(sql);

        sentenca.setString(1, plano.getNome());

        sentenca.setInt(2, plano.getCodigoPlano());

        boolean retorno = !sentenca.execute();
        
        DB.desconectar();
        
        return retorno;
    }



    public boolean excluir (PlanoSaudeModel plano) throws SQLException {
        
        String sql = "delete from planos_saude where codigo_plano = ?";
        
        DB.conectar();

        PreparedStatement sentenca = DB.conector.prepareStatement(sql);

        sentenca.setInt(1, plano.getCodigoPlano());

        boolean retorno = !sentenca.execute();
        
        DB.desconectar();
        
        return retorno;
    }



    public PlanoSaudeModel pesquisar (PlanoSaudeModel plano) throws SQLException {
        
        PlanoSaudeModel retorno = null;

        String sql = "select * from planos_saude where codigo_plano = ?";
        
        DB.conectar();

        PreparedStatement sentenca = DB.conector.prepareStatement(sql);

        sentenca.setInt(1, plano.getCodigoPlano());

        ResultSet encontrado = sentenca.executeQuery();

        if (encontrado.next()) {

            retorno = new PlanoSaudeModel();

            retorno.setCodigoPlano(encontrado.getInt(1));

            retorno.setNome(encontrado.getString(2));
        }
        
        DB.desconectar();
        
        return retorno;
    }
    
    

    public List<PlanoSaudeModel> selecionarTodos() throws SQLException {

        List<PlanoSaudeModel> retorno = new ArrayList<>();

        String sql = "select * from planos_saude";

        DB.conectar();

        PreparedStatement sentenca = DB.conector.prepareStatement(sql);

        ResultSet encontrado = sentenca.executeQuery();

        while (encontrado.next()) {

            PlanoSaudeModel plano = new PlanoSaudeModel();

            plano.setCodigoPlano(encontrado.getInt(1));

            plano.setNome(encontrado.getString(2));

            retorno.add(plano);
        }

        DB.desconectar();

        return retorno;
    }
}
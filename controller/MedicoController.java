package controller;

import model.MedicoModel;
import java.util.ArrayList;
import util.Conexao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;

public class MedicoController {

    private final Conexao DB = new Conexao("jdbc:mysql://localhost:/HospitalSaoJoaoDeFatima", System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
    
    

    public boolean inserir (MedicoModel medico) throws SQLException {
        
        String sql = "insert into medico(nome, plano) values (?,?)";
        
        DB.conectar();

        PreparedStatement sentenca = DB.conector.prepareStatement(sql);

        sentenca.setString(1, medico.getNome());

        sentenca.setInt(2, medico.getPlano().getCodigoPlano());

        boolean retorno = !sentenca.execute();

        DB.desconectar();

        return retorno;
    }



    public boolean editar (MedicoModel medico) throws SQLException {

        String sql = "update medico set nome = ?, plano = ? where codigo_medico = ?";

        DB.conectar();

        PreparedStatement sentenca = DB.conector.prepareStatement(sql);

        sentenca.setString(1, medico.getNome());

        sentenca.setInt(2, medico.getPlano().getCodigoPlano());

        sentenca.setInt(3, medico.getCodigoMedico());

        boolean retorno = !sentenca.execute();

        DB.desconectar();

        return retorno;
    }



    public boolean excluir (MedicoModel medico) throws SQLException {

        String sql = "delete from medico where codigo_medico = ?";

        DB.conectar();

        PreparedStatement sentenca = DB.conector.prepareStatement(sql);

        sentenca.setInt(1, medico.getCodigoMedico());

        boolean retorno = !sentenca.execute();

        DB.desconectar();

        return retorno;
    }



    public MedicoModel pesquisar (MedicoModel medico) throws SQLException {

        MedicoModel retorno = null;

        String sql = "select * from medico where codigo_medico = ?";

        var codigoMedico = medico.getCodigoMedico();

        DB.conectar();

        PreparedStatement sentenca = DB.conector.prepareStatement(sql);

        sentenca.setInt(1, codigoMedico);

        ResultSet encontrado = sentenca.executeQuery();

        if (encontrado.next()) {

            retorno = new MedicoModel();

            retorno.setCodigoMedico(encontrado.getInt(1));

            retorno.setNome(encontrado.getString(2));

            retorno.getPlano().setCodigoPlano(encontrado.getInt(3));
        }

        else System.out.println("\nNão tinha um médico com o código " + codigoMedico);

        DB.desconectar();

        return retorno;
    }



    public List<MedicoModel> selecionarTodos() throws SQLException {

        List<MedicoModel> retorno = new ArrayList<>();

        String sql = "select * from medico";

        DB.conectar();

        PreparedStatement sentenca = DB.conector.prepareStatement(sql);

        ResultSet encontrado = sentenca.executeQuery();

        while (encontrado.next()) {

            MedicoModel medico = new MedicoModel();

            medico.setCodigoMedico(encontrado.getInt(1));

            medico.setNome(encontrado.getString(2));

            medico.getPlano().setCodigoPlano(encontrado.getInt(3));

            retorno.add(medico);
        }

        DB.desconectar();

        return retorno;
    }
}
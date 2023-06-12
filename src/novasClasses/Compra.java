package novasClasses;

import conexao.MySQL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Compra {
    
    MySQL conn = new MySQL();

    
    public Compra() {
        
    }

    public void create(String idFuncionario, String idInstrumento) throws SQLException {
        
        conn.conectaBanco(); 

        String sql = "INSERT INTO compra (fk_funcionario, fk_instrumento) VALUES(?, ?)";
        PreparedStatement ps = conn.getConn().prepareStatement(sql); 
        ps.setString(1, idFuncionario);
        ps.setString(2, idInstrumento);

        ps.execute();

    }
    
    public ArrayList<String> funcionarioList() throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        conn.conectaBanco(); 
        conn.executarSQL("SELECT id, nome, sobrenome FROM professor;");
        while (conn.getResultSet().next()) {
            list.add(conn.getResultSet().getString(1) + " - " + conn.getResultSet().getString(2) + " " + conn.getResultSet().getString(3));
        }
        return list;  
    }
    
    public ArrayList<String> produtoList() throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        conn.conectaBanco(); 
        conn.executarSQL("SELECT codigo_instrumento, instrumento FROM instrumento;");
        while (conn.getResultSet().next()) {
            list.add(conn.getResultSet().getString(1) + " - " + conn.getResultSet().getString(2));
        }
        return list;
    }  
}
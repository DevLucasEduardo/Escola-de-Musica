package novasClasses;

import conexao.MySQL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompraDAO {
    
    MySQL conn = new MySQL();

    
    public CompraDAO() {
        
    }

    public void create(CompraDTO c) throws SQLException {
        
        conn.conectaBanco(); 

        String sql = "INSERT INTO compra (quantidade, id_compra, fk_funcionario, fk_instrumento) VALUES(?, ?, ?, ?)";
        PreparedStatement ps = conn.getConn().prepareStatement(sql); 
        ps.setString(1, c.getQuantidade());
        ps.setString(2, c.getIdCompra());
        ps.setString(3, c.getFkFuncionario());
        ps.setString(4, c.getFkInstrumento());

        ps.execute();

    }
    
    public void read(CadastroDTO cadastro, String id) throws SQLException{
        
        
        conn.conectaBanco();
        conn.executarSQL("SELECT * FROM fornecedor WHERE cnpj = '" + id + "'");
        
        while (conn.getResultSet().next()) {
            cadastro.setCnpj(cnpjReplace(conn.getResultSet().getString(1)));
            cadastro.setFornecedor(conn.getResultSet().getString(2));
            cadastro.setRazaoSocial(conn.getResultSet().getString(3));
            cadastro.setPais(conn.getResultSet().getString(4));
            cadastro.setEstado(conn.getResultSet().getString(5));
            cadastro.setCidade(conn.getResultSet().getString(6));
            cadastro.setCodigoPostal(cnpjReplace(conn.getResultSet().getString(7)));
            
        }
    }
    
    public void update(CompraDTO c) throws SQLException {
        conn.conectaBanco();
        String sql = "UPDATE compra "
                + "SET quantidade=?, "
                + "fk_funcionario=?, "
                + "fk_instrumento=? "
                + "WHERE id_compra = " + c.getIdCompra();

        PreparedStatement ps = conn.getConn().prepareStatement(sql); 
        ps.setString(1, c.getQuantidade());
        ps.setString(2, c.getFkFuncionario());
        ps.setString(3, c.getFkInstrumento());
        ps.executeUpdate();
    }

    
    public void delete(CompraDTO c) throws SQLException{
        
        conn.conectaBanco(); 
        conn.updateSQL("DELETE FROM compra WHERE id_compra = " + c.getIdCompra() + ";");
        
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
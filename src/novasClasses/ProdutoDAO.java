package novasClasses;

import conexao.MySQL;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.HashMap;

public class ProdutoDAO implements DAOInterface {
    
    MySQL conn = new MySQL();
    
    @Override
    public void create(CadastroDTO cadastro) throws SQLException {
        
        conn.conectaBanco(); 

        cadastro.setFkFornecedor(readHelper(cadastro.getFornecedor()));

        String sql = "INSERT INTO instrumento (instrumento, categoria, marca, fk_fornecedor) VALUES(?, ?, ?, ?)";
        PreparedStatement ps = conn.getConn().prepareStatement(sql); 
        ps.setString(1, cadastro.getInstrumento());
        ps.setString(2, cadastro.getCategoria());
        ps.setString(3, cadastro.getMarca());
        ps.setInt(4, cadastro.getFkFornecedor());
        ps.execute();
/*
        ResultSet generatedKeys = ps.getGeneratedKeys();
        if (generatedKeys.next()) {
            int generatedId = generatedKeys.getInt(1);  
            System.out.println(generatedId);
            return generatedId;
        }
*/
       

    }
    
    @Override
    public void read(CadastroDTO cadastro, int id) throws SQLException{
        conn.conectaBanco();
        conn.executarSQL("SELECT I.instrumento, I.categoria, I.marca, I.codigo_instrumento, F.fornecedor, F.cnpj, I.fk_fornecedor " +
                        "FROM fornecedor F JOIN instrumento I " +
                        "ON F.codigo_fornecedor = I.fk_fornecedor " +
                        "WHERE I.codigo_instrumento = " + id + ";");
        
        while (conn.getResultSet().next()) {
            cadastro.setInstrumento(conn.getResultSet().getString(1));
            cadastro.setCategoria(conn.getResultSet().getString(2));
            cadastro.setMarca(conn.getResultSet().getString(3));
            cadastro.setCodigoInstrumento(conn.getResultSet().getInt(4));
            cadastro.setFornecedor(conn.getResultSet().getString(5));
            cadastro.setCnpj(conn.getResultSet().getString(6));
            cadastro.setFkFornecedor(conn.getResultSet().getInt(7));
        }
    }
    
    @Override
    public void update(CadastroDTO cadastro) throws SQLException{
        String sql = "UPDATE cadastro WHERE";
    }
    
    @Override
    public void delete(CadastroDTO cadastro) throws SQLException{
        String sql = "DELETE cadastro WHERE";
    }
    
    public HashMap<String, String> fornecedorMap() throws SQLException {
        HashMap<String, String> dict = new HashMap<>();
        conn.conectaBanco(); 
        conn.executarSQL("SELECT cnpj, fornecedor FROM fornecedor;");
        while (conn.getResultSet().next()) {
            dict.put(cnpjReplace(conn.getResultSet().getString(1)), conn.getResultSet().getString(2));
        }
        return dict;
    }
    
    public int readHelper(String fornecedor) throws SQLException{
        conn.executarSQL("SELECT * FROM fornecedor WHERE fornecedor = '" + fornecedor + "' limit 1;");
        int a = 0;
        while (conn.getResultSet().next()) {
            a = conn.getResultSet().getInt(4);
        }
        return a;
    }
    
    public String cnpjReplace(String cnpj) {
        String newCnpj = cnpj.replaceAll("[./-]", "");
        return newCnpj;
    }
    
}
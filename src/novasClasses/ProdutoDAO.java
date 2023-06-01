package novasClasses;

import conexao.MySQL;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.HashMap;

public class ProdutoDAO implements DAOInterface {
    
    MySQL conn = new MySQL();
    
    @Override
    public String create(CadastroDTO cadastro) throws SQLException {
        
        conn.conectaBanco(); 
        //cadastro.setCnpj(readFornecedor(cadastro.getFornecedor()));

        String sql = "INSERT INTO instrumento (instrumento, categoria, marca, fk_fornecedor) VALUES(?, ?, ?, ?)";
        PreparedStatement ps = conn.getConn().prepareStatement(sql); 
        ps.setString(1, cadastro.getInstrumento());
        ps.setString(2, cadastro.getCategoria());
        ps.setString(3, cadastro.getMarca());
        ps.setString(4, cadastro.getCnpj());
        ps.execute();

        conn.executarSQL("SELECT * FROM instrumento WHERE instrumento = '"+ cadastro.getInstrumento() + "'" +
                                                   "AND categoria = '"+ cadastro.getCategoria() + "'" +
                                                   "AND marca = '" + cadastro.getMarca() + "'" +
                                                   "AND fk_fornecedor = '" + cadastro.getCnpj() + "'" + 
                                                   "ORDER BY codigo_instrumento DESC LIMIT 1;");

        while (conn.getResultSet().next()) {
            cadastro.setCodigoInstrumento(conn.getResultSet().getString(4));
        }
        return cadastro.getCodigoInstrumento();

    }
    
    @Override
    public void read(CadastroDTO cadastro, String id) throws SQLException{
        conn.conectaBanco();
        conn.executarSQL("SELECT I.instrumento, I.categoria, I.marca, I.codigo_instrumento, F.fornecedor, F.cnpj, I.fk_fornecedor " +
                        "FROM fornecedor F JOIN instrumento I " +
                        "ON F.cnpj = I.fk_fornecedor " +
                        "WHERE I.codigo_instrumento = " + id + ";");
        
        while (conn.getResultSet().next()) {
            cadastro.setInstrumento(conn.getResultSet().getString(1));
            cadastro.setCategoria(conn.getResultSet().getString(2));
            cadastro.setMarca(conn.getResultSet().getString(3));
            cadastro.setCodigoInstrumento(conn.getResultSet().getString(4));
            cadastro.setFornecedor(conn.getResultSet().getString(5));
            cadastro.setCnpj(cnpjReplace(conn.getResultSet().getString(6)));
        }
    }
    
    @Override
    public void update(CadastroDTO cadastro, String id) throws SQLException{

        conn.conectaBanco();
        String sql = ("UPDATE instrumento SET instrumento=?, categoria=?, marca=?, fk_fornecedor=? WHERE codigo_instrumento = " + id);
        
        PreparedStatement ps = conn.getConn().prepareStatement(sql); 
        ps.setString(1, cadastro.getInstrumento());
        ps.setString(2, cadastro.getCategoria());
        ps.setString(3, cadastro.getMarca());
        ps.setString(4, cadastro.getCnpj());
        ps.execute();
    }
    
    @Override
    public void delete(CadastroDTO cadastro, String id) throws SQLException{
        
        conn.conectaBanco(); 
        conn.updateSQL("DELETE FROM instrumento WHERE codigo_instrumento = " + id + ";");
        
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
    
    public String cnpjReplace(String cnpj) {
        String newCnpj = cnpj.replaceAll("[./-]", "");
        return newCnpj;
    }
    
}
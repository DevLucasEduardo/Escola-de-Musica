package novasClasses;

import conexao.MySQL;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class FornecedorDAO implements DAOInterface {
    
    MySQL conn = new MySQL();
    
    @Override
    public String create(CadastroDTO cadastro) throws SQLException {
        
        conn.conectaBanco(); 

        String sql = "INSERT INTO fornecedor (cnpj, fornecedor, razao_social, pais, estado, cidade, codigo_postal) VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.getConn().prepareStatement(sql); 
        ps.setString(1, cadastro.getCnpj());
        ps.setString(2, cadastro.getFornecedor());
        ps.setString(3, cadastro.getRazaoSocial());
        ps.setString(4, cadastro.getPais());
        ps.setString(5, cadastro.getEstado());
        ps.setString(6, cadastro.getCidade());
        ps.setString(7, cadastro.getCodigoPostal());
        ps.execute();
        
        return cadastro.getCnpj();
        
    }
    
    @Override
    public boolean read(CadastroDTO cadastro, String id) throws SQLException{
        
        
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
        
        if (cadastro.getCnpj() == null) {
            return false;
        }
        return true;
    }
    
    @Override
    public void update(CadastroDTO cadastro, String id) throws SQLException{

        conn.conectaBanco();
        String sql = ("UPDATE fornecedor "
                + "SET fornecedor=?, "
                + "razao_social=?, "
                + "pais=?, "
                + "estado=?, "
                + "cidade=?, "
                + "codigo_postal=? "
                + "WHERE cnpj = '" + id + "'");
        
        PreparedStatement ps = conn.getConn().prepareStatement(sql); 
        ps.setString(1, cadastro.getFornecedor());
        ps.setString(2, cadastro.getRazaoSocial());
        ps.setString(3, cadastro.getPais());
        ps.setString(4, cadastro.getEstado());
        ps.setString(5, cadastro.getCidade());
        ps.setString(6, cadastro.getCodigoPostal());
        ps.execute();
    }
    
    @Override
    public void delete(CadastroDTO cadastro, String id) throws SQLException{
        
        conn.conectaBanco(); 
        conn.updateSQL("DELETE FROM instrumento WHERE fk_fornecedor = '" + id + "';");
        conn.updateSQL("DELETE FROM fornecedor WHERE cnpj = '" + id + "' limit 1;");
        
    }
    
    public String cnpjReplace(String cnpj) {
        String newCnpj = cnpj.replaceAll("[./-]", "");
        return newCnpj;
    }
   
    
} 
package novasClasses;


import conexao.MySQL;
import java.util.List;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProdutoDAO implements DAOInterface{
    
    MySQL conn = new MySQL();
    
    @Override
    public void create(CadastroDTO cadastro) throws SQLException {
                
        conn.conectaBanco(); 
        
        
        //String result = readHelper();
        //if (result == null) {
            //JOptionPane message = new JOptionPane();
            //message.showMessageDialog(null, "Fornecedor não encontrado. Cadastre-o antes de cadastrar o produto.");
            //return;
        //}
        String sql = "INSERT INTO instrumento (instrumento, categoria, marca, codigo_instrumento, fk_fornecedor) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.getConn().prepareStatement(sql); 
        ps.setString(1, cadastro.getInstrumento());
        ps.setString(2, cadastro.getCategoria());
        ps.setString(3, cadastro.getMarca());
        ps.setInt(4, cadastro.getCodigoInstrumento());
        ps.setInt(5, cadastro.getFkFornecedor());
        ps.execute();
        
    }
    
    @Override
    public List<CadastroDTO> read(CadastroDTO cadastro) throws SQLException{

        List<CadastroDTO> list = new ArrayList<>();
        String sql = "SELECT I.instrumento, I.categoria, I.marca, I.codigo_instrumento, F.fornecedor, F.cnpj, I.fk_fornecedor\n" +
                        "FROM fornecedor F JOIN instrumento I \n" +
                        "ON F.codigo_fornecedor = i.fk_fornecedor;";
        PreparedStatement ps = conn.getConn().prepareStatement(sql);
        ps.executeQuery();
        ResultSet resultado = ps.executeQuery();
        
        while (resultado.next()) {
            cadastro.setInstrumento(resultado.getString(1));
            cadastro.setCategoria(resultado.getString(2));
            cadastro.setMarca(resultado.getString(3));
            cadastro.setCodigoInstrumento(resultado.getInt(4));
            cadastro.setFornecedor(resultado.getString(5));
            cadastro.setCnpj(resultado.getString(6));
            cadastro.setFkFornecedor(resultado.getInt(7));
        }
        return list;
    }
    
    @Override
    public void update(CadastroDTO cadastro) throws SQLException{
        String sql = "UPDATE cadastro WHERE";
    }
    
    @Override
    public void delete(CadastroDTO cadastro) throws SQLException{
        String sql = "DELETE cadastro WHERE";
    }
    
    public int readHelper() throws SQLException {
        String sql = "SELECT * FROM fornecedor WHERE fornecedor = 'solo'";
        conn.conectaBanco(); 
        PreparedStatement ps = conn.getConn().prepareStatement(sql);
        ps.executeQuery();
        ResultSet resultado = ps.executeQuery();
        return (resultado.getInt(4));
        
    }
    
    
    
}
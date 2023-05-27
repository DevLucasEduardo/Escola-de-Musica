package novasClasses;

import conexao.MySQL;

public class CadastroDAO {
    
    MySQL conn = new MySQL();
    
    public void modifier(CadastroDTO c) {
        
    }
    
    public void create() {
        String sql = "INSERT INTO cadastro VALUES(?, ?, ?);";
    }
    
    public void read() {
        String sql = "SELECT * FROM cadastro;";
    }
    
    public void update() {
        String sql = "UPDATE cadastro WHERE ;";
    }
    
    public void delete() {
        String sql = "DELETE cadastro WHERE ;";
    }
}
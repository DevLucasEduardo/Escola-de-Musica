package classesUsuario;
import conexao.MySQL;
import java.sql.SQLException;

public class LoginUserClass {
    
    MySQL conn = new MySQL();;
    
    public LoginUserClass() {
    }
        
    // authentication method 
    
    public String authentication(String email, String password) {
        
        
        conn.conectaBanco();
        conn.executarSQL("select email, senha from aluno "
                + "where email = '" + email + 
                "'limit 1;");
        
        try {
            
            if(!conn.getResultSet().next()) {
                return "Email não encontrado";
            }
            
            if(conn.getResultSet().getString(1).equals(email)) {
                if(conn.getResultSet().getString(2).equals(password)) {
                    return "Login realizado";
                }
                return "Senha incorreta";
            }
            
        }
        catch (SQLException e) {
            System.out.println("Erro:" + e.getMessage());
        }
        return null;
    }
    
    public String[] registerInfo(String email) {
        
        String[] values = new String[13];
        conn.conectaBanco();
        conn.executarSQL("select * from aluno "
                + "where email = '" + email + 
                "'limit 1;");
        
        try {
            
            if(conn.getResultSet().next()) {
                
                for(int i = 0; i < values.length; i++)
                values[i] = conn.getResultSet().getString(i + 1);

            }

            return values;
            
        }
        catch (SQLException e) {
            System.out.println("Erro:" + e.getMessage());
        }
        return null;
    }
    
}
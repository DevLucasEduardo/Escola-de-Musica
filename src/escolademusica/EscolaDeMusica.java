package escolademusica;

import java.sql.SQLException;
import java.util.HashMap;
import novasClasses.CadastroDTO;
import novasClasses.ProdutoDAO;
import telas.MenuInicial;

public class EscolaDeMusica {

    public static void main(String[] args) throws SQLException {
        
        MenuInicial menu = new MenuInicial();
        
        
        HashMap<String, String> hash = new HashMap<>();
        hash.put("123", "Lucas");
        
        System.out.println(hash.get("123"));
        
    }

}

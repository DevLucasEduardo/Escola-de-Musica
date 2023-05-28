package escolademusica;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import novasClasses.CadastroDTO;
import novasClasses.ProdutoDAO;
import telas.MenuInicial;

public class EscolaDeMusica {

    public static void main(String[] args) throws SQLException {
        
        //MenuInicial menu = new MenuInicial();
        
        
        ProdutoDAO p = new ProdutoDAO();
        CadastroDTO c = new CadastroDTO("piano", "cordas", "crafter", 1, "123", "solo");
        
        p.create(c);
    }
    
}

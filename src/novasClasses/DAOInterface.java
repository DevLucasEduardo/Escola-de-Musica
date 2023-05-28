/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package novasClasses;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Lucas
 */
public interface DAOInterface {
    
    void create(CadastroDTO cadastro) throws SQLException;
    List<CadastroDTO> read(CadastroDTO cadastro) throws SQLException;
    void update(CadastroDTO cadastro) throws SQLException;
    void delete(CadastroDTO cadastro) throws SQLException;
    
}

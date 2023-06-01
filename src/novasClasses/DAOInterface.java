/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package novasClasses;

import java.sql.SQLException;

public interface DAOInterface {
    
    String create(CadastroDTO cadastro) throws SQLException;
    void read(CadastroDTO cadastro, String id) throws SQLException;
    void update(CadastroDTO cadastro, String id) throws SQLException;
    void delete(CadastroDTO cadastro, String id) throws SQLException;
    
}

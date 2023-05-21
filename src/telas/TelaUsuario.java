/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package telas;

import classesUsuario.CadastroProfessor;
import classesUsuario.SignupUserClass;
import conexao.MySQL;
import javax.swing.JOptionPane;

/**
 *
 * @author Lucas
 */
public class TelaUsuario extends javax.swing.JFrame {

    MySQL conectar = new MySQL();
    SignupUserClass novoCliente = new SignupUserClass();
    
    public TelaUsuario() {
        initComponents();
        
        this.setTitle("Tela aluno");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public String auxDateMySQL() {
        
        String ano = (String) cbxAno.getSelectedItem();
        String mes = (String) cbxMes.getSelectedItem();
        String dia = (String) cbxDia.getSelectedItem();
        
        switch (mes) {
            case "janeiro" -> mes = "01";
            case "fevereiro" -> mes = "02";
            case "março" -> mes = "03";
            case "abril" -> mes = "04";
            case "maio" -> mes = "05";
            case "junho" -> mes = "06";
            case "julho" -> mes = "07";
            case "agosto" -> mes = "08";
            case "setembro" -> mes = "09";
            case "outubro" -> mes = "10";
            case "novembro" -> mes = "11";
            case "dezembro" -> mes = "12";
            
        }
    
        if (dia.length() == 1) {
            return ano + "-" + mes + "-0" + dia;
        } 
        
        return ano + "-" + mes + "-" + dia;

    }
    
    private int auxInstrument() {
        int aula_prof_fk = 0;
        
        if(rbnBaixo.isSelected()) {
            aula_prof_fk = 1;

        }
        else if(rbnBateria.isSelected()) {
            aula_prof_fk = 2;

        }
        else if(rbnCanto.isSelected()) {
            aula_prof_fk = 3;

        }
        else if(rbnGuitarra.isSelected()) {
            aula_prof_fk = 4;

        }
        else if(rbnPiano.isSelected()) {
            aula_prof_fk = 5;

        }
        else if(rbnViolao.isSelected()) {
            aula_prof_fk = 6;
        }
        return aula_prof_fk;
    }

    private void deletarCliente(SignupUserClass novoCliente){
        this.conectar.conectaBanco();
        
        String consultaCpf = this.consultaCpf.getText(); 
        
        try {            
            this.conectar.updateSQL(
                "DELETE FROM aluno "
                + " WHERE "
                    + "cpf = '" + consultaCpf + "'"
                + ";"            
            );
            
            JOptionPane.showMessageDialog(null, "Aluno deletado com sucesso");  
            
        } catch (Exception e) {
            System.out.println("Erro ao deletar cliente " +  e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao deletar aluno");
        }finally{
            this.conectar.fechaBanco();
            limparCamposCadastro();
                      
        }     
        
    }
        
    
    private void cadastraCliente(SignupUserClass novoCliente){
        this.conectar.conectaBanco(); 
        
        String genero = "";

        if (rbnFeminino.isSelected()) {
            genero ="F";
        }
         if (rbnMasculino.isSelected()) {
            genero ="M";
        }
        String dataNasc = auxDateMySQL();
        System.out.println(dataNasc);
        novoCliente.setNome(txtNomeUsuario.getText());
        novoCliente.setSobrenome(txtSobrenomeUsuario.getText());
        novoCliente.setCpf(txtCpf.getText());
        novoCliente.setEmail(txtEmail.getText());
        novoCliente.setDataNasc(dataNasc);
        novoCliente.setGenero(genero);
        novoCliente.setEstado(String.valueOf(cbxEstado.getSelectedItem()));
        novoCliente.setCidade(txtCidade.getText());
        novoCliente.setBairro(txtBairro.getText());
        novoCliente.setRua(txtRua.getText());
        novoCliente.setNumero(Integer.parseInt( txtNumero.getText()));
        
        int aula_aluno_fk = auxInstrument();
        try {

            this.conectar.insertSQL("INSERT INTO aluno ("
                    + "nome,"
                    + "sobrenome,"
                    + "cpf,"
                    + "email,"
                    + "data_nasc,"
                    + "genero,"
                    + "estado,"
                    + "cidade,"
                    + "bairro,"
                    + "rua,"
                    + "numero,"
                    + "aula_aluno_fk"
                    
                + ") VALUES ("
                    + "'" + novoCliente.getNome() + "',"
                    + "'" + novoCliente.getSobrenome() + "',"
                    + "'" + novoCliente.getCpf() + "',"
                    + "'" + novoCliente.getEmail() + "',"        
                    + "'" + novoCliente.getDataNasc()+ "',"
                    + "'" + novoCliente.getGenero() + "',"
                    + "'" + novoCliente.getEstado() + "',"
                    + "'" + novoCliente.getCidade() + "',"
                    + "'" + novoCliente.getBairro()+ "'," 
                    + "'" + novoCliente.getRua()+ "',"  
                    +       novoCliente.getNumero() + "," 
                    + aula_aluno_fk
                    + ");"
            );
            
            JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso");
            
        } catch (Exception e) {
            
            System.out.println("Erro ao cadastrar cliente " +  e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar aluno");
            
        } finally{            
            this.conectar.fechaBanco();
            
            limparCamposCadastro();
        }                
    }
    
    private void buscarCliente(SignupUserClass novoCliente){
        this.conectar.conectaBanco();
        int aula_aluno_fk = 0;
        String consultaCpf = this.consultaCpf.getText();
                
        try {
            this.conectar.executarSQL(
                   "SELECT "
                    + "nome,"
                    +"sobrenome,"
                    +"cpf,"
                    +"email,"
                    +"data_nasc,"
                    +"genero,"
                    +"estado,"
                    +"cidade,"
                    +"bairro,"
                    +"rua,"
                    +"numero,"
                    +"aula_aluno_fk"
                 + " FROM"
                     + " aluno"
                 + " WHERE"
                     + " cpf = '" + consultaCpf + "'"
                + ";"
            );
            
            while(this.conectar.getResultSet().next()){
                novoCliente.setNome(this.conectar.getResultSet().getString(1));
                novoCliente.setSobrenome(this.conectar.getResultSet().getString(2));
                novoCliente.setCpf(this.conectar.getResultSet().getString(3));
                novoCliente.setEmail(this.conectar.getResultSet().getString(4));
                novoCliente.setDataNasc(this.conectar.getResultSet().getString(5));
                novoCliente.setGenero(this.conectar.getResultSet().getString(6));
                novoCliente.setEstado(this.conectar.getResultSet().getString(7));
                novoCliente.setCidade(this.conectar.getResultSet().getString(8));
                novoCliente.setBairro(this.conectar.getResultSet().getString(9));
                novoCliente.setRua(this.conectar.getResultSet().getString(10));
                novoCliente.setNumero(this.conectar.getResultSet().getInt(11));
                aula_aluno_fk = this.conectar.getResultSet().getInt(12);
           }
            
            
           if(novoCliente.getNome() == ""){
                JOptionPane.showMessageDialog(null, "Aluno não encontrado!");
           }
           
        } catch (Exception e) {            
            System.out.println("Erro ao consultar cliente " +  e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao buscar aluno");
            
        }finally{
            String data = String.valueOf( novoCliente.getDataNasc());
            String dia = data.substring(8,10);
            String mes = data.substring(5,7);
            String ano = data.substring(0,4);
            
           switch (mes) {
            case "01" -> mes = "janeiro";
            case "02" -> mes = "fevereiro";
            case "03" -> mes = "março";
            case "04" -> mes = "abril";
            case "05" -> mes = "maio";
            case "06" -> mes = "junho";
            case "07" -> mes = "julho";
            case "08" -> mes = "agosto";
            case "09" -> mes = "setembro";
            case "10" -> mes = "outubro";
            case "11" -> mes = "novembro";
            case "12" -> mes = "dezembro";
        }
          
           String genero = novoCliente.getGenero();
            System.out.println(genero);
                      
           switch(genero){
                case "F" -> rbnFeminino.setSelected(true);
                case "M" -> rbnMasculino.setSelected(true);
           }   
           
           switch(aula_aluno_fk) {
               case 1 -> rbnBaixo.setSelected(true);
               case 2 -> rbnBateria.setSelected(true);
               case 3 -> rbnCanto.setSelected(true);
               case 4 -> rbnGuitarra.setSelected(true);
               case 5 -> rbnPiano.setSelected(true);
               case 6 -> rbnViolao.setSelected(true);
           }
            
            txtNomeUsuario.setText(novoCliente.getNome());
            txtSobrenomeUsuario.setText(novoCliente.getSobrenome());
            txtCpf.setText(novoCliente.getCpf());
            txtEmail.setText(novoCliente.getEmail());
            cbxDia.setSelectedItem(dia);
            cbxMes.setSelectedItem(mes);
            cbxAno.setSelectedItem(ano);
            cbxEstado.setSelectedItem(novoCliente.getEstado());
            txtCidade.setText(novoCliente.getCidade());
            txtBairro.setText(novoCliente.getBairro());
            txtRua.setText(novoCliente.getRua());
            txtNumero.setText(String.valueOf(novoCliente.getNumero()));
           
           
            this.conectar.fechaBanco();   
        }               
    }
    
    public void atualizarCliente(SignupUserClass novoCliente){
        this.conectar.conectaBanco();
        int aula_aluno_fk = auxInstrument();
        String consultaCpf = this.consultaCpf.getText();
        String dataNasc = auxDateMySQL();
        String genero = "";
        if (rbnFeminino.isSelected()) {
            genero ="F";
        }
        if (rbnMasculino.isSelected()) {
            genero ="M";
        }
        
        
        
        try {
            this.conectar.updateSQL(
                "UPDATE aluno SET "                    
                    + "nome = '" + txtNomeUsuario.getText() + "',"
                    + "sobrenome = '" + txtSobrenomeUsuario.getText() + "',"  
                    + "cpf = '" + txtCpf.getText() + "',"
                    + "email = '" + txtEmail.getText() + "',"
                    + "data_nasc = '" + dataNasc + "'," 
                    + "genero = '" + genero + "',"
                    + "estado = '" + String.valueOf(cbxEstado.getSelectedItem()) + "',"
                    + "cidade = '" + txtCidade.getText() + "',"
                    + "bairro = '" + txtBairro.getText() + "',"
                    + "rua = '" + txtRua.getText() + "'," 
                    + "numero = " + txtNumero.getText() + "," 
                    + "aula_aluno_fk = " + aula_aluno_fk 
                            
                    
                + " WHERE "
                    + "cpf = '" + consultaCpf + "'"
                + ";"
                    
            );
            JOptionPane.showMessageDialog(null, "Aluno atualizado com sucesso");
        }catch(Exception e){
            System.out.println("Erro ao atualizar cliente " +  e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao atualizar aluno");
        }finally{
            this.conectar.fechaBanco();
            limparCamposCadastro();
            
        }
    }
    
    
    private void limparCamposCadastro(){
        
        txtNomeUsuario.setText("");
        txtSobrenomeUsuario.setText("");
        txtCpf.setText("");
        txtEmail.setText("");
        cbxDia.setSelectedItem(01);
        cbxMes.setSelectedItem(01);
        cbxAno.setSelectedItem(2023);
        rbnFeminino.setSelected(false);
        rbnMasculino.setSelected(false);
        cbxEstado.setSelectedItem("<Selecione>");
        txtCidade.setText("");
        txtBairro.setText("");
        txtRua.setText("");
        txtNumero.setText("");
        rbnBaixo.setSelected(false);
        rbnBateria.setSelected(false);
        rbnCanto.setSelected(false);
        rbnGuitarra.setSelected(false);
        rbnPiano.setSelected(false);
        rbnViolao.setSelected(false);

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtBairro = new javax.swing.JTextField();
        cbxEstado = new javax.swing.JComboBox<>();
        txtCidade = new javax.swing.JTextField();
        txtRua = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtNomeUsuario = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSobrenomeUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        cbxDia = new javax.swing.JComboBox<>();
        cbxMes = new javax.swing.JComboBox<>();
        cbxAno = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtCpf = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        rbnFeminino = new javax.swing.JRadioButton();
        rbnMasculino = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        rbnBaixo = new javax.swing.JRadioButton();
        rbnBateria = new javax.swing.JRadioButton();
        rbnCanto = new javax.swing.JRadioButton();
        rbnGuitarra = new javax.swing.JRadioButton();
        rbnPiano = new javax.swing.JRadioButton();
        rbnViolao = new javax.swing.JRadioButton();
        btnDeletar = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        consultaCpf = new javax.swing.JTextField();
        btnbuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Endereço"));

        cbxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Selecione>", "AC", "AL", "AP", "AM", "BA", "CE", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO", "DF" }));
        cbxEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxEstadoActionPerformed(evt);
            }
        });

        jLabel1.setText("Estado");

        jLabel5.setText("Cidade");

        jLabel6.setText("Rua");

        jLabel8.setText("Número");

        jLabel7.setText("Bairro");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtBairro)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7))
                                    .addGap(126, 126, 126))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addGap(112, 112, 112)))
                            .addComponent(txtRua)
                            .addComponent(txtCidade)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(cbxEstado, 0, 207, Short.MAX_VALUE))))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações de usuário"));

        txtNomeUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeUsuarioActionPerformed(evt);
            }
        });

        jLabel14.setText("Nome");

        jLabel2.setText("Sobrenome");

        jLabel3.setText("Email");

        cbxDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        cbxMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "janeiro", "fevereiro", "março", "abril", "maio", "junho", "julho", "agosto", "setembro", "outubro", "novembro", "dezembro" }));
        cbxMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxMesActionPerformed(evt);
            }
        });

        cbxAno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2023", "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920", "1919", "1918", "1917", "1916", "1915", "1914", "1913", "1912", "1911", "1910", "1909", "1908", "1907", "1906", "1905", "1904", "1903", "1902", "1901", "1900" }));
        cbxAno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxAnoActionPerformed(evt);
            }
        });

        jLabel9.setText("Data de nascimento");

        jLabel10.setText("CPF");

        jLabel11.setText("Gênero");

        rbnFeminino.setText("Feminino");

        rbnMasculino.setText("Masculino");
        rbnMasculino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnMasculinoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cbxDia, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxMes, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxAno, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtEmail)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(txtNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(24, 24, 24)
                            .addComponent(txtSobrenomeUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                        .addComponent(jLabel9)
                        .addComponent(jLabel3)
                        .addComponent(txtCpf)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel11)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(rbnFeminino)
                            .addGap(21, 21, 21)
                            .addComponent(rbnMasculino)))
                    .addComponent(jLabel10))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSobrenomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbnFeminino)
                    .addComponent(rbnMasculino))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Aulas "));

        rbnBaixo.setText("Baixo");

        rbnBateria.setText("Bateria");

        rbnCanto.setText("Canto");

        rbnGuitarra.setText("Guitarra");

        rbnPiano.setText("Piano");

        rbnViolao.setText("Violão");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbnBaixo)
                    .addComponent(rbnBateria))
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbnGuitarra)
                    .addComponent(rbnCanto))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbnPiano)
                    .addComponent(rbnViolao))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbnBaixo)
                    .addComponent(rbnCanto)
                    .addComponent(rbnPiano))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbnBateria)
                    .addComponent(rbnGuitarra)
                    .addComponent(rbnViolao))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        btnDeletar.setText("Deletar");
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });

        btnAtualizar.setText("Atualizar");
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar"));

        jLabel4.setText("Buscar CPF: ");

        btnbuscar.setText("Buscar");
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(consultaCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnbuscar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(consultaCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscar))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(btnAtualizar)
                                .addGap(107, 107, 107)
                                .addComponent(btnDeletar)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxEstadoActionPerformed

    private void txtNomeUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeUsuarioActionPerformed

    private void cbxMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxMesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxMesActionPerformed

    private void cbxAnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxAnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxAnoActionPerformed

    private void rbnMasculinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnMasculinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbnMasculinoActionPerformed

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed
        deletarCliente(novoCliente);
    }//GEN-LAST:event_btnDeletarActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        atualizarCliente(novoCliente);
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        buscarCliente(novoCliente);
    }//GEN-LAST:event_btnbuscarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JComboBox<String> cbxAno;
    private javax.swing.JComboBox<String> cbxDia;
    private javax.swing.JComboBox<String> cbxEstado;
    private javax.swing.JComboBox<String> cbxMes;
    private javax.swing.JTextField consultaCpf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton rbnBaixo;
    private javax.swing.JRadioButton rbnBateria;
    private javax.swing.JRadioButton rbnCanto;
    private javax.swing.JRadioButton rbnFeminino;
    private javax.swing.JRadioButton rbnGuitarra;
    private javax.swing.JRadioButton rbnMasculino;
    private javax.swing.JRadioButton rbnPiano;
    private javax.swing.JRadioButton rbnViolao;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtCpf;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNomeUsuario;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtRua;
    private javax.swing.JTextField txtSobrenomeUsuario;
    // End of variables declaration//GEN-END:variables
}
